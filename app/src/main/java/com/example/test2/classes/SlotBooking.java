package com.example.test2.classes;

public class SlotBooking {
    long bookingStart;
    int slotId;
    String userId;

    public SlotBooking(){

    }
    public SlotBooking(long bookingStart, int slotId, String userId)
    {
        this.bookingStart = bookingStart;
        this.slotId = slotId;
        this.userId = userId;
    }
    public long getBookingStart() {
        return bookingStart;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setBookingStart(long bookingStart) {
        this.bookingStart = bookingStart;
    }
}

