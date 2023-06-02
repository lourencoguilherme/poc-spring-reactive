build-image:
	docker build . --tag gml/factory --file Dockerfile
run-image:
	docker run --rm -p 8081:8081 gml/factory
run-test:
	bash test.sh
perform-tests:
	cd .. && make perform-tests
perform-tests-encoded:
	cd .. && make perform-tests-encoded
perform-tests-encoded-no-db:
	cd .. && make perform-tests-encoded-no-db
