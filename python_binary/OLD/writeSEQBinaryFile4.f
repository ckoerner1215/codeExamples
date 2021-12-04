c********************************************************
c Write a Sequential binary file
c
c Output Files:
c       Sequential.bin       Unit 11       direct access binary file
c       Contains rows of 4 byte reals.
c       Set the number of elements in a record with nelementsPerRecord
c       Set the number of records in the file with nlines
c
c********************************************************

        program writeSEQBinaryFile4
           real *4,dimension(:), allocatable :: block
           integer :: nlines, i,j,k
           integer :: nelementsPerRecord,recLengthInBytes
           integer, parameter :: nbytes=4
           character(len=4) string1, string2

           character (len=:), allocatable :: filename_trim
           character(10), parameter :: fname1="Sequential"
           character(50) :: fname2

           print*,"Enter the number of records you want in the file: "
           read (*,*) nlines

           print*,"Enter the number of elements in each record: "
           read (*,*) nelementsPerRecord

           recLengthInBytes = nelementsPerRecord*nbytes
           allocate(block(nelementsPerRecord))

           write (string1,'(I4)') nelementsPerRecord
           write (string2,'(I4)') nlines

           fname2 = fname1//"_"//trim(adjustl(string1))//"x"//
     &      trim(adjustl(string2))//".bin4"
           filename_trim = trim(fname2)
           open(unit=11, file=filename_trim, access="sequential",
     &       action="write",form="unformatted")

           do i=1,nlines
              do j=1,nelementsPerRecord
                 block(j)=3.15888*i
              end do
              write(11) (block(k),k=1,nelementsPerRecord)
           end do
           close(11)
           print*,"The name of your new sequential ",
     &             "4 byte binary file is  :  ",
     &        filename_trim
     
        end program writeSEQBinaryFile4

