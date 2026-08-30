// hairetsu.cpp : コンソール アプリケーションのエントリ ポイントを定義します。
//

#include "stdafx.h"

#include <iostream>
using namespace std;

void getString(char string[],int size){
	char test[] = "TestString!";
	int i;

	for(i = 0; i<size && test[i]!='\0'; i++){
		string[i] = test[i];
		cout << i << "_";
	}
	if(i == size){
		i--;
	}
	string[i] = '\0';

	cout << endl;
}


int _tmain(int argc, _TCHAR* argv[])
{
	char testString[20];
	getString(testString,20);

	cout << testString << endl;

	return 0;
}

