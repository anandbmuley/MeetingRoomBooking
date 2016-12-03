package com.merobo.common

import com.merobo.dtos.BookingTo
import com.merobo.dtos.TeamTo
import com.merobo.dtos.UserTo
import com.merobo.utils.BookingStatus

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
        assert userTo.password == null
        assert userTo.teamName == "Rock Solid"
    }

    public static void assertCreateUserTo(UserTo userTo) {
        assert userTo.id == "UID101"
        assert userTo.name == "Albert"
        assert userTo.password == null
        assert userTo.teamName == "Scientific Explorers"
        assert userTo.cookieTimeout == 15
        assert userTo.loginTime == null
        assert userTo.adminPasscode == null
        assert userTo.adminToken == null
    }

    public static void assertTeams(List<TeamTo> actualTeams) {
        assert actualTeams.size() == 1
        assert actualTeams[0].name == "Scientific Explorers"

        assert actualTeams[0].memberTos.size() == 1
        assert actualTeams[0].memberTos[0].name == "Albert"
        assert actualTeams[0].memberTos[0].adminPasscode == null
        assert actualTeams[0].memberTos[0].adminToken == null
        assert actualTeams[0].memberTos[0].cookieTimeout == 15
        assert actualTeams[0].memberTos[0].id == "UID101"
        assert actualTeams[0].memberTos[0].loginTime == null
        assert actualTeams[0].memberTos[0].password == null
        assert actualTeams[0].memberTos[0].teamName == "Scientific Explorers"
        assert actualTeams[0].memberTos[0].username == null
    }

    public static void assertBookingsTo(List<BookingTo> bookingTos) {
        assert bookingTos.size() == 1
        BookingTo bookingTo = bookingTos[0]
        assert bookingTo.id == "562ba47c44ae605f13522a83"
        assert bookingTo.teamName == "CVoS"
        assert bookingTo.startTime == "02:00 PM"
        assert bookingTo.startDateTime != null
        assert bookingTo.endTime == "03:00 PM"
        assert bookingTo.endDateTime != null
        assert bookingTo.bookedBy == "Aron Johnson"
        assert bookingTo.bookedWhen == "08:00 AM"
        assert bookingTo.roomName == "PINNACLE"
        assert bookingTo.status == BookingStatus.BOOKED


    }
}
