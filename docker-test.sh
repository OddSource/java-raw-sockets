#!/bin/bash

if [[ "$1" != "8" ]] && [[ "$1" != "11" ]]
then
    echo "Java 8 Usage:   docker-test.sh 8"
    echo "Java 11 Usage:  docker-test.sh 11"
    exit 1
fi

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

set -e

if [[ ! -f "${DIR}/target/.bash_history" ]]
then
    echo "mvn test-compile -V -e" > "${DIR}/target/.bash_history"
fi

docker build --tag "oddsource/raw-sockets:$1" --file "Dockerfile-jdk$1" .

docker run -it \
    --mount "type=bind,src=${HOME}/.m2/repository,dst=/root/.m2/repository" \
    --mount "type=bind,src=${DIR}/target/.bash_history,dst=/root/.bash_history" \
    --entrypoint /bin/bash "oddsource/raw-sockets:$1"
