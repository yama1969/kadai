#include <iostream>
#include "User.h"
#include "Shop.h"
using namespace std;

void User::showMenu(){
	Shop shop;
	Taiyaki *taiyaki = NULL;

	int comm = 0;
	while(comm != 4){
		cout << "何をしますか？" << endl;
		cout << "１．たい焼きを注文する" << endl;
		cout << "２．たい焼きを買う" << endl;
		cout << "３．たい焼きを食べる" << endl;
		cout << "４．終了" << endl;
		cout << "番号=> ";

		cin >> comm;

		switch(comm){
			case 1:
				if( shop.make() ){
					cout << "たい焼きを作りました。" << endl;
				}else{
					cout << "もう作れません。" << endl;
				}
				break;
			case 2:
				if( !taiyaki ){
					taiyaki = shop.sell();
					if( taiyaki ){
						cout << "たい焼きを買いました。" << endl;
						cout << "たい焼き屋の売上は" << shop.getSales() << "になりました。" << endl;
					}else{
						cout << "もう売り切れです。" << endl;
					}
				}else{
					cout << "もう買ってあります。" << endl;
				}
				break;
			case 3:
				if( taiyaki ){
					char *mess = taiyaki->eat();
					cout << mess << endl;
					delete taiyaki;
					taiyaki = NULL;
				}else{
					cout << "たい焼きを持っていません。" << endl;
				}
				break;
			case 4:
				break;
			default:
				break;
		}
		cout << endl;
	}
}