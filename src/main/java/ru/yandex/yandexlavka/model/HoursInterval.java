package ru.yandex.yandexlavka.model;

import org.jetbrains.annotations.NotNull;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class HoursInterval {
    final LocalTime start;
    final LocalTime end;
    final String intervalString;

    HoursInterval(@NotNull String intervalString){
        this.intervalString=intervalString;
        int[] splitIntervalString = Arrays.stream(intervalString.split("[-:]")).mapToInt(Integer::parseInt).toArray();
        start = LocalTime.of(splitIntervalString[0],splitIntervalString[1]);
        end = LocalTime.of(splitIntervalString[2], splitIntervalString[3]);
        if (start.isAfter(end)) throw new RuntimeException("HoursInterval constructor: start.isAfter(end)");
    }

    HoursInterval (@NotNull LocalTime start, @NotNull LocalTime end){
        this.start = start;
        this.end = end;
        intervalString = start.toString().substring(0,5)+"-"+end.toString().substring(0,5);
    }

    boolean isIntersect (@NotNull HoursInterval twoInterval){
        return !this.end.isBefore(twoInterval.start) && !this.start.isAfter(twoInterval.end);
    }

    HoursInterval getIntersect (HoursInterval twoInterval){
        if (this.isIntersect(twoInterval)) {
            LocalTime interStart;
            LocalTime interEnd;
            if (this.start.isAfter(twoInterval.start)) interStart = this.start;
            else interStart = twoInterval.start;
            if (this.end.isAfter(twoInterval.end)) interEnd = twoInterval.end;
            else  interEnd = this.end;
            return  new HoursInterval(interStart, interEnd);
        }
        return null;
    }

    ArrayList<HoursInterval> getNotIntersectThis (HoursInterval twoInterval){
        ArrayList<HoursInterval> hoursIntervalArrayList = new ArrayList<>(2);
        if (this.isIntersect(twoInterval)){
            HoursInterval intersectInterval = this.getIntersect(twoInterval);
            if (!this.intervalString.equals(intersectInterval.intervalString)){
                HoursInterval left = new HoursInterval(this.start, intersectInterval.start);
                HoursInterval right = new HoursInterval(intersectInterval.end, this.end);
                if (!this.start.equals(intersectInterval.start)) hoursIntervalArrayList.add(new HoursInterval(left.start, left.end.minusMinutes(1)));
                if (!this.end.equals(intersectInterval.end)) hoursIntervalArrayList.add(new HoursInterval(right.start.plusMinutes(1), right.end));
            }
        }
        else hoursIntervalArrayList.add(new HoursInterval(this.intervalString));
        return hoursIntervalArrayList;
    }
}
