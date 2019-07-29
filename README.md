# Funds transfer demo application

[![Build Status](https://travis-ci.org/garlicsauce/funds-transfer.svg?branch=master)](https://travis-ci.org/garlicsauce/funds-transfer)

## Table of Contents
1. [**Overview**](#Overview)
2. [**Prerequisites**](#Prerequisites)
3. [**Quick start**](#QuickStart)
4. [**Build with**](#BuiltWith)

## Overview <a name="Overview"></a>

This repository contains demo application for transferring funds between accounts with currency exchange.

## Prerequisites <a name="Prerequisites"></a>

Here are some conventions that I made:
* for rates of currency exchange I consume [Exchange rates API](https://exchangeratesapi.io/)
* implementation is in-memory for ease of coding

## Quick start <a name="QuickStart"></a>

### Running application

You can run application in many ways, easiest are:
* running via maven with usage of provided mvn wrapper - `./mvnw spring-boot:run`
* building with maven and running jar - 
```sh
./mvnw clean install
java -jar target/funds-transfer*.jar
```
* running it via IDE - main method is located in FundsTransferApplication class

### Running tests

All tests were written in Spock. POM file was configured to compile & run groovy tests.
There are three levels of tests - unit with -UT suffix, integration with -IT suffix and functional with -FT suffix. 
To invoke them use: `./mnv clean test`

### Swagger

There is configured swagger for REST api. Simply run application and enter `localhost:8080/swagger-ui.html` in your browser.

### Test data  
I've provided 4 test accounts to ease testing of this demo app. Details of them are:


| id                                   | currency | balance |
|--------------------------------------|----------|---------|
| e719da9b-dbf4-41bd-a802-cf8d5fb9e6e4 | EUR      | 1000    |
| d8d4f285-d214-4ce4-81cc-1faab82f1bb6 | EUR      | 200     |
| 0c3b8117-55fb-4e15-8702-c10a691edfbf | PLN      | 2000    |
| eab3d3f0-b1c9-4e42-8ce4-17f43b476e8e | CHF      | 5000    |

## Built with <a name="BuiltWith"></a>

* [Maven](https://maven.apache.org/) - dependency management & build tool 
* [Spring boot](https://spring.io/projects/spring-boot) - cause I don't like having to put war on jboss
* [Lombok](https://projectlombok.org/) - because who likes boilerplate
* [Spock](http://spockframework.org/) - for beautiful tests
* [OpenFeign](https://github.com/OpenFeign/feign) - for ease of consuming REST services
