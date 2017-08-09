pipeline {
  agent any
  stages {
    stage('Stage-1') {
      steps {
        parallel(
          "Stage-1": {
            echo 'This is stage One'
            
          },
          "Stage-1-1": {
            echo 'This is ???'
            echo 'This 1-1'
            
          }
        )
      }
    }
    stage('Stage-2') {
      steps {
        echo 'Stage2'
      }
    }
    stage('Stage-3') {
      steps {
        echo 'FINAL'
      }
    }
  }
}