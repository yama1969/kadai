#ifndef __THINKHAND__
#define __THINKHAND__

#include "Hand.h"

/******************************************************************************
 * クラスThinkHand
 * 　考え深い手を出すじゃんけんの手
 * 　相手の手を２手までパターン解析し、それに従って自分の手を決める。
 ******************************************************************************/
class ThinkHand : public Hand{
private:
	int preType;							//相手の直前の手
	unsigned int pattern[TE_NUM][TE_NUM];	//相手の手パターン
public:
	ThinkHand();							//コンストラクタ
	int decideType();						//手を決定する
	void learnHand(Hand &preHand);			//相手の直前の手を学習する
};

#endif