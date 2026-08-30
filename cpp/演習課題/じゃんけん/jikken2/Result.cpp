#include "Result.h"

Result::Result(){
	win_num = 0;
	lose_num = 0;
	draw_num = 0;
}

int Result::win(){
	return ++win_num;
}

int Result::lose(){
	return ++lose_num;
}

int Result::draw(){
	return ++draw_num;
}

int Result::getWin(){
	return win_num;
}

int Result::getLose(){
	return lose_num;
}

int Result::getDraw(){
	return draw_num;
}
