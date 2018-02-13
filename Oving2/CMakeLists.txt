cmake_minimum_required(VERSION 2.8)

project(Nettverksprogrammering)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -fopenmp -std=c++1y -Wall -Wextra")

find_package(Threads REQUIRED)

add_executable(Oving2 main.cpp)


target_link_libraries(Oving2 ${CMAKE_THREAD_LIBS_INIT})

