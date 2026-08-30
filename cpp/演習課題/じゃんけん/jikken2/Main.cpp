#include "ThinkHand.h"
#include <iostream>
using namespace std;

int main(){
	ThinkHand th1;
	ThinkHand th2;

	cout << "「考え深いコンピュータ1」と「考え深いコンピュータ2」が1,000,000回じゃんけんをします。" << endl;
	cout << endl;
	cout << "じゃんけん始めました。" << endl;
	cout << endl;
	cout << "「考え深いコンピュータ1」の成績" << endl;

	for(int i = 0; i < 10; i++){
		for(int j = 0; j < 100000; j++){
			th1.decideType();
			th2.decideType();

			th1.compareType(th2);

			th1.inputHand(th2);
			th2.inputHand(th1);
		}
		cout << ((i + 1) * 100000) << "回：" << th1.getWin() << "勝" << th1.getLose() << "敗" << th1.getDraw() << "分" << endl;
	}
	cout << endl;
	cout << "じゃんけん終わりました。" << endl;
}