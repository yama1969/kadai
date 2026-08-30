#include "stdafx.h"
#include <iostream>
using namespace std;

class oya{
public:
	virtual void mess();
}

oya::mess(){
	cout << "これは親クラスのメソッドです。" << endl;
}

class ko : public oya{
	void mess();
}

ko::mess(){
	cout << "これは子クラスのメソッドです。" << endl;
}

int main(){
	oya o = new ko;
	o.mess();
	
	ko k;
	k = (ko)o;
	k.mess();
}