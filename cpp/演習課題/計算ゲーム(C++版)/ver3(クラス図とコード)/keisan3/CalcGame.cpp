/******************************************************************************
 * クラスCalcGame
 * 　計算ゲーム本体
 ******************************************************************************/
#include <iostream>
#include <ctime>
#include "CalcGame.h"
#include "Addition.h"
#include "Subtraction.h"
#include "Multiplication.h"
#include "Division.h"
#include "Score.h"
using namespace std;

/******************************************************************************
 * コンストラクタ
 * 　メンバ初期化
 * 引数：なし
 ******************************************************************************/
CalcGame::CalcGame(){
	quest[0] = new Addition();
	quest[1] = new Subtraction();
	quest[2] = new Multiplication();
	quest[3] = new Division();

	for(int i = 0; i < KIND_NUM; i++){
		queran[i] = new QuestionWithRank(*quest[i]);
	}
}

/******************************************************************************
 * デストラクタ
 * 　ヒープ上のオブジェクト開放
 * 引数：なし
 ******************************************************************************/
CalcGame::~CalcGame(){
	for(int i = 0; i < KIND_NUM; i++){
		delete queran[i];
		delete quest[i];
	}
}

/******************************************************************************
 * 関数startGame()
 * 　計算ゲームを実行する
 * 引数：なし
 * 戻値：なし
 ******************************************************************************/
void CalcGame::startGame(){
	int comm = 0;								//ユーザのコマンド
	QuestionWithRank *crrentQuest;				//現在の問題

	while(1){
		comm = showInitMenu();					//初期メニュー表示と入力
		if(comm == 5){
			return;
		}

												//ゲーム開始
		crrentQuest = queran[comm - 1];				//現在の問題をセット
		showReady(comm);							//問題表示前の表示

		time_t start = time(NULL);					//開始時刻
		int r = 0;									//正答数

		for(int i = 0; i < QUEST_NUM; i++){			//問題表示と入力
			crrentQuest->showNextQuestion();
			int ans = inputNum();
			if( crrentQuest->isRightAnswer(ans) ){
				cout << "正解!" << endl;
				r++;
			}else{
				cout << "まちがい!" << endl;
			}
		}

		time_t end = time(NULL);					//終了時刻
		int t = (int)(end - start);					//所要時間

		Score *sp = new Score();
		sp->calcScore(t, r, QUEST_NUM);			//スコア計算
		showResult(r, t, *sp);					//結果表示

		int rank = crrentQuest->addScore(*sp);	//ランキング登録
		showRank(rank, *sp);
		crrentQuest->showRanking();				//ランキング表示

		cout << endl << endl;
	}
}

//---ここからprivate関数-------------------------------------------------------

/******************************************************************************
 * 関数inputNum()
 * 　数値を入力する
 * 引数：なし
 * 戻値：int ユーザが入力した数値
 ******************************************************************************/
int CalcGame::inputNum(){
	while( !isdigit( cin.peek() ) ){
		cin.get();
	}
	int num = 0;
	cin >> ws >> num;

	return num;
}


/******************************************************************************
 * 関数showInitMenu()
 * 　初期メニューを表示し、番号入力する
 * 引数：なし
 * 戻値：int ユーザが選択した番号
 ******************************************************************************/
int CalcGame::showInitMenu(){
	while(1){
		cout << "計算ゲーム" << endl;
		cout << "１．足し算" << endl;
		cout << "２．引き算" << endl;
		cout << "３．掛け算" << endl;
		cout << "４．割り算" << endl;
		cout << "５．終了"   << endl;
		cout << "どれをやりますか(1～5) > ";

		int comm = inputNum();
		switch(comm){
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
				cout << endl;
				return comm;
				break;
			default:
				cout << endl;
				break;
		}
	}
}

/******************************************************************************
 * 関数showReady()
 * 　問題開始メッセージを表示し、確認入力する
 * 引数：int quest : 問題の種類番号
 * 戻値：なし
 ******************************************************************************/
void CalcGame::showReady(int quest){
	cout << "これから";
	switch(quest){
		case 1:
			cout << "足し算";
			break;
		case 2:
			cout << "引き算";
			break;
		case 3:
			cout << "掛け算";
			break;
		case 4:
			cout << "割り算";
			break;
		default:
			cout << "？？？";
			break;
	}
	cout << "の問題を" << QUEST_NUM << "問出します。" << endl;
	cout << "0を入力してください！";
	int non = 0;
	cin >> ws >> non;
	
	cout << endl;
}

/******************************************************************************
 * 関数showResult()
 * 　結果を表示する
 * 引数：int right : 正答数
 * 　　　int time  : 所要時間
 * 　　　Score &s  : スコアオブジェクト
 * 戻値：なし
 ******************************************************************************/
void CalcGame::showResult(int right, int time, Score &s){
	cout << endl;
	cout << "正答数：" << right << "問／" << QUEST_NUM << "問" << endl;
	cout << "時間　：" << time << "秒" << endl;
	cout << "スコア：" << s.getScore() << endl;
	cout << endl;
}

/******************************************************************************
 * 関数showRank()
 * 　結果を表示する
 * 引数：int rank : 順位
 * 　　　Score &s : スコアオブジェクト
 * 戻値：なし
 ******************************************************************************/
void CalcGame::showRank(int rank, Score &s){
	if(rank == 0){
		cout << "ランク外です。ザンネン！" << endl;
	}else{
		cout << "第" << rank << "位にランクインしました。" << endl;
		cout << "名前を入力してください。" << endl;
		s.inputName();
	}
	cout << endl;
}