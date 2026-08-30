#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(void)
{
    int a = 0;      //問題の数値1
    int b = 0;      //問題の数値2
    int no = 0;     //問題番号
    int ans = 0;    //回答の値
    int score = 0;  //得点
    
    srand(time(NULL));
    
    for(no = 1; no <=5; no++){
        a = (int)(rand() / (RAND_MAX + 1.0) * 10.0);
        b = (int)(rand() / (RAND_MAX + 1.0) * 10.0);
        printf("No.%d  %d x %d = ", no, a, b);
        scanf("%d", &ans);
        if(ans == a * b){
            printf("正解\n");
            score += 20;
        }else{
            printf("はずれ\n");
        }
    }
    printf("得点は、%d点でした。\n", score);
    return 0;
}
