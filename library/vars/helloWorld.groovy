#!/usr/bin/env groovy
import com.bana.pipeline.jenkinslib.HelloWorld

def call(String name = 'human') {
    def helloworld = new HelloWorld(name)
    println("This is calling HelloWorld from the src directory in the shared library")
    String output = helloworld.printOutHello()
    println(output);
}
