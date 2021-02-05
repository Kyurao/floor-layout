package com.kyurao.floorlayout.service;

import com.kyurao.floorlayout.domain.Point;
import com.kyurao.floorlayout.domain.Room;
import com.kyurao.floorlayout.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room getById(Long id) {
        return roomRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Room with id " + id + " doesn't exist"));
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public void saveRoom(Room room) {
        roomRepository.save(room);
    }

    public void revomeRoom(Long id) {
        roomRepository.deleteById(id);
    }

    public void editRoom(Long id, Collection<Point> points) {
        Room room = getById(id);
        room.setPoints(new ArrayList<>(points));

        roomRepository.save(room);
    }
}
