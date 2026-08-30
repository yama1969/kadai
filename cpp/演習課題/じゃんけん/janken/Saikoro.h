#ifndef __SAIKORO__
#define __SAIKORO__

/******************************************************************************
 * クラスSaikoro
 * 　最大値可変のサイコロ
 ******************************************************************************/
class Saikoro{
private:
	int order;				//さいころの目
	int maxOrder;			//目の最大値
public:
	Saikoro(int max = 6);	//コンストラクタ
	int throwSaikoro();		//サイコロを投げる=(目がランダムに決まる)
	int getOrder();			//目を得る
	int getMax();			//目の最大値を得る
};

#endif