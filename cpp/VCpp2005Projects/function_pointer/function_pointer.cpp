// function_pointer.cpp : コンソール アプリケーションのエントリ ポイントを定義します。
//

#include "stdafx.h"
#include <iostream>
using namespace std;

int func1(int x){
	cout << "func1が実行されました。" << endl;

	x++;
	return x;
}

int func2(int x){
	cout << "func2が実行されました。" << endl;

	x = x + 10;
	return x;
}

int _tmain(int argc, _TCHAR* argv[])
{
	int (*f)(int);							//ファンクションポインタの宣言の仕方はこれです！

	f = func1;
	cout << (*f)(1) << endl;				//そんでもって、呼び出しはこうする。

	f = func2;
	cout << (*f)(1) << endl;;

	return 0;
}

