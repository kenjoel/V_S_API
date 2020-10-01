CREATE DATABASE education;
\c education;
CREATE TABLE schools (
 id SERIAL PRIMARY KEY,
 schoolname  VARCHAR,
 schooladdress VARCHAR,
 schoolwebsite VARCHAR,
 schoolemail VARCHAR,
 schoolphone VARCHAR

);

CREATE TABLE students (
 id SERIAL PRIMARY KEY,
 studentname VARCHAR,
 studentemail VARCHAR,
 studentcourse VARCHAR
);

CREATE TABLE educators (
 id SERIAL PRIMARY KEY,
 educatorsname VARCHAR,
 educatorsphone VARCHAR,
 educatorsemail VARCHAR,
 educatorscourse VARCHAR
);

CREATE TABLE school_student (
 id SERIAL PRIMARY KEY,
 studentid INTEGER,
 schoolid INTEGER
);

CREATE TABLE school_educator (
id SERIAL PRIMARY KEY,
educatorid VARCHAR,
schoolid VARCHAR
);

CREATE DATABASE education_test WITH TEMPLATE education;