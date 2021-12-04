#!/usr/bin/env groovy

def call(String module) {
    runLabel()
    setColor('green')
    sh """
        cd ${module}
        ./${module}.sh
    """
    unsetColor()
}
