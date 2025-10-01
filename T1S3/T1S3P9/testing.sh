#!/bin/bash
option='1'

for ((i=0; i<10; i++)); do
  gcc T1S3P9.c -o T1S3P9 && echo $option | ./T1S3P9
  echo ""
done
