#include <iostream>
#include "Shop.h"
#include "Taiyaki.h"

Shop::Shop(){
	stock = new Taiyaki *[MAX_STOCK];
	stock_num = 0;
	make_pt = 0;
	sell_pt = 0;
	sales = 0;
}

Shop::~Shop(){
	delete [] stock;
}

int Shop::make(){
	if(stock_num > MAX_STOCK - 1){
		return 0;
	}
	stock[make_pt] = new Taiyaki();
	make_pt = (make_pt + 1) % MAX_STOCK;
	stock_num++;
	return 1;
}

Taiyaki *Shop::sell(){
	if(stock_num < 1){
		return NULL;
	}
	Taiyaki *t = stock[sell_pt];
	stock[sell_pt] = NULL;
	sell_pt = (sell_pt + 1) % MAX_STOCK;
	stock_num--;
	sales += t->getPrice();
	return t;
}

int Shop::getSales(){
	return sales;
}