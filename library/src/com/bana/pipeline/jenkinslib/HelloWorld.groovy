package com.bana.pipeline.jenkinslib

class HelloWorld implements Serializable {
    private String _name

    HelloWorld(String name) {
        _name = name
    }

    public String printOutHello() {
       String output = "HELLO " + _name + "!!!!!!!!"
       return output;    
    }
}
