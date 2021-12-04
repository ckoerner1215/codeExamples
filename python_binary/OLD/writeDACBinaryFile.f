c********************************************************
c Write a direct access binary file
c
c Output Files:
c       DirectAccess.bin       Unit 11       direct access binary file
c       Contains rows of 4 byte reals.
c       Set the number of elements in a record with nelementsPerRecord
c       Set the number of records in the file with nlines
c
c********************************************************

        program writeDACBinaryFile
           real *4,dimension(:), allocatable :: block
           integer :: nlines, i,j,k
           integer :: nelementsPerRecord,recLengthInBytes
           integer, parameter :: nbytes=4
           character(len=4) string1, string2

           character (len=:), allocatable :: filename_trim
           character(12), parameter :: fname1="DirectAccess"
           character(80) :: fname2

           print*,"Enter the number of records you want in the file: "
           read (*,*) nlines

           print*,"Enter the number of elements in each record: "
           read (*,*) nelementsPerRecord

           recLengthInBytes = nelementsPerRecord*nbytes
           allocate(block(nelementsPerRecord))

           write (string1,'(I4)') nelementsPerRecord
           write (string2,'(I4)') nlines

           fname2 = fname1//"_F_"//trim(adjustl(string1))//"x"//
     &      trim(adjustl(string2))//".bin"
           filename_trim = trim(fname2)
           open(unit=11, file=filename_trim, access="direct",
     &       action="write",form="unformatted", recl=recLengthInBytes)

           do i=1,nlines
              block(1)=1.0*i
              do j=2,nelementsPerRecord
                 block(j)=1.0*(j-1)
              end do
              write(11,rec=i) (block(k),k=1,nelementsPerRecord)
           end do
           close(11)
           print*,"The name of your new direct access ",
     &        " binary file is :   ",
     &        filename_trim
           print*,"Note that the values in the file ",
     &        "are 4 byte reals."


     
        end program writeDACBinaryFile

