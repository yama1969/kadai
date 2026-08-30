// const_test.cpp : コンソール アプリケーションのエントリ ポイントを定義します。
//

#include "stdafx.h"
#include <iostream>
using namespace std;

class test{
public:
	int i;
	test();
	int& normal();
	const int& constMethod();
	const int& methodConst() const;
};

test::test(){
	i = 1;
	cout << "コンストラクタ実行 i = " << i << endl;
}

int& test::normal(){
	cout << "normal()関数実行" << endl;
	i++;
	cout << "return前に+1" << endl;
	return i;
}

const int& test::constMethod(){
	cout << "constMethod()関数実行" << endl;
	i++;
	cout << "return前に+1" << endl;
	return i;
}

const int& test::methodConst() const{          //後const関数内ではメンバがconstなので、メンバを戻り値にするには戻り値もconstにせねばならん。
	cout << "methodConst()関数実行" << endl;
//	i++;                                       //後const関数内ではメンバの変更ができない。
	return i;
}

int _tmain(int argc, _TCHAR* argv[])
{
	test t;
	
	int &a = t.normal();
	cout << "normal()結果 ・・・ " << a << endl;
	a++;
	cout << "+1するとメンバも・・・ " << t.i << endl;

	int &b = (int &)t.constMethod();                      //(int &)でキャストすると、折角のconst関数が台無しになる。
	cout << "constMethod()結果 ・・・ " << b << endl;
	b++;
	cout << "+1してもメンバは・・・ " << t.i << endl;

	int &c = (int &)t.methodConst();                      //(int &)でキャストすれば、constを逃れてしまうのは上記と同じ。
	cout << "methodConst()結果 ・・・ " << c << endl;
	c++;
	cout << "+1してもメンバは・・・ " << t.i << endl;

	return 0;
}

