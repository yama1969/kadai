extern void func1(char *str);

void main()
{
    printf("外部ソースmainです。func1を呼び出します。\n");
    func1("外部mainからfunc1へ渡された文字列");
}
