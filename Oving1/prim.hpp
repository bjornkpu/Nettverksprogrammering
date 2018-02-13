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