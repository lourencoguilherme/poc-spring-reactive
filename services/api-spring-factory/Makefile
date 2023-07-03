buid-gradle:
	rm -rf build && rm -rf gradle
	gradle wrapper --gradle-version 8.1.1 --distribution-type all
	./gradlew
	./gradlew build
	make build-jar
	cp build/libs/api-spring-factory-0.0.1-SNAPSHOT.jar jvm/application.jar
build-jar:
	echo "Please run this with JDK17!"
	./gradlew bootJar