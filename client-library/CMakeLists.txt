cmake_minimum_required(VERSION 3.10)
project(client_library_intec)

set(CMAKE_CXX_STANDART 11)

include_directories(include)

file(GLOB SOURCES "src/*.cpp")
add_executable(client_library ${SOURCES})
