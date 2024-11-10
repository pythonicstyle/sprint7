# Sprint_7 Тестирование API
Проект в рамках курса Яндекс-практикум "Java QA Automatisation" - написать API тесты на приложение для курьеров [Яндекс. Самокат](https://qa-scooter.praktikum-services.ru/)
В проекте описаны основные тесты, такие как:
- создание курьера,
- логин курьера,
- удаление курьера,
- формирование заказов,
- получение списка заказов курьера.

## Содержание
- [Технологии](#Технологии)
- [Установка зависимостей](#Установка зависимостей)
- [Запуск API тестов](#Запуск API тестов)
- [Отчет Allure](#Отчет Allure)
- [Документация](#Документация)

## Технологии
- [Java 11](https://www.oracle.com/cis/java/technologies/javase/jdk11-archive-downloads.html)
- [Maven](https://mvnrepository.com/)
- [Git](https://github.com/)


## {#Установка зависимостей}
Добавьте файл pom.xml в корневую директорию проетка. Установите следующие зависимости:
Вертикальные линии обозначают столбцы.

| Библиотека          |  Версия  |  
|---------------------|:--------:| 
| junit               |  4.13.2  |
| rest-assured        |  4.4.0   |
| gson                |  2.8.9   |
| jackson-databind    | 2.13.4.1 |

Добавьте файл pom.xml следующие плагины:

| Плагин                | Версия |  
|-----------------------|:------:| 
| maven-compiler-plugin | 3.10.1 |
| maven-surefire-plugin | 2.22.2 |
| allure-maven          | 2.12.0 |


### Запуск API тестов
Чтобы запустить тесты, выполните команду:
```sh
mvn clean test
```

### Отчет Allure
Чтобы открыть отчет allure, выполните команду
```sh
allure serve target/surefire-reports/
```

## Документация
- [API-documentation](https://qa-scooter.praktikum-services.ru/docs/)