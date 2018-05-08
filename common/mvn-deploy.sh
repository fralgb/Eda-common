#!/bin/bash
mvn versions:set versions:commit -DnewVersion="1.0.5-SNAPSHOT"
mvn clean source:jar install
mvn deploy
echo "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
echo "---------------------------deploy success !!!------------------------------------"
echo "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"

