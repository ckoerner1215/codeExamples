C     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
C     *                                                               *
C     *                  copyright (c) 2011 by UCAR                   *
C     *                                                               *
C     *       University Corporation for Atmospheric Research         *
C     *                                                               *
C     *                      all rights reserved                      *
C     *                                                               *
C     *                     FFTPACK  version 5.1                      *
C     *                                                               *
C     *                 A Fortran Package of Fast Fourier             *
C     *                                                               *
C     *                Subroutines and Example Programs               *
C     *                                                               *
C     *                             by                                *
C     *                                                               *
C     *               Paul Swarztrauber and Dick Valent               *
C     *                                                               *
C     *                             of                                *
C     *                                                               *
C     *         the National Center for Atmospheric Research          *
C     *                                                               *
C     *                Boulder, Colorado  (80307)  U.S.A.             *
C     *                                                               *
C     *                   which is sponsored by                       *
C     *                                                               *
C     *              the National Science Foundation                  *
C     *                                                               *
C     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
C
      PROGRAM SinFFT
      IMPLICIT NONE
C
      INTEGER I, N, LENSAV, IER, LENWRK
      PARAMETER(N=512) 
C     PARAMETER(LENSAV= N/2 + N + INT(LOG(REAL(N))/LOG(2.)) + 4)
      PARAMETER(LENSAV= 1513)
      PARAMETER(LENWRK=2*N+2)
      REAL R(N), RCOPY(N)
      REAL WSAVE(LENSAV), WORK(LENWRK), DIFF
      real pi,f
      integer t(N)

      open(7,file='original.txt')
      open(8,file='fft.txt')
C
C --- INITIALIZE FFT 
C
      WRITE(6,*) '******PROGRAM SinFFT******'
      print *,'Adding something to test Jenkins 2:00...'
      CALL SINT1I (N,WSAVE,LENSAV,IER)
      IF (IER.NE.0) THEN
         WRITE(6,*) 'ERROR ',IER,' IN ROUTINE SINT1I'
         STOP
      END IF
C
C --- GENERATE TEST VECTOR FOR FORWARD Fourier Transform
C
c      CALL RANDOM_SEED()
c      CALL RANDOM_NUMBER(R)
c      RCOPY = R
      pi=4.0*ATAN(1.0)
      f=0.25
      do i=0,N-1
        t(i)=i
      end do
      do i=1,N
c        r(i)=sin(2*pi*t(i)*f)
        r(i)=cos(2*pi*t(i)*f)
      end do
      rcopy=r
C
C --- PERFORM FORWARD TRANSFORM
C
      CALL SINT1F (N,1,R,N,
     .             WSAVE,LENSAV,WORK,LENWRK,IER)
      IF (IER.NE.0) THEN
         WRITE(6,*) 'ERROR ',IER,' IN ROUTINE SINT1F !'
         STOP
      END IF

C
C --- PERFORM BACKWARD TRANSFORM
C
      CALL SINT1B (N,1,R,N,
     .             WSAVE,LENSAV,WORK,LENWRK,IER)
      IF (IER.NE.0) THEN
         WRITE(6,*) 'ERROR ',IER,' IN ROUTINE SINT1B !'
         STOP
      END IF
C
C --- PRINT TEST RESULTS
C
      IER = 0
      DIFF = 0.
      DO I=1,N
         DIFF = MAX(DIFF,ABS(R(I)-RCOPY(I)))
      END DO
      WRITE(6,*) 'SINT1 FORWARD-BACKWARD MAX ERROR =',DIFF

C
C --- PRINT RESULTS
C
      DO I=1,N
         write(7,*)i,RCOPY(i)
         write(8,*)i,R(i)
      END DO

      close(7)
      close(8)

      if(DIFF .gt. 10) then
         write(6,*)'Forward-Backward error is too big - FAIL :('
         call exit(1)
      else
         write(6,*)'FFT program working as expected - SUCCESS!!'
         call exit(0)
      end if
      WRITE(6,'(A,/)') ' END PROGRAM '
      STOP
      END
