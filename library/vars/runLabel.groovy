#!/usr/bin/env groovy
import com.bana.pipeline.jenkinslib.Color

def call() {
    def color = new Color('on_green')
    print(color.set())
    print("[Run]")
    print(color.unset())
}
