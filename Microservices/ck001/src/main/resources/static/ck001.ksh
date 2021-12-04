EXECBASE=ck001
EXECUTABLE=$PWD/$EXECBASE.exe
rm -f fort.*

ln -s llh.bin fort.9
ln -s ck001-out.txt fort.10
ln -s ck001-out.bin fort.11

echo $EXECUTABLE

$EXECUTABLE < input.dat > output.dat

echo "--------------------"
echo "Done running ck001"
echo "-------------------"
echo "  "
echo "-----------"
echo "OUPUT FILES:"
echo "-----------"
echo "output.dat: "
cat output.dat
echo "   "
echo "   "
echo "ck001-out.txt: "
cat ck001-out.txt
echo "   "
echo "   "
echo "Binary File:"
ls -l ck001-out.bin
echo "************************"

rm fort.*

