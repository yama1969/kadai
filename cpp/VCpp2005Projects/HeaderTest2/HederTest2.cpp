// HederTest2.cpp : コンソール アプリケーションのエントリ ポイントを定義します。
//

#include <iostream>
#include "stdafx.h"
using namespace std;

class oya{
public:
	virtual void mess();
};

void oya::mess(){
	cout << "親クラスのメソッドです。" << endl;
}

class ko : public oya{
public:
	void mess();
	void mess2();
};

void ko::mess(){
	cout << "これは親クラスをオーバーライドした子クラスのメソッドです。" << endl;
}

void ko::mess2(){
	cout << "これは子クラスで新規に定義したメソッドです。" << endl;
}

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

