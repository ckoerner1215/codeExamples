#!/usr/bin/env groovy
import java.util.Map

def call(String module) {
  buildLabel()
  setColor('cyan')
  sh """
        cd ${module}
        make clean
        make all
    """
    unsetColor()
}
