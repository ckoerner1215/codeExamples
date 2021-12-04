
CC=gfortran
#OPTIONS=--save-temps -fcheck=all -g
OPTIONS=-fcheck=all -g

all: writeDACBinaryFile.exe writeSEQBinaryFile8.exe

writeDACBinaryFile.exe: writeDACBinaryFile.o
	$(CC) $(OPTIONS) -C -owriteDACBinaryFile.exe writeDACBinaryFile.o
	@echo 'writeDACBinaryFile build complete'

writeSEQBinaryFile8.exe: writeSEQBinaryFile8.o
	$(CC) $(OPTIONS) -C -owriteSEQBinaryFile8.exe writeSEQBinaryFile8.o
	@echo 'writeSEQBinaryFile8 build complete'

writeDACBinaryFile.o: writeDACBinaryFile.f
	$(CC) $(OPTIONS) -C -c writeDACBinaryFile.f

writeSEQBinaryFile8.o: writeSEQBinaryFile8.f
	$(CC) $(OPTIONS) -C -c writeSEQBinaryFile8.f

.PHONY: all clean

clean:
	rm -f *.o
	rm -f *.s
	rm -f *.exe
