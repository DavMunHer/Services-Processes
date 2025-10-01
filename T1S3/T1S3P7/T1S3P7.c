#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>


int main() {
    pid_t child_1, child_2, child_3;
    int status1, status2, status3;

    child_1 = fork();

    if (child_1 == 0) {
        printf("Jo soc el fill 1, el meu pare és PID=%i, jo soc PID=%i\n", getppid(), getpid());
    } else {
        // Back to the parent process
        waitpid(child_1, &status1, 0);
        child_2 = fork();
        if (child_2 == 0) {
            printf("Jo soc el fill 2, el meu pare és PID=%i, jo soc PID=%i\n", getppid(), getpid());
        } else {
            // Back to parent
            waitpid(child_2, &status2, 0);
            child_3 = fork();
            if (child_3 == 0) {
                printf("Jo soc el fill 3, el meu pare és PID=%i, jo soc PID=%i\n", getppid(), getpid());
            } else {
                waitpid(child_3, &status3, 0);
            }
        }
    }
}