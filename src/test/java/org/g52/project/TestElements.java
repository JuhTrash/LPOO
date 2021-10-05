package org.g52.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestElements {

    @Test
    void weakness(){
        Elements e = Elements.FIRE;
        assertEquals(Elements.WATER,e.getWeakness());

        e = Elements.WATER;
        assertEquals(Elements.ELECTRICITY,e.getWeakness());

        e = Elements.ELECTRICITY;
        assertEquals(Elements.PLANT,e.getWeakness());

        e = Elements.PLANT;
        assertEquals(Elements.FIRE,e.getWeakness());

        e = Elements.DARK;
        assertEquals(Elements.LIGHT,e.getWeakness());

        e = Elements.LIGHT;
        assertEquals(Elements.DARK,e.getWeakness());

    }
}
