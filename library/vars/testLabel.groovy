#!/usr/bin/env groovy
import com.bana.pipeline.jenkinslib.Color

def call() {
    def color = new Color('on_blue')
    print(color.set())
    print("[Test]")
    print(color.unset())
}
