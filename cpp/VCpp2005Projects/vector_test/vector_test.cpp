/*
・static constメンバの初期化の仕方！
・vectorは、erase()して消したものは、
　あくまでもリストから消すのであって、実体は消さない。
*/
#include <iostream>
#include <vector>
using namespace std;

class Mono{
private:
	static const int max = 10;
	static const int min;
	static const char name[]/* = { "これはstatic constのテスト" }*/;
	int no;
public:
	Mono(){ no = rand(); }
	void showNo(){ cout << no << endl; }
	int getNo(){ return no; }
};

const char Mono::name[] = {"これはstatic constのテスト"};
const int Mono::min = 1;

/*
bool operator<(Mono a, Mono b){
	return (a.getNo() < b.getNo());
}

bool operator==(Mono a, Mono b){
	return (a.getNo() == b.getNo());
}
*/

ostream &operator<<(ostream &o, Mono &m){
	o << m.getNo();
	return o;
}

void main(){
	Mono mono1;
	Mono *mono2 = new Mono();

	vector<Mono> v;
	v.push_back(mono1);
	v.push_back(*mono2);

	vector<Mono>::iterator p = v.begin();
	while(p != v.end()){
		cout << *p << endl;
		p++;
	}
	cout << endl;
	cout << "これから削除されるのは" << endl;
	cout << *mono2 << endl;
	cout << endl;

	p = v.begin();
	p += 1;
	v.erase(p);
	p = v.begin();
	while(p != v.end()){
		cout << *p << endl;
		p++;
	}
	cout << endl;
	cout << "削除したのは" << endl;
	cout << *mono2 << endl;
	cout << endl;

	delete mono2;
	cout << "削除したのは" << endl;
	cout << *mono2 << endl;
	cout << endl;
}
