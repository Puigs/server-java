#include "../include/my.h"

void loop(data_t *data)
{
    bool tmp = true;

    while (1) {
        if (tmp == true) {
            write(1, "Veuillez écrire un nom : ", 27);
            tmp = false;
        }
        tmp = do_select(data);
    }
}

int main(int ac, char **av)
{
    if (ac != 3) {
        printf("Problem with argument, you need to add the ip and the port\n");
        return (84);
    }
    data_t data;
    if (init_socket(&data, av[1], av[2]) != 0) {
        printf("Problem on init_socket\n");
        return (84);
    }
    write(1, "Ce client sert à envoyer des informations au serveur, chaque message doit comporter un nom et un prénom\n", 107);
    loop(&data);
    return (0);
}