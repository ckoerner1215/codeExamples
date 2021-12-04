#!/usr/bin/env groovy

def call(String branch = 'master') {
            dir('library') {
                checkout([
		           $class: 'GitSCM', 
		           branches: [[name: "*/${branch}"]], 
		           doGenerateSubmoduleConfigurations: false, 	
		           extensions: [[$class: 'WipeWorkspace']], 
		           submoduleCfg: [], 
		           userRemoteConfigs: [[url: 'git@gitlab.gms.bana.com:gms/library.git']]])
            }
  }
