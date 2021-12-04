#!/usr/bin/env groovy

def call(String module, Boolean forceBuild, String changes) {
	if(changes == 'yes'){
	    setColor('on_yellow')
	    echo '*******************************************************'
	    echo 'Changes were made to the module so everything was built'
	    echo '*******************************************************'
	} else if (forceBuild){
	    setColor('on_cyan')
	    echo '*******************************************************'
	    echo 'Module was built on demand'
	    echo '*******************************************************'
	} else {
	    setColor('white_on_black')
	    echo '*******************************************************'
	    echo "No changes to the module so everthing was skipped"
	    echo '*******************************************************'
	}
	unsetColor()
	echo ' '
	setColor('black')
	echo "Done with module ${module}"
}
