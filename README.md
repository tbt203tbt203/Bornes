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

Voici un exemple de la table utilisateur :

![Image](https://private-user-images.githubusercontent.com/137182634/247544596-3140d70b-3790-4a06-ab39-dd99a67ec9b2.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOiJrZXkxIiwiZXhwIjoxNjg3MzUwMzgzLCJuYmYiOjE2ODczNTAwODMsInBhdGgiOiIvMTM3MTgyNjM0LzI0NzU0NDU5Ni0zMTQwZDcwYi0zNzkwLTRhMDYtYWIzOS1kZDk5YTY3ZWM5YjIucG5nP1gtQW16LUFsZ29yaXRobT1BV1M0LUhNQUMtU0hBMjU2JlgtQW16LUNyZWRlbnRpYWw9QUtJQUlXTkpZQVg0Q1NWRUg1M0ElMkYyMDIzMDYyMSUyRnVzLWVhc3QtMSUyRnMzJTJGYXdzNF9yZXF1ZXN0JlgtQW16LURhdGU9MjAyMzA2MjFUMTIyMTIzWiZYLUFtei1FeHBpcmVzPTMwMCZYLUFtei1TaWduYXR1cmU9YjUyODk1YzNkMzQ3ODU0OTI4OWVlOWJlNDRkMTIzYTIwOGI2NGY5NjFiMzBjZWI2OTg3ZWNiNzBjNWM2MGU2YyZYLUFtei1TaWduZWRIZWFkZXJzPWhvc3QmYWN0b3JfaWQ9MCZrZXlfaWQ9MCZyZXBvX2lkPTAifQ.Kut9D9mJPAhtyMTiMxXkINHexStkPY6xd5901NOeKXo)
        

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
        
## 📝  Log table

La table log est composer de 7 colones en effet il y'en a une "ancienne_valeur qui peut etre assez flou je vais expliquer.

- id, colone basique qui sur mon screen est un peut buggé en effet quand ont reset la base il faut enlever des morceaux du code pour que sa crée les lignes correctement mais des que tous les début sont présents alors remettre le morceau de code je n'ai pas trouver d'alternative je montre les 2 screens(BornesController) :

<br>
Image avant modif :

![Image](https://private-user-images.githubusercontent.com/137182634/247547077-50b05492-5922-4042-8578-e8a3f8a1df8e.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOiJrZXkxIiwiZXhwIjoxNjg3MzUwOTgwLCJuYmYiOjE2ODczNTA2ODAsInBhdGgiOiIvMTM3MTgyNjM0LzI0NzU0NzA3Ny01MGIwNTQ5Mi01OTIyLTQwNDItODU3OC1lOGEzZjhhMWRmOGUucG5nP1gtQW16LUFsZ29yaXRobT1BV1M0LUhNQUMtU0hBMjU2JlgtQW16LUNyZWRlbnRpYWw9QUtJQUlXTkpZQVg0Q1NWRUg1M0ElMkYyMDIzMDYyMSUyRnVzLWVhc3QtMSUyRnMzJTJGYXdzNF9yZXF1ZXN0JlgtQW16LURhdGU9MjAyMzA2MjFUMTIzMTIwWiZYLUFtei1FeHBpcmVzPTMwMCZYLUFtei1TaWduYXR1cmU9N2NhYTVmNTg0MGY3OTI1YWNlOWQ0N2I2OGMyYjU1MzIzMTE0OGI3ZWUyMjk2OTliZWJlNjg1MjkwMDQ5NmMwYiZYLUFtei1TaWduZWRIZWFkZXJzPWhvc3QmYWN0b3JfaWQ9MCZrZXlfaWQ9MCZyZXBvX2lkPTAifQ.eNAUjSu9BS2t0MBN1mA0dRXyKOqZnxNDf4bad2g3pKM)
        
Image si tu veut clear la base : 


![Image](https://private-user-images.githubusercontent.com/137182634/247547086-9f168d66-5bee-4d3e-88da-a7730f069726.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOiJrZXkxIiwiZXhwIjoxNjg3MzUwOTgwLCJuYmYiOjE2ODczNTA2ODAsInBhdGgiOiIvMTM3MTgyNjM0LzI0NzU0NzA4Ni05ZjE2OGQ2Ni01YmVlLTRkM2UtODhkYS1hNzczMGYwNjk3MjYucG5nP1gtQW16LUFsZ29yaXRobT1BV1M0LUhNQUMtU0hBMjU2JlgtQW16LUNyZWRlbnRpYWw9QUtJQUlXTkpZQVg0Q1NWRUg1M0ElMkYyMDIzMDYyMSUyRnVzLWVhc3QtMSUyRnMzJTJGYXdzNF9yZXF1ZXN0JlgtQW16LURhdGU9MjAyMzA2MjFUMTIzMTIwWiZYLUFtei1FeHBpcmVzPTMwMCZYLUFtei1TaWduYXR1cmU9MGQwMWQ1ZWQzZGQ4YjQwOTk0ZDBlNDFkMjkwYjU2ZTk2OWYyMDgyNzVjNzVmZTgyZjZiOTU5MjM5YjM0NzJkYSZYLUFtei1TaWduZWRIZWFkZXJzPWhvc3QmYWN0b3JfaWQ9MCZrZXlfaWQ9MCZyZXBvX2lkPTAifQ.UaMD0Y-FvPDJKGNc2qCZ1ORw6JOkHGVhXQ7y0Ik1yLg)

- borne_id, c'est tous simplement l'id de la borne ou il y'a ue une modification, cela permet de voir qu'elle borne est libre ...
- debutfin, comme au dessus (" qui indique si c'es le debut ou la fin de la charge, si c'est debut cela veut dire qu'elle charge puis si c'est Fin alors c'est que la charge est fini")
- Le reste c'est egalement la meme chose que dans bornes
        
Voici un exemple de la table Log :

![Image](https://private-user-images.githubusercontent.com/137182634/247544598-124be901-732a-467f-a4eb-562ebc3df0fc.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOiJrZXkxIiwiZXhwIjoxNjg3MzUwMzgzLCJuYmYiOjE2ODczNTAwODMsInBhdGgiOiIvMTM3MTgyNjM0LzI0NzU0NDU5OC0xMjRiZTkwMS03MzJhLTQ2N2YtYTRlYi01NjJlYmMzZGYwZmMucG5nP1gtQW16LUFsZ29yaXRobT1BV1M0LUhNQUMtU0hBMjU2JlgtQW16LUNyZWRlbnRpYWw9QUtJQUlXTkpZQVg0Q1NWRUg1M0ElMkYyMDIzMDYyMSUyRnVzLWVhc3QtMSUyRnMzJTJGYXdzNF9yZXF1ZXN0JlgtQW16LURhdGU9MjAyMzA2MjFUMTIyMTIzWiZYLUFtei1FeHBpcmVzPTMwMCZYLUFtei1TaWduYXR1cmU9YmVlOTVmNTI1MmUzNGJlZWVlMmMwZjFjMWJmYjBmNmRkYTBjOThiMzhhZDA4YjlmN2NjNmIyZTFhODgzZDllZCZYLUFtei1TaWduZWRIZWFkZXJzPWhvc3QmYWN0b3JfaWQ9MCZrZXlfaWQ9MCZyZXBvX2lkPTAifQ.eergRQJhaKHU1hPGkXTnUDqhRHY4xWafRjnHntyhP68)
        

## 📚 Présentation complete src



 

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



        
