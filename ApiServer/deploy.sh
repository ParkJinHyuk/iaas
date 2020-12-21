#!/bin/bash

REPOSITORY=~/app/test
PROJECT_NAME=ApiServer

cd $REPOSITORY/$PROJECT_NAME/

git pull

./gradlew build

cd $REPOSITORY

cp $REPOSITORY/$PROJECT_NAME/build/libs/*.jar $REPOSITORY/

CURRENT_PID=$(pgrep -f ${PROJECT_NAME}*.jar)

if [ -z "$CURRENT_PID" ]; then
	echo "> 실행 중인 어플리케이션 종료"
else
	kill -15 $CURRENT_PID
	sleep 5
fi

echo "> 새 어플리케이션 배포"

JAR_NAME=$(ls -tr $REPOSITORY/ | grep *.jar | tail -n 1)

nohup java -jar $REPOSITORY/$JAR_NAME 2>&1 &