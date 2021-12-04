
	program average

! Read in some numbers and take the average.
! As written, if there are no data points, an average of zero is returned.
! While this may not be desired behavior, it keeps this example simple.

	  implicit none
	  integer :: number_of_points
         integer sum,ierr,io

	  real, dimension(:), allocatable :: points
	  real :: average_points=0., positive_average=0.,
     &       negative_average=0.
         character (len=200) :: header

         print *,'Starting ck100 - program Average'

         open (unit=10,file="ck100-out.txt",action="write")


         write(*,*) 'Enter the number of points: '
	 read (*,*) number_of_points

         write(*,*) number_of_points

	  allocate (points(number_of_points))
         write(*,*) 'Enter the points separated by spaces: '
	  read (*,*) points

         write(*,*) points

! Take the average by summing points and dividing by number_of_points
	  if (number_of_points > 0) average_points =
     &        sum(points)/number_of_points

! Now form average over positive and negative points only
	  if (count(points > 0.) > 0) positive_average =
     &      sum(points, points > 0.) / count(points > 0.)
	  if (count(points < 0.) > 0) negative_average =
     &      sum(points, points < 0.) / count(points < 0.)

	  deallocate (points)

! Print result to Unit 10
	  write (10,'(''Average = '', 1g12.4)') average_points
	  write (10,'(''Average of positive points = '', 1g12.4)')
     &         positive_average
	  write (10,'(''Average of negative points = '', 1g12.4)')
     &        negative_average

	  close(10)

	  print *,'Done with program Average - module ck100'
	  print *,'See ck100-out.txt for output'

	end program average

