// HeaderTest.cpp : コンソール アプリケーションのエントリ ポイントを定義します。
//

#include <iostream>
#include "stdafx.h"
#include "oya.h"
#include "ko.h"

using namespace std;

int _tmain(int argc, _TCHAR* argv[])
{
	oya *o = new ko;
	o->mess();

	ko *k;
	if(typeid(*o) == typeid(ko)){
		k = (ko *)o;
		k->mess();
		k->mess2();
	}else{
		cout << "型が合わないのでキャストできませんでした。" << endl;
	}

	return 0;
}

