#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>

#define FIFO_NAME "FIFO1"

int main() {
    int fd;
    char buffer[100];
    int i = 0;

    // Obrir FIFO per escriure
    fd = open(FIFO_NAME, O_WRONLY);
    if (fd == -1) {
        perror("Error obrint FIFO");
        exit(EXIT_FAILURE);
    }

    printf("> ");
    while (1) {
        char c = getchar();
        if (c == '.') {
            break;
        }
        buffer[i++] = c;
    }
    buffer[i] = '\0';
    
    write(fd, buffer, strlen(buffer));
    
    close(fd);

    return 0;
}
