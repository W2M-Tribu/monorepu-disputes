# monorepu-disputes

docker exec -it keycloak /opt/keycloak/bin/kc.sh export --dir /tmp/export --realm w2m-disputes --users skip
docker cp keycloak:/tmp/export/w2m-disputes-realm.json "C:\Users\dgp8\w2m-disputes-realm.json"