// 配列と参照.cpp : コンソール アプリケーションのエントリ ポイントを定義します。
//

#include "stdafx.h"
#include <iostream>
using namespace std;

void disp(const char *string){
	cout << string << endl;
}

int _tmain(int argc, _TCHAR* argv[])
{
	char string[] = "TestString!";
	disp(string);

	return 0;
}
