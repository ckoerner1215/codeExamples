#!/usr/bin/python
import sys
import struct

nlines = input("Enter the number of records you want in the file: ")

nelementsPerRecord =input("Enter the number of elements in each record: ")

print "Do you want 4 byte or 8 byte values in the file?"
nbytes = input("Enter 4 or 8  :  " )
nb = int(nbytes)

if nb == 4:
   type = 'f4'
elif nb == 8:
   type = 'f8'
else:
   print "You have to choose either 4 or 8"
   exit()

recLengthInBytes = nelementsPerRecord*nbytes

filename = "DirectAccess_"+str(nelementsPerRecord)+"x"+str(nlines)+".bin"+str(nb)

f = open(filename, 'w+b')

array = []
for i in range (0,nelementsPerRecord):
     array.append(float(i))
for j in range (0,nlines):
     array[0] = float(j+1)
     print array
     for i in range(0,nelementsPerRecord):
        buf=struct.pack('f', array[i])
        f.write(buf)

print "New direct access file is called: ",filename

