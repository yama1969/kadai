#include <stdio.h>

int main(){
    char mess[5];
    
    printf("こんにちは！\n");
    printf("何か5文字までの文字列を入力してね\n");
    
    scanf("%5s%*[^\n]", mess);  //5文字まで読み込み、それ以上は改行まで読み飛ばす
    getchar();                  //5文字以下の場合、改行コードが残るので空読みする
    printf("%s\n", mess);
    
    
    scanf("%1s%*[^\n]", mess);
    return 0;
}
