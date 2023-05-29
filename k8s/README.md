kind create cluster --name=esquenta

kubectl cluster-info --context kind-esquenta

kubectl get nodes

kubectl apply -f k8s/pod.yml

kubectl port-forward pod/nginx 9090:80

kubectl delete pod nginx