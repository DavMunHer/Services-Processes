# Services & Processes

## What is
This repo has been created for following all the learning process of the subject called "Services and Processes", which is taught in the vocational training of "Multiplatform App Development".

## Which content will you find here?
Here, you will find all the different theorical concepts of each lesson in their respective .md files, as well as this concepts applied to the practice with some `C` easy scripts.

Table of contents: 

| Content                              | Markdown File |
|:------------------------------------:|:-------------:|
| Duplicated processes - Fork()        | [fork()](https://github.com/DavMunHer/Services-Processes/blob/main/T1S2/README.md)              |
| Sharing info between processes       | [pipe() & FIFOs](https://github.com/DavMunHer/Services-Processes/blob/main/T1S3/ProcessesCommunication.md)               |


### How to run the different C files?
For running all the different `C` files, you will first need to install the `gcc` compiler. For doing this:

- In Windows
    - Highly recommendable to install [wsl](https://learn.microsoft.com/en-us/windows/wsl/install). Once downloaded, you will have to start the wsl by running `wsl` in your PowerShell.
        - Now that you are in the Windows Subsystem for Linux, you will be able to run the linux commands. For downloading the necessary dependencies for developing in C in any Debian based distro, run the following:
            ```bash
            sudo apt update;
            sudo apt install libc-dev g++ build-essential;
            ```
        - Now you can start compiling and running the files. For a file called `T1S2P2TresGeneracions.c`, the command for compiling and running the file would be the following:
          ```bash
          gcc path/to/T1S2P2TresGeneracions.c -o output/path/T1S2P2TresGeneracions && output/path/T1S2P2TresGeneracions
          ```  


- In Linux
    - If not installed, install the necessary dependencies for developing in `C`. For Debian based distros:
        ```bash
        sudo apt update;
        sudo apt install libc-dev g++ build-essential;
        ```
    - Run the files. Example for a file called `T1S2P2TresGeneracions.c`:
        ```bash
          gcc path/to/T1S2P2TresGeneracions.c -o output/path/T1S2P2TresGeneracions && output/path/T1S2P2TresGeneracions
        ```

## Author
> DavMunHer - [@LinkedIn](https://linkedin.com/in/DavMunHer) · [@GitHub](https://github.com/davmunher)

