#!/usr/bin/env groovy
import com.bana.pipeline.jenkinslib.HelloWorld

//default value of 'human' for the name variable
def call(String name = 'human') {
  def hello = new HelloWorld('Carolyn')
  println(hello.printOutHello())

  echo "Hello, ${name}."
  echo "This is from the shared library in the library repo..."
}
