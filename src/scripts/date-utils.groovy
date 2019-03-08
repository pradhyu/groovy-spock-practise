// Working with dates

// using Date.parse()
date = new Date().parse('yyyy/MM/dd', '2019/01/1')
date.each { println it }

// getting fields from date object
println date[Calendar.YEAR]
println date.getAt(Calendar.DATE)

// add  subtract operations
println date + 1

println date--

// using date as range
newDate = date.clone() + 100
// dae range
println ((date..<newDate).size())
// inclusive
println ((date..newDate).size())



// Setting Date as Calendar Values with Subscript Operators
import static java.util.Calendar.*

def otherDate=new Date()
println "new Date() =$otherDate"
otherDate[YEAR]= 2010
otherDate[MONTH]=JUNE
otherDate[DATE]=1





println otherDate

