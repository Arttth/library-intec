![image](https://github.com/user-attachments/assets/3c51378a-8a2b-4933-b1bf-e74bfa153533)# Описание
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
# USER API
![image](https://github.com/user-attachments/assets/7f99e3b8-1e33-4ba8-a133-715e3c2dee5b)
Примеры:
```
http://localhost:8080/users
```
![image](https://github.com/user-attachments/assets/7cdd688a-3fba-47bf-b005-1b6ae15deafd)
```
http://localhost:8080/return/1/4
```
![image](https://github.com/user-attachments/assets/bdec4873-9073-41f3-a067-8709c2a68d55)
# BOOK API
Примеры:
![image](https://github.com/user-attachments/assets/9c491b0d-57f8-4989-8664-c3e00e26bd6d)
```
[http://localhost:8080/return/1/4](http://localhost:8080/books/2
```
![image](https://github.com/user-attachments/assets/dad36acb-1054-459c-96fe-ba37a7afb2a2)




