import numpy as np

filename = raw_input("Enter the Direct Access Binary filename: ") 
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

dt = np.dtype(type)
f = open(filename, "rb")
ct=1
while True:
   #  x is an array of 4 byte floats, with nelem elements
   x = np.fromfile(f, dtype=dt, count=nelem)
   if x.size == nelem:
      print ct, x
      ct = ct + 1
   if x.size < nelem:
      print "Number of records in file: ", ct-1
      print "Number of bytes in a record: ",nelem*nb
      break 


