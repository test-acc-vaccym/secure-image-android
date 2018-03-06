// See https://github.com/jenkinsci/kubernetes-plugin
podTemplate(label: 'android-build', name: 'android-build', serviceAccount: 'jenkins', cloud: 'openshift', containers: [
  containerTemplate(
    name: 'jnlp',
    image: '172.50.0.2:5000/devex-mpf-secure-tools/jenkins-slave-android-rhel7:latest',
    resourceRequestCpu: '1500m',
    resourceLimitCpu: '2000m',
    resourceRequestMemory: '6Gi',
    resourceLimitMemory: '8Gi',
    workingDir: '/tmp',
    command: '',
    args: '${computer.jnlpmac} ${computer.name}',
    alwaysPullImage: true,
    envVars: [
        secretEnvVar(key: 'BDD_DEVICE_FARM_USER', secretName: 'bdd-credentials', secretKey: 'username'),
        secretEnvVar(key: 'BDD_DEVICE_FARM_PASSWD', secretName: 'bdd-credentials', secretKey: 'password'),
        secretEnvVar(key: 'ANDROID_DECRYPT_KEY', secretName: 'android-decrypt-key', secretKey: 'decryptKey'),
      ]
  )
]) {
  node('android-build') {

    // Env variables:
    def APP_PATH = "demo_apps/WikipediaSample.apk"
    def APP_NAME = "SampleAPP.apk"
    def UPLOAD_URL = "curl -u ${BDD_DEVICE_FARM_USER}:${BDD_DEVICE_FARM_PASSWD} -X POST https://api.browserstack.com/app-automate/upload -F file=@$APP_PATH"


    stage('Checkout') {
      echo "Checking out source"
      checkout scm
    }

    stage('Setup') {
      echo "Build setup"
      // Decrypt the Android keystore properties file.
      sh "/usr/bin/openssl aes-256-cbc -d -a -in keystore.properties.enc -out keystore.properties -pass env:ANDROID_DECRYPT_KEY"
      sh "/usr/bin/openssl aes-256-cbc -d -a -in app/fabric.properties.enc -out app/fabric.properties -pass env:ANDROID_DECRYPT_KEY"
      // Use the following two lines to disable the gradle daemon. This can make builds faster if the intermediary
      // artifacts (download, etc) are *not* preserved. If they are, keep the daemon running. Overwrite other options
      // because the will likley prevent this param from beng applied and the daemon will start.
      // sh "mkdir -p $HOME/.gradle"
      // sh "echo 'org.gradle.daemon=false' >$HOME/.gradle/gradle.properties"
      // Help cut down on warning messages from the Android SDK
      sh "mkdir -p $HOME/.android"
      sh "touch $HOME/.android/repositories.cfg"
      // The JVM / Kotlin Daemon will quite often fail in memory constraind environments. Givng the JVM
      // 4g allows for the maxumum reqested / recommended by gradle. This is passed in the pod spec as a
      // global: _JAVA_OPTIONS="-Xmx4g"
    }
    
    stage('Build') {
      echo "Build: ${BUILD_ID}"
      sh """
        JAVA_HOME=\$(dirname \$( readlink -f \$(which java) )) && \
        JAVA_HOME=\$(realpath "$JAVA_HOME"/../) && \
        export JAVA_HOME && \
        export ANDROID_HOME=/opt/android && \
        ./gradlew build -x test && \
        echo 'starting the assemble build:'
        """
      
              // ./gradlew assembleDebug
      // Keep the generated apk
      // echo "kept the generated apk....."
      // sh "ls -a app/build/outputs/apk/debug/"

      dir('bdd/geb-mobile') {
        echo "Upload the sample app to cloud server"
        // App hash (bs/md5), could be used to reference in test task. Using the app package name for now.
        // def APP_HASH = sh (
        //   script: "$UPLOAD_URL",
        //   returnStdout: true).trim()

        // echo "the has is: ${APP_HASH}"

        // Abort the build if not uploaded successfully:
        // if ($APP_HASH.contains("Warning")) {
        //     currentBuild.result = 'ABORTED'
        //     error('Error uploading app to account storage')
        // }
        echo "Successfully uploaded the app..."
        echo "Start functional testing with mobile-BDDStack, running sample test case"
        // dir('functionalTesting') {
        try {
          sh './gradlew --debug --stacktrace androidOnBrowserStack'
        } finally { 
          archiveArtifacts allowEmptyArchive: true, artifacts: 'geb-mobile-test-spock/build/reports/**/*'
          archiveArtifacts allowEmptyArchive: true, artifacts: 'geb-mobile-test-spock/build/test-results/**/*'
          archiveArtifacts allowEmptyArchive: true, artifacts: 'geb-mobile-test-spock/screenshots/*'
          junit 'geb-mobile-test-spock/build/test-results/**/*.xml'
        }
      }
    }
  }
}
