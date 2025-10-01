#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main() {
    __pid_t parent, child;
    child = fork();
    int num = 6, status, status2;


    if (child == 0) {
        num -= 5;
        printf("Soc el Fill. El valor de la variable és %d\n", num);
    } else {
        num += 5;
        printf("Soc el Pare. El valor de la variable és %d\n", num);
    }
    /*
    Currently using Multiprogramming - Not paralelism

    Explanation on what is going on:
        - According to the output of this file,
        most of the time we can see that the father calculation is being printed before the child one.
        Therefore, we can see that the variable num is set to 11 at that point.
        However, when running the child process, we can see that the result is 1 (6-5) instead of 6 (11-5).

            - The reason behind this is the context change, where the OS saves the last context where the other process was on.
            This results on the child process starting with the num variable at 6 again.
            Hence, both processes do their thing from their last status where they come from.
            The changes made in one process after this fork(), will not affect the other one.

    ---
    Explicació del que ocorre:
    - Segons la sortida d'aquest fitxer,
    la majoria de les vegades podem veure que el càlcul del pare s'imprimeix abans del del fill.
    Per tant, podem veure que la variable num està definida a 11 en aquest punt.
    No obstant això, quan s'executa el procés fill, podem veure que el resultat és 1 (6-5) en lloc de 6 (11-5).
    
    - La raó d'això és el canvi de context, on el sistema operatiu desa l'últim context on l'altre procés estava activat.
    Això fa que el procés fill comenci de nou amb la variable num a 6.
    Per tant, tots dos processos fan la seva feina des del seu últim estat d'on provenen.
    Els canvis fets en un procés després d'aquest fork() no afectaran l'altre.

    */

    return 0;
}