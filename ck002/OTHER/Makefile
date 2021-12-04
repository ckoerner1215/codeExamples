
SRC=tcfft1.f trfft1.f tcosq1.f tcost1.f tsinq1.f tsint1.f tcfft2.f trfft2.f
CC=gfortran

EXES=$(subst .f,.exe, $(SRC))

all : $(EXES)

$(EXES) : $(SRC)

%.exe : %.f
	rm -f $@
	$(CC) -g $< -o $@ -L../../library/fftpack5.1/lib/ -l fftpack
#	./$@

clean :
	rm -f *.exe
