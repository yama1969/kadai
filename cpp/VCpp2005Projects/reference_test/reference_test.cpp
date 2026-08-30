// reference_test.cpp : コンソール アプリケーションのエントリ ポイントを定義します。
//

#include "stdafx.h"
#include <iostream>
using namespace std;

const char *getString(){
	char *string = new char[12];
	strcpy(string,"TestString!");
	return string;
}

int _tmain(int argc, _TCHAR* argv[])
{
	const char *str = getString();
//	str[0] = 'A';
	cout << str << endl;

	return 0;
}

