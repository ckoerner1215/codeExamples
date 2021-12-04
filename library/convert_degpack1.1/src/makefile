
include ../make.inc

SRC=deg2dms.f deg2rad.f dms2deg.f rads2deg.f

OBJ=$(subst .f,.o,$(SRC))
OBJS=$(addprefix ../objs/,$(OBJ))

$(LIB) : $(OBJS)
	$(AR) -rv $@ $? 

../objs/%.o : %.f
	$(F90) -c $< -o $@

clean:
	rm -f $(LIB) $(OBJS) 
