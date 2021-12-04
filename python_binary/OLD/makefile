
CC=gfortran
#OPTIONS=--save-temps -fcheck=all -g
OPTIONS=-fcheck=all -g

all: writeDACBinaryFile.exe writeSEQBinaryFile4.exe

writeDACBinaryFile.exe: writeDACBinaryFile.o
	$(CC) $(OPTIONS) -C -owriteDACBinaryFile.exe writeDACBinaryFile.o
	@echo 'writeDACBinaryFile build complete'

writeSEQBinaryFile4.exe: writeSEQBinaryFile4.o
	$(CC) $(OPTIONS) -C -owriteSEQBinaryFile4.exe writeSEQBinaryFile4.o
	@echo 'writeSEQBinaryFile build complete'

writeDACBinaryFile.o: writeDACBinaryFile.f
	$(CC) $(OPTIONS) -C -c writeDACBinaryFile.f

writeSEQBinaryFile4.o: writeSEQBinaryFile4.f
	$(CC) $(OPTIONS) -C -c writeSEQBinaryFile4.f

.PHONY: all clean

clean:
	rm -f *.o
	rm -f *.s
	rm -f *.exe
