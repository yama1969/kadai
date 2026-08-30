#include <ctime>
#include <iostream>
#include "Saikoro.h"
using namespace std;

Saikoro::Saikoro(int max){
	srand((unsigned)time(NULL));
	maxOrder = 6;
	if(max > 0){
		maxOrder = max;
	}
	throwSaikoro();
}

int Saikoro::throwSaikoro(){
	order = rand() % maxOrder + 1;
	return order;
}

int Saikoro::getOrder(){
	return order;
}

int Saikoro::getMax(){
	return maxOrder;
}