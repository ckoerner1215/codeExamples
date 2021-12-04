README_libBK.txt


HOW TO Build library.
	See Francis Gaegler's library_config.docx file, under the Team's File tab within Microsoft Teams.


For additional reading on Fortran libraries:
	https://docs.oracle.com/cd/E19957-01/805-4940/6j4m1u7ov/index.html


Fortran Library:	libBK


Library Elements/Modules (2 modules)

	* mult_mod	// mult_mod.f90, mult_mod.mod, mult_mod.o
		MODULE DESCRIPTION:
		A Fortran module which returns the product of type integer, when passed 2 integer numbers as arguments
		or returns the product of type real, when passed 2 real numbers as arguments.

	* div_mod	// div_mod.f90, div_mod.mod, div_mod.o
		MODULE DESCRIPTION:
		A Fortran module which returns the quotient of type integer, when passed 2 integer numbers as arguments
		or returns the quotient of type real, when passed 2 real numbers as arguments.


Library Files (10 files)
	* libBK.a		// Fortran Static Library; created from Object files (.o files)
	* mult_mod.f90		// Fortran Source file
	* mult_mod.mod		// Module file created from Source file
	* mult_mod.o		// Object file created from Source file
	* div_mod.f90		// Fortran Source file
	* div_mod.mod		// Module file created from Source file
	* div_mod.o		// Object file created from Source file
	* a.out			// Resulting Output file, resulting from Fortran library
	* run_libBK.f90		// a Fortran program which calls the libBK library
	* README_libBK.txt	// README file for Fortran library libBK


Library Module Interfaces
	* product
	* quotient
	* version


HOW TO Use this library.
	NOTE:
	To use this library, one only needs the libBK.a file BUT does require a workable understanding of the .f90 module
		files used to create the library.
	Step 1. Within your program file, reference a module from the libBK library.
			use mult_mod
	Step 2. Also from your program file, use the module's interface to call a procedure either of the lbrary's modules.
			var_int = product(4,2)		// calls a function; returns an integer value
			var_float = product(4.0,2.0)	// calls a function; returns a real value
			call version()			// calls a subroutine; does not return a value
	Step 3. Compile and your program, while including the libBK library and directory location in your commandline command.
			gfortran run_libBK.f90 -L /apps/barak/libBK -lBK
	Step 4. Run the resulting a.out file.
			./a.out

