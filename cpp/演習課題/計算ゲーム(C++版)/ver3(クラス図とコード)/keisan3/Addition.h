#ifndef __ADDITION__
#define __ADDITION__

#include "Question.h"

/******************************************************************************
 * クラスAddition
 * 　足し算問題を出題するクラス
 ******************************************************************************/
class Addition : public Question{
public:
	void showNextQuestion();
};

#endif