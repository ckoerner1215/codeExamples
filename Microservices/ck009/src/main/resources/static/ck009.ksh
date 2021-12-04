
EXECBASE=ck009
EXECUTABLE=$PWD/$EXECBASE.exe
rm -f fort.*
rm -f *.bin
rm -f *.txt

#Input file
ln -s input.dat fort.9

#Output files
ln -s ck009-out.txt fort.10

$EXECUTABLE

rm fort.*

