from scipy.io import FortranFile
f = FortranFile('test.unf', 'w')
f.write_record(np.array([1,2,3,4,5], dtype = np.int32))
#f.write_record(np.linspace(0,1,20).reshape(5,4)).T)
f.close()

