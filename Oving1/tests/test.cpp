#include <iostream>
#include <thread>
#include <vector>

using namespace std;

//Tests if the vector is sorted and has no duplicates
bool testIsSorted(vector<int> primes){
  int last    = 0;
  int current = 0;
  for(size_t i = 0; i < primes.size(); i++){
    current = primes[i];
    if (current <= last) return false;
    last = current;
  }
  return true;
}