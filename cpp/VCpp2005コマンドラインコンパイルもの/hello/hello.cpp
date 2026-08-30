#include <iostream>
#include <stdlib.h>
#include <time.h>
#include <string.h>
using namespace std;

bool getHello(char *str, int size){
    if(size < 14){
        str = 0;
        return false;
    }
    
    int i = rand() * 10 / RAND_MAX;
    strcpy(str,"Hello World ");
    str[12] = (char)(i + 48);
    str[13] = '\0';
    return true;
}

int main(){
    srand((unsigned int)time(0));
    
    for(int i = 0; i < 10; i++){
        char str[15];
        if(getHello(str, 15)){
            cout << str << endl;
        }
    }
}
