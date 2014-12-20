#!/bin/sh
javac -g -cp json-simple-1.1.1.jar RESTCall.java
java -cp .:./json-simple-1.1.1.jar RESTCall
