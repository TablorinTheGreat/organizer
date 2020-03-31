package com.example.user1.organizer.datatypes;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by user1 on 22/11/2017.
 */

public class event {
    Calendar d;
    String name;
    int minuets_to_notify_before;
    boolean tonotify;

    public event(Calendar d, String name, int minuets_to_notify_before, boolean tonotify) {
        this.d = d;
        this.name = name;
        this.minuets_to_notify_before = minuets_to_notify_before;
        this.tonotify = tonotify;
    }

    public Calendar getD() {
        return d;
    }

    public void setD(Calendar d) {
        this.d = d;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinuets_to_notify_before() {
        return minuets_to_notify_before;
    }

    public void setMinuets_to_notify_before(int minuets_to_notify_before) {
        this.minuets_to_notify_before = minuets_to_notify_before;
    }

    public boolean isTonotify() {
        return tonotify;
    }

    public void setTonotify(boolean tonotify) {
        this.tonotify = tonotify;
    }
}
