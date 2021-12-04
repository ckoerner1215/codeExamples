package com.bana.pipeline.jenkinslib
import com.bana.pipeline.jenkinslib.Colors

class Color implements Serializable {
    private String _color

    Color(){
       _color = 'nc'
    }

    Color(String color) {
        _color = color
    }

    public String getColor(){
       return _color;
    }

    public String set() {

       def colorMap = [:]
	    colorMap.put('black',Colors.BLACK);
	    colorMap.put('red',Colors.RED);
	    colorMap.put('green',Colors.GREEN);
	    colorMap.put('yellow',Colors.YELLOW);
	    colorMap.put('blue',Colors.BLUE);
	    colorMap.put('purple',Colors.PURPLE);
	    colorMap.put('cyan',Colors.CYAN);
	    colorMap.put('white',Colors.WHITE);
	
            colorMap.put('bred',Colors.BRED);
            colorMap.put('bblue',Colors.BBLUE);
            colorMap.put('bgreen',Colors.BGREEN);
            colorMap.put('bpurple',Colors.BPURPLE);
            colorMap.put('bcyan',Colors.BCYAN);
            colorMap.put('bblack',Colors.BBLACK);
            colorMap.put('bwhite',Colors.BWHITE);   
     	
	    colorMap.put('on_black',Colors.ON_BLACK);
	    colorMap.put('on_red',Colors.ON_RED);
	    colorMap.put('on_green',Colors.ON_GREEN);
	    colorMap.put('on_yellow',Colors.ON_YELLOW);
	    colorMap.put('on_blue',Colors.ON_BLUE);
	    colorMap.put('on_purple',Colors.ON_PURPLE);
	    colorMap.put('on_cyan',Colors.ON_CYAN);
	    colorMap.put('white_on_black',Colors.WHITE_ON_BLACK);
	
        colorMap.put('no_color',Colors.NC);

       String output = colorMap.get(_color.toLowerCase());      
       if(output == null){
		output = Colors.BLACK;
       }

       return output;    
    }

    public String unset() {
       String output = Colors.NC;
       return output;
    }
}
