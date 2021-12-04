#!/bin/bash

files=`find . | zenity --list --title "Select the direct access file you would like to read: " --column "Files" --separator=" " --multiple --width=600 --height=400`
for myfile in ${files}; do
    echo ${myfile}
done

nelem=$(zenity --entry --title "Elements per record" --text "How many elements are there in a record? ")
echo You have entered "${nelem}"

output=$(readDirectAccessBinary.py << heredocument
${myfile}
${nelem}
heredocument)

zenity --info --title="Contents of the direct access file: " --text="$output"  --width=600 --height=400






