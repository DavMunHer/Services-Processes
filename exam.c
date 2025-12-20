#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    pid_t child, grandChild1, grandChild2, grandChild3, grandGrandChild;
    int childStatus, grandChildStatus1, grandChildStatus2, grandChildStatus3, grandGrandChildStatus;


    child = fork();

    if (child == 0) {
        grandChild1 = fork();

        if (grandChild1 == 0) {
            grandGrandChild = fork();

            if (grandGrandChild == 0) {
                printf("Soy Bisnieto (%d, hijo de %d)\n", getpid(), getppid());
            } else {
                waitpid(grandGrandChild, &grandGrandChildStatus, 0);
                printf("Soy Nieto1 (%d, hijo de %d)\n", getpid(), getppid());
            }

        } else {
            // Father process
            waitpid(grandGrandChild, &grandGrandChildStatus, 0);
            grandChild2 = fork();
            grandChild3 = fork();
            if (grandChild2 == 0) {
                printf("Soy Nieto2 (%d, hijo de %d)\n", getpid(), getppid());
            }

            if (grandChild3 == 0) {
                printf("Soy Nieto3 (%d, hijo de %d)\n", getpid(), getppid());
            }

            waitpid(grandChild1, &grandChildStatus1, 0);
            waitpid(grandChild2, &grandChildStatus2, 0);
            waitpid(grandChild3, &grandChildStatus3, 0);
            printf("Soy Hijo (%d, hijo de %d)\n", getpid(), getppid());
        }
    } else {
        waitpid(child, &childStatus, 0);
        printf("Soy Padre (%d, hijo de %d)", getpid(), getppid());
    }
    return 0;
}

//    gcc path/to/T1S2P2TresGeneracions.c -o output/path/T1S2P2TresGeneracions && output/path/T1S2P2TresGeneracions