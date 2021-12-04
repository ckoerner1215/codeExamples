      REAL*4 FUNCTION rads2deg(in_rads)
*
*  This program will convert entered radians to
*  degrees
*

       REAL*4 in_rads

       rads2deg = ((in_rads * 180.0) / 3.1415927)
       RETURN

       END

