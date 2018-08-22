/*
//this is groovy script
@Grab('com.github.groovy-wslite:groovy-wslite:1.1.2')

import wslite.soap.*
def client = new SOAPClient('http://www.holidaywebservice.com/Holidays/US/Dates/USHolidayDates.asmx')
def response = client.send(SOAPAction:'http://www.27seconds.com/Holidays/US/Dates/GetMothersDay') {
    body {
        GetMothersDay('xmlns':'http://www.27seconds.com/Holidays/US/Dates/') {
            year(2011)
        }
    }
}
println(response.GetMothersDayResponse.GetMothersDayResult.text())

assert "2011-05-08T00:00:00" == response.GetMothersDayResponse.GetMothersDayResult.text()
assert 200 == response.httpResponse.statusCode
assert "ASP.NET" == response.httpResponse.headers['X-Powered-By']

*/