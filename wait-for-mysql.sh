#!/bin/sh

echo "⏳ Waiting for MySQL to be ready..."

while ! mysqladmin ping -h mysql-db -uroot -proot --silent; do
  sleep 2
done

echo "✅ MySQL is ready. Starting Spring Boot..."
exec java -jar foodsharing.jar