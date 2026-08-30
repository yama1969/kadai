// class_test2.cpp : コンソール アプリケーションのエントリ ポイントを定義します。
//

#include "stdafx.h"

#include <iostream>
using namespace std;

class oya{
public:
	virtual void mess();
};

void oya::mess(){
	cout << "これは親クラスのメソッドです。" << endl;
}

class ko : public oya{
public:
	void mess();
	void mess2();
};

void ko::mess(){
	cout << "これは子クラスでオーバーライドしたメソッドです。" << endl;
}

void ko::mess2(){
	cout << "これは子クラスの新規メソッドです。" << endl;
}

int main(){
	oya *o = new ko;
	o->mess();
	
	ko *k;

	if(typeid(*o) == typeid(ko)){			//typeid・dynamic_castいずれにしても、oyaにvirtualがなくてはいけない。
		k = (ko *)o;
		k->mess();
		k->mess2();
	}else{
		cout << "型が合いません" << endl;
	}

/*
	k = dynamic_cast<ko *>(o);
	if(k != 0){
		k->mess();
		k->mess2();
	}
*/
}
