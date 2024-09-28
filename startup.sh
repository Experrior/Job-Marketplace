#!/bin/bash
# Example usage:
# ./startup.sh -> puts compose down, and starts compose again
# ./startup.sh --k8s -> runs using k8s, provided that you already have minikube and docker setup locally
# ./startup.sh --rebuild -> additionally rebuilds all images before starting compose again


# Initialize flags
k8s=false
rebuild=false
profile="zpi"
set -e

# Cleanup
docker compose -f infra/compose.yaml down
docker rm -fv job_market_db_local

# Parse command-line arguments
while [[ "$#" -gt 0 ]]; do
    case $1 in
        --k8s) k8s=true ;;
        --rebuild) rebuild=true ;;
		--k8s-full) k8s_full=true ;;
        *) echo "Unknown option: $1" ;;
    esac
    shift
done


if $rebuild; then
    docker build -f services/db/Dockerfile_db . -t docker.local:5000/job_market_database
	docker build -f services/backend/Dockerfile_backend . -t docker.local:5000/job_market_backend
fi


if $k8s || $k8s_full; then


    if minikube status -p="${profile}" | grep 'not found' || minikube status -p="${profile}" | grep 'Stopped'  ; then
		minikube start -p="${profile}" --driver=docker --cpus 6 --memory 10000 --static-ip 192.168.10.10
	elif $k8s_full; then
		minikube delete -p="${profile}"
		minikube start -p="${profile}" --driver=docker --cpus 6 --memory 10000 --static-ip 192.168.10.10
	fi

	# minikube -p=${profile} tunnel --cleanup=true & echo 'Added minikube tunnel'
	minikube -p=${profile} addons enable ingress
	minikube -p=${profile} addons enable metrics-server

	# load images if not present
	if ! ( minikube image ls -p="${profile}" | grep -q 'job_market_backend' ); then
		minikube image load job_market_backend:latest -p="${profile}"
	fi

	if ! ( minikube image ls -p="${profile}" | grep -q 'job_market_database' ); then
		minikube image load job_market_database:latest -p="${profile}"
	fi

	if ! ( kubectl config current-context | grep -qv "${profile}" ); then
		kubectl config set-cluster "${profile}"
		kubectl config set-context --current --namespace dev
	fi

	kubectl apply -k infra/k8s/clusters/"${profile}"/
	# restart deplyoments to allow for configmaps update
	kubectl rollout restart deployment
	

	minikube dashboard --url=true -p="${profile}"

else
	# Run compose
	docker compose -f infra/compose.yaml up -d --remove-orphans
	watch -n 1 docker compose -f infra/compose.yaml ps

fi

set +e
