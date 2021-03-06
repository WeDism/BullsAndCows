[![Build Status](https://travis-ci.org/WeDism/BullsAndCows.svg?branch=master)](https://travis-ci.org/WeDism/BullsAndCows) 
[![codecov](https://codecov.io/gh/WeDism/BullsAndCows/branch/master/graph/badge.svg)](https://codecov.io/gh/WeDism/BullsAndCows)

# Веб приложение BullAndCows (Быки и Коровы) представляет из себя логическую игру. Протестировать игру можно по [ссылке](https://bulls-and-cows.herokuapp.com)
## Оглавление
1. [Об игре](#об-игре)
1. [Правила](#правила)
1. [Введение в архитектуру](#введение-в-архитектуру)
    1. [Диаграмма использования](#диаграмма-использования)
    1. [Логическая схема данных](#логическая-схема-данных)
    1. [Физическая схема данных](#физическая-схема-данных)
    1. [Руководство пользователя](#руководство-пользователя)
        1. [Регистрация и вход в систему](#регистрация-и-вход-в-систему)
        1. [Создание новой игры](#создание-новой-игры)
        1. [Ввод и отправка данных на сервер](#ввод-и-отправка-данных-на-сервер)
        1. [Завершение игры](#завершение-игры)
        1. [Возобновление игры](#возобновление-игры)
        1. [Просмотр истории игр](#просмотр-истории-игр)
        
        
        
## Об игре
Быки и коровы — логическая игра, в ходе которой за несколько попыток один из игроков должен определить, 
что задумал другой игрок. Варианты игры могут зависеть от типа отгадываемой последовательности — это могут быть 
числа, цвета, пиктограммы или слова. После каждой попытки задумавший игрок выставляет «оценку», указывая количество 
угаданного без совпадения с их позициями (количество «коров») и полных совпадений (количество «быков»). Роли участников 
игры не равнозначны — угадывающий должен анализировать сделанные попытки и полученные оценки, то есть его роль активна. 
Его партнёр лишь сравнивает очередной вариант с задуманным и выставляет оценку по формальным правилам, то есть его роль пассивна.

Смотреть подробнее по [ссылке](https://ru.wikipedia.org/wiki/Быки_и_коровы).

## Правила
В классическом варианте игра рассчитана на двух игроков. Каждый из игроков задумывает и записывает тайное 4-значное число с неповторяющимися цифрами. 
Игрок, который начинает игру по жребию, делает первую попытку отгадать число. Попытка — это 4-значное число с неповторяющимися цифрами, сообщаемое противнику. 
Противник сообщает в ответ, сколько цифр угадано без совпадения с их позициями в тайном числе (то есть количество коров) и сколько угадано вплоть до позиции 
в тайном числе (то есть количество быков). Например:

Задумано тайное число «3219».

Попытка: «2310».

Результат: две «коровы» (две цифры: «2» и «3» — угаданы на неверных позициях) и один «бык» (одна цифра «1» угадана вплоть до позиции).

Игроки делают попытки угадать по очереди. Побеждает тот, кто угадает число первым, при условии, что он не начинал игру. 
Если же отгадавший начинал игру — его противнику предоставляется последний шанс угадать последовательность.

Данная реализация это игра против компьютера, где игрок вводит комбинации одну за другой, пока не отгадает всю последовательность.

## Введение в архитектуру
Игра Быки и Коровы это классическое веб приложение представляющее из себя [трех уровневую архитектуру](https://ru.wikipedia.org/wiki/Трёхуровневая_архитектура).
 
### Диаграмма использования
[Диаграмма вариантов использования](https://ru.wikipedia.org/wiki/Диаграмма_прецедентов) (англ. use case diagram) в UML — диаграмма, отражающая отношения между актёрами и прецедентами 
и являющаяся составной частью модели прецедентов, позволяющей описать систему на концептуальном уровне.

На диаграмме ниже представлен актер Пользователь, который может совершать следующие действия, показанные на изоображении нижею

![Use case diagram](/design/UML/UseCase.png)

### Логическая схема данных
Логическая схема данных спроектированна по методологии [IDEF1x](http://www.interface.ru/home.asp?artId=1135).
В конкретной схеме базы данных используются внешние виды связи такие как многие ко многим. Если говорить более предметно,
то отношение таблицы Пользователь к таблице Игра является необязательная, неидентифицирующая. Так же отношение таблицы Игра к
таблице Шаг_Игры так же является необязательной, неидентифицирующей. Ниже представлена логическая схема БД.

![Logical DB Schema](/design/DB/DB.png)

### Физическая схема данных
Текущий проект был построен с использованием двух СУБД Postgres и H2. Физическая схема БД успешно протестированна на той паре СУБД, 
которые указаны выше по тексту. Физическая схема БД была разработана с использованием ограничений, которые помогают реализовывать целостность базы данных.
Ниже представлена физическая схема БД.

![PHYSICAL DB Schema](/readme_images/physical_scheme/public.png)

### Руководство пользователя
#### Регистрация и вход в систему
Для регистрации в системе требуется перейти в корневой каталог url ___'/'___ или по следующему url ___'/login'___ .
После перехода на страницу с авторизацией следует назать на вкладку ___${Sign up}___ после чего ввести валидные данные.
Следом после перезагрузки страницы после успешной регистрации появится сообщающее об успешном вводе данных.
Ниже представлена анимация, которая показывает успешные шаги.
![SIGN IN AND SIGN UP](/readme_images/sigin_in_and_sigin_up/sign_in_and_sign_up.gif)

#### Создание новой игры
Зарегистрируйтесь и авторизируйтись в приложении. Далее появится страничка состоящая из трех пунктов ___${Resume game}___ ,
___${Start new game}___ , ___${View history games}___ . Если у вас нет начатой игры следует нажать на кнопку ___${Start new game}___ 
для начала новой игры.
Ниже представлена анимация, которая показывает процесс создания новой игры.
![START NEW GAME](/readme_images/start_new_game/start_new_game.gif)

#### Ввод и отправка данных на сервер
Ввод числа существляется с помощью выдвижных списков содержащих порядковые числа. После успешного выбора следует нажать 
кнопку ___${Send message}___ . Введеные данные отправятся на сервер, об успешности операции сообщит зеленое уведомление в 
правом углу страницы и ответное сообщение сервера сообщающее колличество быков и коров в обртаном сообщении. Следует учитывать 
возможные неудачи отсылки попытки угадать число. При полноценной работе системы существует две основные ощибки ввода сообщения, 
заключающиеся в повторении однозначных чисел и отсутствии выбора одного или нескольких однозначых чисел.
Ниже представлена анимация, которая показывает игровой процесс.
![PROCESS GAME](/readme_images/process_game/process_game.gif)
 
#### Завершение игры
Для завершения игры существует два пути. Первый путь это пройти игру одержав победу. Второй путь это вернуться в главное меню
нажав кнопку ___${Home}___ после чего нажать кнопку ___${New game}___ после чего будет создана новая игра с новой загадкой.

#### Возобновление игры
Чтобы возобновить игру следует перейти домой нажав кнопку ___${Home}___ . После успешного перехода в меню появится кнопка 
___${Resume game}___ , нажав на которую пользователь будет перенаправлен к последней незавершеной игре.
Ниже представлена анимация, которая показывает возобновление игры.
![Resume game](/readme_images/resume_game/resume_game.gif)

#### Просмотр истории игр
Для просмотра игры следует перейти домой нажав кнопку ___${Home}___ . После успешного перехода в меню появится кнопка 
___${View game history}___ , нажав на которую пользователь сможет перейти и посмотреть все завершенные игры и статистику по ним. 
Так же находясь на страничке с историей пользователю будет доступен рейтинг вычисляющийся по отношению всех завершенных игр к всем выполненым 
ходам к этим играм. Стоит отметить, что на страничке с историей пользователю доступна информация есть ди у текущего пользователя начатые игры.
Ниже представлена анимация, которая показывает переход к страничке с историей и информаций доступной на ней.
![GAME HISTORY](/readme_images/game_history/game_history.gif)
