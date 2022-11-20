package com.example.test2.classes;

import java.util.LinkedList;
import java.util.List;

public class history_data {
    String Slot;
    String BookingStart;
String Date;
    public history_data() {

    }
    public history_data(String slot, String bookingStart, String date) {
        this.Slot = slot;
        this.BookingStart = bookingStart;
        this.Date = date;
    }

    public String getSlot() {
        return Slot;
    }

    public void setSlot(String slot) {
        Slot = slot;
    }

    public String getBookingStart() {
        return BookingStart;
    }

    public void setBookingStart(String bookingStart) {
        BookingStart = bookingStart;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
