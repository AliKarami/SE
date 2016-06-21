#!/usr/bin/env bash
rm -rf ./bin/*
javac -cp .:/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/ext/jfxrt.jar:/home/ali/Documents/mysql-connector-java-5.1.38/mysql-connector-java-5.1.38-bin.jar -sourcepath src -d out src/sample/*.java
cp -rf ./src/sample/*.fxml ./out/sample/
cp -rf ./src/sample/*.css ./out/sample/
cp -rf ./src/sample/*.jpg ./out/sample/
cp -rf ./src/sample/*.sql ./out/sample/