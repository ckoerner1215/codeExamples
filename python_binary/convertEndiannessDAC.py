#!/usr/bin/python
import sys
import struct

#####################################################################
#  This code will convert a binary file containing all 4 byte floats
#  from one endianness to the other.  The user specifies which.
#  So it will either go from big to little or little to big.
#  However, it is assuming that ALL of the values in the binary file
#  are 4 byte floats.

#  The new file will have the same name as the original, with 
#  ".swapped" attached to the end of the name.
#
#####################################################################

filename = raw_input("Enter the Direct Access Binary filename you want to convert: ") 
print filename
nb=4

print "Do you want to read this as big or little endian?"
endian = input("Enter 1 for Big or 2 for Little:")

if endian == 1:
    format1=">f"
    format2="<f"
else:
    format1="<f"
    format2=">f"

f = open(filename, "rb")
filename_swapped = filename+".swapped"
print filename_swapped
fswap = open(filename_swapped, "wb")
ct=0

while True:
    bytevalue = f.read(nb)
    if not bytevalue:
       break
    floatvalue = struct.unpack(format1,bytevalue)
    print ct,"   ",floatvalue[0]
    buf=struct.pack(format2, floatvalue[0])
    fswap.write(buf)

    ct=ct+1

print "Number of elements in file: ",ct

