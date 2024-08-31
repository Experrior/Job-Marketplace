#!/bin/bash
# Example usage:
# ./startup.sh -> puts compose down, and starts compose again
# ./startup.sh 1 -> additionally rebuilds all images before starting compose again

set -ex

docker compose -f docker/compose.yaml down

# Build images
if [[ $# -ge 1 ]] ; then
	docker build -f docker/Dockerfile_db . -t job_market_db
	docker build -f docker/Dockerfile_backend . -t job_market_backend
fi

# Run compose
docker compose -f docker/compose.yaml up -d --remove-orphans

set +ex
watch -n 1 docker compose -f docker/compose.yaml ps
