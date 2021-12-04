
CC=gfortran
#OPTIONS=--save-temps -fcheck=all -g
OPTIONS=-fcheck=all -g

all: writeSEQBinaryFile4.exe writeSEQBinaryFile8.exe

writeSEQBinaryFile4.exe: writeSEQBinaryFile4.o
	$(CC) $(OPTIONS) -C -owriteSEQBinaryFile4.exe writeSEQBinaryFile4.o
	@echo 'writeSEQBinaryFile4 build complete'

writeSEQBinaryFile8.exe: writeSEQBinaryFile8.o
	$(CC) $(OPTIONS) -C -owriteSEQBinaryFile8.exe writeSEQBinaryFile8.o
	@echo 'writeSEQBinaryFile8 build complete'

writeSEQBinaryFile4.o: writeSEQBinaryFile4.f
	$(CC) $(OPTIONS) -C -c writeSEQBinaryFile4.f

writeSEQBinaryFile8.o: writeSEQBinaryFile8.f
	$(CC) $(OPTIONS) -C -c writeSEQBinaryFile8.f

.PHONY: all clean

clean:
	rm -f *.o
	rm -f *.s
	rm -f *.exe
