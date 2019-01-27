#!/bin/bash
#
# Copyright © 2010-2019 OddSource Code (license@oddsource.io)
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

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
