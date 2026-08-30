#ifndef __SHOP__
#define __SHOP__

#include "Taiyaki.h"

#define MAX_STOCK 5

class Shop{
private:
	Taiyaki **stock;
	int stock_num;
	int make_pt;
	int sell_pt;
	int sales;
public:
	Shop();
	~Shop();
	int make();
	Taiyaki *sell();
	int getSales();
};

#endif