#! /usr/local/bin/groovy
@GrabConfig(systemClassLoader = true)
// change the resolver path and grab artifact details if you want ojdbc6 jar from some other repo
@GrabResolver(name = 'ojdbc6', root = 'https://mvnrepository.com/artifact/com.oracle/ojdbc6')
@Grapes(
        @Grab(group = 'com.oracle', module = 'ojdbc6', version = '12.1.0.1-atlassian-hosted')
)

/*
# Spawning docker oracle xe for testing, if you already have oracle instance to test we don't need this
# For testing started a docker container at port 1521 mapped to 49161 host port
➜  ~ git:(master) ✗ docker run -d -p 49161:1521 wnameless/oracle-xe-11g
1515e90918ca348145f072c44cf9a94c320e53218da39def3387f6fb3cb84a9b
➜  ~ git:(master) ✗ docker ps
CONTAINER ID        IMAGE                     COMMAND                  CREATED             STATUS              PORTS                                       NAMES
1515e90918ca        wnameless/oracle-xe-11g   "/bin/sh -c '/usr/sb…"   4 seconds ago       Up 3 seconds        22/tcp, 8080/tcp, 0.0.0.0:49161->1521/tcp   compassionate_haslett

# container spawns with following connection details
    hostname: localhost
    port: 49161
    sid: xe
    username: system
    password: oracle
*/


def driver = "oracle.jdbc.driver.OracleDriver"
def url = "jdbc:oracle:thin:@localhost:49161:XE" // change the connection string as required
def username = "system" // change user/pass as required
def password = "oracle"

import groovy.sql.Sql

Sql.withInstance(url, username, password, driver) { sql ->
    sql.eachRow("""
          |select sysdate today from dual
          |union all
          |select sysdate+100 from dual""".stripMargin())
            { row ->
                println row
            }
}

/*
# testing the script
➜  practise git:(develop) ✗ groovy ojdbcGrape.groovy
[TODAY:2019-01-25 02:10:20.0]
[TODAY:2019-05-05 02:10:20.0]
➜  practise git:(develop) ✗
# checking grape list of dependencies installed
➜  practise git:(develop) ✗ grape list | grep oracle
com.oracle ojdbc6  [12.1.0.1-atlassian-hosted]
➜  practise git:(develop) ✗

 */
