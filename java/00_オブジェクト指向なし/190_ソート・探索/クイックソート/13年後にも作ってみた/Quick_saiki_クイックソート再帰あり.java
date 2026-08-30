/*
 * クイックソート(再帰呼出)
 */
class Quick_saiki{
	/**************************************************************************
	 * データ生成と表示
	 */
	public static void main(String[] args){
		int size = 100;	//データ個数
		
		//ソート対象の乱数配列の生成
		int[] dat = new int[size];
		for(int i = 0; i < dat.length; i++){
			dat[i] = (int)(Math.random() * dat.length);
		}
		
		//乱数配列の表示
		for(int i = 0; i < dat.length; i++){
			System.out.print(dat[i] + " ");
		}
		System.out.println();
		
		//ソート
		sort(dat, 0, dat.length - 1);
		
		//乱数配列の表示
		for(int i = 0; i < dat.length; i++){
			System.out.print(dat[i] + " ");
		}
	}
	
	/**************************************************************************
	 * ソート処理
	 */
	static void sort(int[] dat, int left, int right){
		if(left == right){
			return;
		}
		
		//大小グループ分け処理
		int l = left;				//探索位置左側
		int r = right;				//探索位置右側
		int sp = dat[l];			//グループ分け基準値
		while(l < r){
			//左側探索
			while(l < r && dat[l] < sp){
				l++;
			}
			//右側探索
			while(l < r && dat[r] >= sp){
				r--;
			}
			//交換または終了判定
			if(l < r){
				//交換
				int work = dat[l];
				dat[l] = dat[r];
				dat[r] = work;
			}
		}
		//今回は終了(lとrをグループの最大・最小添字にする)
		if(dat[l] < sp || l == left){	//位置l(=r)の右側で分ける
			r++;
		}else{							//位置l(=r)の左側で分ける
			l--;
		}
		//小グループ側ソート(基準値が最小値の場合、小グループ無し)
		if(l != left){
			sort(dat, left, l);
		}
		//大グループ側ソート(基準値が最大値の場合、大グループ無し)
		if(r != right){
			sort(dat, r, right);
		}
	}
}
