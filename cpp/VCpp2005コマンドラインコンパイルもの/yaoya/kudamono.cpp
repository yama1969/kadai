#include "kudamono.h"

Kudamono::Kudamono(int price, int net) : Syohin(price, net){
    amami = (float)1.0;
}

void Kudamono::kanri(){
    amami += (float)0.1;
}

int Kudamono::getPrice(){
    return (int)(amami * Syohin::getPrice());
}
