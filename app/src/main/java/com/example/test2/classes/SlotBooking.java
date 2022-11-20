package com.example.test2.classes;

public class SlotBooking {
    long BookingStart;
    String Slot;
    String UserID;


    public SlotBooking(){

    }
    public SlotBooking(long bookingStart, String slotId, String userId)
    {
        this.BookingStart = bookingStart;
        this.Slot = slotId;
        this.UserID = userId;
    }
    public long getBookingStart() {
        return this.BookingStart;
    }

    public String getSlot() {
        return this.Slot;
    }

    public void setSlot(String slotId) {
        this.Slot = slotId;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userId) {
        this.UserID = userId;
    }

    public void setBookingStart(long bookingStart) {
        this.BookingStart = bookingStart;
    }
}

