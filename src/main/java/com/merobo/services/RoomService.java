package com.merobo.services;

import com.merobo.beans.Room;
import com.merobo.dtos.RoomDto;
import com.merobo.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final BookingService bookingService;

    @Autowired
    public RoomService(RoomRepository roomRepository, BookingService bookingService) {
        this.roomRepository = roomRepository;
        this.bookingService = bookingService;
    }

    public String save(RoomDto roomDto) {
        Room room = new Room(roomDto.getName(),
                roomDto.getHasProjector(),
                roomDto.getHasAc(),
                roomDto.getCapacity());
        roomRepository.save(room);
        return room.getId();
    }

    public List<RoomDto> fetchAll() {
        return roomRepository.findAll().stream().map($ ->
                {
                    boolean isBooked = bookingService.getCurrent($.getId()).isPresent();
                    return new RoomDto($.getId(),
                            $.getName(),
                            $.hasProjector(),
                            $.hasAc(),
                            $.getCapacity(), isBooked);
                }
        ).collect(Collectors.toList());
    }

    public Optional<RoomDto> findOne(String id) {
        return roomRepository.findById(id).map(room -> new RoomDto(
                room.getId(), room.getName(), room.hasProjector(), room.hasAc(), room.getCapacity()
        ));
    }

    public void update(String roomId, RoomDto roomDto) {
        roomRepository.findById(roomId).map(foundRoom -> foundRoom.map(roomDto))
                .ifPresent(roomRepository::save);
    }
}
