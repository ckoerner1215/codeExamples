
EXECBASE=ck100
rm -f fort.*

LOCATION=$1
OUTPUTDIR=$2
NUMBER_OF_POINTS=$3
POINTS=$4

EXECUTABLE=$LOCATION/$EXECBASE.exe

mkdir -p $OUTPUTDIR
chmod 777 $OUTPUTDIR

echo $NUMBER_OF_POINTS
echo $POINTS
echo '*******************'

#Output files
ln -s ck100-out.txt fort.10

$EXECUTABLE << heredoc
 $NUMBER_OF_POINTS
 $POINTS
heredoc

exitcode1=$?

mv ck100-out.txt $OUTPUTDIR

ls $OUTPUTDIR/ck100-out.txt
exitcode2=$?

if [ $exitcode1 -eq 0 ]; then
        echo "ck100.exe ran properly"
else
        echo "ck100.exe did not run properly."
        exit $exitcode1
fi
if [ $exitcode2 -eq 0 ]; then
        echo "Found ck100-out.txt in " $OUTPUTDIR
else
        echo "Could not find ck100-out.txt in " $OUTPUTDIR
        exit $exitcode2
fi


echo "--------------------"
echo "Done running ck100"
echo "-------------------"
echo "  "
echo "-----------"
echo "OUPUT FILES in " $OUTPUTDIR ":"
echo "-----------"
echo "ck100-out.txt: "
cat $OUTPUTDIR/ck100-out.txt
echo "   "
echo "   "

rm fort.*

exit 0

