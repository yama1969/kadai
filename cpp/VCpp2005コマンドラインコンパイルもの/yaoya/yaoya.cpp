#include <iostream>
#include <typeinfo>
using namespace std;

#include "yaoya.h"
#include "yasai.h"
#include "kudamono.h"

Yaoya::Yaoya(){
    uriage = 0;
    syohin = 0;
}

void Yaoya::make(int kind){
    if(syohin){
        return;
    }
    
    switch(kind){
        case 1:
            syohin = new Yasai(2,100);
            break;
        case 2:
            syohin = new Kudamono(3,100);
            break;
        default:
            break;
    }
}

void Yaoya::sell(){
    if(syohin){
        uriage += syohin->getPrice();
        delete syohin;
        syohin = 0;
    }
}

void Yaoya::kanri(){
    if(syohin){
        syohin->kanri();
    }
}

void Yaoya::gamen(){
    int cmd = 0;
    
    while(cmd != 4){
        cout << "1.商品を作る" << endl;
        cout << "2.商品を管理する" << endl;
        cout << "3.商品を売る" << endl;
        cout << "4.終了" << endl;
        cout << "cmd>";
        cin >> cmd;
        cout << endl;
        
        int kind = 0;
        switch(cmd){
            case 1:
                while(kind != 3){
                    cout << "1.野菜" << endl;
                    cout << "2.果物" << endl;
                    cout << "3.キャンセル" << endl;
                    cout << "cmd>";
                    cin >> kind;
                    cout << endl;
                    
                    switch(kind){
                        case 1:
                        case 2:
                            make(kind);
                            if(syohin){
                                cout << "現在の商品は" << typeid(*syohin).name() << "です。" << endl;
                            }
                            kind = 3;
                        case 3:
                        default:
                            break;
                    }
                }
                break;
            case 2:
                kanri();
                if(syohin){
                    if(typeid(*syohin) == typeid(Yasai)){
                        cout << "野菜の鮮度が上がりました。" << endl;
                    }else if(typeid(*syohin) == typeid(Kudamono)){
                        cout << "果物の甘みが上がりました。" << endl;
                    }
                }else{
                    cout << "商品がありません。" << endl;
                }
                break;
            case 3:
                sell();
                cout << "売上金額が" << uriage << "になりました。" << endl;
            case 4:
            default:
                break;
        }
        cout << endl;
    }
}
