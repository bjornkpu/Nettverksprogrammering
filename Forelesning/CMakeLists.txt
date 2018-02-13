cmake_minimum_required(VERSION 2.8)

project(Nettverksprogrammering)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -fopenmp -std=c++1y -Wall -Wextra")

find_package(Threads REQUIRED)


add_executable(main mainF2.cpp)
add_executable(openMp openMP.cpp)

target_link_libraries(main ${CMAKE_THREAD_LIBS_INIT})
target_link_libraries(openMp ${CMAKE_THREAD_LIBS_INIT})
