#include "ThinkHand.h"
#include "CheapHand.h"
#include <iostream>
using namespace std;

/******************************************************************************
 * 関数main()
 * 　手の決定アルゴリズムの違うHandを利用した、じゃんけん対戦実験
 * 引数：なし
 * 戻値：int 常に0
 ******************************************************************************/
int main(){
	CheapHand ch;
	ThinkHand th;

	cout << "「安易なコンピュータ」と「考え深いコンピュータ」が";
	cout << "1,000,000回じゃんけんをします。" << endl;
	cout << endl;
	cout << "じゃんけん始めました。" << endl;
	cout << endl;
	cout << "「考え深いコンピュータ」の成績" << endl;

	for(int i = 0; i < 10; i++){
		for(int j = 0; j < 100000; j++){
			ch.decideType();				//手の決定
			th.decideType();

			th.compareType(ch);				//勝敗判定

			ch.inputHand(th);				//互いの手を学習
			th.inputHand(ch);
		}
		cout << ((i + 1) * 100000) << "回：" << th.getWin() << "勝";
		cout << th.getLose() << "敗" << th.getDraw() << "分" << endl;
	}
	cout << endl;
	cout << "じゃんけん終わりました。" << endl;

	return 0;
}