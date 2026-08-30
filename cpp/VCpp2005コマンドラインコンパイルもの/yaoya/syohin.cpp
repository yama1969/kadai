#include "syohin.h"

Syohin::Syohin(int price, int net){
    if(price < 0){
        price = 0;
    }
    if(net < 0){
        net = 0;
    }
    this->price = price;
    this->net = net;
}

int Syohin::getPrice(){
    return price * net;
}
