# Dojo - La main de Poker
## Contexte du projet
Nous pouvons grâce à ce projet comparer deux mains de poker saisies sur l'entrée standard. Nous pouvons déterminer laquelle des deux mains est la plus forte et afficher le résultat. Pour comparer les mains nous avons implémenter les combinaisons possibles du poker et leur détection c'est à dire:
-   Plus haute carte
-   Paire
-   Deux paires
-   Brelan
-   Suite
-   Couleur
-   Full
-   Carré
-   Quinte Flush

### Développé avec  :
- 	Java 17
- 	Maven 3.8.6
- 	Junit 5.7.0

## Pré-requis
- Github
- JAVA version 17
- Maven version 3.8.6
## Guide d'installation et d'éxécution 
#### Télécharger la dernière release du projet et éxécuter : 
	java -jar ProjetS5-v1.0.x.jar (x étant la dernière version du projet)

#### ou bien clone ce repo:
	git clone https://github.com/pns-si3-projects/dojo-poker-23-24-ps5-23-24-poker-a.git
- 	se mettre à la racine 
- 	éxécuter cette commande qui permettre de générer le jar du projet :  
    mvn clean install 
- 	éxécuter (x étant la dernière version du projet) : 
java -jar /target/ProjetS5-1-0-x.jar 
- 	Pour effctuer les tests :
mvn clean test 

## Guide d'utilisation 
- Saisir sur l'entrée standart les deux mains composées de cinq cartes chacune et appuyer sur entrée à la fin de la saisie de chaque main. 
- Une carte peut avoir comme valeur 2,3,4,5,6,7,8,9,10,V,D,R,A . 
- Une carte peut avoir comme couleur Tr (trèfle), Pi (Pique), Co (Coeur), Ca (Carreau)
- Une fois la deuxième main saisie et après avoir appuyer sur entrez, le résultat s'affichera. 

## Contributeurs 
- Antoine FADDA RODRIGUEZ : https://github.com/Antoine-FdRg
- Baptiste LACROIX : https://github.com/BaptisteLacroix
- Clément LEFEVRE : https://github.com/Firelods
- Emma ALLAIN : https://github.com/emmaallain
