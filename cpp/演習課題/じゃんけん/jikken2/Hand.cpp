#include "Hand.h"

Hand::Hand(){
	handType = NON;
	result = new Result();
}

Hand::~Hand(){
	delete result;
}

int Hand::compareType(Hand &mate){
	//勝敗テーブル         相手 GUH   CHO   PAH
	int dat[TE_NUM][TE_NUM] = {{AIKO, KATI, MAKE},   //GUH 自分
	                           {MAKE, AIKO, KATI},   //CHO  ↓
	                           {KATI, MAKE, AIKO}};  //PAH

	//GUH=0,CHO=1,PAH=2の値を知った上での処理となっているが、
	//この定数を定義しているクラス内の関数なので、良しとする。
	
	int res = dat[handType][mate.getType()];
	switch(res){
		case KATI:
			result->win();
			break;
		case AIKO:
			result->draw();
			break;
		case MAKE:
			result->lose();
			break;
	}
	return res;
}

int Hand::getType(){
	return handType;
}

int Hand::getWin(){
	return result->getWin();
}

int Hand::getLose(){
	return result->getLose();
}

int Hand::getDraw(){
	return result->getDraw();
}