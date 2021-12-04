#!/usr/bin/python

import sys
import struct

filename1 = raw_input("Enter the name of the file to byte-swap: ") 
filename2 = raw_input("What would you like to call the byte-swapped version? ")

f1 = open(filename1,"rb")
f2 = open(filename2,"wb")

ct=0
isize=4

while True:
       barray=[]
       bswap=[]
      
       for i in range(0,isize):
          byte = f1.read(1)
          if not byte:
            break
          barray.append(byte)
       print barray
       if len(barray)== 0:
          break
       bswap.append(barray[3])
       bswap.append(barray[2])
       bswap.append(barray[1])
       bswap.append(barray[0])
       print bswap
       b2=bytearray(bswap)
       for i in range(0,isize):
          buf=struct.pack('c', bswap[i])
          f2.write(buf)
       ct=ct+1

print "number of words: ",ct




