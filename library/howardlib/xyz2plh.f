      subroutine xyz2plh(a,finv,dlat,dlon,h,x,y,z)
      implicit none
      ! subroutine to compute latitude, longtidude and height above a
      !  reference ellipsoid given cartersian coordinates.
      !
      !  inputs:  x, y, z coordinates referenced to the ref. ellip.
      !             a - semimajor axis of ref. ellip. in meters
      !          finv - inverse flattening of ref. ellip.
      !
      ! outputs: dlat - latitude in decimal degrees
      !          dlon - longitude indecimal degrees
      !             h - height above ref. ellip in meters
      !
      ! This is a non-linear problem and must be iterated.
      !
      ! This code was copied from SU612 class notes provided by Clyde
      !   Goad, 1990, at The Ohio State Univerity. Go BUCKEYES!

       real*8 a, finv, dlat, dlon, h, x, y, z
       real*8 dtr,pi,N,e2,flat,p,dsinlat,tolsq,rtd,p2,r,dcoslat,
     .        dp, dz
       integer*8 iter,maxit

      ! compute/set constants
        pi = 4.d0*datan(1.d0)
        dtr = pi/180.d0
        rtd = 1.d0/dtr
        flat = 1.d0/finv
        maxit = 10        ! max iterations
        tolsq = 1.d-10    ! convergence tolerance

      ! eccentricity squared
        e2 = (2.d0 - flat)*flat

      ! compute longitude directly (0-360)
        dlon = datan2(y,x)*rtd
       ! if (dlon .lt. 0.d0) dlon = dlon+360.d0

      ! set some initial conditions
      ! P is distance to point from z-axis
        p2 = x*x +y*y
        p = dsqrt(p2)

      ! r is distance from origin
        r = dsqrt(p2 + z*z)
        dsinlat = z/r
        dlat = dasin(dsinlat)

      ! initial value of height = distance from origin minus approx.
      ! distance from origin to surface of ellipsoid
        h = r-a*(1.d0-flat*dsinlat*dsinlat)

      ! iterate
        do 100 iter=1,maxit
            dsinlat = dsin(dlat)
            dcoslat = dcos(dlat)

      ! compute radius of curvature in prime vertical
            N = a/dsqrt(1.d0 - e2*dsinlat*dsinlat)

      ! compute residuals in p and z
            dp = p - (N + h)*dcoslat
            dz = z - (N*(1.d0 - e2) + h)*dsinlat

      ! update height and latitude
            h = h + (dsinlat*dz + dcoslat*dp)
            dlat = dlat + (dcoslat*dz - dsinlat*dp)/(N+h)

      ! test for convergence
            if (dp*dp + dz*dz .lt. tolsq)then
                dlat=dlat*rtd
                return
            endif
 100    continue

      ! non-convergence warning
        write(*,101)maxit
 101    format(' Problem in xyz2plh: did not converge in ',i2,
     .' iterations.')
      return
      end subroutine xyz2plh
