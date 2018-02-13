#include <iostream>
#include <atomic>

using namespace std;

int main() {
  
  //int n = 0;
  atomic<int> n;
  #pragma omp parallel for
  for(int i=0;i<1000;++i) {
    n++;
  }
  cout << n << endl;
}