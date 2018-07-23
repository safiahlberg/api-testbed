# api-testbed

A test application for testing the
[API](https://emma.skatteverket.se/ezwa100/minameddelanden_wsdl/open/)
of [Mina Meddelanden](http://www.minameddelanden.se/mm/teknikochdrift.html)

> Note: This requires JDK 8

## Executing integration test

To execute an integration test that generates artifacts from WSDL and
deploys a service implementation to a cargo embedded servlet container
and runs a test for the service(s) you use Maven.

```mvn
mvn clean integration-test -P [cxf|metro] -P integration
```

Tests can be run with CXF or Metro as implementations, these are
activated by choosing a maven profile.

### CXF

### Metro

## Changing JDK version
There are several ways to change the JDK, but here are an example

### Changing JDK version Mac OS X

```shell
export JAVA_HOME="`/usr/libexec/java_home -v 1.8`"
```