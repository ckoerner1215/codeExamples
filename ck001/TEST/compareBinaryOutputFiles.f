c******************************************************
c  Compare two sequential binary files
c     Input: stdin
c          file1 file2
c
c******************************************************
        program compareBinaryOutputFiles
        real *4,dimension(:), allocatable :: block1
        real *4,dimension(:), allocatable :: block2
        character*120 file1, file2
        logical same /.true./

        kin1=12
        kin2=13
        read(5,*) file1, file2
        print *,'  '
        print *,"Files: ",file1, file2
        call findNumberOfWordsPerRecordInFile(file1,numberOfWords1)
        print *,'Number of words per record in file1: ',numberOfWords1
        call findNumberOfWordsPerRecordInFile(file2,numberOfWords2)
        print *,'Number of words per record in file2: ',numberOfWords2

        if(numberOfWords1 .ne. numberOfWords2) then
            print *,'Test failed: Number of elements per record differs'
            print *,numberOfWords1,NumberOfWords2
            go to 101
        end if
        nelements=numberOfWords1

        open(kin1,file=file1,form='unformatted')
        open(kin2,file=file2,form='unformatted')
 
        allocate(block1(nelements))
        allocate(block2(nelements))

        irecord=1
   5    continue
        read(kin1, iostat=iostatnew1)block1
        if(iostatnew1 .lt. 0 .or. iostatnew1 .gt. 0) then
          go to 99
        else
          continue
        end if

        read(kin2, iostat=iostatnew2)block2
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
  10     continue
         irecord=irecord+1
         go to 5
  99     print *,'Done reading ',irecord,' records in the file.'
 101     close(kin1)
         close(kin2)

         if(same .eqv. .true.) then
              print *,'Test Success: Files match'
              call exit(0)
         else
              print *,'Test Failed: Files do not match'
              call exit(1)
         end if

         stop
         end

         subroutine findNumberOfWordsPerRecordInFile(infile,nelem)
                   integer nelem
                   character*120 infile
                   integer*4 bookend
                   character bytes(4)
                   equivalence(bookend,bytes)
                   integer*4 bookend2
                   character bytes2(4)
                   equivalence(bookend2,bytes2)

                   open(unit=12,file=infile,form='unformatted',
     &              status='old', access='direct',recl=4)
                   read(12,rec=1)bookend
                   bytes2(4)=bytes(1)
                   bytes2(3)=bytes(2)
                   bytes2(2)=bytes(3)
                   bytes2(1)=bytes(4)
                   if(abs(bookend).GT.abs(bookend2))then
                      nelem=bookend2/4
                   else
                      nelem=bookend/4
                   endif
                   close(12)
         end

