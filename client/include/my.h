/*
** EPITECH PROJECT, 2021
** my.h
** File description:
** my
*/

#ifndef MY_H_
#define MY_H_

#include <netdb.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <uuid/uuid.h>
#include <stdbool.h>

typedef struct socket_s {
    int fd;
    int sd;
    int max_sd;
    int list_fd[2];
    fd_set fds;
    struct sockaddr_in servaddr;
    struct sockaddr_in cli;
    struct timeval tv ;
} socket_t;

typedef struct data_s {
    socket_t socket;
} data_t;

int init_socket(data_t *data, char *ip, char *port);
void loop(data_t *data);
bool do_select(data_t *data);
#endif /* !MY_H_ */
