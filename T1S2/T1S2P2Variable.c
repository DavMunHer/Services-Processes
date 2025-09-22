#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main() {
    __pid_t parent, child;
    child = fork();
    int num = 6, status, status2;


    if (child == 0) {
        num -= 5;
        printf("Soc el Fill. El valor de la variable és %d\n", num);
    } else {
        num += 5;
        printf("Soc el Pare. El valor de la variable és %d\n", num);
    }
    

    return 0;
}