package com.test.practise

@GrabConfig(systemClassLoader=true)
@Grab('com.oracle:ojdbc6:11g')
        url= "jdbc:oracle:thin:@localhost:1521:XE"
username = "system"
password = "mypassword123"
driver = "oracle.jdbc.driver.OracleDriver"

import groovy.sql.*
Sql.withInstance(url, username, password, driver) { sql ->
    sql.eachRow('select sysdate from dual'){ row ->
        println row
    }
}

