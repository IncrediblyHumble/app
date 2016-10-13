# app

You need to download the server repo and have that running before this will work.

install dependencies:
```
mvn clean install
```

run:
```
mvn package
mvn exec:java -D exec.mainClass=com.incredibly_humble.app.App
```
