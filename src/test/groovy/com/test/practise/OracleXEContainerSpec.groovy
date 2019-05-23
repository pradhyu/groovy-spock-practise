package com.test.practise

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.junit.ClassRule
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.OracleContainer
import spock.lang.Shared
import spock.lang.Specification

import java.sql.ResultSet
import java.sql.Statement

//@Testcontainers
@spock.lang.Ignore
class OracleXEContainerSpec extends Specification {

    @Shared
    @ClassRule
    // make sure that the docker image is oracleXe docker image
    OracleContainer oracleXEContainer = new OracleContainer<>("martinsthiago/oraclexe-11g-fig")
            .withUsername("system")
            .withPassword("oracle")
            .withCreateContainerCmdModifier{it -> it.withName("oracle")} // setting container name
    def "waits until oracleXE accepts jdbc connections"() {

        given: "a jdbc connection"
        HikariConfig hikariConfig = new HikariConfig()
        println("jdbc url: ${oracleXEContainer.jdbcUrl}")
        hikariConfig.setJdbcUrl(oracleXEContainer.jdbcUrl)
        hikariConfig.setUsername("system")
        hikariConfig.setPassword("oracle")
        HikariDataSource ds = new HikariDataSource(hikariConfig)

        when: "querying the database"
        Statement statement = ds.getConnection().createStatement()
        statement.execute("SELECT 1 from dual")
        ResultSet resultSet = statement.getResultSet()
        resultSet.next()

        then: "result is returned"
        int resultSetInt = resultSet.getInt(1)
        resultSetInt ==1

        cleanup:
        ds.close()
    }

}
