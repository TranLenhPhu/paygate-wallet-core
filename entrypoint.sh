#!/bin/bash

echo "Waiting for MySQL to start..."
until mysql -h mysql -u root -e "select 1" > /dev/null 2>&1; do
  sleep 1
done

echo "Executing schema.sql..."
mysql -h mysql -u root < /app/schema.sql

export PATH=/opt/java/openjdk/bin:$PATH
exec java -jar /app/wallet-core.jar