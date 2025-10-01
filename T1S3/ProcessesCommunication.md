# Processes communication
In some scenarios, we might need to stablish some communication in between to processes via sharing information or similar. For doing so, we can use:

1. `Pipes`: Useful for communication in between family processes. We can share a buffer and write on it and read it in the other process. Example of this can be found in the [T1S3P4ForkPipe.c](https://github.com/DavMunHer/Services-Processes/blob/main/T1S3/T1S3P4/T1S3P4ForkPipe.c) file.
2. `FIFOS`: For storing data in something similar to a file with a name. By taking this approach, the information can be shared in between processes even if they are not from the same family. It is important to note that once the info is read, it cannot be read twice. Example of this can be found in the `T1S3P5Productor.c` and `T1S3P5Consumidor.c` files, located in [this folder](https://github.com/DavMunHer/Services-Processes/blob/main/T1S3/T1S3P4/)
    - Key differences: 
        - The FIFO info to be written will be stopped until another process asks for reading it.
        - Can also be used in `bash` by the `mknod` instruction.


