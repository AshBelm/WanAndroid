package com.mcmo.z.module_login;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.junit.Assert.*;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.*;
@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivity_MLoginTest {
    @Rule
    public ActivityTestRule<LoginActivity_MLogin> mActivityRule = new ActivityTestRule<>(LoginActivity_MLogin.class);
    @Before
    public void setUp() throws Exception {
    }
    @Test
    public void testLogin(){
        onView(withId(R.id.et_userName)).perform(typeText("AshBelm"),closeSoftKeyboard());
//        onView(withId(R.id.btn_register)).perform(click());
//        onData(allOf(is(instanceOf(Integer.class)),Matchers.is(2))).perform(click());//listview中点击
//        onView(allOf(withId(R.id.lv),isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));//recycleview中点击
    }
}