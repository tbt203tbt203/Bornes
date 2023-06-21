# <p align="center">Bornes Ntico 🏎️</p>
  
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

## 🔌 Borne table

La table Borne est très simple à comprendre, elle est composer de 5 colones
- id_borne, qui a est assez comprehensible donc on retrouvera les id 818, 820, 821, 822.
- heure, qui affiche l'heure ou il y'a eu le dernier changement dans la table
- debutfin, qui indique si c'es le debut ou la fin de la charge, si c'est debut cela veut dire qu'elle charge puis si c'est Fin alors c'est que la charge est fini
- utilisateur, indique l'utilisateur connecter actuelement si c'est debut et le pseudo de la derniere personne connecté si c'est Fin
- libre, si la corne est occupé alors la colone libre = non et inversement

Cette table est entierement fournie grace à la page BorneService, la ou toutes les informations vont etre prélever et redistribué dans les différents codes du BorneController.
Cela permettra d'allimenter la page /borne/all ou on trouvera toutes les informations en temps reel des bornes.

## 📝  Log table

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

