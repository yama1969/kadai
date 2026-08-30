#ifndef __SIMPLEHAND__
#define __SIMPLEHAND__

#include "Hand.h"

class SimpleHand : public Hand{
public:
	int decideType();
	void inputHand(Hand &preHand);
};

#endif