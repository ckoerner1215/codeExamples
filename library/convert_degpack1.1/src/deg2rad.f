      REAL*4 FUNCTION deg2rad(in_degs)
*
*  This program will convert entered radians to
*  degrees.
*  For example, 30 degrees = 0.5236
*

       REAL*4 in_degs

       deg2rad = (in_degs * (3.1415927 / 180.0))
       RETURN

       END
