import numpy as np

filename = raw_input("Enter the Direct Access Binary filename: ") 
nelementsInRecord = input("Enter the number of elements in each record: ")
nelem = int(nelementsInRecord)

print "Do you have 4 byte or 8 byte values in the file?"
nbytes = input("Enter 4 or 8:")
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
while True:
   recordString = input("Enter the record number (starting from 1) you would like to read (0 to quit): ")
   record = int(recordString)
   if record > 0:
      byte_offset = nb*nelem*record - nb*nelem
      f.seek(byte_offset)
      x = np.fromfile(f,dtype=dt, count=nelem)
      print "Record ",record," is: "
      print x
   else:
      break
