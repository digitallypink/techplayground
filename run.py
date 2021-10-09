#!/usr/bin/python
import os
import platform
from distutils.spawn import find_executable

system = platform.system()
docker_is_absent = find_executable('docker') is None
docker_compose_is_absent = find_executable('docker-compose') is None

if docker_is_absent:
    print('Install Docker -> https://docs.docker.com/get-docker/')
    exit()

if docker_compose_is_absent:
    print("Installing docker-compose")
    os.system('pip3 install docker-compose')

print("Starting Tech playground in "+system+" Operating system")

print('Building Springboot application')
if "Windows".__eq__(system):
    os.system('./apis/gradlew.bat -p apis clean build')
else:
    os.system('./apis/gradlew -p apis clean build')

print('Starting docker containers')
os.system('docker-compose up --build -d')
os.system('docker ps')

