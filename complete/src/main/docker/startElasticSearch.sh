#!/usr/bin/env bash

set -x

eval $(docker-machine env default)

docker run -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -e ES_JAVA_OPTS="-Xms512m -Xmx512m" -e "http.cors.enabled=true" -e "http.cors.allow-origin=/https?:\/\/192.168.99.100(:[0-9]+)?/" -e "http.cors.allow-headers=Authorization,X-Requested-With,Content-Length,Content-Type" -e "http.cors.allow-credentials=true" -e "cluster.name=elasticsearch" -e "xpack.security.enabled=false" docker.elastic.co/elasticsearch/elasticsearch:5.4.3

