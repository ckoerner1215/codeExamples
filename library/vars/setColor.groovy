#!/usr/bin/env groovy
import com.bana.pipeline.jenkinslib.Color

def call(String colorChoice = 'red') {
    def color = new Color(colorChoice)
    print(color.set())
}
