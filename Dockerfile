FROM amazonlinux:2 AS my-spring-native-aws-builder

RUN yum update -y
RUN yum groupinstall -y "Development Tools"
RUN yum install -y     gcc     glibc-devel     gzip     tar     unzip  \
    wget     which     zip     zlib-devel     yum clean all \
    fontconfig freetype
RUN rm -rf /var/cache/yum # buildkit



ENV GRAALVM_VERSION=22.2.r11-grl
ENV SDKMAN_HOME=/root/.sdkman
ENV JAVA_HOME=/root/.sdkman/candidates/java/22.2.r11-grl
ENV MAVEN_PROFILE=native
ENV SKIP_TESTS=true
RUN curl -s "https://get.sdkman.io" | bash &&     source "/root/.sdkman/bin/sdkman-init.sh" &&     sdk install java ${GRAALVM_VERSION} &&     sdk flush &&     ${JAVA_HOME}/bin/gu install native-image # buildkit

WORKDIR /app
COPY ./build.sh / # buildkit
CMD ["../build.sh"]
