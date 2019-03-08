// update one map with another
// + operator in map map A + Map B =>  Key sets in (B - A) U (A - B)
import groovy.json.JsonSlurper
// read default json file to map
def Map<String,Object> jsonFileToMap(filePath) {
   def final jsonFileParser= new JsonSlurper().&parse
   jsonFileParser(new File(filePath))
}


def defaultJsonMap = jsonFileToMap("default.json")
def patientUpdateMap= jsonFileToMap("patient.json")
println "default Map: $defaultJsonMap"
println "patient Update Map: $patientUpdateMap"
println "final Map ${( defaultJsonMap + patientUpdateMap)} "

