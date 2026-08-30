// cin_test.cpp : コンソール アプリケーションのエントリ ポイントを定義します。
//

#include "stdafx.h"
#include <iostream>
using namespace std;

//cinを使って数値を入力させる関数。cinで入力する変数が数値ならば、数値しか入らない。
//それ以外が入力された場合は、cinの値が0となる。
int inputNum(){
	int num;
	
	cout << "数値 ? ";
	cin >> num;
	while(!cin){
		cin.clear();								//cinのうち、読み込まれた部分をクリアする。
		cin.ignore(INT_MAX,'\n');					//改行入力までを無効入力として取り消す。
		cout << "数値を入力してください" << endl;
		cout << "数値 ? ";
		cin >> num;
	}
	return num;
}

void inputString(char *string,int size){
	char ch;

	cout << "文字列 ? ";
	while(!cin){
	}
}


int _tmain(int argc, _TCHAR* argv[])
{
	int num1,num2;

	num1 = inputNum();
	num2 = inputNum();

	cout << num1 << " + ";
	if(num2 < 0){
		cout << "(";
	}
	cout << num2;
	if(num2 < 0){
		cout << ")";
	}
	cout << " = " << (num1 + num2) << endl;

	cout << "ちなみにINT_MAX = " << INT_MAX << endl;

	return 0;
}

