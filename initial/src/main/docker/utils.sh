#!/usr/bin/env bash

set -x

# Check status of Docker Machine
docker-machine status default

# Start Docker Machine
docker-machine start default


# Check the IP Address
docker-machine ls