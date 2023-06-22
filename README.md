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

![Image](https://private-user-images.githubusercontent.com/137182634/247544596-3140d70b-3790-4a06-ab39-dd99a67ec9b2.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOiJrZXkxIiwiZXhwIjoxNjg3MzUwMzgzLCJuYmYiOjE2ODczNTAwODMsInBhdGgiOiIvMTM3MTgyNjM0LzI0NzU0NDU5Ni0zMTQwZDcwYi0zNzkwLTRhMDYtYWIzOS1kZDk5YTY3ZWM5YjIucG5nP1gtQW16LUFsZ29yaXRobT1BV1M0LUhNQUMtU0hBMjU2JlgtQW16LUNyZWRlbnRpYWw9QUtJQUlXTkpZQVg0Q1NWRUg1M0ElMkYyMDIzMDYyMSUyRnVzLWVhc3QtMSUyRnMzJTJGYXdzNF9yZXF1ZXN0JlgtQW16LURhdGU9MjAyMzA2MjFUMTIyMTIzWiZYLUFtei1FeHBpcmVzPTMwMCZYLUFtei1TaWduYXR1cmU9YjUyODk1YzNkMzQ3ODU0OTI4OWVlOWJlNDRkMTIzYTIwOGI2NGY5NjFiMzBjZWI2OTg3ZWNiNzBjNWM2MGU2YyZYLUFtei1TaWduZWRIZWFkZXJzPWhvc3QmYWN0b3JfaWQ9MCZrZXlfaWQ9MCZyZXBvX2lkPTAifQ.Kut9D9mJPAhtyMTiMxXkINHexStkPY6xd5901NOeKXo)

https://prnt.sc/HTAjRS8yM9ab
        

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

![Image](https://private-user-images.githubusercontent.com/137182634/247542616-f227d968-2d87-4172-9db4-d8a252e70296.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOiJrZXkxIiwiZXhwIjoxNjg3MzQ5OTQxLCJuYmYiOjE2ODczNDk2NDEsInBhdGgiOiIvMTM3MTgyNjM0LzI0NzU0MjYxNi1mMjI3ZDk2OC0yZDg3LTQxNzItOWRiNC1kOGEyNTJlNzAyOTYucG5nP1gtQW16LUFsZ29yaXRobT1BV1M0LUhNQUMtU0hBMjU2JlgtQW16LUNyZWRlbnRpYWw9QUtJQUlXTkpZQVg0Q1NWRUg1M0ElMkYyMDIzMDYyMSUyRnVzLWVhc3QtMSUyRnMzJTJGYXdzNF9yZXF1ZXN0JlgtQW16LURhdGU9MjAyMzA2MjFUMTIxNDAxWiZYLUFtei1FeHBpcmVzPTMwMCZYLUFtei1TaWduYXR1cmU9MWIxMzkzYzVhNGEwYTY5OGZhMTU2MmY1YWQwYzYzYzg3M2Y4MzhiYmJkY2QwNjY5YzAyYjFmNzI2Mjg0ODVjZiZYLUFtei1TaWduZWRIZWFkZXJzPWhvc3QmYWN0b3JfaWQ9MCZrZXlfaWQ9MCZyZXBvX2lkPTAifQ.XJiuPXvCSffnf4T-bTo0fDlBJ_1bDaYek0od4Vu5Rhw)

https://prnt.sc/btTnG-TEpemj
        
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
        

![Image](https://private-user-images.githubusercontent.com/137182634/247547086-9f168d66-5bee-4d3e-88da-a7730f069726.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOiJrZXkxIiwiZXhwIjoxNjg3MzUwOTgwLCJuYmYiOjE2ODczNTA2ODAsInBhdGgiOiIvMTM3MTgyNjM0LzI0NzU0NzA4Ni05ZjE2OGQ2Ni01YmVlLTRkM2UtODhkYS1hNzczMGYwNjk3MjYucG5nP1gtQW16LUFsZ29yaXRobT1BV1M0LUhNQUMtU0hBMjU2JlgtQW16LUNyZWRlbnRpYWw9QUtJQUlXTkpZQVg0Q1NWRUg1M0ElMkYyMDIzMDYyMSUyRnVzLWVhc3QtMSUyRnMzJTJGYXdzNF9yZXF1ZXN0JlgtQW16LURhdGU9MjAyMzA2MjFUMTIzMTIwWiZYLUFtei1FeHBpcmVzPTMwMCZYLUFtei1TaWduYXR1cmU9MGQwMWQ1ZWQzZGQ4YjQwOTk0ZDBlNDFkMjkwYjU2ZTk2OWYyMDgyNzVjNzVmZTgyZjZiOTU5MjM5YjM0NzJkYSZYLUFtei1TaWduZWRIZWFkZXJzPWhvc3QmYWN0b3JfaWQ9MCZrZXlfaWQ9MCZyZXBvX2lkPTAifQ.UaMD0Y-FvPDJKGNc2qCZ1ORw6JOkHGVhXQ7y0Ik1yLg)

https://prnt.sc/5NYd8oaV48VQ

- borne_id, c'est tous simplement l'id de la borne ou il y'a ue une modification, cela permet de voir qu'elle borne est libre ...
- debutfin, comme au dessus (" qui indique si c'es le debut ou la fin de la charge, si c'est debut cela veut dire qu'elle charge puis si c'est Fin alors c'est que la charge est fini")
- Le reste c'est egalement la meme chose que dans bornes

- ancienne_valeur, pour comprendre cette valeur il faut voir que c'est celle ci qui va mettre les informations en fonction de la valeur précedente, je veut dire par la que si la valeure libre = Non et que la personne sur la borne se débranche alors la valeure sera egale à Oui dans ce cas cela créra une nouvelle lignes avec toutes les infos.
        
Voici un exemple de la table Log :

![Image](https://private-user-images.githubusercontent.com/137182634/247544598-124be901-732a-467f-a4eb-562ebc3df0fc.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOiJrZXkxIiwiZXhwIjoxNjg3MzUwMzgzLCJuYmYiOjE2ODczNTAwODMsInBhdGgiOiIvMTM3MTgyNjM0LzI0NzU0NDU5OC0xMjRiZTkwMS03MzJhLTQ2N2YtYTRlYi01NjJlYmMzZGYwZmMucG5nP1gtQW16LUFsZ29yaXRobT1BV1M0LUhNQUMtU0hBMjU2JlgtQW16LUNyZWRlbnRpYWw9QUtJQUlXTkpZQVg0Q1NWRUg1M0ElMkYyMDIzMDYyMSUyRnVzLWVhc3QtMSUyRnMzJTJGYXdzNF9yZXF1ZXN0JlgtQW16LURhdGU9MjAyMzA2MjFUMTIyMTIzWiZYLUFtei1FeHBpcmVzPTMwMCZYLUFtei1TaWduYXR1cmU9YmVlOTVmNTI1MmUzNGJlZWVlMmMwZjFjMWJmYjBmNmRkYTBjOThiMzhhZDA4YjlmN2NjNmIyZTFhODgzZDllZCZYLUFtei1TaWduZWRIZWFkZXJzPWhvc3QmYWN0b3JfaWQ9MCZrZXlfaWQ9MCZyZXBvX2lkPTAifQ.eergRQJhaKHU1hPGkXTnUDqhRHY4xWafRjnHntyhP68)

https://prnt.sc/PqMrGwK-_yzK
        

## 📚 Présentation complete src

Nous avous une présentation comme celle ci : 

![Image](https://private-user-images.githubusercontent.com/137182634/247553679-fd2ec8ce-38cf-43cd-b129-559aa99d5837.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOiJrZXkxIiwiZXhwIjoxNjg3MzUyMzE0LCJuYmYiOjE2ODczNTIwMTQsInBhdGgiOiIvMTM3MTgyNjM0LzI0NzU1MzY3OS1mZDJlYzhjZS0zOGNmLTQzY2QtYjEyOS01NTlhYTk5ZDU4MzcucG5nP1gtQW16LUFsZ29yaXRobT1BV1M0LUhNQUMtU0hBMjU2JlgtQW16LUNyZWRlbnRpYWw9QUtJQUlXTkpZQVg0Q1NWRUg1M0ElMkYyMDIzMDYyMSUyRnVzLWVhc3QtMSUyRnMzJTJGYXdzNF9yZXF1ZXN0JlgtQW16LURhdGU9MjAyMzA2MjFUMTI1MzM0WiZYLUFtei1FeHBpcmVzPTMwMCZYLUFtei1TaWduYXR1cmU9YWM1NDllZGJjM2FkMWZmNWFiOTFmMmZjMTU4NDRlMjlkMmQ5YzM3OTViYTk3YjRjMmU4OWFiOGNmNmRhYTJlOCZYLUFtei1TaWduZWRIZWFkZXJzPWhvc3QmYWN0b3JfaWQ9MCZrZXlfaWQ9MCZyZXBvX2lkPTAifQ.p87e6Fe0CmGwGxx85Nhb4rBYLk7wRpl411rykWqd7XA)

https://prnt.sc/08s0pjtmVsgR
        
- Voyons donc Archive celui ci ce sont juste des codes que j'ai utiliser avant et que j'ai garder au cas ou je devais recuperer des infos pour améliorer le code ou reprendre une version précedente.

- config, ici il y'a les 2 codes pour l'actualisation toutes les 30 secondes de l'appel sur l'API de Rossini.
- controller, dans ce dossier nous avons BornesController qui est la base même de notre API car c'est lui qui va crée la relation entre le code dur JAVA et une page affichable soit en html soit aussi par du GETMAPPING qui va crée des redirections  comme celle ci par ex : http://localhost:8080/borne/all
avec des /.../... 
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


![Image](https://private-user-images.githubusercontent.com/137182634/247554929-0ff7f85f-00d1-4b52-bb19-bb810af56554.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOiJrZXkxIiwiZXhwIjoxNjg3MzUyNjE1LCJuYmYiOjE2ODczNTIzMTUsInBhdGgiOiIvMTM3MTgyNjM0LzI0NzU1NDkyOS0wZmY3Zjg1Zi0wMGQxLTRiNTItYmIxOS1iYjgxMGFmNTY1NTQucG5nP1gtQW16LUFsZ29yaXRobT1BV1M0LUhNQUMtU0hBMjU2JlgtQW16LUNyZWRlbnRpYWw9QUtJQUlXTkpZQVg0Q1NWRUg1M0ElMkYyMDIzMDYyMSUyRnVzLWVhc3QtMSUyRnMzJTJGYXdzNF9yZXF1ZXN0JlgtQW16LURhdGU9MjAyMzA2MjFUMTI1ODM1WiZYLUFtei1FeHBpcmVzPTMwMCZYLUFtei1TaWduYXR1cmU9NTZkYTcyMWNjZjM2NDJlNGIwMWU0YWYyMjk1NDFmYWVlYWQ4MTAxMmE2YzFkZDhhMWFmNTM5ZmI2NjYyMWZhNSZYLUFtei1TaWduZWRIZWFkZXJzPWhvc3QmYWN0b3JfaWQ9MCZrZXlfaWQ9MCZyZXBvX2lkPTAifQ.oZaNELpZxUgqeV_7weIBnrOjDwxZe8MnSDzZvy_TChA)

https://prnt.sc/VFAB2Mcbdfru
        
<br>

http://localhost:8080/borne/all

https://prnt.sc/TA32xp_i2RhN

        
