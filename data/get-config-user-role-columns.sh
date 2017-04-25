#export PROJ_NAME=gcp-labs-nk
export PROJ_NAME=ananda-cloud
export FB_URL="https://${ PROJ_NAME}.firebaseio.com"
export ROLE_PATH="rest/saving-data/user_role"
export ROLE_NAME=LD

curl -X GET "${ FB_URL}/${ ROLE_PATH}/roles/${ ROLE_NAME}.json"

# curl 'https://gcp-labs-nk.firebaseio.com/rest/saving-data/user_role/roles/LD.json'