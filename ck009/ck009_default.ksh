
EXECBASE=ck009
rm -f fort.*

LOCATION=$PWD
INPUT9=input.dat
OUTPUTDIR=.


EXECUTABLE=$LOCATION/$EXECBASE.exe

mkdir -p $OUTPUTDIR
chmod 777 $OUTPUTDIR

#Input file
ln -s $INPUT9 fort.9

#Output files
ln -s ck009-out.txt fort.10

$EXECUTABLE
exitcode1=$?

mv ck009-out.txt $OUTPUTDIR

ls $OUTPUTDIR/ck009-out.txt
exitcode2=$?

if [ $exitcode1 -eq 0 ]; then
        echo "ck009.exe ran properly"
else
        echo "ck009.exe did not run properly."
        exit $exitcode1
fi
if [ $exitcode2 -eq 0 ]; then
        echo "Found ck009-out.txt in " $OUTPUTDIR
else
        echo "Could not find ck009-out.txt in " $OUTPUTDIR
        exit $exitcode2
fi


echo "--------------------"
echo "Done running ck009"
echo "-------------------"
echo "  "
echo "-----------"
echo "OUPUT FILES in " $OUTPUTDIR ":"
echo "-----------"
echo "ck009-out.txt: "
cat $OUTPUTDIR/ck009-out.txt
echo "   "
echo "  75555555555555555777  "

rm fort.*

exit 0

