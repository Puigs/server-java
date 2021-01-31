#include "../include/my.h"

int init_socket(data_t *data, char *ip, char *port)
{
    data->socket.fd = socket(AF_INET, SOCK_STREAM, 0);
    if (data->socket.fd == -1) {
        printf("Socket creation failed\n");
        return (84);
    } else
        printf("Socket has been create\n");
    bzero(&data->socket.servaddr, sizeof(data->socket.servaddr));
    data->socket.servaddr.sin_family = AF_INET;
    data->socket.servaddr.sin_addr.s_addr = inet_addr(ip);
    data->socket.servaddr.sin_port = htons(atoi(port));
    if (connect(data->socket.fd, (struct sockaddr *)&data->socket.servaddr, \
    sizeof(data->socket.servaddr)) < 0) {
        printf("Connection failed with the server\n");
        return (84);
    } else
        printf("Connected to the server done\n");
    data->socket.list_fd[0] = data->socket.fd;
    data->socket.list_fd[1] = 0;
    data->socket.tv.tv_sec = 1;
    data->socket.tv.tv_usec = 0;
    data->socket.max_sd = data->socket.fd;
    return (0);
}