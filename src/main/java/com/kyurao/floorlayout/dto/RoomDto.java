package com.kyurao.floorlayout.dto;

import java.util.ArrayList;
import java.util.List;

public class RoomDto {

    private List<PointReq> room = new ArrayList<>();

    public List<PointReq> getRoom() {
        return room;
    }

    public void setRoom(List<PointReq> room) {
        this.room = room;
    }
}
