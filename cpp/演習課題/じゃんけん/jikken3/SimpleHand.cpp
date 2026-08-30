#include <iostream>
#include "SimpleHand.h"
using namespace std;

int SimpleHand::decideType(){
	int s = 19;
	int r = rand() % 3;
	switch(r){
		case 0:
			s = 7;
			break;
		case 1:
			s = 13;
			break;
		case 2:
			s = 17;
			break;
		default:
			break;
	}
	handType = (handType + s) % TE_NUM;
	return handType;
}

void SimpleHand::inputHand(Hand &preHand){
}