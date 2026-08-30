#ifndef YASAI
#define YASAI

#include "syohin.h"

class Yasai : public Syohin{
private:
    float sendo;                 //‘N“x
public:
    Yasai(int price, int net);
    void kanri();                //–ìØ‚ğŠÇ—‚·‚é
    int getPrice();              //‰¿Ši‚ğ“¾‚é
};

#endif
