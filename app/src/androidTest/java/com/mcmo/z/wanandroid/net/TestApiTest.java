package com.mcmo.z.wanandroid.net;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestApiTest {
    public TestApi api;
    @Before
    public void setUp() throws Exception {
        api = new TestApi();
    }

    @Test
    public void getMainList() {
        api.getMainList();
    }

    @Test
    public void getBanner() {
        api.getBanner();
    }
}