//マルチスレッドプログラムと mutex の使い方
#include <stdio.h>
#include <windows.h>
#include <process.h>

HANDLE	hMutex; //ミューテックスのハンドル
int main(void);

int count1 = 0; //mutex で変数を保護する
int count2 = 0; //保護しない

void func1(LPVOID	pParam)
{
	int	i;
	while(1){
		WaitForSingleObject(hMutex,INFINITE); //mutex 間は他のスレッドから変数を変更できない
		printf("count1:");
		for(i=0;i<10;i++){
			printf("%d:",count1);
			count1++;
			Sleep(50);
		}
		printf("\n");
		ReleaseMutex(hMutex);

		//mutex で変数を保護しないと他のスレッドから変数を変更できる
		printf("count2:");
		for(i=0;i<10;i++){
			printf("%d:",count2);
			count2++;
			Sleep(50);
		}
		printf("\n");
	}
}

void func2(LPVOID	pParam)
{
	while(1){	
		//mutex で  count1 を保護
		WaitForSingleObject(hMutex,INFINITE);
		count1=0;
		ReleaseMutex(hMutex);

		count2=0;
	}
}

int main(void){
	HANDLE	hThread[2];

	hMutex = CreateMutex(NULL,FALSE,NULL);	//ミューテックス生成

	hThread[0] = (HANDLE)_beginthread(func1,0,NULL);	//スレッド１作成
	hThread[1] = (HANDLE)_beginthread(func2,0,NULL);	//スレッド２作成

	//スレッド１、２終了待ち
	WaitForMultipleObjects(2,hThread,TRUE,INFINITE);

	//ハンドルクローズ
	CloseHandle(hThread[0]);
	CloseHandle(hThread[1]);
	CloseHandle(hMutex);

	return 0;
}
