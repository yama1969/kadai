// private_test.cpp : コンソール アプリケーションのエントリ ポイントを定義します。
//

#include "stdafx.h"
#include <iostream>
using namespace std;

class test{
private:
	int i;
public:
	test();
	int *getNum();
};

test::test(){
	i = 1;
}

int *test::getNum(){                  //privateメンバであっても、ポインタを返してしまえば変更可能らしい。
	return &i;
}

int _tmain(int argc, _TCHAR* argv[])
{
	test t;
	int *a;

	a = t.getNum();
	cout << "privateメンバ　i = " << *a << endl;
	*a = 2;
	cout << "ポインタを使用してprivateメンバを2に変更しました。" << endl;

	a = t.getNum();
	cout << "privateメンバ　i = " << *a << endl;

	return 0;
}
