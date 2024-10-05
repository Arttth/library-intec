#include <iostream>
#include <boost/json.hpp>
#include <string>
#include "Client.hpp"
#include "Menu.hpp"

using namespace std;

Client client("localhost", "8080");
bool isRunning = true;


/*
void printJSONArray(boost::json::array array, std::string titles[]) {
    std::cout << "id -- " << "title -- " << "author " << std::endl;
    if (sizeof(titles) != 0) {

    
        int n = sizeof(titles) / sizeof(titles[0]);
        for (int i = 0; i < n-1; ++i) {
            std::cout << titles[i] << " --- "; 
        }
        std::cout << titles[n-1] << std::endl;
    } 
    std::cout << "---------------" << std::endl;
    for (int i = 0; i < array.size(); ++i) {
        boost::json::value bookJV = array.at(i);
        std::cout 
            << "Номер: "
            << bookJV.at("id") 
            << "\nНазвание:"
            << bookJV.at("title")
            << "\nАвтор:"
            << bookJV.at("author")
            << "\n------------------"
            << std::endl;
    }
}
*/
void getAllBooks() {
    boost::json::value jv = boost::json::parse(client.getRequest("GET", "/books"));
    boost::json::array data = jv.as_array();
    std::cout << "id -- " << "title -- " << "author " << std::endl; 
    std::cout << "---------------" << std::endl;
    for (int i = 0; i < data.size(); ++i) {
        boost::json::value bookJV = data.at(i);
        std::cout 
            << "Номер: "
            << bookJV.at("id") 
            << "\nНазвание: "
            << bookJV.at("title")
            << "\nАвтор: "
            << bookJV.at("author")
            << "\nДоступна? "
            << (bookJV.at("available") == true ? "Да" : "Нет")
            << "\n------------------"
            << std::endl;
    } 
}

void getAllUsers() {
    boost::json::value jv = boost::json::parse(client.getRequest("GET", "/users")); 
    boost::json::array data = jv.as_array();
    std::cout << "id -- " << "name" << std::endl; 
    std::cout << "---------------" << std::endl;
    for (int i = 0; i < data.size(); ++i) {
        boost::json::value bookJV = data.at(i);
        std::cout 
            << "Номер: "
            << bookJV.at("id") 
            << "\nИмя:"
            << bookJV.at("name")
            << "\n------------------"
            << std::endl;
    }
}

void findBookByTitle() {
    
}

void findBookByAuthor() {

}

void registration() {
    std::string userName;
    do {
        std::cout << "Введите имя пользователя: ";
        std::cin >> userName;
    } while (userName == "");
    
    boost::json::value data = {
        {"name", userName}
    };
    
    cout << "log: " << client.postRequest("/users", data) << std::endl;

}

void borrowBook() {
    int userId;
    do {
        std::cout << "Введите номер пользователя: ";
        std::cin >> userId;
    } while (userId < 0);
    int bookId;
    do {
        std::cout << "Введите номер книги: ";
        std::cin >> bookId;
    } while (bookId < 0);
    
    std::string apiArg = "/borrow/" + std::to_string(userId) + "/" + std::to_string(bookId);
    cout << "log: " << client.postRequest(apiArg, nullptr) << std::endl;

}

void returnBook() {
    int userId;
    do {
        std::cout << "Введите номер пользователя: ";
        std::cin >> userId;
    } while (userId < 0);
    int bookId;
    do {
        std::cout << "Введите номер книги: ";
        std::cin >> bookId;
    } while (bookId < 0);
    
    std::string apiArg = "/return/" + std::to_string(userId) + "/" + std::to_string(bookId);
    cout << "log: " << client.postRequest(apiArg, nullptr) << std::endl;


}

void exitFromApp() {
    isRunning = false;
}

int main() {

    Menu menu("Библиотека");
    menu.addOption("Просмотреть все книги.", getAllBooks);
    menu.addOption("Просмотреть всех читателей.", getAllUsers);
    menu.addOption("Поиск книги по названию.", findBookByTitle);
    menu.addOption("Поиск книги по автору.", findBookByAuthor);
    menu.addOption("Регистрация пользователя.", registration);
    menu.addOption("Взять книгу.", borrowBook);
    menu.addOption("Вернуть книгу", returnBook);
    menu.addOption("Выйти", exitFromApp);
    while (isRunning) {
        menu.printMenu();
        menu.processMenu();
    }


    return 0;    
}

