# Dojo - La main de Poker - Equipe A 512 Bank
## À propos de la main de poker
### But du projet 
Le projet Dojo Poker vise à créer un jeu de poker en Java, avec une interface en ligne de commande (CLI). Actuellement, le jeu permet la comparaison de deux mains de poker saisies sur l'entrée standard, déterminant ainsi la main la plus forte et affichant le résultat.
### Règle du jeu 
Chaque main possède cinq cartes. Celle qui possède la meilleure combinaison de carte dans sa main gagne. 

Pour déterminer la meilleure combinaison voici la liste des combinaisons détaillées, elles sont triées de la moins bonne à la meilleure combinaison :
* Main sans combinaison (meilleurs carte) : `4Pi 7Tr DCo VPi 2Ca` 
    
    C'est une main qui ne contient aucune combinaison, cette main est classée par la valeur de la carte la plus haute.


* Paire : `4Pi 7Tr 7Co VPi 2Ca`

    2 des 5 cartes ont la même valeur. Deux mains qui contiennent une paire sont classées par la valeur des cartes formant la paire.


* Deux paires : `4Pi 7Tr 7Co VPi 4Ca`
    
    La main contient deux paires différentes. Les mains qui contiennent deux paires sont classées par la valeur de la paire la plus élevée.


* Brelan: `4Pi 8Tr 8Co VPi 8Ca`
    
    3 cartes dans la main ont la même valeur. Deux mains avec un brelan sont classées par la valeur des 3 cartes


* Suite : `4Pi 5Tr 6Co 7Pi 8Ca`
    
    5 cartes de valeurs consécutives. Deux suites sont classées par la valeur de leur carte la plus élevée. 


* Couleur : `4Pi DPi 6Pi 7Pi 8Pi`
    
    La main contient 5 cartes de la même couleur. Deux mains couleurs sont classées par les règles de la carte la plus élevée. 


* Full : `4Pi 8Tr 8Co 8Pi 4Ca`
    
    La main contient 3 cartes de la même valeur avec les 2 autres cartes formant une paire, le classement se fait par la valeur des 3 cartes. 


* Carré : `4Pi 8Tr 8Co 8Pi 8Ca`
    
    4 cartes de la même valeur, le classement se fait par la valeur des 4 cartes. 


* Quinte Flush : `4Pi 5Pi 6Pi 7Pi 8Pi`
    5 cartes de la même couleur avec des valeurs consécutives, le classement se fait par la carte la plus élevée dans la main. 
### Fonctionnalités 
* Contrôle de la saisie correcte des cartes sur l'entrée standard, avec affichage d'un message d'erreur en cas de saisie incorrecte.
* Détermination de la meilleure combinaison entre deux mains et affichage du résultat, incluant la main et la combinaison gagnante.
* Implémentation des combinaisons du poker, permettant de détecter :

  * Plus haute carte
  * Paire
  * Deux paires
  * Brelan
  * Suite 
  * Couleur 
  * Full 
  * Carré 
  * Quinte Flush

### Développé avec :
- 	Java 17
- 	Maven 3.8.6
- 	Junit 5.7.0

## Pré-requis
- JAVA 
- Maven 
## Guide d'installation et d'éxécution 
#### Télécharger la dernière release du projet et exécuter : 
```java -jar ProjetS5-v1.0.x.jar (x étant la dernière version du projet)```

#### ou bien clone ce repo:
    `git clone https://github.com/pns-si3-projects/dojo-poker-23-24-ps5-23-24-poker-a.git`
- 	se mettre à la racine :
     `cd dojo-poker-23-24-ps5-23-24-poker-a/`
- 	Générer le jar du projet :`mvn clean install`
- 	exécuter le jar:
     `java -jar /target/ProjetS5-1-0-x.jar`
- 	Pour effectuer les tests :
     `mvn clean test`

## Guide d'utilisation 
- Saisir sur l'entrée standard les deux mains composées de cinq cartes chacune et appuyer sur entrée à la fin de la saisie de chaque main. 
- Une carte peut avoir comme valeur 2,3,4,5,6,7,8,9,10,V,D,R,A . 
- Une carte peut avoir comme couleur Tr (trèfle), Pi (Pique), Co (Cœur), Ca (Carreau)
- Une fois la deuxième main saisie et après avoir appuyé sur entrée, le résultat s'affichera. 
 
Exemple d'une main saisie valide :  ``DCo ACa RPi 2Co 10Ca``

Exemple d'une main saisie invalide : ``Atr 10Co 5Pi 2Co RCa`` (ici la couleur trèfle n'est pas bien saisie, elle devrait s'écrire 'Tr' )
## Contributeurs 
- Antoine FADDA RODRIGUEZ : https://github.com/Antoine-FdRg
- Baptiste LACROIX : https://github.com/BaptisteLacroix
- Clément LEFEVRE : https://github.com/Firelods
- Emma ALLAIN : https://github.com/emmaallain
