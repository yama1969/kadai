#ifndef __MONDAI__
#define __MONDAI__

/******************************************************************************
 * クラスQuestion
 * 　計算問題を出題するクラスの基底抽象クラス。
 ******************************************************************************/
class Question{
protected:
	int x;									//計算問題数値１
	int y;									//計算問題数値２
	int answer;								//正答
public:
	Question();
	virtual void showNextQuestion() = 0;	//新しい問題の作成と表示
	int isRightAnswer(int ans);				//正答と引数との比較
};

#endif