#!groovy

/*
THIS IS A TEST SCRIPT
*/

node{

	stage('stage1'){
		println "stage1"
	}

	input "go to stage2?"
	
	stage('stage2'){
		steps{
			println "stage2"
		}
	
		steps{
			println "stage3"
		}
	}

	stage('stage3'){
		println "4444"
	}



}
