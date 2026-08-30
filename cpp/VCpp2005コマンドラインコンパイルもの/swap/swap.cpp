#include <iostream>
using namespace std;

void swaparg(int &a, int &b){
    int c;
    c = a;
    a = b;
    b = c;
}

int main(){
    double a=3.8, b=5.2;
    cout << "a=" << a << " b=" << b << endl;
    swaparg(a,b);
    cout << "a=" << a << " b=" << b << endl;
}
