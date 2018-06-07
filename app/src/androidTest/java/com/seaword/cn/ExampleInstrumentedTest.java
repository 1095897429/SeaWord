package com.seaword.cn;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
      format(16050 +"");
    }

    public static String format   (String num) {
        Integer integer = Integer.valueOf(num);
        if (integer < 10000) {
            return String.valueOf(num);
        }
        String unit = "万";
        double newNum = integer / 10000.0;
        String numStr = String.format("%." + 1 + "f", newNum);
        return numStr + unit;
    }

}
