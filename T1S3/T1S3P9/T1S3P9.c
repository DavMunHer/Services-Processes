#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>


int main() {
    char option;
    pid_t child_1, child_2;
    int status1, status2;
    int fd[2];
    char tempC;

    pipe(fd);

    printf(">");

    option = getchar();

    child_1 = fork();

    if (child_1 == 0) {
        if (option == '1') {
            close(fd[0]);
            write(fd[1], "x", 1);
            close(fd[1]);
        } else {
            close(fd[1]);
            read(fd[0], &tempC, 1);
            close(fd[0]);
        }
        printf("> Soc procés 1 i acabe\n");
    } else {
        child_2 = fork();

        if (child_2 == 0) {
            if (option == '2') {
                close(fd[0]);
                write(fd[1], "x", 1);
                close(fd[1]);
            } else {
                close(fd[1]);
                read(fd[0], &tempC, 1);
                close(fd[0]);
            }
            printf("> Soc procés 2 i acabe\n");
        }
    }
    close(fd[0]);
    close(fd[1]);

    waitpid(child_1, &status1, 0);
    waitpid(child_2, &status2, 0);
}

