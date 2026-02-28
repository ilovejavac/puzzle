#!/bin/bash

chown spring:spring /app
chown -R spring:spring /app/log

exec su-exec spring java \
  -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE \
  -Duser.timezone=Asia/Shanghai \
  -Dserver.port=8080 \
  -jar app.jar "$@"