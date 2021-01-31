##
## EPITECH PROJECT, 2020
## Makefile
## File description:
## my make file
##

NAME 	=	client

SRCS  	=	src/main.c 		\
			src/socket.c	\
			src/select.c	\
			
			

OBJS	=	$(SRCS:.c=.o)

NBR_OF_FILES := $(words $(SRCS))

CC	=	gcc -g3

CFLAGS += -W -Wall -Wextra -Werror -Wextra -g3 -I ./include -fsanitize=address

all: $(NAME)

$(NAME): $(OBJS)
		 @ echo "\033[1;36m[ FILES COMPILED ] \033[0m \033[1;34m$(NBR_OF_FILES)\033[0m"
		 @ $(CC) -g3 -o $(NAME) $(OBJS) $(CFLAGS)
		 @ echo "\033[1;34mBinary : \033[1;32m$(NAME)\033[1;34m created sucesfully.\033[0m"

clean:
		@ echo "\033[3;31mRemoving: \033[0m"
		@ echo "\033[3;31m$(OBJS)\033[0m" | tr ' ' '\n'
		@ $(RM) $(OBJS)

fclean:	clean
		@ echo "\033[3;31m./$(NAME)\033[0m"
		@ $(RM) $(NAME)

re: fclean all

%.o:	%.c
	@ echo "\033[1;34m[ OK ]\033[0m Compiling" $<
	@ $(CC) -o $@ -c $< $(CFLAGS) -fPIC

.PHONY: all clean fclean re
