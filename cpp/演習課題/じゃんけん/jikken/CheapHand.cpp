#include "CheapHand.h"
#include "Saikoro.h"

CheapHand::CheapHand(){
	sai = new Saikoro(TE_NUM);
}

CheapHand::~CheapHand(){
	delete sai;
}

int CheapHand::decideType(){
	int s = sai->throwSaikoro();
	switch(s){
		case 1:
			handType = GUH;
			break;
		case 2:
			handType = CHO;
			break;
		case 3:
			handType = PAH;
			break;
		default:
			handType = GUH;		//‚±‚±‚É‚Í—ˆ‚È‚¢‚Í‚¸
			break;
	}
	return handType;
}

void CheapHand::inputHand(Hand &preHand){
}