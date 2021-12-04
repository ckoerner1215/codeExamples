import numpy as np

dt = np.dtype('f4')
f = open("c537input.dac", "rb")
ct=1
while True:
   #  x is a 25 element array of 4 byte floats
   x = np.fromfile(f, dtype=dt, count=25)
   if 30597 <= ct <= 30604:
      print ct
      print x
   ct = ct + 1
   if x.size < 25:
      print ct
      break 

