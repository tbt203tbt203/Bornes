# <p align="center">Bornes Ntico 🏎️</p>
  
Création d'une API qui récupere les informations des bornes de voitures électrique, depuis l'API de chez Rossini Energy.

## 🧐 Présentation (API Ntico)
Appel de l'API de chez Rossini Energy, refresh toutes les 30 secondes pour mettre à jour les informations, pour pouvoir les exploiter.<br>
Création d'un mini site basique qui est relier au code java qui exploite l'API. 
<br>La page d'acceuil d'appel Index.html
<br>Celui-ci renvoie plusieurs informations.
<br><br>
La premiere, c'est l'information des bornes en temps reel (30s) :
- Numéro de la borne
- La personne qui l'utilise
- Depuis combien de temps elle est en charge
- La puissance distribuée actuellement en Wh
- La puissance totale distribué depuis le début de la charge (pour ensuite savoir approximativement sont pourcentage de charge)<br><br>
Ensuite Il y'a la partie utilisateur : 
- Se mettre en attente (StatutAttente.html)
- S'enlever de l'attente (StatutNOAttente.html)
- Regardez les utilisateurs en attente (Statut.html)
- Ajouter un nouvel utilisateur (AjoutUtilisateur.html), (Renvoie sur Merci.html qui demande si on crée un new utilisateur / si on retourne aux bornes ou à l'acceuil)<br><br>
Chacunes sont relier a une ou plusieures pages HTML qui sont relier au code java qui permet de faire des redirections et de modifier la table Utilisateur.

Les pages java qui permettent d'avoir le rafrechissement toutes les 30 secondes sont les suivantes : 
ScheduledFixedRateExample,
SchedulerConfig

Dans ScheduledFixedRateExample la premiere "response" permet de refresh la table bornes que l'on va voir juste après. Et le "response8" permet de refresh la table Log en rapport à celle bornes car elles sont reliser.

Le pom.xml est de base dans l'installation du initializr mais ont peut tajouter des pakages à la main si on le souhaite.

## 👶 Utilisateur table

La création de la table utilisateur permet de relier toutes les pages html ci-dessus:

- Statut.html, savoir qui est en attente. Cependant elle renvoie un JSON. 
- StatutAttente.html, permet grace à son pseudo de se mettre en attente.
-  StatutNOAttente.html, permet de s'enlever de l'attente grace à son pseudo.
- AjoutUtilisateur.html, permet de crée un nouvel utilisateur et renvoie sur la page Merci.html expliquer au dessus.<br><br>
Pour expliquer la table, nous avons 4 colones :
- id, pour avoir la clé primaire et elle n'est pas vraiment a expliquer
- email, pour avoir l'email de a personne qui c'est enregistrer 
- pseudo, pour avoir le pseudo enregistrer et il est important pour la modif du statut en attente
- statut, attente ou null simple a comprendre et a modifier grace a StatutAttente.html et StatutNOAttente.html<br>

Pour expliquer d'ou viennent ces informations on les retrouvent dans la page html suivante AjoutUtilisateur.html car c'est elle qui va permettre la création des utilisateurs

Voici un exemple de la table utilisateur :

![image](https://github.com/tbt203tbt203/Bornes/assets/137182634/5896d51e-a6dd-45d0-a13b-9fa737d46262)


## 🔌 Borne table

La table Borne est très simple à comprendre, elle est composer de 5 colones
- id_borne, qui a est assez comprehensible donc on retrouvera les id 818, 820, 821, 822.
- heure, qui affiche l'heure ou il y'a eu le dernier changement dans la table
- debutfin, qui indique si c'es le debut ou la fin de la charge, si c'est debut cela veut dire qu'elle charge puis si c'est Fin alors c'est que la charge est fini
- utilisateur, indique l'utilisateur connecter actuelement si c'est debut et le pseudo de la derniere personne connecté si c'est Fin
- libre, si la corne est occupé alors la colone libre = non et inversement

Cette table est entierement fournie grace à la page BorneService, la ou toutes les informations vont etre prélever et redistribué dans les différents codes du BorneController.
Cela permettra d'allimenter la page /borne/all ou on trouvera toutes les informations en temps reel des bornes.

Voici un exemple de la table bornes :

![image](https://github.com/tbt203tbt203/Bornes/assets/137182634/195ed0fe-ed79-432f-ada3-bcc7d400c5f7)

        
## 📝  Log table

La table log est composer de 7 colones en effet il y'en a une "ancienne_valeur qui peut etre assez flou je vais expliquer.

- id, colone basique qui sur mon screen est un peut buggé en effet quand ont reset la base il faut enlever des morceaux du code pour que sa crée les lignes correctement mais des que tous les début sont présents alors remettre le morceau de code je n'ai pas trouver d'alternative je montre les 2 screens(BornesController) :

<br>
Code avant modif :

```java
 if (oldBornes.getUtiliser() == null || !oldBornes.getUtiliser().equals(Libre)) {

                    if (Libre != null && Libre.equals("non")) { //Libre.equals("non")
                        Log log = createLogEntry(borneId, oldBornes.getUtiliser(), BorneHeure, Username, debutfin, Libre);
                        logRepository.save(log);
                        oldBornes.setUtiliser(Libre);
                        oldBornes.setDf(debutfin);
                        oldBornes.setHeure(BorneHeure);
                    }

                    if(oldBornes.getUtiliser() != null || oldBornes.getUtiliser().equals(Libre)){

                        LocalDateTime dateTimeActuelle = LocalDateTime.now();
                        DateTimeFormatter formatteur = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        String dateTimeFormatee = dateTimeActuelle.format(formatteur);
                        oldBornes.setHeure(dateTimeFormatee);

                        String pseudo = logRepository.findBybornes(borneId).get(0).getPseudo();
                        oldBornes.setUtilisateur(pseudo);

                        Log log = createLogEntry(borneId, oldBornes.getUtiliser(), dateTimeFormatee, pseudo, debutfin, Libre);
                        logRepository.save(log);
                        oldBornes.setUtiliser(Libre);
                        oldBornes.setDf(debutfin);
                    }


                }
```
        
        
Image et code si tu veut clear la base : 

```java
 if (oldBornes.getUtiliser() == null || !oldBornes.getUtiliser().equals(Libre)) {

                    if (Libre != null /* && Libre.equals("non")*/) { //Libre.equals("non")
                        Log log = createLogEntry(borneId, oldBornes.getUtiliser(), BorneHeure, Username, debutfin, Libre);
                        logRepository.save(log);
                        oldBornes.setUtiliser(Libre);
                        oldBornes.setDf(debutfin);
                        oldBornes.setHeure(BorneHeure);
                    }

               /*     if(oldBornes.getUtiliser() != null || oldBornes.getUtiliser().equals(Libre)){

                        LocalDateTime dateTimeActuelle = LocalDateTime.now();
                        DateTimeFormatter formatteur = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        String dateTimeFormatee = dateTimeActuelle.format(formatteur);
                        oldBornes.setHeure(dateTimeFormatee);

                        String pseudo = logRepository.findBybornes(borneId).get(0).getPseudo();
                        oldBornes.setUtilisateur(pseudo);

                        Log log = createLogEntry(borneId, oldBornes.getUtiliser(), dateTimeFormatee, pseudo, debutfin, Libre);
                        logRepository.save(log);
                        oldBornes.setUtiliser(Libre);
                        oldBornes.setDf(debutfin);
                    }*/


                }
```
        

![image](https://github.com/tbt203tbt203/Bornes/assets/137182634/69310f59-d617-41d1-926c-394c9c591c0c)


- borne_id, c'est tous simplement l'id de la borne ou il y'a ue une modification, cela permet de voir qu'elle borne est libre ...
- debutfin, comme au dessus (" qui indique si c'es le debut ou la fin de la charge, si c'est debut cela veut dire qu'elle charge puis si c'est Fin alors c'est que la charge est fini")
- Le reste c'est egalement la meme chose que dans bornes

- ancienne_valeur, pour comprendre cette valeur il faut voir que c'est celle ci qui va mettre les informations en fonction de la valeur précedente, je veut dire par la que si la valeure libre = Non et que la personne sur la borne se débranche alors la valeure sera egale à Oui dans ce cas cela créra une nouvelle lignes avec toutes les infos.
        
Voici un exemple de la table Log :

![image](https://github.com/tbt203tbt203/Bornes/assets/137182634/bfacc062-7328-46a8-9084-a5416fdd3293)

        

## 📚 Présentation complete src

Nous avous une présentation comme celle ci : 

![image](https://github.com/tbt203tbt203/Bornes/assets/137182634/419d53e7-3b29-405a-a6ad-d0b0c72e6252)


https://prnt.sc/08s0pjtmVsgR
        
- Voyons donc Archive celui ci ce sont juste des codes que j'ai utiliser avant et que j'ai garder au cas ou je devais recuperer des infos pour améliorer le code ou reprendre une version précedente.

- config, ici il y'a les 2 codes pour l'actualisation toutes les 30 secondes de l'appel sur l'API de Rossini.
- controller, dans ce dossier nous avons BornesController qui est la base même de notre API car c'est lui qui va crée la relation entre le code dur JAVA et une page affichable soit en html soit aussi par du GETMAPPING qui va crée des redirections  comme celle ci par ex : http://localhost:8080/borne/all
avec des /.../... 
<br>Je vais le détailler en dessous.
- repository, ce fichier est très important pour notre base de données postgres car ce sont c'est fichier qui font la relations entre le sql et le java, c'est ici que l'on va récupérer, modifier ou supprimer des infos dans la base et dans les différentes tables.
Ici l'exemple avec le code de la table Log :
 

```java
    @Query(nativeQuery = true, value = "SELECT * FROM log WHERE borne_id = CAST(:borne_id AS VARCHAR) AND pseudo <> 'nul' ORDER BY id DESC LIMIT 1")
    ArrayList<Log> findBybornes(String borne_id);
```
        

<h4><b>- service, regroupe l'ensemble du code qui n'a pas de refresh de sql ou de relations avec d'autres pages. Il est composer de 8 fichier java et 1 9 eme LogService que je n'ai pas eu le temps de faire donc le code est toujours dans BornesCoontrolller.
<br></h4></b>
- Borne, premiere page de cration de la table bone elle est composer a 100% de la création des colones



```java
 @Id
    @Column(name = "id_borne")
    private Long idBorne;

    @Column(name = "utilisateur")
    private String utilisateur;

    @Column(name = "libre")
    private String utiliser;

    @Column(name = "Heure")
    private String Heure;
    // Getters and setters
    @Column(name = "debutfin")
    private String df;
```
comme ont peut le voir sa crée les colones et id_borne est aussi crée en tant que clé primaire.

- BornesService, c'est la ou le code principale ce trouve, avec la requette api et ont peut egalement appercevoir ceci : 


```java
public static String getBorne(String borneNumber) throws IOException {
        try {
            URL url = new URL("https://api.charge.re/public/1/chargecontroller/"+ borneNumber +"/?format=json");
           
```
ce que l'on voit, c'est que nous ne marqu'on plus le numero de la borne mais que sa sera fait directement dans le controller pour ne pas avoir un code qui se repete 4 fois.
Ensuite, nous remarqu'on que le code est séparer en deux, dans la premiere partie, ont peut voir que si la borne indique que active charge = null alors afficher oui (librte = oui)
sinon faire le code qui est en dessous.


```java
if (jsonResponse.contains("\"active_charge\":")) {
                int startIndex = jsonResponse.indexOf("\"active_charge\":") + "\"active_charge\":".length();
                int endIndex = jsonResponse.indexOf(",", startIndex);
                if (endIndex == -1) {
                    endIndex = jsonResponse.indexOf("}", startIndex);
                }
                String activeChargeValue = jsonResponse.substring(startIndex, endIndex).trim();
                if (activeChargeValue.equals("null")) {
                    isActiveCharge = "Oui";
                }else{
```
        
        
        
Par la suite on à le start qui nous donnent l'heure du début de la charge on l'appel et on recupere l'heure puis ont le change de format, après ont appel l'heure actuelle avec le timestamp et on change aussi le format, ensuite on fait les soustrait pour avoir l'heure actuel.

```java
String formatEntree = "yyyy-MM-dd'T'HH:mm:ss[.SSSSSS][.SSSSS][.SSS][.SS][.S]X";
                    String formatSortie = "yyyy-MM-dd HH:mm:ss";
```
        
Ensuite nous allons chercher le power qui est le wh actuel donc voir si sa charge bien.

Puis le ernergy_wh qui est la charge totale distribué depuis le debut.

Et enfin nousa allons récuperer le username de la même façcon.
```JSON
 "active_charge": {
        "id": 99077,
        "start": "2023-06-22T06:45:38.734114Z",
        "stop": null,
        "user": {
            "id": 12685,
            "username": "pboulangeot",
            "has_guest_feat": false
        },
        "last_nonzero_power_time": "2023-06-22T06:52:10.624858Z",
        "energy_wh": 1100,
        "last_power": {
            "chargingstation": 632,
            "power": 11078,
            "timestamp": "2023-06-22T06:52:10.529254Z"
        }
    },
```
        

Puis les return de fin qui permettent d'aller au controller et de l'afficher.

- Log, création de la table Log comme bornes sauf que j'ai rajouter un Override c'etait pour tester de voir toutes les infos dans la table, mais maintenant on peut les avoir juste grace à updateLogJSON dans le controller sa permet d'afficher les infos de la table en json.

- LogDebutfin, permet juste de definir si la borne est en debut ou en fin de charge et de le returner pour la base log.

- Logheure, permet d'afficher l'heure actuel si la born est connecté sinon elle est afficher dans  born controller par 

```java
LocalDateTime dateTimeActuelle = LocalDateTime.now();
                        DateTimeFormatter formatteur = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        String dateTimeFormatee = dateTimeActuelle.format(formatteur);
                        oldBornes.setHeure(dateTimeFormatee);
```
oui c'est possible de mettre que avec cette formule et d'enlever LogHeure mais je n'ai pas eu le temps de le faire.
        
- LogLibre, permet de definir si la born est libre ou non et de l'afficher dans la table Log.
- LogUsername, permet de mettre le user name si la borne est en début de charge, sinon sa appelera dans lae borne controller cela

```java
 String pseudo = logRepository.findBybornes(borneId).get(0).getPseudo();
                        oldBornes.setUtilisateur(pseudo);
```
   
qui permetra de récuperer le pseudo de la derniere personne connecter sur la borne en question à partir de cette table Log.

<b>Alors oui tous les Log... pourrait être regrouper avec l'appel get dans le controller.</b>     

- Utilisateur, Permet de definir les colones dans la table Utilisateur.

-NticoApplication, essentiel dans Spring boot mais je ne l'ai pas fait personnelement

- ressources, déja pas mal expliquer au dessus dans la présentation, je peux juste rajouter, que casi chaques pages a un titre, des bouttons et les pages ou il faut ecrire, il y'a un toLowerCase pour tous mettre en minuscule comme sa il n'y a plus de pb de majuscules ...

## 👨🏽‍💻 BornesController
Pour expliquer concretement, au début ont importe toutes les autres pages java  et autres. Après ont définis la page et ce qu'il va y avoir  comme le fait que c'est un controller et le requestMapping borne.


```java
@SpringBootApplication
@Controller
@RestController
@Component
@RequestMapping("borne")
```
        
Ensuite on fait des Autowired qui permette d'appeler les autres pages java et de les définir dans cette page : 


```java
    @Autowired
    private BornesService bornesService;
    private final UtilisateursRepository utilisateursRepository;
    private LogDebutfin LogDebutfin;
    @Autowired
    private BornesRepository bornesRepository;
    @Autowired
    private LogUsername logUsername;
    @Autowired
    private LogLibre LogLibre;
    @Autowired
    private LogHeure LogHeure;
    @Autowired
    private LogRepository logRepository;
```
        
Après ont appel les tables ...
<br>
Ensuite ont a des GetMapping ce sont toutes des pages que l'on appel grace au localhost.
<br>
Et également les PostMapping, Alors eux sont des pages qui renvoient sur d'autres pages.
<br>
- Le premier     @GetMapping("/all") ligne 55 permet d'afficher la pages ou toutes les bornes sont afficher.

- GetMapping("/statut") Permet d'afficher la page ou l'on voit le statut en attente des personnes de la table Utilisateur.

- PostMapping("/create/utilisateur")
reliser a la page html AjoutUtilisateur.html comme son nom l'indique permet de gererer un nouvel utilisateur pour la table utilisateur.

- GetMapping("/merci/redirection") redirection pour la page Merci.html.

- PostMapping("/product/all/AjoutUtilisateur") celui ci aussi.

- GetMapping("/enregistrement/attente") lui aussi.

- GetMapping("/mettreAttente") permet de ce mettre en attente par le pseudo relier aussi a la page StatutAttente.html

- GetMapping("/enleverAttente") celui ci c'est la même mais pour s'enlever de l'attente et relier à StatutNOAttente.html

- GetMapping("/logenjson") Permet de voir la table Log en json
 
Ce code la permet de definir en plus de la page Log les valeures pour la table Log 


```java
private Log createLogEntry(String borneId, String ancienneValeur, String heure, String pseudo, String debutfin, String libre) {
        Log log = new Log();
        log.setBorneId(borneId);

        log.setAncienneValeur(ancienneValeur);
        log.setHeure(heure);
        log.setPseudo(pseudo);
        log.setDebutfin(debutfin);
        log.setLibre(libre);
        return log;
    }
```
- GetMapping("/product/all/create/updateAllBornesB")
ce code la est la pour la creations des valeurs dans la table log tout ce qu'elle comporte en fonctions des connections et déconnection des bornes ...
 
- GetMapping("/pseudoRecup/818")
permet juste de récuperer le pseudo de la borne 818 si elle est connecter, c'etait un prémlsse pour la table Log et son pseudo.
        
## ☀ Récap
- Appel API 
- Création tables : Log / Bornes / Utilisateur
- Relation entre : HTML / Tables / Code JAVA

## 🛠️ Liens des Bornes (API Rossini)
[Voir le park de Ntico](http://api.charge.re/public/1/parks/224) <br>
http://api.charge.re/public/1/parks/224
<br>
[Borne 818](http://api.charge.re/public/1/chargecontroller/818)<br>
http://api.charge.re/public/1/chargecontroller/818
<br>
[Borne 820](http://api.charge.re/public/1/chargecontroller/820)<br>
http://api.charge.re/public/1/chargecontroller/820
        <br>
[Borne 821](http://api.charge.re/public/1/chargecontroller/821)<br>
http://api.charge.re/public/1/chargecontroller/821
<br>
[Borne 822](http://api.charge.re/public/1/chargecontroller/822)
<br>
http://api.charge.re/public/1/chargecontroller/822

## 🗂️ Logiciels utilisé
- Initializr spring (création de l'API Ntico)
- Docker Desktop (base de donnée postgres)
- Intelij Idea 2023.1.1 (full code)
- Dbeaver (test sql)

## 🔦 Liens internes a utiliser


http://localhost:8080/index.html


![image](https://github.com/tbt203tbt203/Bornes/assets/137182634/44818b38-bb1b-46f2-8146-f2f83d923efa)


        
<br>

http://localhost:8080/borne/all



㊗️
        

        
        
