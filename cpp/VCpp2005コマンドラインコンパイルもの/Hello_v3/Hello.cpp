#include <iostream>
using namespace std;

const int temp_max_size = 13;
int temp_size = 13;
char temp[temp_max_size];

bool makeString(char *s, int &cr_num, const int size){
    static int num = 1;
    if(num > temp_size - 1 || num > size - 1){
        return false;
    }
    
    strncpy(s, temp, num);
    s[num] = '\0';
    cr_num = num;
    num++;
    return true;
}

bool makeString(char *s, const int size){
    int cr_num = 0;
    return makeString(s, cr_num, size);
}

void showString(){
    const int n = 13;
    char s[n];
    while(makeString(s, n)){
        cout << s << endl;
    }
}

void inputString(){
    cout << "12•¶ŽšˆÈ“à‚Ì•¶Žš—ñ‚ð“ü—Í‚µ‚Ä‚­‚¾‚³‚¢B" << endl;
//    cin >> temp;

    int i = 0;
    while(cin.get(temp[i])){
        if(temp[i] == '\n' || i == temp_max_size - 1){
            break;
        }
        i++;
    }
    temp[i] = '\0';
    temp_size = i + 1;
}

int main(){
    strcpy(temp, "Hello World!");
    temp_size = 13;
    inputString();
    showString();
    return 0;
}
