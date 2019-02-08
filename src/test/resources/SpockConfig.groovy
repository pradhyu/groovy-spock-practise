spockReports {
    // shows code blocks in report 
    set 'com.athaydes.spockframework.report.showCodeBlocks': true
    // change the output dir of spock reports 
    set 'com.athaydes.spockframework.report.outputDir': 'target/spock-reports'
    // using templates 
    set 'com.athaydes.spockframework.report.IReportCreator': 'com.athaydes.spockframework.report.template.TemplateReportCreator'
    // Set properties of the report creator
    set 'com.athaydes.spockframework.report.template.TemplateReportCreator.specTemplateFile':'/reportTemplates/spec-template.md'
    set   'com.athaydes.spockframework.report.template.TemplateReportCreator.summaryTemplateFile':'/reportTemplates/summary-template.md'
    set 'com.athaydes.spockframework.report.template.TemplateReportCreator.enabled':true
    set 'com.athaydes.spockframework.report.template.TemplateReportCreator.summaryFileName':'summary.md'
    set 'com.athaydes.spockframework.report.template.TemplateReportCreator.enabled':true

}

runner {
    filterStackTrace false
    println "Using SpockConfig" // // ./gradlew test --info  will show this line as standard output
}
