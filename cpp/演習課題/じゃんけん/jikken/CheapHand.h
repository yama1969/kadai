#ifndef __CHEAPHAND__
#define __CHEAPHAND__

#include "Hand.h"
#include "Saikoro.h"

class CheapHand : public Hand{
private:
	Saikoro *sai;
public:
	CheapHand();
	~CheapHand();
	int decideType();
	void inputHand(Hand &preHand);
};

#endif