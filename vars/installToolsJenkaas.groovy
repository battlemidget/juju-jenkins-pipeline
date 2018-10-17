/* Installs required tools for testing
 *
 */
def call() {
    sh "sudo apt install -qyf libffi-dev"
    sh "sudo -H -E python3 -m pip install -U pipenv"
    sh "cd jobs && /usr/local/bin/pipenv install --skip-lock"

    // Charmstore auth
    withCredentials([file(credentialsId: 'charm_creds', variable: 'CHARMCREDS'),
                     file(credentialsId: 'juju_creds', variable: 'JUJUCREDS'),
                     file(credentialsId: 'snapcraft_creds', variable: 'SNAPCRAFTCREDS')]) {

        sh "cd jobs/infra && /usr/local/bin/pipenv run ansible-playbook playbook-jenkins.yml --extra-vars 'charm_creds=${CHARMCREDS} juju_creds=${JUJUCREDS} snapcraft_creds=${SNAPCRAFTCREDS}'"
    }
}
