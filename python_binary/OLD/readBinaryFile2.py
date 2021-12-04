import numpy as np

dt = np.dtype('f4')
f = open("binaryfile.bin", "rb")
count=-1
while True:
   #  x is a 2 element array of 4 byte floats
   x = np.fromfile(f, dtype=dt, count=2)
   print x
   count = count + 1
   if x.size < 2:
      print count
      break 

