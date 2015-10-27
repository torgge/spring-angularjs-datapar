#!/bin/sh
read -p "Version to generate javadoc for: " version
project_names=(json-path xml-path rest-assured rest-assured-commmon spring-mock-mvc json-schema-validator scala-support)

echo "Generating Javadoc for version ${version}."

for project_name in ${project_names[*]}
do
    echo "Generating for ${project_name}"
    curl -Ss http://www.javadoc.io/doc/com.jayway.restassured/${project_name}/${version} >/dev/null 2>&1
done
echo "Completed"

