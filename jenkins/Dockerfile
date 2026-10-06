FROM jenkins/jenkins:lts-jdk21

USER root

RUN apt-get update && \
    apt-get install -y curl ca-certificates gnupg docker.io && \
    curl -fsSL https://deb.nodesource.com/setup_24.x | bash - && \
    apt-get install -y nodejs && \
    node --version && \
    npm --version && \
    docker --version && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

USER jenkins
