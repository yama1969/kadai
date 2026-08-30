#include <iostream>										//入出力：cin,cout
#include <ctime>										//時刻関連：time()関数など
#include <cmath>										//数学関連：floor()関数など
using namespace std;

/**********************************************************************************************
 * 足し算ゲームプログラム
 * 関数main()
 **********************************************************************************************/
int main(){
	//乱数シードを時刻によって変更
	srand((unsigned)time(NULL));

	//最初の画面表示
	char non = 0;
	cout << "これから計算問題を10問出します。" << endl;
	cout << "準備ができたらEnterを押してください。";
	cin >> noskipws >> non;								//noskipwsは先頭空白を除く。
														//それによりEnter空打ち入力ができる。

	time_t start_t = time(NULL);						//開始時刻を得る

	//足し算問題を10回表示
	int hit = 0;
	int penalty = 0;
	for(int i = 0; i < 10; i++){
		int z = 0;
		int x = rand() % 10;							//rand()は0～32767
		int y = rand() % 10;
		cout << "第" << i + 1 << "問目" << endl;
		cout << x << " + " << y << " = ";

		while(!isdigit(cin.peek())){					//数値以外の入力を読み飛ばし
			cin.get();
		}
		cin >> ws >> z;

		if(z == x + y){
			cout << "あたり！" << endl;
			hit++;
		}else{
			cout << "はずれ。" << endl;
			penalty += 2;
		}
		cout << endl;
	}

	//終了後の処理
	time_t end_t = time(NULL);							//終了時刻を得る
	int t = (int)(end_t - start_t);						//時間計算
	float score = (float)(t + penalty) / (float)hit;	//スコア計算
	score = floor(score * (float)100) / (float)100.0;	//スコア丸め

	//結果表示
	cout << endl;
	cout << "正答は" << hit << "問でした。" << endl;
	cout << "時間は" << t << "秒でした。" << endl;
	cout << "得点は" << score << "でした。" << endl;
}