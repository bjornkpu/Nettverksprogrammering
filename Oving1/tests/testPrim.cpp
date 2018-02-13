#include "prim.hpp"
#include <cassert>
//To test the isPrim method by it self
int main() {
  assert(isPrim(2));
  assert(isPrim(19));
  assert(isPrim(97));
  
  assert(!isPrim(1));
  assert(!isPrim(6));
  assert(!isPrim(20009));
  assert(!isPrim(-1));
}