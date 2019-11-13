package com.merobo.utils;

import com.merobo.beans.BookingBean;
import com.merobo.beans.TeamBean;
import com.merobo.beans.UserBean;
import com.merobo.dtos.BookingTo;
import com.merobo.dtos.TeamTo;
import com.merobo.dtos.UserTo;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class DtoCreatorUtil {

    public static UserTo createUserTo(UserBean userBean) {
        UserTo userTo = new UserTo();
        userTo.setId(userBean.getId());
        userTo.setName(userBean.getName());
        userTo.setTeamName(userBean.getTeamName());
        return userTo;
    }

    public static TeamTo createTeamTo(TeamBean teamBean) {
        TeamTo teamTo = new TeamTo(teamBean.getName());
        teamTo.setId(teamBean.getId());
        if (!CollectionUtils.isEmpty(teamBean.getMembers())) {
            List<UserTo> userTos = teamBean.getMembers()
                    .stream()
                    .flatMap(userBean ->
                            Stream.of(DtoCreatorUtil.createUserTo(userBean))
                    ).collect(Collectors.toList());
            teamTo.getMemberTos().addAll(userTos);
        }
        return teamTo;
    }

    public static List<BookingTo> createBookingTos(
            List<BookingBean> bookingBeans) {
        List<BookingTo> bookingTos = new ArrayList<BookingTo>();
        for (BookingBean bookingBean : bookingBeans) {
            try {
                BookingTo bookingTo = new BookingTo();
                bookingTo.setBookedBy(bookingBean.getBookedBy());
                bookingTo.setBookedWhen(bookingBean.getBookedWhen());

                bookingTo.setEndTime(DateConverterUtil.toString(
                        bookingBean.getEndTime(),
                        DateConverterUtil.PATTERN_HH_MM_MERIDIAN));
                bookingTo.setStartTime(DateConverterUtil.toString(
                        bookingBean.getStartTime(),
                        DateConverterUtil.PATTERN_HH_MM_MERIDIAN));
                bookingTo.setId(bookingBean.getId());
                bookingTo.setTeamName(bookingBean.getTeamName());
                bookingTo.setRoomName(bookingBean.getRoomName());
                bookingTo.setStatus(bookingBean.getStatus());
                bookingTos.add(bookingTo);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return bookingTos;
    }

}
