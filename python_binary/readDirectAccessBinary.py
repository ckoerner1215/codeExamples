#!/usr/bin/python
import sys
import struct

endian=sys.byteorder
print "Endian: ",endian

filename = raw_input("Enter the Direct Access Binary filename: ") 
print filename
nelementsInRecord = input("Enter the number of elements in each record: ")
print nelementsInRecord
nelem = int(nelementsInRecord)
nb=4

print "Do you want to read this as big or little endian?"
endian = input("Enter 1 for Big or 2 for Little:")

if endian == 1:
    iformat=">f"
else:
    iformat="<f"

f = open(filename, "rb")
ct=0

while True:
   try:
       record=()
       for i in range(0,nelem):
          bytevalue = f.read(4)
          floatvalue = struct.unpack(iformat,bytevalue)
          record = record + floatvalue
       print record
       ct=ct+1
   except:
       print "At EOF"
       break

print "Number of records in file: ",ct
print "Number of bytes in a record: ",nelem*nb
print "Number of elements in a record: ",nelem

