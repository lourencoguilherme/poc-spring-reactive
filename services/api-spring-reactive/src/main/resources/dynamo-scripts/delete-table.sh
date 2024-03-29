#!/usr/bin/env bash
for i in $(ls tables/table-*.json);
    do
        tableJsonName=$(echo $i | cut -d'-' -f 2)
        tableName=$(echo $tableJsonName | cut -d'.' -f 1)
        echo "Deleting table : "$tableName
        aws dynamodb delete-table --table-name $tableName --region "us-east-1" --endpoint-url http://localhost:4566;
done;