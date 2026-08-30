#include <ctime>
#include <iostream>
#include "ThinkHand.h"
using namespace std;

ThinkHand::ThinkHand(){
	srand((unsigned)time(NULL));
	preType = NON;
	for(int i = 0; i < TE_NUM; i++){
		for(int j = 0; j < TE_NUM; j++){
			pattern[i][j] = 0;
		}
	}
}

//この関数は、GUH,CHO,PAHが0から始まる連続した整数でないと正しく動作しない。
int ThinkHand::decideType(){
	if(preType == NON){
		handType = rand() % TE_NUM;
		return handType;
	}

	unsigned int total = 0;		//preType後の手の総数
	double rate[TE_NUM];		//preType後の各手の割合
	double p = 0.0;				//こちらの手の決定値

	for(int i = 0; i < TE_NUM; i++){
		total += pattern[preType][i];
	}

	for(int i = 0; i < TE_NUM; i++){
		rate[i] = (double)pattern[preType][i] / (double)total;
	}

	p = (double)rand() / 32768.0;

	for(int i = 0; i < TE_NUM; i++){
		if(p < rate[i]){
			handType = i - 1;
			if(handType < 0){
				handType = 2;
			}
			return handType;
		}
		p -= rate[i];
	}
	handType = GUH;				//ここには来ないはず
	return handType;
}

//この関数は、GUH,CHO,PAHが0から始まる連続した整数でないと正しく動作しない。
void ThinkHand::inputHand(Hand &preHand){
	int mateType = preHand.getType();
	if(preType != NON){
		pattern[preType][mateType]++;
	}
	preType = mateType;
}