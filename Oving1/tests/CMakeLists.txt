include_directories(${CMAKE_SOURCE_DIR})

add_executable(testPrim testPrim.cpp)
target_link_libraries(testPrim ${CMAKE_THREAD_LIBS_INIT})
add_test(testPrim testPrim)