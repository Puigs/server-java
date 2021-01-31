/*
** EPITECH PROJECT, 2020
** select.h
** File description:
** select
*/

#include "../include/my.h"

char *my_concat(char *to_send, char *ret, char *name) {
    int x = 0;

    for (int i = 0; ret[i] != 0; i++, x++) {
        if (ret[i] == '\n') {
            x--;
            continue;
        }
        to_send[x] = ret[i];
    }
    to_send[x] = ':';
    x++;
    for (int i = 0; name[i] != 0; i++, x++) {
        if (name[i] == '\n') {
            x--;
            continue;
        }
        to_send[x] = name[i];
    }
    to_send[x] = '\n';
    to_send[x + 1] = 0;
    return (to_send);
}

char *a_ne_pas_faire(char *tmp, int n) {
    for (int i = 0; i < n; i++)
        tmp[i] = 0;
    return tmp;
}

void do_send(int fd, char *src)
{
    write(fd, src, strlen(src));
}

bool do_read_more(data_t *data)
{
    size_t size = 0;

    if (data->socket.sd != 0) {
        char *ret = malloc(sizeof(char) * 512);
        ret = a_ne_pas_faire(ret, 512);
        size = read(data->socket.sd, ret, 512);
        if (size <= 0)
            return (NULL);
        printf("Voici la réponse obtenu :\n---> %s", ret);
        free(ret);
        return (true);
    }
    if (data->socket.sd == 0) {
        char *ret = malloc(sizeof(char) * 512);
        ret = a_ne_pas_faire(ret, 512);
        size = read(data->socket.sd, ret, 512);
        if (size <= 0)
            return (NULL);
        char *name  = malloc(sizeof(char) * 512);
        name = a_ne_pas_faire(name, 512);
        write(1, "Veuillez écrire un prénom : ", 31);
        size = read(data->socket.sd, name, 512);
        if (size <= 0)
            return (NULL);
        char *to_send = malloc(sizeof(char) * (strlen(name) + strlen(ret)) + 3);
        to_send = my_concat(to_send, ret, name);
        do_send(data->socket.list_fd[0], to_send);
        free(ret);
        free(name);
        free(to_send);
        return (false);
    }
    return (false);
}

bool do_read(data_t *data)
{
    for (int i = 0; i < 2; i++) {
        data->socket.sd = data->socket.list_fd[i];
        if (FD_ISSET(data->socket.sd, &data->socket.fds)) {
            return(do_read_more(data));
        }
    }
    return (false);
}

bool do_select(data_t *data)
{
    FD_ZERO(&data->socket.fds);
    FD_SET(data->socket.fd, &data->socket.fds);
    for (int i = 0; i < 2; i++) {
        data->socket.sd = data->socket.list_fd[i];
        if (data->socket.sd >= 0)
            FD_SET(data->socket.sd, &data->socket.fds);
        if (data->socket.sd >= data->socket.max_sd)
            data->socket.max_sd = data->socket.sd;
    }
    if (select(data->socket.max_sd + 1, &data->socket.fds, NULL, NULL, \
    &data->socket.tv) < 0) {
        printf("Error select\n");
        return (NULL);
    }
    return (do_read(data));
}