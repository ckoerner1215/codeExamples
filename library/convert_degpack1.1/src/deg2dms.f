      SUBROUTINE DEG2DMS(in_dd, deg, minit, secs)
*
*  This program will decimal degrees to
*  degrees, minutes, seconds.
*  For example,  30.263888889 degree angle
*  would convert to 30 degrees 15 minutes 50 seconds.
*

       REAL*4  in_dd, sec
       INTEGER deg, minit,secs

       deg = INT(in_dd)
       minit = INT((in_dd - deg) * 60)
       sec = ((in_dd - deg) - (minit/60.0)) * 3600
       secs = INT(sec)

       RETURN

       END

