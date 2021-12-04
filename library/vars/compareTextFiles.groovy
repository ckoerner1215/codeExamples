#!/usr/bin/env groovy

def call(String module, String file1, String file2) {
  testLabel()
  setColor('red')
  echo "Comparing the text output files for module ${module}."
  println(file1)
  println(file2)

  sh """
        cd ${module}
        python ../tools/CompareTextFiles.py ${file1} ${file2}
    """
  unsetColor()
}
