#!/bin/sh
mvn test -DxmlFile=smoke-test.xml -Dbrowser=chrome
mvn test -DxmlFile=smoke-test.xml -Dbrowser=edge
mvn test -DxmlFile=smoke-test.xml -Dbrowser=firefox
