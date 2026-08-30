#include <iostream>
using namespace std;

bool makeString(char *s, int size){
    static int num = 1;
    if(num > 12 || num > size - 1){
        return false;
    }
    
    char temp[] = "Hello World!";
    strncpy(s, temp, num);
    s[num] = '\0';
    num++;
    return true;
}

int main(){
    char s[13];
    while(makeString(s,13)){
        cout << s << endl;
    }
    return 0;
}
