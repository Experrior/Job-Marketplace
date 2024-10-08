#!/bin/bash
# Example usage:
# ./startup.sh -> puts compose down, and starts compose again
# ./startup.sh --k8s -> runs using k8s, provided that you already have minikube and docker setup locally
# ./startup.sh --rebuild -> additionally rebuilds all images before starting compose again


# Initialize flags
k8s=false
k8s_full=false
rebuild=false
profile="zpi"
pkl=false
run_linter=false

set -ex

# Cleanup
docker compose -f infra/compose.yaml down
docker rm -fv job_market_db_local

# Parse command-line arguments
while [[ "$#" -gt 0 ]]; do
    case $1 in
        --k8s) k8s=true ;;
        --rebuild) rebuild=true ;;
		--k8s-full) k8s_full=true ;;
		--lint) run_linter=true ;;
		--pkl) pkl=true ;;
        *) echo "Unknown option: $1" ;;
    esac
    shift
done


run_linter() {
	kube-linter lint $(find -path '*database-slaves/*.yaml')
}

load_images() {
	# load images if not present
	if ! ( minikube image ls -p="${profile}" | grep -q 'job_market_backend' ); then
		minikube image load job_market_backend:latest -p="${profile}"
	fi

	if ! ( minikube image ls -p="${profile}" | grep -q 'job_market_database' ); then
		minikube image load job_market_database:latest -p="${profile}"
	fi

	if ! ( minikube image ls -p="${profile}" | grep -q 'job_market_db_fill' ); then
		minikube image load job_market_db_fill:latest -p="${profile}"
	fi

	if ! ( kubectl config current-context | grep -qv "${profile}" ); then
		kubectl config set-cluster "${profile}"
		kubectl config set-context --current --namespace dev
	fi
}

if $pkl; then
	# rebuild Pkl config files
	pkl eval -p input=infra/k8s/volumes/pv_config.pkl -o infra/k8s/volumes/pv_generated.yaml || echo "--- [WARNING] pkl generation has failed ---"
fi


if $rebuild; then
    docker build -f services/db/Dockerfile_db . -t docker.local:5000/job_market_database -t job_market_database
	docker build -f services/backend/Dockerfile_backend . -t docker.local:5000/job_market_backend -t job_market_backend
	docker build -f services/db_mockup/Dockerfile . -t docker.local:5000/job_market_db_fill -t job_market_db_fill

fi


if $k8s || $k8s_full; then

	if $k8s_full; then
		# echo "what to do ?..."
		kubectl delete -k infra/k8s/clusters/"${profile}"/ || echo "--- [WARNING] Couldn't delete everything ---"
	fi
	minikube start -p="${profile}" --driver=docker --cpus 6 --memory 10000 --static-ip 192.168.10.10
	sleep 20s

	# minikube -p=${profile} tunnel --cleanup=true & echo 'Added minikube tunnel'
	minikube -p=${profile} addons enable ingress
	minikube -p=${profile} addons enable metrics-server
	minikube -p=${profile} addons enable volumesnapshots
	minikube -p=${profile} addons enable csi-hostpath-driver

	load_images

	kubectl apply -k infra/k8s/clusters/"${profile}"/

	# restart deplyoments to allow for configmaps update
	kubectl rollout restart deployment
	

	minikube dashboard -p="${profile}"

else
	# Run compose
	docker compose -f infra/compose.yaml up -d --remove-orphans
	watch -n 1 docker compose -f infra/compose.yaml ps

fi

set +ex
