cmake_minimum_required(VERSION 2.8)

project(Nettverksprogrammering)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++1y -Wall -Wextra")

find_package(Threads REQUIRED)

add_executable(Oving1 main.cpp)

target_link_libraries(Oving1 ${CMAKE_THREAD_LIBS_INIT})

enable_testing()
add_subdirectory(tests)
