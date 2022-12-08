# Аудит записів в БД за допомогою Hibernate Envers

### Термінал
Перейти до головної папки проекту **/envers_audit** і виконати накатку на БД:

`mvn -e org.liquibase:liquibase-maven-plugin:update -Dliquibase.driver=org.postgresql.Driver -Dliquibase.promptOnNonLocalDatabase=false -Dliquibase.propertyFile=src/main/resources/database/liquibase.properties -Dliquibase.username=liquibase -Dliquibase.password=liquibase -Dliquibase.url=jdbc:postgresql://localhost:5432/structural`

Буде створена схема, таблиці та буде додано одного клієнта *John id=c2d29867-3d0b-d497-9191-18a9d8ee7830*

### Postman
PUT запит http://localhost:8080/customer/c2d29867-3d0b-d497-9191-18a9d8ee7830/name/Bill

В результаті, *John* буде перейменований на *Bill*. В логах при кожній операції буде виведена повна історія змін даного клієнта.

Можна додавати більше полів до таблиці `customer`. Аби зміни в цих полях відстежувалися, також додаємо їх до таблиці `customer_audit` (клас `CustomerHistory`).

На даний момент додано відстежувані поля `name` та `city`, і невідстежуване поле `age`. При оновленні `age` не створюються записи у таблицях `customer_audit` та `revision_info`

Реалізовано такі запити:

#### 1. Створення клієнта

POST http://localhost:8080/customer
Тіло запиту:
`{
   "name": "Jane",
   "age": "33",
   "city": "Kyiv"
}`

#### 2. Видалення клієнта

DELETE http://localhost:8080/customer/c2d29867-3d0b-d497-9191-18a9d8ee7830

#### 3. Зміна ім'я

PUT http://localhost:8080/customer/c2d29867-3d0b-d497-9191-18a9d8ee7830/name/Bill

#### 4. Зміна міста

PUT http://localhost:8080/customer/c2d29867-3d0b-d497-9191-18a9d8ee7830/city/Odesa

#### 5. Зміна віку

PUT http://localhost:8080/customer/c2d29867-3d0b-d497-9191-18a9d8ee7830/age/10

### Корисні джерела
1. https://www.bytefish.de/blog/hibernate_envers_versioning_and_auditing.html
2. https://github.com/bytefish/VersioningWithEnvers
3. https://docs.jboss.org/hibernate/orm/4.2/devguide/en-US/html/ch15.html#d5e4079
