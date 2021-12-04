#!/usr/bin/env groovy

def call(String module, String file1, String file2) {
   testLabel()
   setColor('purple')
   echo "Comparing the binary output files for module ${module}."
   println(file1)
   println(file2)
   sh """
        cd tools
        make clean -f makeCompareBinary
        make -f makeCompareBinary
        cd ../${module}
        echo '"'${file1}'" "'${file2}'"'  | ../tools/CompareBinaryFiles.exe
    """
    unsetColor()
}
