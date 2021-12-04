c********************************************************
c Input Files:
c       input.dat              Unit 5           text file (stdin)
c                 Contains initial a, b, c values
c       llh.bin                   Unit 9    sequential binary file
c                 Contains rows of lat, lon, height values
c
c Output Files:
c       ck001-out.txt        Unit 10       text file
c                 Contains rows of lat, lon, height values
c       ck001-out.bin       Unit 11       sequential binary file
c                 Contains new a, b, c, values
c
c********************************************************

      program ck001
         implicit none

         real*4 a,b
         real*4 c
         real*4 lat,lon,height
         integer i

         read(5,*) a,b,c
         print *,'a b c   ',a,b,c

         open(unit=9, action="read", form="unformatted")
         open (unit=10,file="ck001-out.txt",action="write")
         open(unit=11, file="ck001-out.bin", access="sequential",
     &       action="write",form="unformatted")
         write(10,*) "Values of lat lon height"
         do i=1,10
              read(9) lat,lon,height
              a=a*2
              b=b*2
              c=c+1
              print *, a, b, c, lat, lon, height
              write(10,*) lat, lon, height
              write(11) a, b, c
         end do
         close(9)
         close(10)
      end program ck001
