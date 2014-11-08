QuickShare
==========

Binôme : GONZALEZ Alban - TRAN Albert


**Présentation du projet :**
  Le projet **QuickShare** est une plateforme permettant l'hébergement d'images (ImageShack like)
  Une image hébergé génère une URL afin que l'utilisateur puisse utiliser son image sur internet (sur un forum, sur un site ou autre).


**Technologies utilisées :**
  Ce projet est une application WEB développée en J2EE.
  Le principal framework utilisée est le framework Spring MVC qui permet de simplifier la gestion des Modèles/Vues/Controleurs.
  Nous avons aussi intégré une base de données embarquée H2, que nous avons implémenté à l'aide de la librairie Hibernate.
  Afin de dynamiser certaines fonctionnalités de l'application, du javascript ainsi que du Jquery ont été utilisés (notamment pour les contrôles sur formulaire, activation/désactivations de bouton)


**Fonctionnalités :**
  L'application WEB présentée permet de :
    - Héberger une image 
    - Rechercher une image (par titre, le titre pouvant être défini avant d'héberger l'image, sinon le nom de l'image est utilisé par défaut)
    - Définir comme privée une image (avant l'upload). L'image sera ainsi introuvable par la fonctionnalité de recherche.
    - Télécharger une image


**Choix technique :**
  Les images étant hébergés en local (car nous n'avons pas de serveur), nous avons choisi d'utiliser l'api imgur afin de pouvoir générer de réels liens, plutôt que d'afficher des chemins d'accès locaux. Nous avons estimé que manipuler des URL était plus "propre" que d'afficher des adresses locales.
  Afin d'utiliser l'API imgur il faut envoyer une requête HTTP contenant l'image, à l'api, puis celle-ci retourne un fichier JSON.


**L'application est composée de 3 vues : **
    - La page principale qui permet de définir l'image locale à héberger, et qui affiche aussi les dernières images hébergées par les internautes.
    - La page de détail d'une image qui affiche un apperçu de l'image ainsi que son détail (taille, dimensions,...) et ses liens générés
    - La page de résultat d'une recherche (la barre de recherche étant toujours visible quelque soit la vue)
    
    
**Captures d'écran de l'application :**

Page d'accueil : http://i.imgur.com/g50hxsw.png

Détail d'une image hébergée : http://i.imgur.com/3sqLYOr.png

Recherche d'images : http://i.imgur.com/FBD47fm.png

