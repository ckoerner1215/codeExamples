        program compareTextOutputFiles
c******************************************************
c  Compare two text files
c     Input: stdin
c          file1 file2
c
c******************************************************
        real *4,dimension(:), allocatable :: block1
        real *4,dimension(:), allocatable :: block2
        character*120 file1, file2
        character*120 title
        logical same /.true./

        kin1=12
        kin2=13
        read(5,*) file1, file2
        print *,'   '
        print *,"Files: ",file1, file2

        nelements=3

        open(kin1,file=file1,form='formatted')
        open(kin2,file=file2,form='formatted')

        allocate(block1(nelements))
        allocate(block2(nelements))

        irecord=1
        read(kin1,fmt=*)title
        read(kin2,fmt=*)title
   5   continue
        read(kin1, fmt=*, iostat=iostatnew1)block1
        if(iostatnew1 .lt. 0 .or. iostatnew1 .gt. 0) then
          go to 99
        else
          continue
        end if

        read(kin2, fmt=*,  iostat=iostatnew2)block2
        if(iostatnew2 .lt. 0 .or. iostatnew2 .gt. 0) then
          go to 99
        else
          continue
        end if

        do 10 i=1,nelements
          if(block1(i) .ne. block2(i)) then
              same = .false.
              print *,'Test Failed: Files differ on record ', irecord,
     &            block1(i),block2(i)
          end if
  10   continue
         irecord=irecord+1
         go to 5
  99   print *,'Done reading ',irecord,' records in the file.'
 101  close(kin1)
      close(kin2)

      if(same .eqv. .true.) then
         print *,'Test Success: Files match'
         call exit(0)
      else
         print *,'Test Failed: Files do not match'
         call exit(1)
      end if

         stop
         end program compareTextOutputFiles
