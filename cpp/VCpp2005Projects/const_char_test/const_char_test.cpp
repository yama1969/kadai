/*
const char * は、アドレスそのものは変更できるが、文字配列の中身は変更できない。
(Javaのfinal配列とは逆である)
*/
#include <iostream>
using namespace std;

int main(){
	cout << "char str1[]" << endl;
	char str1[] = {"これはstr1です。charの配列です。"};
	cout << str1 << endl;
	cout << endl;
	
	cout << "char *str2" << endl;
	char *str2;
	str2 = str1;
	cout << "str2にstr1を代入しました。" << endl;
	cout << str2 << endl;
	str2[9] = '2';
	cout << "str2[9]を書き換えました。" << endl;
	cout << str2 << endl;
	cout << endl;

	cout << "const char *str3" << endl;
	const char *str3;
	str3 = str2;
	cout << "str3にstr2を代入しました。" << endl;
	cout << str3 << endl;
//	str3[9] = '3';
	cout << "str3[9]を書き換えました。" << endl;
	cout << str3 << endl;
	cout << endl;

	char str4[] = {"これはstr4です。これもcharの配列です。"};
	str3 = str4;
	cout << "str3にstr4を代入しました。" << endl;
	cout << str3 << endl;
}
