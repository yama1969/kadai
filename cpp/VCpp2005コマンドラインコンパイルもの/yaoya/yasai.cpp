#include "yasai.h"

Yasai::Yasai(int price, int net) : Syohin(price, net){
    sendo = (float)1.0;
}

void Yasai::kanri(){
    sendo += (float)0.1;
}

int Yasai::getPrice(){
    return (int)(sendo * Syohin::getPrice());
}
