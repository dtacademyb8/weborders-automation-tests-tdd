call mvn clean test -DxmlFile=smoke-test.xml
call mvn test -DxmlFile=target/surefire-reports/testng-failed.xml
