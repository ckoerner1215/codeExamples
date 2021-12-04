EXECBASE=ck001
rm -f fort.*

LOCATION=$1
INPUT1=$2
INPUT9=$3
OUTPUTDIR=$4
EXECUTABLE=$LOCATION/$EXECBASE.exe

mkdir -p $OUTPUTDIR

ln -s $INPUT9 fort.9
ln -s ck001-out.txt fort.10
ln -s ck001-out.bin fort.11

echo "Running the executable ..." $EXECUTABLE

$EXECUTABLE < $INPUT1 > $OUTPUTDIR/output.dat
exitcode1=$?

mv ck001-out.txt $OUTPUTDIR
exitcode2=$?

mv ck001-out.bin $OUTPUTDIR
exitcode3=$?

if [ $exitcode1 -eq 0 ]; then
        echo "ck001.exe ran properly"
else
        echo "ck001.exe did not run properly."
        exit $exitcode1
fi
if [ $exitcode2 -eq 0 ]; then
        echo "Moved ck001-out.txt to " $OUTPUTDIR
else
        echo "Could not move ck001-out.txt to " $OUTPUTDIR
        exit $exitcode2
fi
if [ $exitcode3 -eq 0 ]; then
        echo "Moved ck001-out.bin to " $OUTPUTDIR
else
        echo "Could not move ck001-out.bin to " $OUTPUTDIR
        exit $exitcode3
fi


echo "--------------------"
echo "Done running ck001"
echo "-------------------"
echo "  "
echo "-----------"
echo "OUPUT FILES in " $OUTPUTDIR ":"
echo "-----------"
echo "output.dat: "
cat $OUTPUTDIR/output.dat
echo "   "
echo "   "
echo "ck001-out.txt: "
cat $OUTPUTDIR/ck001-out.txt
echo "   "
echo "   "
echo "Binary File:"
ls -l $OUTPUTDIR/ck001-out.bin
echo "*******11111111111111111*****************"

rm fort.*

exit 0

