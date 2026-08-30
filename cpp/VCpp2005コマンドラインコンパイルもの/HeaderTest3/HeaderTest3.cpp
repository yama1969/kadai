#include <iostream>
#include "oya.h"
#include "ko.h"
using namespace std;

int main()
{
	char name[] = "ko";
	ko   ko_test;

	oya *o = new ko;
	o->mess();

	ko *k;
//	if(typeid(*o) == typeid(name)){				//コンパイラは通るが、実行すると「型が合わない」になる。
	if(typeid(*o) == typeid(ko_test)){			//こちらは行ける。スタックに比較用koオブジェクトを作成。比較用オブジェクトは必ず生成しなくてはならない。
		k = (ko *)o;
		k->mess();
		k->mess2();
	}else{
		cout << "型が合わないのでキャストできませんでした。" << endl;
	}

	return 0;
}

