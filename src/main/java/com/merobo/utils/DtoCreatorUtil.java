package com.merobo.utils;

import abm.authenticator.domain.User;
import com.merobo.beans.Booking;
import com.merobo.beans.Team;
import com.merobo.dtos.BookingDto;
import com.merobo.dtos.TeamDto;
import com.merobo.dtos.UserDto;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DtoCreatorUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DtoCreatorUtil.applicationContext = applicationContext;
    }

    public static UserDto createUserTo(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        return userDto;
    }

    public static TeamDto createTeamTo(Team team) {
        TeamDto teamDto = applicationContext.getBean(TeamDto.class, team.getName());
        teamDto.setId(team.getId());
        if (!CollectionUtils.isEmpty(team.getMembers())) {
            List<UserDto> userDtos = team.getMembers()
                    .stream()
                    .flatMap(userBean ->
                            Stream.of(DtoCreatorUtil.createUserTo(userBean))
                    ).collect(Collectors.toList());
            teamDto.getMemberTos().addAll(userDtos);
        }
        return teamDto;
    }

    public static List<BookingDto> createBookingTos(
            List<Booking> bookings) {
        List<BookingDto> bookingDtos = new ArrayList<BookingDto>();
        for (Booking booking : bookings) {
//            try {
            BookingDto bookingDto = new BookingDto(null, null, null);
//                bookingTo.setBookedBy(bookingBean.getBookedBy());
//                bookingTo.setBookedWhen(bookingBean.getBookedWhen());
//
//                bookingTo.setEndTime(DateConverterUtil.toString(
//                        bookingBean.getEndTime(),
//                        DateConverterUtil.PATTERN_HH_MM_MERIDIAN));
//                bookingTo.setStartTime(DateConverterUtil.toString(
//                        bookingBean.getStartTime(),
//                        DateConverterUtil.PATTERN_HH_MM_MERIDIAN));
//                bookingTo.setId(bookingBean.getId());
//                bookingTo.setTeamName(bookingBean.getTeamName());
//                bookingTo.setRoomName(bookingBean.getRoomName());
//                bookingTo.setStatus(bookingBean.getStatus());
            bookingDtos.add(bookingDto);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
        }
        return bookingDtos;
    }

}
