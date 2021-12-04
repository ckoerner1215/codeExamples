! Fortran module
! This appears to work; unsure if best way
! gfortran <program>.f90 <mod>.f90
! ./a.out

! Do This
! gfortran -c mult_mod.f90 hello.f90
! gfortran mult_mod.o hello.o
! ./a.out
module mult_mod
        implicit none
        private
        public :: product

        interface product
                procedure mult_real, mult_int
        end interface product

        contains
                real function mult_real(n1, n2)
                        real, intent(in) :: n1, n2
                        real :: prod
                        prod = n1 * n2
                end function mult_real

                integer function mult_int(n1, n2)
                        integer, intent(in) :: n1, n2
                        integer :: prod
                        prod = n1 * n2
                end function mult_int

end module mult_mod
