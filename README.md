# [![Typing SVG](https://readme-typing-svg.herokuapp.com?font=Fira+Code&size=27&pause=1000&width=435&lines=Island)](https://git.io/typing-svg)
<h2><a>Simulation of island with locations, animals and plants.</a></h2>

An island of locations with animals and plants is being created.
Each animal has such functions as:
- move - from one location to another accessible. There is a limit on the capacity of each type
- eat - a creature suitable for the location is selected, which is included in the diet of the eater.
And **all** creatures have function "copy".

The chances of taking an action depend on a randomly drawn number
Every creature has a cycle of life. In animals, it depends on saturation. At 0 saturation, the object dies.
Also, throughout the simulation, the statistics of the island are kept.

<h3><a>Operations:</a></h3>

* Create island with auto locations
* Create island with special parameters
* Get information about state of island

<h3><a>Build: </a></h3>
```$ mvn package```

<h3 ><a>Launch:</a></h3>
```$ java -jar ./target/island-1.0.jar```

<h3 ><a>Description of classes:</a></h3>
>Root package ```ua.com.javarush.shestakova.Island``` has classes:
- ```App``` - start point to app ```ua.com.shestakova.island.App```
- ```Dialod``` - builds interface fot user
- ```Statistics``` - updates and collects field statistics

>Package ```resources``` has classes:
- ```settingIsland.properties``` - general settings of simulation
- ```allAnimal.json``` - data on all possibilities about the whereabouts of animals

>Package ```creature``` has all kinds of island creatures
- class-parent ```Creature``` - common fields and methods of all creatures
```ua.com.shestakova.island.creature.Creature```
- ```Animal``` and ```Plant``` - heirs ```Creature``` more
  statistical characteristics of animals and plants
- ```Herbivore``` and ```Predator``` - heirs ```Animal```,
even more detailed characteristics of these types of animals

- subpackage ```herbivore``` and ```predator```

>Package ```constructorGame``` - classes for creating:
- ```Island``` - create island and locations
- ```Location``` - add creatures in locations
- ```Parser``` - write and read user's settings
- ```Tools``` - keep the map all animals and some tools for work with simple numbers

>Package ```performingActions``` - general logic creature's behavior
- ```LifeTime``` - life cycle of creature
- ```Simulation``` - simulation's steps
>Package ```exceptions``` has classes:
- ```ParsingExceptions```
- ```CreateException``` 
- ```ThreadsException``` 
- ```InputException``` 
- 
<h3><a>User's interface</a></h3>
  Dialog with user and getting sitting (if it was chosen) has 2 various.
  It has to be numbers 1 or 2
User must add something symbol for start of simulation and "Enter"
When running the simulation, data on changes in animals on the island is displayed (the number of births and deaths)

<h3><a>Technology</a></h3>

- reading and writing data from a file properties, write to JSON
- Stream API
- ExecutorService (пакет java.util.concurrent)
- Reflection API
- Maven (Lombok и JColor)

### Example:
https://user-images.githubusercontent.com/105308647/193412048-86d7ce3f-9e21-45b1-a247-c06e235f0bfe.mp4
