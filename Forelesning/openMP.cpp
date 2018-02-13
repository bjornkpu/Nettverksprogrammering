#include <iostream>
#include <vector>

using namespace std;

int main() {
  
  vector<int> a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
  vector<int> b = {0, 1, 2, 0, 1, 2, 0, 1, 2, 0};
  vector<int> c(10);
  
  #pragma omp parallel for
  for(int i=0;i<10;++i) {
    c[i]=a[i]+b[i];
  }
  
  for(int i = 0; i < 10; i++)
    cout << c[i] <<" ";
  cout << endl;

// c: 0 2 4 3 5 7 6 8 10 9
}
// Compile with g++ and add the flag -fopenmp