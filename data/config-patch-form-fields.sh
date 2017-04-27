#!/bin/bash

# export PROJ_NAME=gcp-labs-nk
export PROJ_NAME=ananda-cloud
export FB_URL="https://${PROJ_NAME}.firebaseio.com"
export ROLE_PATH="rest/saving-data/user_role"

ROLES=( LD LS BD DD MI RSK )
for i in "${ROLES[@]}"
do
curl -vX PATCH -d @form_fields_${i}.json "${FB_URL}/${ROLE_PATH}/roles/${i}/.json" --header "Content-Type: application/json"
done
