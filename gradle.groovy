/*
	forma de invocación de método call:
	def ejecucion = load 'script.groovy'
	ejecucion.call()
*/
def call(){
    env.TAREA = "Paso 1: Build && Test"
    stage("${env.TAREA}"){
        sh "gradle clean build"
        sh "${GIT_REPO}"
    }
    stage("Paso 2: Sonar - Análisis Estático"){
        sh "echo 'Análisis Estático!'"
        withSonarQubeEnv('sonarqube') {
            sh './gradlew sonarqube -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build'
    } 

}
}
return this;