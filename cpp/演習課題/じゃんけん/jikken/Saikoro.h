#ifndef __SAIKORO__
#define __SAIKORO__

class Saikoro{
private:
	int order;
	int maxOrder;
public:
	Saikoro(int max = 6);
	int throwSaikoro();
	int getOrder();
	int getMax();
};

#endif