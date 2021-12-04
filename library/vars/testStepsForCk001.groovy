#!/usr/bin/env groovy

def call(String module) {
   testLabel()
   setColor('purple')
   sh """
        cd ${module}/TEST
        make -f makeCompare clean
        make -f makeCompare
        ./runCompareExecutable
    """
    unsetColor()
}
