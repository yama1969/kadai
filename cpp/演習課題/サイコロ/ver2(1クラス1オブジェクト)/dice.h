#ifndef _DICE

#define _DICE

class Dice{
private:
	int max;
	int num;
public:
	Dice(int max);
	int throwDice();
	int getNum();
	int compNum(int n);
};

#endif