package ru.yandex.yandexlavka.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HoursInterval {
    final int start;
    final int end;
    final String intervalString;

    public HoursInterval(@NotNull String intervalString){
        this.intervalString=intervalString;
        String[] splitIntervalString = intervalString.split("[-:]");

        start = Integer.parseInt(splitIntervalString[0])*60 + Integer.parseInt(splitIntervalString[1]);

        if (start > 0 && splitIntervalString[2].equals("00") && splitIntervalString[3].equals("00"))
            end = 1440;
        else
            end = Integer.parseInt(splitIntervalString[2])*60 + Integer.parseInt(splitIntervalString[3]);

        if (start > end) throw new RuntimeException("HoursInterval constructor: start.isAfter(end)");
    }

    public HoursInterval (int startInMin, int endInMin){
        start = startInMin;
        end = endInMin;
        int var;
        String HHstart;
        String MMstart;
        String HHend;
        String MMend;
        if ((var=(start/60) % 24)<10) HHstart = "0"+var;
        else HHstart = String.valueOf(var);
        if ((var=start%60) < 10) MMstart = "0"+var;
        else MMstart = String.valueOf(var);

        if ((var=(end/60) % 24)<10) HHend = "0"+var;
        else HHend = String.valueOf(var);
        if ((var=end%60) < 10) MMend = "0"+var;
        else MMend = String.valueOf(var);

        intervalString = String.join("", HHstart, ":", MMstart,"-",HHend,":",MMend);
    }

    boolean isIntersect (@NotNull HoursInterval twoInterval){
        return !(this.end < twoInterval.start) && !(this.start > twoInterval.end);
    }

    HoursInterval getIntersect (HoursInterval twoInterval){
        if (this.isIntersect(twoInterval)) {
            int interStart;
            int interEnd;
            interStart = Math.max(this.start, twoInterval.start);
            interEnd = Math.min(this.end, twoInterval.end);
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
                if (!(this.start==intersectInterval.start)) hoursIntervalArrayList.add(new HoursInterval(left.start, left.end-1));
                if (!(this.end==intersectInterval.end)) hoursIntervalArrayList.add(new HoursInterval(right.start+1, right.end));
            }
        }
        else hoursIntervalArrayList.add(new HoursInterval(this.intervalString));
        return hoursIntervalArrayList;
    }
}
