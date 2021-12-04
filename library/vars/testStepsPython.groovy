#!/usr/bin/env groovy

def call(String module) {
  echo "This is module ${module}."
  sh """
        cd ${module}
        python ../tools/CompareTextFiles.py ${module}-out.txt CORRECT_OUTPUT/${module}-out.txt
    """
}
