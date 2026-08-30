#include <stdio.h>

struct Size{
    int height;
    int weight;
};

struct Person{
    char name[40];
    int age;
    struct Size size;
};

int main(void)
{
    struct Size s;
    s.height = 163;
    s.weight = 64;
    struct Person p;
    strcpy(p.name, "山田　洋");
    p.age = 46;
    p.size = s;
    
    printf("名前：%s\n", p.name);
    printf("年齢：%d\n", p.age);
    printf("身長：%d\n", p.size.height);
    printf("体重：%d\n", p.size.weight);
    
    return 0;
}
