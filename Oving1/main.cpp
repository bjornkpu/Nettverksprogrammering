#include "prim.hpp"
#include "tests/test.cpp"  //should use header file, but small project.
#include <iostream>
#include <thread>
#include <vector>
#include <mutex>
#include <atomic>

using namespace std;
atomic<int> toCheck;    //the number the worker is getting
vector<int> primes; //the list of prime numbers
mutex primeLock;

bool testIsSorted(vector<int> primes); //To run tests

//giving the worker a number to check
//skipping the even numbers
int get_next_number(){
  int number = toCheck;
  toCheck += 2;
  return number;
}

//the work each thread is given
//getting a number, checking it, and apends if true.
void worker(int end){
  while(true){
    int num = get_next_number();
    if (num > end ) break;
    if (isPrim(num) == 1) {
      primeLock.lock();  //Locking the vector to prevent crashing
      primes.emplace_back(num);  //places the number in the vector
      primeLock.unlock();  //unlocks the vector again
    }
  }
}

//setup start, and init threads.
void finnPrim(int start, int end, int num_threads){
  if (start <= 2){ //checking and setting start
    start = 3;
    primes.emplace_back(2);
  }
  //Setting start to the first odd number,
  //all even numbers can be devided by 2
  else if (start % 2 == 0 ) start +=1;
  
  //setting the first number a worker/thread should check.
  toCheck = start; 
  
  vector<thread> threads; //list of threads
  for(int i = 0; i < num_threads; i++) {
    threads.emplace_back([end] {
      worker(end); //giving the threads work
    });
  }
  for(auto &thread : threads){
    thread.join();  //Gather here my minnions!
  }
};

int main() {
  int start   = 0;    //the start number
  int end     = 100;  //the end number
  int threads = 4;    //the amount of threads
  finnPrim(start, end, threads);
  for(size_t i = 0; i < primes.size(); i++){
    cout << primes[i]<< endl;  //printing the primes
  }
  //running a quick test to check if it's sorted and no duplicates. 
  string res = (testIsSorted(primes)) ? "true":"false";
  cout << "Test was " << res << endl;
}
