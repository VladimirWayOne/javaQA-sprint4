# Проект 4 сприната

## Параметры запуска

- Java version: 11.0.20.1, vendor: Amazon.com Inc.
- JUnit 4.13.2
- Apache Maven 3.9.2

В файле настроек браузера [browser.properties](src%2Fmain%2Fresources%2Fbrowser.properties) необходимо указать
используемый драйвер
`browserName=chrome`
## Запуск тестов

### 1. Тесты вопросов о важном

``mvn clean test -Dtest=FaqTest``

### 2. Позитивный сценарий заказа

``mvn clean test -Dtest=OrderWorkFlowTest``

### 3. Тесты кнопок Яндекса и Самоката

``mvn clean test -Dtest=RedirectTest``
