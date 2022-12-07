Терминал: перейти в корневую папку проекта /envers_audit и выполнить накатку на БД:
`mvn -e org.liquibase:liquibase-maven-plugin:update -Dliquibase.driver=org.postgresql.Driver -Dliquibase.promptOnNonLocalDatabase=false -Dliquibase.propertyFile=src/main/resources/database/liquibase.properties -Dliquibase.username=liquibase -Dliquibase.password=liquibase -Dliquibase.url=jdbc:postgresql://localhost:5432/structural`
Будет создана схема, таблицы и создастся один клиент *John id=c2d29867-3d0b-d497-9191-18a9d8ee7830*

Postman: PUT запрос http://localhost:8080/customer/c2d29867-3d0b-d497-9191-18a9d8ee7830/name/Bill
В результате, *John* будет переименован в *Bill*. В логах при каждом обновлении имени будет выводиться полная история изменений данного клиента.

Можно добавить больше полей в таблицу `customer`. Чтобы эти поля отслеживались, также добавляем их в таблицу `customer_aud` (класс `CustomerHistory`).
