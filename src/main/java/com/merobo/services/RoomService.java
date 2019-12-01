package com.merobo.services;

import com.merobo.beans.RoomBean;
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

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public String save(RoomDto roomDto) {
        RoomBean roomBean = new RoomBean(roomDto.getName(),
                roomDto.getHasProjector(),
                roomDto.getHasAc(),
                roomDto.getCapacity());
        roomRepository.save(roomBean);
        return roomBean.getId();
    }

    public List<RoomDto> fetchAll() {
        return roomRepository.findAll().stream().map($ ->
                new RoomDto($.getId(),
                        $.getName(),
                        $.hasProjector(),
                        $.hasAc(),
                        $.getCapacity())
        ).collect(Collectors.toList());
    }

    public Optional<RoomDto> findOne(String id) {
        return roomRepository.findById(id).map(roomBean -> new RoomDto(
                roomBean.getId(), roomBean.getName(), roomBean.hasProjector(), roomBean.hasAc(), roomBean.getCapacity()
        ));
    }
}
