#include "stdafx.h"
#include <iostream>
#include "ko.h"

using namespace std;

void ko::mess(){
	cout << "これは親クラスをオーバーライドした子クラスのメソッドです。" << endl;
}

void ko::mess2(){
	cout << "これは子クラスで新規に定義したメソッドです。" << endl;
}
