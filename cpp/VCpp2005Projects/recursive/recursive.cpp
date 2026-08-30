#include <iostream>
using namespace std;

int test(int num){
	if(num == 1){
		return num;
	}
	return num * test(num - 1);
}

int main(){
	cout << test(5) << '\n';
}