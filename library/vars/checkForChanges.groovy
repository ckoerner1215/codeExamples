#!/usr/bin/env groovy
import java.util.Map

def call(String module) {
           setColor('red')
           
           //scm is a Global Variable in Jenkins.
           //It represents the SCM (Source Code Management) configuration in a
           //multibranch project build.
           //You may also use this in a standalond project configured with 
           //"Pipeline from SCM", though in that case the checkout will just be
           //of the latest revision in the branch, possibly newer than the revision
           //from which the Pipeline was loaded.
           checkout(scm)
           
           //currentBuild is a Global Variable available in Jenkins.  
           //It may be used to refer to the currently running build.
           println(currentBuild.changeSets) // should print out any changes in the current build
           echo "*******************************************"
            
           def changeLogSets = currentBuild.changeSets
           for (int i = 0; i < changeLogSets.size(); i++) {
           def entries = changeLogSets[i].items
           for (int j = 0; j < entries.length; j++) {
               def entry = entries[j]
               echo "${entry.commitId} by ${entry.author} on ${new Date(entry.timestamp)}: ${entry.msg}"
               def files = new ArrayList(entry.affectedFiles)
               for (int k = 0; k < files.size(); k++) {
                   def file = files[k]
                   def path = file.path
                   echo "path: ${path}"
                   echo "Edit Type: ${file.editType.name} File path: ${file.path}"
                   if (path.contains(module)){
                      env.changes='yes'
                      echo "There are changes in ${module} so we will be building it."
                      echo " "
                   } else {
                      env.changes='no'
                      echo "There was a change, but it was not in module ${module}."
                      echo " "
                   }
               }
            }
           }
           
           if(changeLogSets.size() == 0){
               echo "There are no changes in any of the repositories."
           }
           unsetColor()

}
