#ifndef KUDAMONO
#define KUDAMONO

#include "syohin.h"

class Kudamono : public Syohin{
private:
    float amami;                   //ŠÃ‚İ
public:
    Kudamono(int price, int net);
    void kanri();                  //ŠÇ—‚·‚é
    int getPrice();                //‰¿Ši‚ğ“¾‚é
};

#endif
