package org.example;

import org.testng.annotations.Test;

@Test
public class TestClass {

    @Test
    public static void simpleStaticTest() {
        System.out.println("Hello world!");
    }

    @Test
    public void simpleTest() {
        System.out.println("Test method invoked.");
    }

    @Test
    public void testInt(int x) {
        System.out.println("Test method invoked.");
    }

    @Test
    public void testCharInt(int x, char y) {
        System.out.println("Test method invoked.");
    }

    @Test
    public void testLong(long z) {
        System.out.println("Test method invoked. " + z);
    }

    @Test
    public void testDouble(double x) {
        System.out.println("Test method invoked." + x);
    }

    @Test
    public void testString(String x) {
        System.out.println("Value of x: " + x);
    }

    @Test
    public static void testBool(boolean x) {
        System.out.println("Test method invoked." + x);
    }



}
