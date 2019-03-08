import groovy.json.JsonSlurper
// read default json file to map
def Map<String,String> jsonFileToMap(filePath) {
   def final jsonFileParser= new JsonSlurper().&parse
   jsonFileParser(new File(filePath))
}
def defaultJsonMap = jsonFileToMap("default.json")
def patientUpdateMap= jsonFileToMap("patient.json")
println "default Map $defaultJsonMap"
println "patient Update Map $patientUpdateMap"
println "final ${( defaultJsonMap + patientUpdateMap)} "

