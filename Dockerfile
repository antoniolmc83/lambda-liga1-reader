FROM amazonlinux:2 AS my-spring-native-aws-builder

RUN yum update -y
RUN yum groupinstall -y "Development Tools"
RUN yum install -y wget tar gzip bzip2-devel ed gcc gcc-c++ gcc-gfortran \
        less libcurl-devel openssl openssl-devel readline-devel xz-devel \
        zlib-devel glibc-static libcxx libcxx-devel llvm-toolset-7 zlib-static \
        fontconfig freetype
RUN rm -rf /var/cache/yum



ENV GRAALVM_VERSION=22.2.r11-grl
ENV SDKMAN_HOME=/root/.sdkman
ENV JAVA_HOME=/root/.sdkman/candidates/java/22.2.r11-grl
ENV MAVEN_PROFILE=native
ENV SKIP_TESTS=true
RUN curl -s "https://get.sdkman.io" | bash &&     source "/root/.sdkman/bin/sdkman-init.sh" &&     sdk install java ${GRAALVM_VERSION} &&     sdk flush &&     ${JAVA_HOME}/bin/gu install native-image

WORKDIR /app
COPY ./build_mvn.sh /
RUN chmod u+x  /build_mvn.sh
CMD ["../build_mvn.sh"]
