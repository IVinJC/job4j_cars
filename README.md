# Проект сайт по продаже машин

На сайте должны быть объявления. 
В объявлении должно быть: описание, марка машины, тип кузова, фото.
Объявление имеет статус продано или нет.

На главной странице можно увидеть все объявления
И ссылка на создание топика.
![](src/main/resources/images/Screenshot_2.png)

Кликнув название объявления, мы можем перейти на страницу подробного описания,
где есть возможность удалить объявление, перевести в статус продано и вернуться на начальную страницу
![](src/main/resources/images/Screenshot_5.png)

Страница создания объявления:
![](src/main/resources/images/Create.png)

## Запуск приложения через Docker Compose
* Клонируем проект git clone https://github.com/IVinJC/job4j_cars.git
* Собираем проект mvn install
* Создаем Dockerfile
_FROM openjdk
WORKDIR cars
ADD target/job4j_cars-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT java -jar app.jar_
* Запуск docker-compose up