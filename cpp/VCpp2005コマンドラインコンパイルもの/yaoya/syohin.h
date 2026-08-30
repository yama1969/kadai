#ifndef SYOHIN
#define SYOHIN

class Syohin{     //商品クラス(抽象クラス)
private:
    int price;                     //単価[\/g]
    int net;                       //内容量[g]
public:
    Syohin(int price, int net);
    virtual void kanri() = 0;      //商品管理
    virtual int getPrice();        //価格取得
};

#endif
