#!/bin/bash -xe

BUILD_DIR="${BUILD_DIR:?BUILD_DIR variable is not defined}"
GITHUB_REPOSITORY="${GITHUB_REPOSITORY:?GITHUB_REPOSITORY variable is not defined}"
SONAR_TOKEN="${SONAR_TOKEN:?SONAR_TOKEN variable is not defined}"
BRANCH_NAME="${BRANCH_NAME:?BRANCH_NAME variable is not defined}"

if [[ "$BRANCH_NAME"  == refs/heads/* ]]; then
  BRANCH_NAME="${BRANCH_NAME#refs/heads/}"
elif [[ "$BRANCH_NAME"  == refs/tags/* ]]; then
  BRANCH_NAME="${BRANCH_NAME#refs/tags/}"
elif [[ "$BRANCH_NAME"  == refs/pull/* ]]; then
  BRANCH_NAME="${GITHUB_HEAD_REF}"
fi

sonar_option=""
if [ -n "${CHANGE_ID}" ]; then
  sonar_option="-Dsonar.github.pullRequest=${CHANGE_ID}"
fi

./gradlew sonarqube -x test \
    -Dsonar.host.url=https://sonar.soramitsu.co.jp \
    -Dsonar.exclusions='**/*.txt, **/*.md, **/*.sh, **/generated/*.java' \
    -Dsonar.github.disableInlineComments=true \
    -Dsonar.github.repository="${GITHUB_REPOSITORY}" \
    -Dsonar.login=${SONAR_TOKEN} \
    -Dsonar.branch.name=${BRANCH_NAME} ${sonar_option}