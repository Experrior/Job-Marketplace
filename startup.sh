#!/bin/bash

set -e

docker compose -f docker/compose.yaml down

# Build images
if [[ $# -ge 1 ]] ; then
	docker build -f docker/Dockerfile_db . -t job_market_db
fi

# Run compose
docker compose -f docker/compose.yaml up -d --remove-orphans

set +e
watch -n 1 docker compose -f docker/compose.yaml ps
