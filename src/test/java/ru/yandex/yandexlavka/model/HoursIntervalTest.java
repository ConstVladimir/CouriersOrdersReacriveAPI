package ru.yandex.yandexlavka.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.yandexlavka.assign.HoursInterval;

import java.util.ArrayList;

public class HoursIntervalTest {

    @Test
    void isIntersectFalse(){
        HoursInterval one = new HoursInterval("10:00-12:00");
        HoursInterval two = new HoursInterval("13:00-14:00");
        Assertions.assertFalse(one.isIntersect(two));
        Assertions.assertFalse(two.isIntersect(one));
    }

    @Test
    void isIntersectTrue(){
        HoursInterval one = new HoursInterval("10:00-12:00");
        HoursInterval two = new HoursInterval("11:00-14:00");
        Assertions.assertTrue(one.isIntersect(two));
        Assertions.assertTrue(two.isIntersect(one));

        one = new HoursInterval("10:00-12:00");
        two = new HoursInterval("12:00-14:00");
        Assertions.assertTrue(one.isIntersect(two));
        Assertions.assertTrue(two.isIntersect(one));

        one = new HoursInterval("10:00-18:00");
        two = new HoursInterval("12:00-14:00");
        Assertions.assertTrue(one.isIntersect(two));
        Assertions.assertTrue(two.isIntersect(one));

        one = new HoursInterval("10:00-12:00");
        two = new HoursInterval("10:00-12:00");
        Assertions.assertTrue(one.isIntersect(two));
        Assertions.assertTrue(two.isIntersect(one));
    }

    @Test
    void  getIntersectNull (){
        HoursInterval one = new HoursInterval("10:00-12:00");
        HoursInterval two = new HoursInterval("13:00-14:00");
        Assertions.assertEquals(null, one.getIntersect(two));
        Assertions.assertEquals(null, two.getIntersect(one));
    }

    @Test
    void  getIntersect (){
        HoursInterval one = new HoursInterval("10:00-12:00");
        HoursInterval two = new HoursInterval("11:00-14:00");
        Assertions.assertEquals("11:00-12:00", one.getIntersect(two).intervalString);
        Assertions.assertEquals("11:00-12:00", two.getIntersect(one).intervalString);

        one = new HoursInterval("10:00-12:00");
        two = new HoursInterval("12:00-14:00");
        Assertions.assertEquals("12:00-12:00", one.getIntersect(two).intervalString);
        Assertions.assertEquals("12:00-12:00", two.getIntersect(one).intervalString);

        one = new HoursInterval("10:00-16:00");
        two = new HoursInterval("12:00-14:00");
        Assertions.assertEquals("12:00-14:00", one.getIntersect(two).intervalString);
        Assertions.assertEquals("12:00-14:00", two.getIntersect(one).intervalString);

        one = new HoursInterval("10:00-12:00");
        two = new HoursInterval("10:00-12:00");
        Assertions.assertEquals("10:00-12:00", one.getIntersect(two).intervalString);
        Assertions.assertEquals("10:00-12:00", two.getIntersect(one).intervalString);
    }

    @Test
    void getNotIntersectThisNull (){
        HoursInterval one = new HoursInterval("10:00-12:00");
        HoursInterval two = new HoursInterval("09:00-14:00");
        Assertions.assertTrue(one.getNotIntersectThis(two).isEmpty());

        one = new HoursInterval("10:00-12:00");
        two = new HoursInterval("10:00-12:00");
        Assertions.assertTrue(one.getNotIntersectThis(two).isEmpty());
    }

    @Test
    void getNotIntersectThis (){
        HoursInterval one = new HoursInterval("10:00-12:00");
        HoursInterval two = new HoursInterval("13:00-14:00");
        ArrayList<HoursInterval> result = one.getNotIntersectThis(two);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("10:00-12:00", result.get(0).intervalString);
        result = two.getNotIntersectThis(one);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("13:00-14:00", result.get(0).intervalString);

        one = new HoursInterval("10:00-12:00");
        two = new HoursInterval("12:00-14:00");
        result = one.getNotIntersectThis(two);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("10:00-11:59", result.get(0).intervalString);
        result = two.getNotIntersectThis(one);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("12:01-14:00", result.get(0).intervalString);

        one = new HoursInterval("10:00-12:00");
        two = new HoursInterval("11:00-11:00");
        result = one.getNotIntersectThis(two);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("10:00-10:59", result.get(0).intervalString);
        Assertions.assertEquals("11:01-12:00", result.get(1).intervalString);

    }

}
