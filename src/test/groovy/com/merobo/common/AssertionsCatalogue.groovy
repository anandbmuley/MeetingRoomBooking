package com.merobo.common

import com.merobo.dtos.TeamTo

class AssertionsCatalogue {

    public void assertTodaysDate(Date actual) {
        Calendar actualCalendar = Calendar.getInstance();
        actualCalendar.setTime(actual);
        Calendar expectedCalendar = Calendar.getInstance();
        assert actualCalendar.get(Calendar.DAY_OF_MONTH) == expectedCalendar.get(Calendar.DAY_OF_MONTH): "Day of month mismatched"
        assert actualCalendar.get(Calendar.MONTH) == expectedCalendar.get(Calendar.MONTH): "Month number mismatched"
        assert actualCalendar.get(Calendar.YEAR) == expectedCalendar.get(Calendar.YEAR): "Year mismatched"
    }

    public static void assertTeamTo(TeamTo actual){
        assert actual.name == "Australia"
    }
}
