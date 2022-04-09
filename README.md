***
ColtExpres, Alice Petiot, Oscar Blanc, Anaëlle Cossard 
***

1. Les parties traitées


    (1.2)   Dans un premier temps, nous nous sommes mis d'accord sur un diagramme de 
        classe afin de mettre en place l'architecture Modèle-Vue-Contrôleur. 
        Ensuite nous avons implémenté les différentes classes y correspondant. 
    
    (1.3)   Des tests ont été implémenté pour les classes Butins, Bandits, Marshall, 
        TModele ainsi que Wagons. Pour chaque action effectuée par un bandit, 
        un compte rendu est affiché sur la sortie standard.
    
    (1.4)   Pour l'affichage graphique, la fenêtre comporte 3 zones :
    
        * la zone des boutons de commande avec les différentes actions possibles
        ainsi que le bouton action qui effectue une à une les actions des bandits.
        * la zone d'affichage du train avec les wagons, intérieur et extérieur ainsi
        que la locomotive. La zone est mise à jour à chaque actions des bandits.
        * la zone du compte rendu qui affiche pour chaque bandit son nom, sa positon,
        les différents butins en sa possession avec le montant total ainsi que 
        le nombre de balles restantes.
    
    (2.)    Au début du jeu, il y a entre 1 à 4 butins initialisé aléatoirement par
        wagons, la magot et le Marshall sont placés à l'intérieur de la locomotive.
        A chaque action d'un bandit le Marshall à une probabilité p = 0.3 de se 
        déplacer d'un wagon à l'autre. Lorsque le Marshall tombe sur un bandit,
        le bandit fuit vers le toit du wagon et laisse tomber un butin dans le 
        wagon.
    
    (2.1)   Chaque bandit à 4 actions à jouer, chaque joueur choisi ses 4 actions
        à la suite puis laisse le joueur suivant choisir les siennes. 
        Lorsque chaque bandit à choisi ses 4 actions, on appuie sur le bouton action
        pour déclencher la première action du premier bandit, puis la première du second
        et ainsi de suite, à chaque action on affiche le nom du joueur ainsi que 
        l'action en cours.
        
    (2.2)   Dans le modèle, on initialise le nombre de joueurs à 4 bandits. 
        Pour déterminer l'ordre, chaque bandits à un id qui détermine son tour dans
        l'ordre de passage. Parmis les actions des bandits, ils peuvent tirer à 
        droite, à gauche, en haut ou en bas. Si des bandits se trouvent dans la 
        direction choisi, alors un bandits est tiré alétoirement et lache un butin,
        sinon le tireur perd une balle. Les actions des bandits sont notifiés sur 
        la sortie standard et sur la sortie CompteRendue avec les comptes des balles 
        ainsi que des butins.
        
    (2.3)   Amélioration des graphismes du train.
    
2. Les choix d'architecture



    Pour l'architecture, nous avons choisi de faire 12 classes ainis qu'une interface, 
    pour les classes Bandits,Butins, Wagons, Marshall ainsi que TModele des getter 
    et des setter ont été implémentés afin de rendre les manipulations plus simples.
    
    (1)     Bandits : elle implémente les caractéristiques du bandit telles que 
        son nom, son id, dans quel wagon il se trouve ainsi que sa position à l'intérieur
        ou sur le toit de ce dernier à l'aide d'un boolean, la liste des butins qu'il
        possède et le nombre de balles qu'il possède. Chaque bandit est initialisé
        avec un id, un  nom, le nombre de balles maximal, sans butin et sur le toit
        du dernier wagon. Les fonctions telles que les déplacements ou l'ajout de
        butin pour les bandits sont implémentés dans cette classe.
        
    (2)     Butins : elle implémente les caractéristiques des butins telles que sa
        nature, son id, sa valeur, sa positon sur le toit ou non, ainsi que l'id 
        du wagon dans lequel il se trouve. Il est initialisé dans à l'intérieur d'un
        wagon, sa nature est aléatoire et si le butin est une bourse, on lui attribue
        une valeur aléatoire entre 250, 300, 350, 400, 450 et 500.
        
    (3)     ColtExpress : c'est la classe principale, elle initialise le contrôleur,
        la vue ainsi que le modele.
        
    (4)     Controleur : Le contrôleur permet de faire le lien entre les actions
        données par les commandes de la vue et les actions dans le modele.
        
    (5)     CVue : la classe CVue gère la fenêtre d'affichage. Elle regroupe et 
        affiche les vues VueTrain, VueCommandes et VueCompteRendu.
        
    (6)     Marshall : elle implémente le Marshall, il contient la NERVOSITE_MARSHALL
        soit la probabilité que le Marshall se déplace à chaque tour, son nom, sa position 
        et son id. Il est initialisé à l'intérieur de la locomotive, et ne peut se 
        déplacer qu'à l'intérieur des wagons.
        
    (7)     Observable : représente la classe des objets observés avec l'observateur.
        On déclare l'observateur ensuite dans les différentes classes observées.
    
    (8)     Observer : l'interface des objets observateurs, permet d'update l'affichage
        lorsqu'il y a des changements.
        
    (9)     TModele : c'est la classe qui permet de gérer le modèle et regroupe 
        tous les éléments. Il contient un observateur qui permet de gérer la vue, 
        le nombres de wagons, la taille des wagons pour l'affichage, le nombre 
        de joueurs et les nombres de coups possible par joueur, il contient les 
        bandits ainsi que les butins et les wagons, il est le train. 
        Lorsqu'on le construit, il initialise les wagons ainsi que la locomotive, 
        il crée les bandits et les ajoute sur le toit du dernier wagon, il y ajoute 
        le Marshall ainsi que les différents butins. La classe TModele regroupe 
        également toutes les fonctions permettant les actions des joueurs, telle 
        que les tirs, les déplacements et la fonction action qui récupère l'action 
        choisi dans la vue pour l'appliquer au modèle.
        
    (10)    VueCommandes : VueCommandes permet l'affichage des boutons de commande 
        du jeu. On y trouve les boutons de tirs, de déplacements ainsi que d'action 
        et de braquage.
        
    (11)    VueCompteRendu : dans cette classe on met en place l'affichage du compte 
        rendue en bas de l'écran. Elle permet d'afficher pour chaque joueurs son id 
        ainsi que son nom, les bijoux, bourses et magots en sa possession, le montant 
        total de ses butins sa position et enfin le nombre de balles qu'il lui reste.
        
    (12)    VueTrain : Ici c'est l'affichage du train qui est gérer. Il faut d'abord 
        mettre en place l'affichage des wagons séparément, avec les deux étages, les 
        butins et les bandits présent dans le wagon. Ensuite on affiche le train en 
        affichant chaque wagon.
        
    (13)    Wagons : elle implémente les caractéristiques du wagon. Chaque wagon a 
        un id, une liste de butins ainsi qu'une liste des bandits présent dans ce 
        wagon ainsi que le Marshall pour savoir s'il se trouve ou non à bord.
    
    Pour l'organisation, nous avons discuté ensemble du diagramme de classe puis chacun 
    à coder une partie en fonction des besoins et de l'avancement des autres au cours du temps.
    
    Alice : tout sauf vueCompteRendu,vueTrain et le README
    
    Oscar : implémentation de méthodes dans les classes excepté la vue , implémentation de tests, diagramme de classe
    
    Anaëlle : implémentation des classes VueCompteRendu et VueTrain ainsi que le README 

3. Problèmes présents


    (1)     Problème d'indexage durant l'initialisation des butins, bandits et wagons    
	    Solution : passer l'indice de l'objet en paramètre du constructeur
           
    (2)     Problème de superposition des fenêtres avec la fonction repaint()    
        Solution : utiliser get.clear() pour la vue du train et compte rendu               
           
    (3)     Problème d'indexage avec la liste des bandits pour les déplacements     
	    Solution : utiliser un dictionnaire avec l'identifiant du bandit et sa valeur plutôt qu'une liste           
           
    (4)     Problème de valeur nulle pour le Marshall et certains Wagons     
    	Solution : si la valeur est null alors on affiche un message sur la sortie standart et on quitte
        la fonction    

    (5)     Problème pour passer une vue au contrôleur (permet d'avoir acces aux boutons 
        de la vue commandes et de leur assigner une action à faire par rapport au modele)    
        Solution : initialiser le controleur dans le main


4. Morceaux de codes écrit à plusieurs ou empruntés


    Nous n'avons pas emprunté de code, seul le fichier CONWAY fournis nous a servi 
    d'inspiration et de repère. 