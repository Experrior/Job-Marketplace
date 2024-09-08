#!/bin/bash
# Example usage:
# ./startup.sh -> puts compose down, and starts compose again
# ./startup.sh 1 -> additionally rebuilds all images before starting compose again

set -ex

docker compose -f infra/compose.yaml down
docker rm -fv job_market_db_local

# Build images
if [[ $# -ge 1 ]] ; then
	docker build -f services/db/Dockerfile_db . -t job_market_db
	docker build -f services/backend/Dockerfile_backend . -t job_market_backend
fi

# Run compose
docker compose -f infra/compose.yaml up -d --remove-orphans

set +ex
watch -n 1 docker compose -f infra/compose.yaml ps
