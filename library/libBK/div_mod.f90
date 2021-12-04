! Fortran module
! This appears to work; unsure if best way
! gfortran <program>.f90 <mod>.f90
! ./a.out

! Do This
! gfortran -c mult_mod.f90 hello.f90
! gfortran mult_mod.o hello.o
! ./a.out
module div_mod
        implicit none
        private
        public :: quotient
        public :: version

        interface quotient
                procedure divi_real, divi_int
        end interface quotient

        interface version
                procedure vers
        end interface version

        contains
                real function divi_real(n1, n2)
                        real, intent(in) :: n1, n2
                        real :: quot
                        quot = n1 / n2
                end function divi_real

                integer function divi_int(n1, n2)
                        integer, intent(in) :: n1, n2
                        integer :: quot
                        quot = n1 / n2
                end function divi_int

                subroutine vers()
                        print *, "div_mod version 1.0 "
                end subroutine vers
end module div_mod
