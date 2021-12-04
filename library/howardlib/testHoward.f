      program testHoward
        real*8 a,finv,dlat,dlon,h,x,y,z
        a = 6378137.d0
        finv = 298.257223563d0
        dlat = 38.591244
        dlon =  -90.2073855
        h = 0.0
        x = 0
        y = 0
        z = 0
        call plh2xyz(a,finv,dlat,dlon,h,x,y,z)
        print *, x,y,z

        call xyz2plh(a,finv,dlat,dlon,h,x,y,z)
        print *, dlat,dlon,h
      end program testHoward
