# Create and duplicate processes

During this lesson, we learn how to use the `fork()` function of `C`, which creates a "copy" of the father process. By doing so, we create a new child process.

We can make sure that we are currently in the child process by checking the value returned by the fork() function. If it's equal to zero, it means that we are in the child process:

```C
#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>

int main(int argc, char *argv[])
{
	pid_t pid;
	pid=fork();
	if (pid == 0) {
        /* Child process */
		printf("I'm the child (%d, child of %d)\n", getpid(), getppid());
	}
	else { 
        /* father process (main process in this case) */
		printf("I'm the father (%d, child of %d)\n", getpid(), getppid());
	}
	return 0;
}
```

It's important to note that in this example we are currenly using **multiprogramming** (Single CPU, same core), not ~~paralelism~~ (Single CPU but one process in one core and the other one in the other) in the context of concurrency.


## Interesting data
Since we are running this from the bash, we can see that the father process is the child of the bash process itself. We can check this by running `pgrep bash`. This will return the pid of the bash, which is the same as the parent process id displayed in the last `printf` of the above script.

