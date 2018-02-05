#include <iostream>
#include <thread>
#include <vector>
#include "test.cpp"  //should use header file, but small project.


using namespace std;
int toCheck = 0;    //the number the worker is getting
vector<int> primes; //the list of prime numbers

bool testIsSorted(vector<int> primes); //To run tests

//giving the worker a number to check
//skipping the even numbers
int get_next_number(){
  int number = toCheck;
  toCheck += 2;
  return number;
}

//Checking if a given number is a prime number
//the higher the number the longer it takes.
bool isPrim(int n){
  if (n <= 1) return false;
  else if (n <= 3) return true;
  else if (n % 2 == 0 || n % 3 == 0) return false;
  int i = 5;
  while(i * i <= n){
    if(n % i == 0 || n % (i + 2) == 0) return false;
    i++;
  }
  return true;
}

//the work each thread is given
//getting a number, checking it, and apends if true.
void worker(int end){
  while(true){
    int num = get_next_number();
    if (num > end ) break;
    if (isPrim(num) == 1) primes.emplace_back(num);
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

//if Exit code: 139, run again.
//-this is a segmentation fault or "seg fault".
int main() {
  int start   = 0;    //the start number
  int end     = 100;  //the end number
  int threads = 10;    //the about of threads
  finnPrim(start, end, threads);
  for(size_t i = 0; i < primes.size(); i++){
    cout << primes[i] << endl;  //printing the primes
  }
  //running a quick test to check if it's sorted and no duplicates. 
  string res = (testIsSorted(primes) == 1) ? "true":"false";
  cout << "Test was " << res << endl;
}
