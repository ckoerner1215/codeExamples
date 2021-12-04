#!/usr/bin/python
import sys
import struct

endian=sys.byteorder
print "Endian: ",endian

fileName = raw_input("Enter the Sequential Binary filename: ") 
nb=4

file=open(fileName, mode='rb')
#read first bookend in record
fileContent = file.read(4)
ct=1

while fileContent:
    header = struct.unpack("i",fileContent)[0]
    nelem = header/nb
    try:
       byterecord = file.read(header)
       record = struct.unpack("f"*nelem,byterecord)
       print ct,record
       #read last bookend in record
       fileContent = file.read(4)
       #read first bookend in next record
       fileContent = file.read(4)
       ct=ct+1
    except:
       print "Something is wrong with the file in record ",ct
       break

print "Number of bytes in a record: ",header
print "Number of elements in a record: ",nelem
print "Number of records in file : ",ct-1

