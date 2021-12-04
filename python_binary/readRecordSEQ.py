import numpy as np

print "                             **************************"
print "This script will only work if all of the records in the sequential file have the same number of elements in them."
print "                             **************************"
print " "

filename = raw_input("Enter the Sequential Binary filename: ") 

print "Do you have 4 byte or 8 byte values in the file?"
nbytes = input("Enter 4 or 8 :  ")
nb = int(nbytes)

if nb == 4:
   type = 'f4'
elif nb == 8:
   type = 'f8'
else:
   print "You have to choose either 4 or 8"
   exit()

dt = np.dtype(type)
#Note that the bookends are ALWAYS 4 byte integers
i4 = np.dtype('i4')

f = open(filename, "rb")
#Read the first 4 byte integer in the file.
#This will tell us the number of bytes in the record.
nbytes = np.fromfile(f, dtype=i4, count=1)
nelem = nbytes/nb
nElementsInARecord=nelem[0]

print " "
print "We are assuming there are ",str(nElementsInARecord)," ",nb," byte values in ALL of the records."
print " "

while True:
   recordString = input("Enter the record number (starting from 1) you would like to read (0 to quit): ")
   record = int(recordString)
   if record > 0:
      #Go to the record you want to read, but skip reading the first
      #4 byte integer - that is the number of bytes in the record
      byte_offset = (nb*nElementsInARecord + 4*2)*record - (nb*nElementsInARecord + 4*2)+4
      f.seek(byte_offset)
      #Starting from the value after the first 4 byte bookend, read all of the elements in the record
      #Don't bother to read the last bookend.
      x = np.fromfile(f,dtype=dt, count=nElementsInARecord)
      print "Record ",record," is: "
      print x
   else:
      break
