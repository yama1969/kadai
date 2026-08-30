#ifndef __HAND__
#define __HAND__

#include "Result.h"

class Hand{
private:
	Result *result;
protected:
	int handType;
public:
	//このクラスで用いる定数の定義
	static const int TE_NUM = 3;	//手の種類数
	static const int NON    = -1;	//手の指定なし
	static const int GUH    = 0;	//グー
	static const int CHO    = 1;	//チョキ
	static const int PAH    = 2;	//パー
	static const int KATI   = 1;	//勝ち
	static const int AIKO   = 0;	//あいこ
	static const int MAKE   = -1;	//負け

	Hand();
	~Hand();
	virtual int decideType() = 0;
	virtual void inputHand(Hand &preHand) = 0;
	int getType();
	int compareType(Hand &mate);
	int getWin();
	int getLose();
	int getDraw();
};

#endif