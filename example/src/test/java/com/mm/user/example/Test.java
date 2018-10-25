package com.mm.user.example;

import com.mm.user.amap.location.LocationTest;
import com.mm.user.fastmap.location.LocationSource;
import com.mm.user.fastmap.location.MapLocations;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * created by hongyang on 18-9-27
 */
public class Test {

    @org.junit.Test
    public void hello() {
        Class c = null; //包名为interview
        try {
            c = Class.forName("com.mm.user.amap.location.AMapLocation");
            LocationSource locationSource = (LocationSource) c.newInstance();
            new MapLocations(locationSource).stopLocation();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}