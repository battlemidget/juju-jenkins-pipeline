import groovy.transform.Field

@Field cibin = '/var/lib/jenkins/venvs/ci/bin'
@Field cipy = "${cibin}/python3.6"
@Field pytest = "${cibin}/pytest -v -s"
