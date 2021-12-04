       SUBROUTINE DMS2DEG(deg, minit, sec, out_dd)
*
*  This program will convert degrees, minutes,
*  seconds to decimal degrees.
*  For example, 30 degrees 15 minutes 50 seconds
*  would convert to 30.263888889 decimal degrees.
*

       REAL*4  out_dd
       INTEGER deg, minit,sec

       out_dd = (deg + (minit/60.0) + (sec/3600.))

       RETURN

       END
