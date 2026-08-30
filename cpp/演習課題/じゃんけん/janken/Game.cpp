/******************************************************************************
 * クラスGame
 * 　じゃんけんゲームを実行するクラス
 ******************************************************************************/
#include <iostream>
#include "Game.h"
#include "CheapHand.h"
#include "ThinkHand.h"
#include "UserHand.h"
#include "Hand.h"
using namespace std;

/******************************************************************************
 * 関数play()
 * 　じゃんけんゲームを行う
 * 引数：なし
 * 戻値：なし
 ******************************************************************************/
void Game::play(){
	CheapHand *ch = new CheapHand();	//安易なコンピュータとしての手
	ThinkHand *th = new ThinkHand();	//考え深いコンピュータとしての手
	UserHand *uh = new UserHand();		//ユーザの手
	Hand *nh = NULL;					//コンピュータの手のうち、稼働中のもの
	int comm = 0;						//コマンド入力用

	while(1){
		//対戦相手の選択
		cout << "じゃんけんをします。相手を選んでください。" << endl;
		cout << "１．安易なコンピュータ" << endl;
		cout << "２．考え深いコンピュータ" << endl;
		cout << "３．プログラム終了" << endl;
		cout << "番号=> ";

		do{
			cin >> comm;
			cout << endl;

			switch(comm){
				case 1:
					cout << "安易なコンピュータが相手をします。" << endl;
					nh = ch;
					break;
				case 2:
					cout << "考え深いコンピュータが相手をします。" << endl;
					nh = th;
					break;
				case 3:
					return;		//ゲーム終了
					break;
				default:
					comm = 0;
					cout << "番号=> ";
			}
		}while(comm == 0);

		cout << endl;

		//じゃんけんの実行
		for(int cnt = 0; cnt < MAX_COUNT; cnt++){
			cout << "【" << (cnt + 1) << "回目】" << endl;

			uh->decideType();					//ユーザの手の決定
			cout << "あなたは";
			showTypeString( uh->getType() );
			cout << "を出しました。" << endl;

			nh->decideType();					//コンピュータの手の決定
			cout << "コンピュータは";
			showTypeString( nh->getType() );
			cout << "を出しました。" << endl;

			switch(nh->compareHand(*uh)){		//判定
				case Hand::KATI:					//コンピュータが勝ちなのでユーザは負け
					cout << "あなたの負けです。" << endl;
					break;
				case Hand::MAKE:					//コンピュータが負けなのでユーザは勝ち
					cout << "あなたの勝ちです。" << endl;
					break;
				case Hand::AIKO:
					cout << "あいこです。" << endl;
					break;
				default:						//判定不能のときは何もしない
					break;
			}
			cout << endl;
			nh->learnHand(*uh);					//ユーザの手を学習
		}

		//成績表示
		cout << "【今までの成績】" << endl;
		if(nh == ch){
			cout << "安易なコンピュータとの勝負" << endl;
		}else{
			cout << "考え深いコンピュータとの勝負" << endl;
		}
		cout << "あなたは" << nh->getLose() << "勝" << nh->getWin() << "敗";
		cout << nh->getDraw() << "分でした。" << endl;
		cout << endl;
	}
}

//ここからprivate関数----------------------------------------------------------

/******************************************************************************
 * 関数showTypeString()
 * 　じゃんけんの手を画面表示する
 * 引数：int type : じゃんけんの手(GUH,CHO,PAH)
 * 戻値：なし
 ******************************************************************************/
void Game::showTypeString(int type){
	switch(type){
		case Hand::GUH:
			cout << "グー";
			break;
		case Hand::CHO:
			cout << "チョキ";
			break;
		case Hand::PAH:
			cout << "パー";
			break;
		default:
			break;
	}
}