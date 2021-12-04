///DEFINE THE DOCKER_FILES_DIR:
def DOCKER_FILES_DIR = '/apps/jenkins/workspace/ECR-TEST-DEMO/ck001'

pipeline
{
    options
    {
        buildDiscarder(logRotator(numToKeepStr: '3')) /// Number of build to keep on the Jenkins Dashboard
    }
    agent any
    environment 
    {
        VERSION = "${BUILD_NUMBER}" ///Build number as version
        PROJECT = 'gms-container-repo' /// Project is the ecr repository
        IMAGE = "$PROJECT:$VERSION"
        ECRURL = 'https://158999742380.dkr.ecr.us-gov-west-1.amazonaws.com/gms-container-repo'
        ECRCRED = 'ecr:us-gov-west-1:Jenkins_ECR_Login' ///ecr region and creddential id
    }
    stages
    {
        stage('Build preparations')
        {
            steps
            {
                script 
                {
                    // calculate GIT lastest commit short-hash, the first 7 letters
                    gitCommitHash = sh(returnStdout: true, script: 'git rev-parse HEAD').trim()
                    shortCommitHash = gitCommitHash.take(7)
                    // calculate a sample version tag
                    VERSION = shortCommitHash
                    // set the build display name
                    currentBuild.displayName = "#${BUILD_ID}-${VERSION}"
                    IMAGE = "$PROJECT:$VERSION"

                }
            }
        }
        stage('Docker build')
        {
            steps
            {
                script
                {
                    // Build the docker image using a Dockerfile
                    dockerImage = docker.build("$IMAGE", "$DOCKER_FILES_DIR")
                }
            }
        }
        stage('Docker push')
        {
            steps
            {
                script
                {
                    // login to AWS ECR using ECR Jenkins plugin is performing the login as expected.
                    // Push the Docker image to ECR
                    docker.withRegistry(ECRURL, ECRCRED)
                    {
                        dockerImage = docker.image(IMAGE).push()
                    }
                }
            }
        }
    }
    
    post
    {
        always
        {
            // Remove the built docker image to save space
            sh "docker rmi $IMAGE | true"
        }
    }
   
}

