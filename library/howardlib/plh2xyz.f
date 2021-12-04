      subroutine plh2xyz(a,finv,dlat,dlon,h,x,y,z)
      ! subroutine to compute cartesian coordinates given latitude,
      !    longtidude and height above a reference ellipsoid.
      !  inputs:    a - semimajor axis of ref. ellip. in meters
      !          finv - inverse flattening of ref. ellip.
      !          dlat - latitude in decimal degrees
      !          dlon - longitude indecimal degrees
      !             h - height above ref. ellip in meters
      ! outputs:  x, y, z coordinates referenced to the ref. ellip.
      !
      ! This code was copied from SU612 class notes provided by Clyde
      !   Goad, 1990, at The Ohio State Univerity. Go BUCKEYES!


       implicit none
       real*8 a, finv, dlat, dlon, h, x, y, z
       real*8 dtr, pi, N, e2, flat, p, dsinlat

      ! compute constants
        pi = 4.d0*datan(1.d0)
        dtr = pi/180.d0
        flat = 1.d0/finv

      ! eccentricity squared
        e2 = (2.d0 - flat)*flat

      ! compute radius of curvature in the prime vertical
        dsinlat = dsin(dlat*dtr)
        N = a/dsqrt(1.d0 - e2*dsinlat*dsinlat)

      ! compute distance to z-axis and the cart. coords.
        p = (N + h)*dcos(dlat*dtr)
        z = (N*(1.d0 - e2) + h)*dsinlat
        x = p*dcos(dlon*dtr)
        y = p*dsin(dlon*dtr)

      return
      end subroutine plh2xyz
