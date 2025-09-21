Currency Converter (Spring Boot)

Простое REST API для конвертации валют.
Проект написан на Spring Boot, использует встроенный сервис для расчёта валютных курсов.

Эндпоинты

Конвертация USD → EUR
POST http://localhost:8080/api/convert-usd-eur

Запрос:
{ "amount": 100 }

Ответ:
{
"amount": 100.0,
"from": "USD",
"to": "EUR",
"result": 85.13,
"message": "Success"
}

Конвертация EUR → USD
POST http://localhost:8080/api/convert-eur-usd

Запрос:
{ "amount": 100 }

Ответ:
{
"amount": 100.0,
"from": "EUR",
"to": "USD",
"result": 117.46,
"message": "Success"
}

Конвертация USD → любая валюта
POST http://localhost:8080/api/convert

Запрос:
{ "amount": 150, "currency": "GBP" }

Ответ:
{
"amount": 150.0,
"from": "USD",
"to": "GBP",
"result": 110.01,
"message": "Success"
}

Структура проекта

controller — контроллер с REST-эндпоинтами

dto — классы запросов и ответов (DTO)

service — бизнес-логика конвертации

application.properties — настройки приложения

Технологии

Java 17+

Spring Boot

Lombok

Maven

Запуск проекта

Клонировать репозиторий:
git clone https://github.com/USERNAME/currency-converter.git

Перейти в папку проекта:
cd currency-converter

Запустить приложение:
mvn spring-boot:run

Тестировать через Postman или HTTP-клиент (например, requests.http в IntelliJ IDEA).
