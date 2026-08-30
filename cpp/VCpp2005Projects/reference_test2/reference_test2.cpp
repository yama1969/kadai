// reference_test2.cpp : コンソール アプリケーションのエントリ ポイントを定義します。
//

#include "stdafx.h"
#include <iostream>
using namespace std;

void setString(char *str,int size){
	strcpy(str,"TestString!");
}

int _tmain(int argc, _TCHAR* argv[])
{
	char str[12];
	setString(str,12);
	cout << str << endl;
	return 0;
}

