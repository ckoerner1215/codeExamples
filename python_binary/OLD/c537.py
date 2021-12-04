import numpy as np


def loops(n,s,w,e,f,inputfile,dt):
   nr=0
   for i in range(n,s+1,-1):
      xlt=float(i)-0.5
      for j in range(w,e-1,1):
	 xln=float(j)+0.5
	 ic=int(xln)+1+int(90-xlt)*360

     # Position the pointer for reading the file at the 
     # correct byte.  Since there are 25 4 byte numbers
     # in each record, and we want record number ic, then 
     # we have to go to byte ic*100.  But this will put us at 
     # the end of the record, and we want to be at the beginning.
     # So, that is why we subtract 100.
     # So, byte_offset is the byte number that we want to start
     # reading the file at.
	 byte_offset=ic*100-100
	 f.seek(byte_offset)
     # Each record of the binary input file has 25 element arrays of 4 byte floats.
     # Read the record:
	 d = np.fromfile(f, dtype=dt, count=25)

	 print "{0:6.1f}{1:6.1f}{2:6.0f}.{3:6.0f}.{4:12.5f}{5:12.5f}{6:12.5f}".\
	    format(xlt,xln,d[0],d[1],d[2],d[3],d[4])
	 print "{0:18.0f}.{1:6.0f}.{2:12.5f}{3:12.5f}{4:12.5f}".\
	    format(d[5],d[6],d[7],d[8],d[9])
	 print "{0:18.0f}.{1:6.0f}.{2:12.5f}{3:12.5f}{4:12.5f}".\
	    format(d[10],d[11],d[12],d[13],d[14])
	 print "{0:18.0f}.{1:6.0f}.{2:12.5f}{3:12.5f}{4:12.5f}".\
	    format(d[15],d[16],d[17],d[18],d[19])
	 print "{0:18.0f}.{1:6.0f}.{2:12.5f}{3:12.5f}{4:12.5f}".\
	    format(d[20],d[21],d[22],d[23],d[24])
	 print ' '
	 nr=nr+1
	 if(nr % 3)== 0:
	    ans=inputfile.readline()
	    print ' CONTINUE?'
	    if(ans.strip() == 'N'):
	       return
   

def main():
   #The input file has 4 byte floats in it.
   dt = np.dtype('f4')
   f = open("c537input.dac", "rb")
   inputfile = open("input.dat","r")

   while True:
	   print ' ENTER LIMITS (N,S,W,E) (-99,0,0,0 TO STOP)'
	   firstLine = inputfile.readline()
	   nswe = firstLine.split()
	   n = int(nswe[0])
	   s = int(nswe[1])
	   w = int(nswe[2])
	   e = int(nswe[3])

           if abs(n) <= 90:
                loops(n,s,w,e,f,inputfile,dt)
           else:
                return 
main()

