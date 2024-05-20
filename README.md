# Демо проект

### с использованием kotlin + spring boot 3 + gradle

---

Проект был создан как кладезь имеющихся знаний. 

В нем используются последние <!-- возможно --> технологии ведения Java (Kotlin) проектов.
Тут на данный момент есть:
1) простейший контроллер (Rest API)
2) простейший сервис (обработка данных)
3) IT - integration test (интеграционные тесты)

Репозиторий будет увеличиваться и актуализироваться по мере ~~как мне не будет лень~~.

---

#### Обязательные версии зависимостей:

Java - 17

Все остальное автоматичсеки подкачивается.

---

#### Полезные команды:

Конечно они не полезные, т.к. все они есть на панели gradle, но основные:

```bash
./gradlew bootRun - локально запустить проект

./gradlew test - локально запустить выполнение тестов

./gradlew rV - проверить наличие вышедших версий зависимостей
```

---

### TODO
1) Добавить кубовые настройки
2) Добавить БД и работу с JPA + репозитории
3) Добавить работу с WEB (html - страницами)
4) Добавить чекеры
4) Возможно... работу с какой нить секьюрностью.

#### Note.
1) Хотелось бы еще версионизацию прикрутить... Подумаю.
1) вспомнить про профиля
---

---

Проект можно назвать "Pegasus Travel Planner". Это будет веб-приложение для планирования путешествий, разработанное с использованием Kotlin и Spring Boot. Вот краткое описание того, что он будет делать:

### Pegasus Travel Planner

#### Описание проекта
Pegasus Travel Planner — это веб-приложение, предназначенное для помощи пользователям в планировании их путешествий. С его помощью можно создать маршрут, забронировать отели и билеты на транспорт, а также получить рекомендации по достопримечательностям и ресторанам в выбранных городах.

#### Основные функции

1. **Регистрация и аутентификация пользователей**:
    - Пользователи могут создать учетную запись и войти в систему.
    - Реализована защита с помощью JWT токенов.

2. **Создание маршрута путешествия**:
    - Пользователь может добавлять города, которые планирует посетить.
    - Возможность указать даты пребывания в каждом городе.

3. **Поиск и бронирование отелей и билетов**:
    - Интеграция с API бронирования отелей и авиабилетов.
    - Пользователь может искать и бронировать отели и билеты напрямую из приложения.

4. **Рекомендации по достопримечательностям и ресторанам**:
    - На основе выбранных городов приложение предлагает список популярных достопримечательностей.
    - Возможность получения рекомендаций по ресторанам с учетом предпочтений пользователя.

5. **Отображение маршрута на карте**:
    - Интеграция с картографическими сервисами для визуализации маршрута путешествия.
    - Отображение всех мест, которые планирует посетить пользователь, на интерактивной карте.

6. **Социальные функции**:
    - Пользователи могут делиться своими маршрутами с друзьями.
    - Возможность оставлять отзывы и комментарии о посещенных местах.

#### Технологии и стек
- **Backend**:
    - Kotlin
    - Spring Boot
    - Spring Security (для аутентификации и авторизации)
    - Spring Data JPA (для работы с базой данных)
    - Hibernate (как ORM)
    - REST API (для взаимодействия с фронтендом и внешними сервисами)

- **Frontend**:
    - React.js или Angular (для создания динамичного пользовательского интерфейса)
    - Leaflet или Google Maps API (для работы с картами)

- **База данных**:
    - PostgreSQL (основная база данных для хранения данных о пользователях, маршрутах, бронированиях и рекомендациях)

- **Интеграции**:
    - API сервисов бронирования (например, Booking.com, Skyscanner)
    - API достопримечательностей (например, TripAdvisor)
    - Карты (Google Maps API или OpenStreetMap)

#### Пример сценария использования
1. Пользователь регистрируется на сайте Pegasus Travel Planner.
2. После входа в систему, он добавляет города, которые хочет посетить, и указывает даты пребывания.
3. Приложение предлагает пользователю варианты отелей и билетов, которые можно забронировать прямо через сайт.
4. Пользователь выбирает и бронирует понравившиеся варианты.
5. Приложение предоставляет рекомендации по достопримечательностям и ресторанам в выбранных городах.
6. Пользователь может просматривать и редактировать свой маршрут, а также делиться им с друзьями.

Таким образом, Pegasus Travel Planner поможет пользователям легко и удобно планировать свои путешествия, предоставляя все необходимые инструменты в одном месте.