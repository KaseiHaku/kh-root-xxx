#!/bin/bash
set -e
set -x


# psql 文档: https://www.postgresql.org/docs/current/app-psql.html
psql -U postgres -f - -e <<-EOF
    create database test;
    create database bpm;
EOF
