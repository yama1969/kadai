#include <iostream>
#include <fstream>
using namespace std;

int main(int ac, char *av[]){
  char c;
  ifstream fi;
  
  for(int i = 1; i < ac; i++){
    fi.open(av[i]);
    if(!fi){
      cerr << "can't open " << av[i] << endl;
      continue;
    }
    
    cout << "open " << av[i] << endl;
    while(fi.get(c)){
      cout << c;
    }
    
    fi.close();
  }
  
  return 0;
}
