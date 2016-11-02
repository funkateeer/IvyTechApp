package net.androidbootcamp.ivytechapp;

/**
 * Created by jamesdrewery on 10/26/16.
 */

public class Classroom {
    private String roomNumber;
    private float latitude;
    private float longitude;

    public Classroom()
    {

    }

    public Classroom(String roomNumber, float latitude, float longitude)
    {
        this.roomNumber = roomNumber;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Classroom(float latitude, float longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
    public String getRoomNumber() {
        return roomNumber;
    }

    public void setLatitude(float latitutde) {
        this.latitude = latitutde;
    }
    public float getLatitude() {
        return latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
    public float getLongitude() {
        return longitude;
    }

}
