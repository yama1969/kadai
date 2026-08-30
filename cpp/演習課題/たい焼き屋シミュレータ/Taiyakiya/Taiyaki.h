#ifndef __TAIYAKI__
#define __TAIYAKI__

class Taiyaki{
private:
	int price;
public:
	Taiyaki(int price = 80);
	char *eat();
	int getPrice();
};

#endif