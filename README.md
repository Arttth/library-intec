# Описание
Тестовое задание "Библиотека"
# Зависимости
- Spring Boot 3.3.4
- JDK 21
- Spring Data JPA
- Spring WEB MVC
- PostgreSQL
- Gradle
- Lombok
# ER-диаграмма
![image](https://github.com/user-attachments/assets/567a3dc8-ee31-419f-a0d4-bd452ce3c457)
# Сборка серверной части
1. Установить зависимости.
2. Склонировать репозиторий.
```
git clone https://github.com/Arttth/library-intec
```
3. Создать базу данных library_intec и в файле src/main/resources/application.yaml указать name и password для входа в БД.
4. Запустить скрипты из src/main/resources/sql для БД (сперва init, затем data).
5. Выполните
6. ```
   gradle bootJar
   ```
7. Затем можно запустить JAR-файл
```
java -jar build/libs/library-intec-0.0.1-SNAPSHOT.jar
```
# Сборка клиентской части
1. Выполните
```
cd client-library/client-build
sudo chmod +x client-library/client-build/client-build.sh
./client-library/client-build/client-build.sh
```
или 
```
cd client-library/client-build
sudo chmod +x client-library/client-build/client-build.sh
cmake ..
make
```
2. Запустите клиент
```
./client_library
```




