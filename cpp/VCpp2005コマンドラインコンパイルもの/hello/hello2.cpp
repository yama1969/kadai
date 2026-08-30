#include <iostream>
using namespace std;

bool getHello(char *str, int size){
    char hello[] = "Hello World!";
    static int end = 1;
    
    if(size < 13 || end > 12){
        str = 0;
        return false;
    }
    
    for(int i = 0; i < end; i++){
        str[i] = hello[i];
    }
    str[end] = '\0';
    end++;
    
    return true;
}

int main(){
    char str[13];
    while(getHello(str, 13)){
        cout << str << endl;
    }
}
