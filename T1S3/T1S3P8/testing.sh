#!/bin/bash

for ((i=0; i<100; i++)); do
  gcc T1S3P8.c -o T1S3P8 && ./T1S3P8
  echo ""
done
