#ifndef __RESULT__
#define __RESULT__

/******************************************************************************
 * クラスResult
 * 　勝負分を記録するクラス
 ******************************************************************************/
class Result{
private:
	unsigned int win_num;		//勝の数
	unsigned int lose_num;		//負の数
	unsigned int draw_num;		//分の数
public:
	Result();					//コンストラクタ
	int win();					//勝を＋１する
	int lose();					//負を＋１する
	int draw();					//分を＋１する
	int getWin();				//勝の数を得る
	int getLose();				//負の数を得る
	int getDraw();				//分の数を得る
};

#endif