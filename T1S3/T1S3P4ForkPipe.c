#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <string.h>
#include <stdlib.h>
#include <sys/wait.h>

int main() {
    pid_t child_p;
    int child_status;
    int fd[2];
    char messageToFather[] = "Hola papi";
    char buffer[25];


    pipe(fd); /* Creating pipe for intercomunication. We will use fd[0] for reading and fd[1] for writing */
    child_p = fork();

    switch (child_p)
    {
    case -1:
        printf("An error occurred");
        break;

    case 0:
        /* Currently in the child process */
        close(fd[0]);
        write(fd[1], messageToFather, strlen(messageToFather) + 1); //Stored the message for the father
        printf("El fill envia al pare...\n");
        break;

    default:
        /* Back to the father (main) process */
        close(fd[1]);
        waitpid(child_p, &child_status, 0);
        
        read(fd[0], buffer, sizeof(buffer));
        printf("El pare rep del fill: %s", buffer);
        break;
    }
}