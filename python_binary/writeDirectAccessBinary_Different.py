import numpy as np

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

filename = "DirectAccess_"+str(nelementsPerRecord)+"x"+str(nlines)+"_different.bin"+str(nb)

f = open(filename, 'w+b')

dt = np.dtype(type)
ndarray = np.zeros((nlines,nelementsPerRecord))

array = []
diff_array = []
for i in range (0,nelementsPerRecord):
       array.append(float(i))
       diff_array.append(float(i)+1)
for j in range (0,nlines):
     if j == 2:
       diff_array[0] = float(j)
       print diff_array
       np.asarray(diff_array,dtype=type).tofile(f)
     else:
       array[0] = float(j)
       print array
       np.asarray(array,dtype=type).tofile(f)

print "New direct access file is called: ",filename

