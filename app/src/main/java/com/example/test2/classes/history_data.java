package com.example.test2.classes;

import java.util.LinkedList;
import java.util.List;

public class history_data {
    int Slot;
int BookingStart;
int Date;
    public history_data() {

    }
    public history_data(int slot, int bookingStart, int date) {
        this.Slot = slot;
        this.BookingStart = bookingStart;
        this.Date = date;
    }

    public int getSlot() {
        return Slot;
    }

    public void setSlot(int slot) {
        Slot = slot;
    }

    public int getBookingStart() {
        return BookingStart;
    }

    public void setBookingStart(int bookingStart) {
        BookingStart = bookingStart;
    }

    public int getDate() {
        return Date;
    }

    public void setDate(int date) {
        Date = date;
    }
}
