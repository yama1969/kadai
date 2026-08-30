#ifndef __USERHAND__
#define __USERHAND__

#include "Hand.h"

/******************************************************************************
 * クラスUserHand
 * 　ユーザのじゃんけんの手
 * 　アプリケーションユーザの入力によって、手を決定する。
 ******************************************************************************/
class UserHand : public Hand{
private:
public:
	int decideType();				//手の決定(=手の入力)
	void learnHand(Hand &preHand);	//相手の直前の手の学習(実際には何もしない)
};

#endif