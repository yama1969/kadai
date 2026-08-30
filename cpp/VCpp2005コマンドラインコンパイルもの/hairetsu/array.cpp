#include <iostream>
using namespace std;

class obj{
    static int n;
public:
    int a;
    obj();
};

int obj::n;

obj::obj(){
    cout << "オブジェクト生成！" << endl;
    n++;
    a = n;
}

int main(){
    obj o[3];
    
    for(int i = 0; i < 3; i++){
        cout << o[i].a << endl;
    }
}
