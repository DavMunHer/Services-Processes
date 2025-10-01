#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>


int main() {
    pid_t child_p;
    int child_status;

    child_p = fork();

    if (child_p == 0) {
        //Child process
        for (int i = 0; i < 10; i++) {
            printf("Soc el fill\n");
        }
    } else {
        // Back to the parent process
        waitpid(child_p, &child_status, 0);
        printf("El meu fill ha acabat\n");
    }
}