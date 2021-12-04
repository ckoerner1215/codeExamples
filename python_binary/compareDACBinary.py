import numpy as np
import os

print "This Python script will compare two direct access binary files."
print "If they are the same, it will output SAME and have an exit code of 0"
print "If they are different, it will output DIFFERENT and have an exit code of 1"
filename1 = raw_input("Enter the first filename: ") 
filename2 = raw_input("Enter the second filename: ") 
nelementsInRecord = input("Enter the number of elements in each record: ")
nelem = int(nelementsInRecord)

print "Do you have 4 byte or 8 byte values in the file?"
nbytes = input("Enter 4 or 8  :  " )
nb = int(nbytes)

if nb == 4:
   type = 'f4'
elif nb == 8:
   type = 'f8'
else:
   print "You have to choose either 4 or 8"
   exit()

print "Do you want to stop at the first difference, or see ALL of the differences?"
showAllOrOne = input("Enter 1 to stop at the first one or 2 to see ALL of the differences:")

dt = np.dtype(type)
f1 = open(filename1, "rb")
f2 = open(filename2, "rb")
ct=1
same = True

size1 = os.stat(filename1).st_size
size2 = os.stat(filename2).st_size

print '   '

if size1 != size2:
    print "DIFFERENT: Size of the files is not the same"
    print 'size 1: ' + str(size1)
    print 'size 2: ' + str(size2)
    exit(1)

while True:
   ct = ct + 1
   #  x1 is an array of 4 or 8  byte floats, with nelem elements
   x1 = np.fromfile(f1, dtype=dt, count=nelem)
   #  x2 is an array of 4 or 8  byte floats, with nelem elements
   x2 = np.fromfile(f2, dtype=dt, count=nelem)
   if x1.size == nelem and x2.size == nelem:
      for i in range(0,nelem):
         if x1[i] != x2[i]:
             print "Difference in record " + str(ct-1)
             print "File 1 value: ",x1[i],"  File 2 value: ",x2[i]
             same = False
             if showAllOrOne == 1:
                print "Files are DIFFERENT"
                print "Stopping at the first difference."
                exit(1)
   elif x1.size != x2.size:
      print "Difference in size of record " + str(ct-1)
      if showAllOrOne == 1:
         print "Files are different.  Stopping."
         exit(1)
   # You have reached the end of the file
   else:
      if same == True:
         print "Files are the SAME"
         print "Number of records in each file: ", ct-1
         print "Size of each record: ",nelem*nb," bytes"
         print "Size of each file: ",size1," bytes"
         break 
      else:
         print "Files are DIFFERENT"
         break

