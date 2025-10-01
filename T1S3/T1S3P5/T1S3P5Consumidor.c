#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <string.h>
#include <errno.h>

#define FIFO_NAME "FIFO1"

int main() {
    int fd;
    char buffer[100];
    ssize_t bytesRead;

    if (mkfifo(FIFO_NAME, 0666) == -1) {
        if (errno != EEXIST) {
            perror("Error creant la FIFO");
            exit(EXIT_FAILURE);
        }
    }

    printf("Consumidor: esperant dades...\n");

    fd = open(FIFO_NAME, O_RDONLY);
    if (fd == -1) {
        perror("Error obrint FIFO");
        exit(EXIT_FAILURE);
    }

    while ((bytesRead = read(fd, buffer, sizeof(buffer)-1)) > 0) {
        buffer[bytesRead] = '\0';  
        printf("Consumidor ha llegit: %s\n", buffer);

        if (strchr(buffer, '.')) {  
            printf("Consumidor: punt rebut, finalitzant.\n");
            break;
        }
    }

    close(fd);

    unlink(FIFO_NAME);

    return 0;
}
