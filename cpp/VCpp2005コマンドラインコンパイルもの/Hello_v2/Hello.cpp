#include <iostream>
using namespace std;

bool makeString(char *s, int &cr_num, const int size){
    static int num = 1;
    if(num > 12 || num > size - 1){
        return false;
    }
    
    char temp[] = "Hello World!";
    strncpy(s, temp, num);
    s[num] = '\0';
    cr_num = num;
    num++;
    return true;
}

bool makeString(char *s, const int size){
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
    const int n = 13;
    char s[n];
    int num = 0;
    while(makeString(s, num, n)){
        cout << num << ' ' << s << endl;
    }
    return 0;
}
