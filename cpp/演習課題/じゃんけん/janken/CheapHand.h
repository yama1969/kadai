#ifndef __CHEAPHAND__
#define __CHEAPHAND__

#include "Hand.h"
#include "Saikoro.h"

/******************************************************************************
 * クラスCheapHand
 * 　安易な手を出すじゃんけんの手
 * 　出す手は全くの乱数で決定する。
 ******************************************************************************/
class CheapHand : public Hand{
private:
	Saikoro *sai;					//手を決める時に使うサイコロオブジェクト
public:
	CheapHand();					//コンストラクタ
	~CheapHand();					//デストラクタ(ヒープ解放あり)
	int decideType();				//手を決定する
	void learnHand(Hand &preHand);	//相手の直前の手を学習する(実際は何もしない)
};

#endif