#!/usr/bin/env groovy
import com.bana.pipeline.jenkinslib.Color

def call() {
    def color = new Color()
    print(color.unset())
}
