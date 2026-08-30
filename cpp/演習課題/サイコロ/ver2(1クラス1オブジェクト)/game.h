#ifndef _GAME
#define _GAME

class Game{
	int max;
	int count;
	void guess();
	void end();
public:
	Game(int max);
	Game();
	void start();
};

#endif