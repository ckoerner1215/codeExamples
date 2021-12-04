#*
#* Makefile to create the convert_deg library
#*
#*  convert_deg library has the following routines-
#*     deg2dms - converts decimal degrees to 
#*               degrees, minutes, and seconds
#*
#*     deg2rad - converts decimal degrees to radians
#*
#*     dms2deg - converts degrees, minutes, seconds
#*               to decimal degrees
#*
#*     rads2deg - converts radians to decimal degrees
#*

include make.inc

all: libconvert_deg

libconvert_deg:
	mkdir -p ./lib
	mkdir -p ./objs
	( cd ./src; $(MAKE) clean; $(MAKE) )

clean:
	( cd ./src; $(MAKE) clean; )
