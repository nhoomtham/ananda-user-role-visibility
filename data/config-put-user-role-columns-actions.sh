#!/bin/bash

#export PROJ_NAME=gcp-labs-nk
export PROJ_NAME=ananda-cloud
export FB_URL="https://${PROJ_NAME}.firebaseio.com"
export ROLE_PATH="rest/saving-data/user_role"

curl -vX PUT -d @columns_actions.json "${FB_URL}/${ROLE_PATH}.json" --header "Content-Type: application/json"