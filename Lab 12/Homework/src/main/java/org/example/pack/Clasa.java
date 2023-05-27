package org.example.pack;

import org.example.TestClass;
import org.testng.annotations.Test;

@Test
public class Clasa extends TestClass implements TestInterface {

    public Clasa() {
        super();
    }

    public int x;
    private char y;

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
    public void specialTest() {
        System.out.println("Test method invoked.");
    }
}
