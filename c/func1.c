/*
void main()
{
    void func1(char *str);
    
    printf("mainです。func1を呼び出します。\n");
    func1("mainから渡した文字列");
}
*/

void func1(char *str)
{
   printf("func1です。引数で渡された文字列を表示します。\n");
   printf(str);
}
