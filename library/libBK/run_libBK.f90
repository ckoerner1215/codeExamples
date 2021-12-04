program run_libBK
        use mult_mod
        use div_mod
        implicit none

        character*32 text
        real :: real_answer

        text = 'Welcome to fun with math. '
        write(*,*) text
        call version()

        print "(a8,i2)", "5 * 4 = ", product(5,4)
        real_answer = product(10.0,4.0)

        print "(a12,f6.2)", "10.0 * 4.0 = ", real_answer

        print *, "4 divided by 2 = ", quotient(4,2)

        real_answer = quotient(4.0,2.0)
        print *, "4.0 divided by 2.0 = ", real_answer

        print *, "Math classroom  has now completed. "

end program run_libBK
