# [![Typing SVG](https://readme-typing-svg.herokuapp.com?font=Fira+Code&size=27&pause=1000&width=435&lines=Island)](https://git.io/typing-svg)

<h2><a>Симуляция острова с локациями животных и растений.</a></h2>

Создается остров локаций с животными и растениями. 
У каждого животного есть такие функции как: 
- двигаться - из одной локации в другую, при этом доступную, т.к. есть ограничение по вместимости каждого вида
- кушать - выбирается подходящее на локации существо, которое входит в рацион поедающего.

Так же, у **всех** существ есть функция размножения. 
Шансы на совершение действия зависят от рандомно выпавшего числа

У каждого существа есть цикл жизни. У животных он зависит от насыщения. При 0 насыщении объект умирает.

Так же, на протяжении всей симуляции ведется статистика острова.

<h3><a>Доступны операции:</a></h3>

* Создание острова с рандомными параметрами
* Создание острова с настраиваемыми параметрами
* Получение данных о состоянии острова

<h3><a>Сборка проекта: </a></h3>

```$ cd island 1.0```
<h3 ><a>Запуск проекта:</a></h3>
```$ java -jar .out/artifacts/island_1_0/island 1.0.jar```

<h3 ><a>Краткое описание классов</a></h3>
>В корневом пакете ```ua.com.javarush.shestakova.Island``` находится:
- класс ```App``` - точка входа в приложение ```ua.com.shestakova.island.App```
- класс ```Dialod``` - строит интерактивный интерфейс для пользователя
- класс ```Statistics``` - обновляет и собирает статистику по полю

>В пакете ```resources``` находятся:
- файл ```settingIsland.properties``` - содержит основные настройки острова
- файл ```allAnimal.json``` - содержит данные по всем возможным на локации животным

>В пакете ```creature``` находятся все виды существ острова
- класс-Родитель ```Creature``` - содержит общие поля и методы всех существ
```ua.com.shestakova.island.creature.Сreature```
- классы ```Animal``` и ```Plant``` - наследники ```Creature``` содержат более 
подробные характеристики для животных и растений
- классы ```Herbivore``` и ```Predator``` - в свою очередь наследники ```Animal```,
и содержат еще более подробные характеристики данных типов животных

- В подПакетах ```herbivore``` и ```predator```
находятся все классы животных, потомков травоядных и хищников, со своими оригинальными 
параметрами

>В пакете ```constructorGame``` - классы для создания локаций острова:
- класс ```Island``` - создание и заполнения поля локациями
- класс ```Location``` - заполнение локаций животными и растениями
- класс ```Parser``` - содержит методы для считывания и записи в документы пользовательских настроек
- класс ```Tools``` - хранит карту всех возможных животных и несколько дополнительных 
инструментов для работы с простыми числами

>В пакете ```performingActions``` - основная логика поведения объектов
- класс ```LifeTime``` - жизненный цикл существа

- 
>В пакете ```exceptions``` находятся классы:
- класс ```ParsingExceptions``` - ошибки чтения/записи в файл
- класс ```CreateException``` - ошибки создания существа/локации
- класс ```ThreadsException```
- 
<h3><a>Пользовательский интерфейс</a></h3>
  Диалог с пользователем и получение от него (при выборе) настроек поля.
  По ходу симуляции, предоставляется 2 варианта выбора. Это должны быть числа 1 или 2
Для старта необходимо ввести любую строку и нажать клавишу "Enter"
При запуске симуляции выводятся данные об изменениях животных на острове (количество рожденных и умерших)

<h3><a>Технологии в проекте</a></h3>

Симуляция построена на основе принципов ООП:
- абстракция - каждый класс объектов имеет минимальный набор полей и методов для определенных задач
- инкапсуляция - "служебные" данные и методы скрыты под приватным модификатором
- наследование - все существа наследуются от главного класса (животные имеют так же "промежуточных родителей")
- полиморфизм - множество методов (классов Statistics, Creature, Tools и др.) используются не единоразово

Минимально используется так же: 
- чтение данных из файла properties, запись в файл JSON
- Stream API
- ExecutorService (пакет java.util.concurrent)
- Reflection API
- Maven (Lombok и JColor)

### Примеры работы программы:
```
Hello) It's a life simulation on the island.  
Do you want to start process with auto-settings? 
Please, enter number - 1 
Do you want to change settings of simulation? 
So, enter number - 2 
```
```
[🐃][🦌][🐛][🐛][🐗][🐺][🦌][🦅][🦌][🦊]
[🦊][🐗][🐛][🐃][🐍][🐑][🐃][🦌][🦊][🐻]
[🐑][🌱][🦅][🦅][🦆][🦊][🐍][🌱][🦆][🐑]
[🦅][🐇][🌱][🐍][🐁][🐎][🐍][🐎][🐁][🐍]
[🐗][🐃][🐇][🦅][🐗][🐺][🐇][🌱][🦅][🦊]
[🦆][ ][🐺][🐗][🦅][ ][🐇][🐻][🐑][🦌]
[🐃][🐺][🦊][🐃][🐁][🐺][🦆][🐍][🐛][🐑]
[🐗][🐐][🐺][🦅][🦊][🐐][🐎][🦌][🦌][🐺]
[🐛][🐃][🌱][🦅][🐍][🐍][🦌][🐇][🦆][🦅]
[🐁][🐺][🐁][🐃][🌱][🐁][🦌][🐻][🦆][🦅]
```
```
Simulation will be finished, in 10 days
You'll see statistics information every day
To run the simulation, enter any text and click ENTER
старт
It is day number 1
From start of simulation 0 creatures died
From start of simulation 106 creatures born
It is day number 2
From start of simulation 96 creatures died
From start of simulation 179 creatures born
```
```
The island got bigger!
Lots of plants now.
Their number has increased by 245
Your island has become more dangerous, there are 1843 more predators!
But, there is enough food for them too) Count of herbivores now: 791
In the beginning, it was the most: Boa
In the ending, it was the most: Eagle
If you run it again, the results may be different! 😏
```
