#!/bin/bash

for ((i=0; i<100; i++)); do
  gcc T1S3P7.c -o T1S3P7 && ./T1S3P7
  echo ""
done
