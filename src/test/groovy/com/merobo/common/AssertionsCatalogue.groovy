package com.merobo.common

import com.merobo.dtos.TeamTo
import com.merobo.dtos.UserTo

class AssertionsCatalogue {

    public void assertTodaysDate(Date actual) {
        Calendar actualCalendar = Calendar.getInstance()
        actualCalendar.setTime(actual)
        Calendar expectedCalendar = Calendar.getInstance()
        assert actualCalendar.get(Calendar.DAY_OF_MONTH) == expectedCalendar.get(Calendar.DAY_OF_MONTH): "Day of month mismatched"
        assert actualCalendar.get(Calendar.MONTH) == expectedCalendar.get(Calendar.MONTH): "Month number mismatched"
        assert actualCalendar.get(Calendar.YEAR) == expectedCalendar.get(Calendar.YEAR): "Year mismatched"
    }

    public static void assertTeamTo(TeamTo actual) {
        assert actual.name == "Australia"
        assert actual.id == "dsa3-dsa3-gsa2-r3ca"
    }

    public static void assertUserTo(UserTo userTo) {
        assert userTo.id == "da2f-3fa2-dai2-da2r"
        assert userTo.name == "Rocky"
        assert userTo.username == "rockstar"
        assert userTo.password == null
        assert userTo.teamName == "Rock Solid"
    }
}
