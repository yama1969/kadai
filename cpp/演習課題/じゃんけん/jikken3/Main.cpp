#include "ThinkHand.h"
#include "SimpleHand.h"
#include <iostream>
using namespace std;

int main(){
	ThinkHand th;
	SimpleHand sh;

	cout << "「考え深いコンピュータ」と「単純なコンピュータ」が1,000,000回じゃんけんをします。" << endl;
	cout << endl;
	cout << "じゃんけん始めました。" << endl;
	cout << endl;
	cout << "「考え深いコンピュータ」の成績" << endl;

	for(int i = 0; i < 10; i++){
		for(int j = 0; j < 100000; j++){
			th.decideType();
			sh.decideType();

			th.compareType(sh);

			th.inputHand(sh);
			sh.inputHand(th);
		}
		cout << ((i + 1) * 100000) << "回：" << th.getWin() << "勝" << th.getLose() << "敗" << th.getDraw() << "分" << endl;
	}
	cout << endl;
	cout << "じゃんけん終わりました。" << endl;
}