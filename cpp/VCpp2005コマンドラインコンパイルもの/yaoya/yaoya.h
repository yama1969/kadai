#ifndef YAOYA
#define YAOYA

#include "Syohin.h"

class Yaoya{
private:
    int uriage;
    Syohin *syohin;
public:
    Yaoya();
    void make(int kind);
    void sell();
    void kanri();
    void gamen();
};

#endif
