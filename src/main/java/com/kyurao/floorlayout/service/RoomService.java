package com.kyurao.floorlayout.service;

import com.kyurao.floorlayout.domain.Point;
import com.kyurao.floorlayout.domain.Room;
import com.kyurao.floorlayout.dto.RoomRes;
import com.kyurao.floorlayout.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public RoomRes getRoom(Long id) {
        return roomToDto(findById(id));
    }

    public List<RoomRes> getAllRooms() {
        return findAllRooms()
                .stream()
                .map(this::roomToDto)
                .collect(Collectors.toList());
    }

    public void saveRoom(Room room) {
        roomRepository.save(room);
    }

    public void editRoom(Long id, Collection<Point> points) {
        Room room = findById(id);
        room.setPoints(new ArrayList<>(points));

        roomRepository.save(room);
    }

    public void removeRoom(Long id) {
        roomRepository.deleteById(id);
    }

    private Room findById(Long id) {
        return roomRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Room with id " + id + " doesn't exist"));
    }

    private List<Room> findAllRooms() {
        return roomRepository.findAll();
    }

    private RoomRes roomToDto(Room room) {
        RoomRes res = new RoomRes();
        res.setId(room.getId());
        res.setQuantity(room.getPoints().size());
        res.setCorners(room.getPoints());

        return res;
    }
}
