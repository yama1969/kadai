#include <stdio.h>

int main(void)
{
    int *ip;
    int a = 158;
    
    *ip = a;
    printf("ipが指すアドレス = %X\n", ip);
    printf("ipが指すアドレスの中身 = %d\n", *ip);
    
    *ip = 246;
    printf("aの中身 = %d\n", a);
    
    return 0;
}
