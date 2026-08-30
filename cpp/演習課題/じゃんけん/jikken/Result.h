#ifndef __RESULT__
#define __RESULT__

class Result{
private:
	unsigned int win_num;
	unsigned int lose_num;
	unsigned int draw_num;
public:
	Result();
	int win();
	int lose();
	int draw();
	int getWin();
	int getLose();
	int getDraw();
};

#endif