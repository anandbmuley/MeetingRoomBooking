package com.merobo.utils

import spock.lang.Specification

class DateConverterUtilSpec extends Specification {

    def "ParseTime"() {
        given:
        def givenTime = "01-12-2019 10:00 AM"

        when:
        def parsedTime = DateConverterUtil.parseTime(givenTime)

        then:
        parsedTime.toString() == "2019-12-01T10:00"
    }
}
