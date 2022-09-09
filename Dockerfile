FROM dyniri/spring-native-aws-builder:java-11 AS almc/my-spring-native-aws-builder
RUN yum -y install freetype.x86_64