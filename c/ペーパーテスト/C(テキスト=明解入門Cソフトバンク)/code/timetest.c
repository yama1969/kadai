#include <stdio.h>
#include <time.h>

int main(void)
{
    time_t t = 0;
    printf("%d\n",t);
    t = time(NULL);
    printf("%d\n",t);
}
