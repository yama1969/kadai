#ifndef __THINKHAND__
#define __THINKHAND__

#include "Hand.h"

class ThinkHand : public Hand{
private:
	int preType;							//相手の直前の手
	unsigned int pattern[TE_NUM][TE_NUM];	//相手の手パターン
public:
	ThinkHand();
	int decideType();
	void inputHand(Hand &preHnad);
};

#endif