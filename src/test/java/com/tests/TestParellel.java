package com.tests;

import org.testng.annotations.Test;

import com.testBase.TestBase;

public class TestParellel extends TestBase {

    @Test
    public void testLogin() {
        driver.get("https://www.amazon.in");
       
    }
}
