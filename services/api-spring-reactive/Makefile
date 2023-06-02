build-gradle:
	./gradlew
	./gradlew nativeCompile
	make build-jar
	cp build/libs/api-spring-reactive-0.0.1-SNAPSHOT.jar jvm/application.jar
build-jar:
	echo "Please run this with JDK17!"
	./gradlew bootJar
run-test:
	bash test.sh
run-test-jvm:
	cd jvm && make run-test
perform-tests:
	cd .. && make perform-tests
perform-tests-encoded:
	cd .. && make perform-tests-encoded
perform-tests-encoded-no-db:
	cd .. && make perform-tests-encoded-no-db
