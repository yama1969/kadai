/*
 * クイックソート(再帰呼出なし) ・・・ 前提知識は制御構文と配列(break,continueは使わない方針)
 */
class Quick{
	public static void main(String[] args){
		//データ個数
		int size = 100;
		
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
		
		//添字スタックの生成と初期化
		int[] left = new int[size / 2];	//左側添字用スタック
		left[0] = 0;					//初期値(左端)
		int left_p = 1;					//左側添字用ポインタ
		int[] right = new int[size /2];	//右側添字用スタック
		right[0] = dat.length - 1;		//初期値(右端)
		int right_p = 1;				//右側添字用ポインタ
		
		//ソート開始
		while(left_p > 0){				//スタックが空になるまで(right_p > 0でも良い)
			//スタック読出し(処理範囲設定)
			int cl = left[--left_p];	//今回の左側添字
			int cr = right[--right_p];	//今回の右側添字
			
			//大小グループ分け処理
			int l = cl;					//探索位置左側
			int r = cr;					//探索位置右側
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
			if(dat[l] < sp || l == cl){	//位置l(=r)の右側で分ける
				r++;
			}else{						//位置l(=r)の左側で分ける
				l--;
			}
			//小グループ範囲スタックpush(基準値が最小値の場合、小グループ無し)
			if(l != cl){
				left[left_p++] = cl;
				right[right_p++] = l;
			}
			//大グループ範囲スタックpush(基準値が最大値の場合、大グループ無し)
			if(r != cr){
				left[left_p++] = r;
				right[right_p++] = cr;
			}
		}
		
		//乱数配列の表示
		for(int i = 0; i < dat.length; i++){
			System.out.print(dat[i] + " ");
		}
	}
}
