package com.jjclade.therappy;

import android.test.ActivityInstrumentationTestCase2;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.jjclade.therappy.MainUITest \
 * com.jjclade.therappy.tests/android.test.InstrumentationTestRunner
 */
public class MainUITest extends ActivityInstrumentationTestCase2<MainUI> {

    public MainUITest() {
        super("com.jjclade.therappy", MainUI.class);
    }

}
