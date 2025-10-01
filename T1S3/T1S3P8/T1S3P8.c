#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main() {
    pid_t child_1, child_2, grand_child;
    int status1, status2, status3;

    child_1 = fork();

    if (child_1 == 0) {
        printf("Jo soc el fill 1, el meu pare és PID=%i, jo soc PID=%i\n", getppid(), getpid());
    } else {
        waitpid(child_1, &status1, 0);
        child_2 = fork();

        if (child_2 == 0) {
            printf("Jo soc el fill 2, el meu pare és PID=%i, jo soc PID=%i\n", getppid(), getpid());

            grand_child = fork();

            if (grand_child == 0) {
                printf("Jo soc el net, el meu pare és PID=%i, jo soc PID=%i\n", getppid(), getpid());
            } else {
                waitpid(grand_child, &status3, 0);
            }
        } else {
            waitpid(child_2, &status2, 0);
        }
    }
}