# Dojo - La main de Poker - Equipe A 512 Bank
## À propos de la main de poker
### But du projet 
Création d'un jeu de poker en Java qui utilise le cli. 
Actuellement avec ce projet nous pouvons comparer deux mains de poker saisies sur l'entrée standard. Nous pouvons déterminer laquelle des deux mains est la plus forte et afficher le résultat.
### Fonctionnalités 
* Notre programme permet de contrôler la bonne saisie des cartes sur l'entrée standard. Un message d'erreur s'affiche si la saisie n'est pas bonne et vous demande de recommencer. 
* Une fois les deux mains saisies, le programme est capable de trouver la meilleure combinaison des deux mains et d'afficher résultat, c'est à dire la main et la combinaison gagnante sur la sortie standard. 
* La comparaison des mains est possible car nous avons implémenter les combinaisons du poker et leur force. Nous pouvons détecter: 

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
#### Télécharger la dernière release du projet et éxécuter : 
```java -jar ProjetS5-v1.0.x.jar (x étant la dernière version du projet)```

#### ou bien clone ce repo:
    `git clone https://github.com/pns-si3-projects/dojo-poker-23-24-ps5-23-24-poker-a.git`
- 	se mettre à la racine :
     `cd dojo-poker-23-24-ps5-23-24-poker-a/`
- 	Générer le jar du projet :`mvn clean install`
- 	éxécuter le jar:
     `java -jar /target/ProjetS5-1-0-x.jar`
- 	Pour effectuer les tests :
     `mvn clean test`

## Guide d'utilisation 
- Saisir sur l'entrée standard les deux mains composées de cinq cartes chacune et appuyer sur entrée à la fin de la saisie de chaque main. 
- Une carte peut avoir comme valeur 2,3,4,5,6,7,8,9,10,V,D,R,A . 
- Une carte peut avoir comme couleur Tr (trèfle), Pi (Pique), Co (Coeur), Ca (Carreau)
- Une fois la deuxième main saisie et après avoir appuyé sur entrée, le résultat s'affichera. 
 
Exemple d'une main saisie valide :  ``DCo ACa RPi 2Co 10Ca``

Exemple d'une main saisie invalide : ``Atr 10Co 5Pi 2Co RCa`` (ici la couleur trèfle n'est pas bien saisi, elle devrait s'écrire 'Tr' )
## Contributeurs 
- Antoine FADDA RODRIGUEZ : https://github.com/Antoine-FdRg
- Baptiste LACROIX : https://github.com/BaptisteLacroix
- Clément LEFEVRE : https://github.com/Firelods
- Emma ALLAIN : https://github.com/emmaallain
