#!/usr/bin/env groovy
import java.util.Map

def call(String module) {
  buildLabel()
  setColor('cyan')
  sh """
        cd ${module}/sccs
        make clean
        make all
        make install
        cd ..
    """
    unsetColor()
}
