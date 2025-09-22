#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    pid_t grandpa, father, child;
    int fatherStatus, childStatus;

    father = fork();

    if (father == 0) {
        child = fork();

        if (child == 0) {
            // Child process
            printf("Soc el net (%d, fill de %d)\n", getpid(), getppid());
        } else {
            // Father process
            waitpid(child, &childStatus, 0);
            printf("Soc el pare (%d, fill de %d)\n", getpid(), getppid());
        }
    } else {
        // Grandpa process
        waitpid(father, &fatherStatus, 0);
        printf("Soc l'avi (%d, fill de %d)\n", getpid(), getppid());
    }
    return 0;
}