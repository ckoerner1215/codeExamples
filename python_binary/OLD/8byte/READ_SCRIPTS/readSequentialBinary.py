import numpy as np

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
ct=1
nbr=0
ne=0
while True:
   nbytes = np.fromfile(f, dtype=i4, count=1)
   nelem = nbytes/nb
   if nelem.size > 0:
      n = nelem[0]
      x = np.fromfile(f, dtype=dt, count=n)
      if x.size == n:
         print ct, x
         nbr = nbytes
         ne = nelem
         ct = ct + 1
         bookend = np.fromfile(f,dtype=i4, count=1)
      if x.size < n:
         print "Bookend does not match size of record"
         print "Something is wrong with the file"
         print "Record number: ", ct-1
         break
   else:
      print "Number of bytes in a record: ",nbr
      print "Number of elements in a record: ",ne
      print "Number of records in file : ",ct-1
      break 
