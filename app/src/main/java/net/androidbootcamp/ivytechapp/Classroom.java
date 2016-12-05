package net.androidbootcamp.ivytechapp;

/**
 * Created by jamesdrewery on 10/26/16.
 */

public class Classroom {

    private String roomNumber;
    private Float latitude;
    private Float longitude;

    public Classroom() {

    }

    public Classroom(String roomNumber, Float latitude, Float longitude) {

        this.roomNumber = roomNumber;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    public Classroom(Float latitude, Float longitude) {

        this.latitude = latitude;
        this.longitude = longitude;

    }

    public void setRoomNumber(String roomNumber) {

        this.roomNumber = roomNumber;

    }
    public String getRoomNumber() {

        return roomNumber;

    }

    public void setLatitude(Float latitutde) {

        this.latitude = latitutde;

    }
    public Float getLatitude() {

        return latitude;

    }

    public void setLongitude(Float longitude) {

        this.longitude = longitude;

    }
    public Float getLongitude() {

        return longitude;

    }

}
