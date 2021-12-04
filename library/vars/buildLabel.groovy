#!/usr/bin/env groovy
import com.bana.pipeline.jenkinslib.Color

def call() {
    def color = new Color('on_cyan')
    print(color.set())
    print("[Build]")
    print(color.unset())
}
