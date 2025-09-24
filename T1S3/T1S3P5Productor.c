#include <stdio.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <fcntl.h>
#include <string.h>


int main(void) {
    int fp;
    char messageToSend[] = "This is a message sent from another process";
    
    
    fp = open("FIFO1", 1);


    if (fp == -1) {
        printf("Error when opening the file\n");
        _exit(1);
    }

    printf("Sending info to the FIFO1...\n");
    write(fp, messageToSend, strlen(messageToSend));
    close(fp);

    return 0;
}