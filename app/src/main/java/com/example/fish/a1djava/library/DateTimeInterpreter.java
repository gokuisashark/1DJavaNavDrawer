package com.example.fish.a1djava.library;

import java.util.Calendar;

/**
 * Created by Albert on 06-Dec-17.
 */

public interface DateTimeInterpreter {
    String interpretDate(Calendar date);
    String interpretTime(int hour);
}
