#Makefile for ck001
MODULE=ck100
FILE=ck100
CC=gfortran
OPTIONS=--save-temps -fcheck=all -g

$(MODULE).exe: $(FILE).o
	$(CC) $(OPTIONS) -C -o$(MODULE).exe $(FILE).o
	@echo '$(MODULE) build complete'

$(FILE).o: $(FILE).f
	$(CC) $(OPTIONS) -C -c $(FILE).f

install:
	cp $(MODULE).exe ../$(MODULE).exe

clean:
	rm -f *.o
	rm -f *.s
	rm -f *.exe
