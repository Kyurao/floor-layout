package com.kyurao.floorlayout.repository;

import com.kyurao.floorlayout.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
