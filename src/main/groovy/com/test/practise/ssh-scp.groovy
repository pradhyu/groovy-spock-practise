

// https://mvnrepository.com/artifact/com.aestasit.infrastructure.sshoogr/sshoogr
@Grapes(
    @Grab(group='com.aestasit.infrastructure.sshoogr', module='sshoogr', version='0.9.25', scope='runtime')
)
import static com.aestasit.infrastructure.ssh.DefaultSsh.*

/*
 Consider localhost has a machine up with sshd listening to port 2222
 open for ssh and scp
 In case the grape error occurs clean
 ~/.m2 and ~/.groovy/grapes directory
 or
 use grape command to resolve
 grape -V resolve commons-logging commons-logging 1.1.1
 if nothing works do following
* create this file : ~/.groovy/grapeConfig.xml
* With these contens:
<ivysettings>
  <settings defaultResolver="downloadGrapes"/>
  <resolvers>
  <chain name="downloadGrapes" returnFirst="true">
    <filesystem name="cachedGrapes">
    <ivy pattern="${user.home}/.groovy/grapes/[organisation]/[module]/ivy-[revision].xml"/>
    <artifact pattern="${user.home}/.groovy/grapes/[organisation]/[module]/[type]s/[artifact]-[revision](-[classifier]).[ext]"/>
    </filesystem>
    <ibiblio name="localm2" root="file:${user.home}/.m2test/repository/" checkmodified="true" changingPattern=".*" changingMatcher="regexp" m2compatible="true" usepoms="false"/>
    <ibiblio name="jcenter" root="https://jcenter.bintray.com/" m2compatible="true"/>
    <ibiblio name="ibiblio" m2compatible="true"/>
  </chain>
  </resolvers>
</ivysettings>

*/
// more options at https://github.com/aestasit/sshoogr
// more examples : http://mariogarcia.github.io/blog/blog/2016/04/sshoogr.html
// groovy grapes for docker https://github.com/bsideup/testing-docker-images-with-testcontainers/blob/master/docker-compose.yml

// run docker host 
//   docker run -d -p 2222:22 arvindr226/alpine-ssh


// set it to get rid of unknown host error 
options.trustUnknownHosts = true
options.verbose = true
remoteSession {
    host = 'localhost'
    username = 'root'
    password = 'root'
    port = 2222
    exec 'rm -rf /tmp/*'
    exec 'mkdir -p /home/tmp/test/'
    exec 'touch /home/tmp/test/my.pid'
    exec 'touch /home/tmp/test/my2.pid'
    remoteFile('/home/tmp/test/my.conf').text = "enabled=true"
    exec (command: "ls /home/tmp/test/", showOutput: true)
    exec (command: "find /", showOutput: true)
}

