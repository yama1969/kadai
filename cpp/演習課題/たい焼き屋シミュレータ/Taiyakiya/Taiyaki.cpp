#include <iostream>
#include "Taiyaki.h"
using namespace std;

Taiyaki::Taiyaki(int price){
	this->price = price;
}

char *Taiyaki::eat(){
	char *mess = new char[23];
	strcpy_s(mess, 23, "‚½‚¢Ä‚«‚ğH‚×‚Ü‚µ‚½B");
	return mess;
}

int Taiyaki::getPrice(){
	return price;
}