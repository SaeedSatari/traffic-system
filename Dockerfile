ARG BUILDER_IMAGE_NAME=eclipse-temurin
ARG BUILDER_IMAGE_TAG=11.0.18_10-jre
ARG BASE_IMAGE_NAME=registry.access.redhat.com/ubi9/openjdk-11-runtime
ARG BASE_IMAGE_TAG=1.14-2.1679391797

FROM ${BUILDER_IMAGE_NAME}:${BUILDER_IMAGE_TAG} AS builder
VOLUME /tmp

ARG JAR_FILE=target/traffic-service-*.jar

COPY ${JAR_FILE} traffic-service.jar
RUN java -Djarmode=layertools -jar traffic-service.jar extract

FROM ${BASE_IMAGE_NAME}:${BASE_IMAGE_TAG}
ARG COMPANY_NAME="SOFTWAVE"
ARG PROJECT_NAME="TRAFFIC-SERVICE"
ARG PROJECT_VERSION="1.0.0"
ARG BUILD_TIME=""
ARG BUILD_USER=""
ARG DOCKER_REGISTRY=""
ARG COMMIT_HASH=""

LABEL io.solvers.company_name="${COMPANY_NAME}" \
io.solvers.project_name="${PROJECT_NAME}" \
io.solvers.project_version="${PROJECT_VERSION}" \
io.solvers.build_time="${BUILD_TIME}" \
io.solvers.build_user="${BUILD_USER}" \
io.solvers.docker_registry="${DOCKER_REGISTRY}" \
io.solvers.commit_hash="${COMMIT_HASH}"

COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]