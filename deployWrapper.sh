#!/bin/bash

# General script environment variables
GROOVY=$(which groovy)
CLASSPATH="/home/vagrant/opt/EAP-6.4.0/bin/client/jboss-cli-client.jar"
SCRIPTNAME="simpleDeploy.groovy"

# Test if argument number is correct.
if [ $# -ne 5 ]; then
    echo "Illegal number of parameters"
    exit 1
else
    # Define arguments:
    # FILEPATH: The path of the file to deploy
    FILEPATH=$1
    # APPNAME: The application name
    APPNAME=$2
    # APPRUNTIMENAME: The application runtime name
    APPRUNTIMENAME=$3
    # SERVERGROUP: The server group to use for deployment
    SERVERGROUP=$4
    # MODE: Deployment mode, "simple" or "rollout"
    MODE=$5
fi

# Run the groovy script
# TODO: Adjust logging
$GROOVY -cp $CLASSPATH $SCRIPTNAME $FILEPATH $APPNAME $APPRUNTIMENAME $SERVERGROUP $MODE

exit 0
