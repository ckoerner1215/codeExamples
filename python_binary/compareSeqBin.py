#!/usr/bin/python
import sys
import struct
import os

#**********************************************************
# Compares 2 sequential binary files.
# If the two files are the same this code will exit with an
# exit code of 0.
# If the two files are different this code will exit with an
# exit code of 1.
#**********************************************************

different = False
#Only deal with 4 byte values
nb=4

print "This Python script will compare two sequential binary files."
print "If they are the same, it will output SAME and have an exit code of 0"
print "If they are different, it will output DIFFERENT and have an exit code of 1"

filename1 = raw_input("Enter the first filename: ") 
print "Was this file written on a little or big endian machine?"
print "AIX: Big endian - Enter 1"
print "Intel: Little endian - Enter 2"
endian1 = input("Enter 1 for AIX or 2 for Intel:")

filename2 = raw_input("Enter the second filename: ") 
print "Was this file written on a little or big endian machine?"
print "AIX: Big endian - Enter 1"
print "Intel: Little endian - Enter 2"
endian2 = input("Enter 1 for AIX or 2 for Intel:")

if endian1 == 1:
    iformat1=">i"
else:
    iformat1="<i"

if endian2 == 1:
    iformat2=">i"
else:
    iformat2="<i"

print "Do you want to stop at the first difference, or see ALL of the differences?"
showAllOrOne = input("Enter 1 to stop at the first one or 2 to see ALL of the differences:")

#Code is expecting filename1 and filename 2 as command line arguments
#parser = argparse.ArgumentParser()
#parser.add_argument("filename1", help="Name of  file 1")
#parser.add_argument("filename2", help="Name of  file 2")
#args = parser.parse_args()
#print args.filename1
#print args.filename2
#filename1 = args.filename1
#filename2 = args.filename2

f1=open(filename1,"rb")
f2=open(filename2,"rb")

size1 = os.stat(filename1).st_size
size2 = os.stat(filename2).st_size

print '   '

if size1 != size2:
    print "DIFFERENT: Size of the files is not the same"
    print 'size 1: ' + str(size1)
    print 'size 2: ' + str(size2)
    exit(1)
else:
   print("Length of files are the same - so far so good!!")

#read first bookend in record
fileContent1 = f1.read(4)
fileContent2 = f2.read(4)
ct=1
different = False

while fileContent1 and fileContent2:
    header1 = struct.unpack(iformat1,fileContent1)[0]
    header2 = struct.unpack(iformat2,fileContent2)[0]
    nelem1 = header1/nb
    nelem2 = header2/nb
    format1='f'*nelem1
    format2='f'*nelem2
    byterecord1 = f1.read(header1)
    byterecord2 = f2.read(header2)
    record1 = struct.unpack(format1,byterecord1)
    record2 = struct.unpack(format2,byterecord2)
    print ct,record1, record2
    for n1,n2 in zip(record1,record2):
       diff=abs(n1-n2)
       print n1,n2
       if diff < 0.000000000001:
          print("Difference is small - ignore  :" + str(diff))
       else:
          print("Difference is large: " + str(diff))
          different = True
          if showAllOrOne == 1:
             print "Files are different.  Stopping."
             exit(1)
    #read last bookend in record
    fileContent1 = f1.read(4)
    fileContent2 = f2.read(4)
    #read first bookend in next record
    fileContent1 = f1.read(4)
    fileContent2 = f2.read(4)
    ct=ct+1

f1.close()
f2.close()
if different == True:
   print ("These 2 files have significant differences    :(")
   exit(1)   
else:
   print ("These 2 files are the same      :)")
   exit(0)

