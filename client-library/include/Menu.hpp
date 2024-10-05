#include <string>
#include <vector>
#include <iostream>

typedef void (*MenuProcessFunction)(void);

class Menu 
{
private:
    struct Option {
        std::string name;
        MenuProcessFunction function;
        Option(std::string name, MenuProcessFunction func) {
            this->name = name;
            this->function = func;
        }
    };

    std::string title;
    std::vector<Option> options;
   
public:
    Menu(std::string title)
    {
        this->title = title;
    }
    void setTitle(std::string title) 
    {
        this->title = title;
    }

    void printMenu() {
        std::cout << title << std::endl;
        for (int i = 0; i < options.size(); ++i) 
        {
            std::cout << std::to_string(i) + ". "<<  options[i].name <<  std::endl; 
        }    
    }

    bool intSigned(const std::string& s)
    {
        size_t offset=0;
        if(s[offset]=='-')
            ++offset;
        return s.find_first_not_of("0123456789", offset) == std::string::npos;
    }

    bool inRange(int n, int a, int b) {
        return n >= a && n <= b;
    }

    void processMenu() {
        std::string choice;
        do {
            std::cout << "Введите число из меню:" << std::endl;
            std::cin >> choice;
        } while (!intSigned(choice) || !inRange(std::stoi(choice), 0, options.size()-1));
        
        processOptionFunc(std::stoi(choice));
    }

    void addOption(std::string name, MenuProcessFunction func) 
    {
        options.push_back(Option(name, func));
    }
    
    bool processOptionFunc(int optionId) 
    {
        if (optionId < 0 || optionId >= options.size()) 
        {
           return false; 
        }
        options[optionId].function();
        return true;
    }

};
