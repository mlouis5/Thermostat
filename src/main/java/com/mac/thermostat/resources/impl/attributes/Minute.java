/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.impl.attributes;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author MacDerson
 */
public class Minute {

    private static final double WHOLE_DAY = 1440;
    private static final int NOON = 720;
    private static final int MINUTES_IN_HOUR = 60;

    int minute;

    public Minute() {
        minute = 0;
    }

    /**
     * Minutes since 12 am
     *
     * @param minute
     */
    public Minute(int minute) {
        this.minute = minute;
    }
    
    public int getMinute(){
        return minute;
    }

    public String as12HourFormat() {
        int hour = minute / MINUTES_IN_HOUR;
        hour = hour == 0 ? (NOON / MINUTES_IN_HOUR)
                : hour > (NOON / MINUTES_IN_HOUR)
                        ? hour - (NOON / MINUTES_IN_HOUR) : hour;

        String time = String.format("%02d:%02d", hour, minute % MINUTES_IN_HOUR);

        return time + ((minute < NOON || minute == WHOLE_DAY) ? " AM" : " PM");
    }

    public String as24HourFormat() {
        int hour = minute / MINUTES_IN_HOUR;

        String time = String.format("%02d:%02d", hour, minute % MINUTES_IN_HOUR);

        return time;
    }

    @Override
    public int hashCode() {
        HashFunction function = Hashing.md5();
        HashCode hc = function.newHasher().putInt(this.minute).hash();
        return hc.asInt();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Minute other = (Minute) obj;
        return Objects.equals(this.minute, other.minute);
    }

    
}
