# <p align="center">Bornes Ntico</p>
  
Création d'une API qui récupere les informations des bornes de voitures électrique, depuis l'API de chez Rossini Energy. Elles se trouvent devant chez Ntico V2.

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
- Se mettre en attente
- S'enlever de l'attente
- Regardez les utilisateurs en attente
- Ajouter un nouvel utilisateur<br>
Chacunes sont relier a une ou plusieures pages HTML qui sont relier au code java qui permet de faire des redirections et de modifier la table Utilisateur.
## Récap
- Appel API 
- Création tables : Log / Bornes / Utilisateur
- Relation entre : HTML / Tables / Code JAVA

## 🛠️ Liens des Bornes (API)
[Voir le park de Ntico](http://api.charge.re/public/1/parks/224)
<br>
[Borne 818](http://api.charge.re/public/1/chargecontroller/818)
<br>
[Borne 820](http://api.charge.re/public/1/chargecontroller/820)
<br>
[Borne 821](http://api.charge.re/public/1/chargecontroller/821)
<br>
[Borne 822](http://api.charge.re/public/1/chargecontroller/822)
<br>


    
    
    
    
