build-image:
	docker build . --tag gml/reactive --file Dockerfile
run-image:
	docker run --rm -p 8080:8080 gml/reactive
run-test:
	bash test.sh
perform-tests:
	cd .. && make perform-tests
perform-tests-encoded:
	cd .. && make perform-tests-encoded
perform-tests-encoded-no-db:
	cd .. && make perform-tests-encoded-no-db
