package com.example.fish.a1djava.calendar;



import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.fish.a1djava.R;
import com.example.fish.a1djava.library.DateTimeInterpreter;
import com.example.fish.a1djava.library.MonthLoader;
import com.example.fish.a1djava.library.WeekView;
import com.example.fish.a1djava.library.WeekViewEvent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Consultation extends AppCompatActivity implements
        WeekView.EventClickListener, MonthLoader.MonthChangeListener, WeekView.EventLongPressListener, WeekView.EmptyViewLongPressListener{

    private static final int TYPE_DAY_VIEW = 1;
    private static final int TYPE_THREE_DAY_VIEW = 2;
    private static final int TYPE_WEEK_VIEW = 3;
    private int mWeekViewType = TYPE_THREE_DAY_VIEW;
    private WeekView mWeekView;

    private int dayFinal, monthFinal, yearFinal, hourFinal = -1, minuteFinal = -1;
    private int  end_hour, end_minute, index;
    private int id;
    private String prof_name, student_name, student_id, status;
    private ArrayList<String> booking_event = new ArrayList<String>();

    private ArrayList<String> consultation_list = new ArrayList<String>();

    Button book;
    Button cancel;
    TextView booking;
    TextView bookinginfo;
    Button ok;
    View add_background;

    private static final String TAG = "Consultation";

    private int prof_count = 0;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultation);

        // Get a reference for the week view in the layout.
        mWeekView = (WeekView) findViewById(R.id.weekView);

        // Show a toast message about the touched event.
        mWeekView.setOnEventClickListener(this);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView.setMonthChangeListener(this);

        // Set long press listener for events.
        mWeekView.setEventLongPressListener(this);

        // Set long press listener for empty view
        mWeekView.setEmptyViewLongPressListener(this);

        // Set up a date time interpreter to interpret how the date and time will be formatted in
        // the week view. This is optional.
        setupDateTimeInterpreter(false);

        book = (Button) findViewById(R.id.ConfirmBooking);
        cancel = (Button) findViewById(R.id.CancelBooking);
        booking = (TextView) findViewById(R.id.Booking);
        bookinginfo = (TextView) findViewById(R.id.BookingInfo);
        ok = (Button) findViewById(R.id.ok);
        add_background = (View) findViewById(R.id.add_background);


        //https://www.youtube.com/watch?v=2duc77R4Hqw&index=4&list=PLgCYzUzKIBE_cyEsXgIcwC3P8ipvlSFd_
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("message");


        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Object value = dataSnapshot.getValue();
                Log.d(TAG, "onDataChange: Added information to database: \n" + value);

                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    private void showData(DataSnapshot dataSnapshot){

        for(DataSnapshot ds: dataSnapshot.getChildren()) {
            // UserInformation uInfo = new UserInformation();
            consultation_list.clear();
            for (HashMap<String, String> map : (ArrayList<HashMap<String, String>>) ds.getValue()) {

                //System.out.println("map: " + map.get("booking_student_name"));
                consultation_list.add(String.valueOf(map.get("prof_year")));
                consultation_list.add(String.valueOf(map.get("prof_month")));
                consultation_list.add(String.valueOf(map.get("prof_day")));
                consultation_list.add(String.valueOf(map.get("prof_start_hour")));
                consultation_list.add(String.valueOf(map.get("prof_start_minute")));
                consultation_list.add(String.valueOf(map.get("prof_end_hour")));
                consultation_list.add(String.valueOf(map.get("prof_end_minute")));
                consultation_list.add(String.valueOf(map.get("prof_name")));
                consultation_list.add(String.valueOf(map.get("booking_student_name")));
                consultation_list.add(String.valueOf(map.get("booking_student_id")));
                consultation_list.add(String.valueOf(map.get("status")));


            }
            break;

        }
        System.out.println(consultation_list);
//            consultation_list.add(uInfo.getProf_month());
//            consultation_list.add(uInfo.getProf_day());
//            consultation_list.add(uInfo.getProf_start_hour());
//            consultation_list.add(uInfo.getProf_start_minute());
//            consultation_list.add(uInfo.getProf_end_hour());
//            consultation_list.add(uInfo.getProf_end_minute());
//            consultation_list.add(uInfo.getProf_name());
//            consultation_list.add(uInfo.getStudent_name());
//            consultation_list.add(uInfo.getStudent_id());


//            System.out.println(ds.child("prof_timetable").getValue(UserInformation.class).getProf_timetable());
        // uInfo.setProf_year(ds.child("prof_timetable").getValue(UserInformation.class).getProf_year());

//            uInfo.setProf_year(ds.child("prof_timetable").child(Integer.toString(prof_count)).child("year").getValue(UserInformation.class).getProf_year());
//            uInfo.setProf_month(ds.child("prof_timetable").child(Integer.toString(prof_count)).child("month").getValue(UserInformation.class).getProf_month());
//            uInfo.setProf_day(ds.child("prof_timetable").child(Integer.toString(prof_count)).child("day").getValue(UserInformation.class).getProf_day());
//            uInfo.setProf_start_hour(ds.child("prof_timetable").child(Integer.toString(prof_count)).child("start_hour").getValue(UserInformation.class).getProf_start_hour());
//            uInfo.setProf_start_minute(ds.child("prof_timetable").child(Integer.toString(prof_count)).child("start_minute").getValue(UserInformation.class).getProf_start_minute());
//            uInfo.setProf_end_hour(ds.child("prof_timetable").child(Integer.toString(prof_count)).child("end_hour").getValue(UserInformation.class).getProf_end_hour());
//            uInfo.setProf_end_minute(ds.child("prof_timetable").child(Integer.toString(prof_count)).child("end_minute").getValue(UserInformation.class).getProf_end_minute());
//            uInfo.setProf_name(ds.child("prof_timetable").child(Integer.toString(prof_count)).child("prof_name").getValue(UserInformation.class).getProf_name());
//            uInfo.setStudent_name(ds.child("prof_timetable").child(Integer.toString(prof_count)).child("student_name").getValue(UserInformation.class).getStudent_name());
//            uInfo.setStudent_id(ds.child("prof_timetable").child(Integer.toString(prof_count)).child("student_id").getValue(UserInformation.class).getStudent_id());

//            consultation_list.add(uInfo.getProf_year());
//            consultation_list.add(uInfo.getProf_month());
//            consultation_list.add(uInfo.getProf_day());
//            consultation_list.add(uInfo.getProf_start_hour());
//            consultation_list.add(uInfo.getProf_start_minute());
//            consultation_list.add(uInfo.getProf_end_hour());
//            consultation_list.add(uInfo.getProf_end_minute());
//            consultation_list.add(uInfo.getProf_name());
//            consultation_list.add(uInfo.getStudent_name());
//            consultation_list.add(uInfo.getStudent_id());
//            System.out.println("LIST: " + consultation_list);
//
//            prof_count++;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        setupDateTimeInterpreter(id == R.id.action_week_view);
        switch (id){
            case R.id.action_today:
                mWeekView.goToToday();
                return true;


            case R.id.action_day_view:
                if (mWeekViewType != TYPE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(1);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;

            case R.id.action_three_day_view:
                if (mWeekViewType != TYPE_THREE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_THREE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(3);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;
            case R.id.action_week_view:
                if (mWeekViewType != TYPE_WEEK_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_WEEK_VIEW;
                    mWeekView.setNumberOfVisibleDays(7);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Set up a date time interpreter which will show short date values when in week view and long
     * date values otherwise.
     * @param shortDate True if the date values should be short.
     */
    private void setupDateTimeInterpreter(final boolean shortDate) {
        mWeekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar date) {
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
                String weekday = weekdayNameFormat.format(date.getTime());
                SimpleDateFormat format = new SimpleDateFormat(" M/d", Locale.getDefault());

                // All android api level do not have a standard way of getting the first letter of
                // the week day name. Hence we get the first char programmatically.
                // Details: http://stackoverflow.com/questions/16959502/get-one-letter-abbreviation-of-week-day-of-a-date-in-java#answer-16959657
                if (shortDate)
                    weekday = String.valueOf(weekday.charAt(0));
                return weekday.toUpperCase() + format.format(date.getTime());
            }

            @Override
            public String interpretTime(int hour) {
                return hour > 11 ? (hour - 12) + " PM" : (hour == 0 ? "12 AM" : hour + " AM");
            }
        });
    }

    protected String getEventTitle(Calendar time) {
        return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH)+1, time.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(this, "Clicked " + event.getName(), Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
//        Toast.makeText(this, "Long pressed event: " + event.getName(), Toast.LENGTH_SHORT).show();

        add_background.setBackgroundColor(getTransparentColor(Color.BLACK, 0.5f));
        String date = (event.getStartTime().getTime()).toString().substring(8, 10) + " " + (event.getStartTime().getTime()).toString().substring(4, 7) + ", " + (event.getStartTime().getTime()).toString().substring(0, 3);
        String starttime = (event.getStartTime().getTime()).toString().substring(11, 16);
        String endtime = (event.getEndTime().getTime()).toString().substring(11, 16);

        int time_lenght = (event.getStartTime().getTime()).toString().length();
        String year = (event.getStartTime().getTime()).toString().substring(time_lenght - 4);


        int month = 0;

        if((event.getStartTime().getTime()).toString().substring(11, 13).equals("Jan")){
            month = 0;
        }
        else if((event.getStartTime().getTime()).toString().substring(11, 13).equals("Feb")){
            month = 1;
        }
        else if((event.getStartTime().getTime()).toString().substring(11, 13).equals("Mar")){
            month = 2;
        }
        else if((event.getStartTime().getTime()).toString().substring(11, 13).equals("Apr")){
            month = 3;
        }
        else if((event.getStartTime().getTime()).toString().substring(11, 13).equals("May")){
            month = 4;
        }
        else if((event.getStartTime().getTime()).toString().substring(11, 13).equals("Jun")){
            month = 5;
        }
        else if((event.getStartTime().getTime()).toString().substring(11, 13).equals("Jul")){
            month = 6;
        }
        else if((event.getStartTime().getTime()).toString().substring(11, 13).equals("Aug")){
            month = 7;
        }
        else if((event.getStartTime().getTime()).toString().substring(11, 13).equals("Sep")){
            month = 8;
        }
        else if((event.getStartTime().getTime()).toString().substring(11, 13).equals("Oct")){
            month = 9;
        }
        else if((event.getStartTime().getTime()).toString().substring(11, 13).equals("Nov")){
            month = 10;
        }
        else if((event.getStartTime().getTime()).toString().substring(11, 13).equals("Dec")){
            month = 11;
        }

        dayFinal = Integer.parseInt((event.getStartTime().getTime()).toString().substring(8, 10));
        monthFinal = month;
        yearFinal = Integer.parseInt(year);
        hourFinal = Integer.parseInt((event.getStartTime().getTime()).toString().substring(11, 13));
        minuteFinal = Integer.parseInt((event.getStartTime().getTime()).toString().substring(14, 16));
        end_hour= Integer.parseInt((event.getEndTime().getTime()).toString().substring(11, 13));
        end_minute= Integer.parseInt((event.getEndTime().getTime()).toString().substring(14, 16));


        index = (int) ( (event.getId() )*10 + (event.getId() ) );        //belong to which index in the list (0-10 or 11-21, etc)

        id = event.getId();
        dayFinal = Integer.parseInt( consultation_list.get(index + 2) );
        monthFinal = Integer.parseInt( consultation_list.get(index + 1) );
        yearFinal = Integer.parseInt( consultation_list.get(index + 0) );
        hourFinal = Integer.parseInt( consultation_list.get(index + 3) );
        minuteFinal = Integer.parseInt( consultation_list.get(index + 4) );
        end_hour = Integer.parseInt( consultation_list.get(index + 5) );
        end_minute = Integer.parseInt( consultation_list.get(index + 6) );
        prof_name = consultation_list.get(index + 7);
        student_name = consultation_list.get(index + 8);
        student_id = consultation_list.get(index + 9);
        status = consultation_list.get(index + 10);


        if (event.getName().equals("Free")){
            add_background.setVisibility(View.VISIBLE);
            book.setVisibility(View.VISIBLE);
            cancel.setVisibility(View.VISIBLE);
            booking.setVisibility(View.VISIBLE);
            bookinginfo.setVisibility(View.VISIBLE);

            bookinginfo.setText("You are booking " + prof_name + " on " + date + " for the slot [" + starttime + " - " + endtime + "].");
        }
        else if (event.getName().equals("Taken")){
            add_background.setVisibility(View.VISIBLE);
            ok.setVisibility(View.VISIBLE);
            booking.setVisibility(View.VISIBLE);
            bookinginfo.setVisibility(View.VISIBLE);

            bookinginfo.setText(date + " slot [" + starttime + " - " + endtime + "] has been booked by " + student_name + " [" + student_id + "}.");

        }
        else
            Toast.makeText(this, "Long pressed event: " + event.getName(), Toast.LENGTH_SHORT).show();

    }

    public void Ok(View view){
        add_background.setVisibility(View.INVISIBLE);
        ok.setVisibility(View.INVISIBLE);
        booking.setVisibility(View.INVISIBLE);
        bookinginfo.setVisibility(View.INVISIBLE);
    }

    public void Cancel (View view){
        add_background.setVisibility(View.INVISIBLE);
        book.setVisibility(View.INVISIBLE);
        cancel.setVisibility(View.INVISIBLE);
        booking.setVisibility(View.INVISIBLE);
        bookinginfo.setVisibility(View.INVISIBLE);

    }

    public void Book (View view){
        add_background.setVisibility(View.INVISIBLE);
        book.setVisibility(View.INVISIBLE);
        cancel.setVisibility(View.INVISIBLE);
        booking.setVisibility(View.INVISIBLE);
        bookinginfo.setVisibility(View.INVISIBLE);

        booking_event.add(Integer.toString(yearFinal));
        booking_event.add(Integer.toString(monthFinal));     //monthFinal = yearFinal + the chosen month  --> chosen month is Jan (0) to Dec (11)
        booking_event.add(Integer.toString(dayFinal));
        booking_event.add(Integer.toString(hourFinal));
        booking_event.add(Integer.toString(minuteFinal));
        booking_event.add(Integer.toString(end_hour));
        booking_event.add(Integer.toString(end_minute));
        booking_event.add("Taken");

        Toast.makeText(this, "Booking is successful", Toast.LENGTH_SHORT).show();

        FirebaseUser user = mAuth.getInstance().getCurrentUser();
//                String userID = user.getUid();
        System.out.println("ok");
        myRef.child("consultation").child("" + (id)).child("booking_student_name").setValue("Albert");
        myRef.child("consultation").child("" + (id)).child("booking_student_id").setValue("1002391");
        myRef.child("consultation").child("" + (id)).child("status").setValue("Taken");








        mWeekView.goToToday();

        hourFinal = -1;
        minuteFinal = -1;


    }

    @Override
    public void onEmptyViewLongPress(Calendar time) {
        Toast.makeText(this, "Empty view long pressed: " + getEventTitle(time), Toast.LENGTH_SHORT).show();
    }

    public WeekView getWeekView() {
        return mWeekView;
    }

    public static int getTransparentColor(int color, float ratio) {
        int newColor = 0;
        int alpha = Math.round(Color.alpha(color) * ratio);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        newColor = Color.argb(alpha, r, g, b);
        return newColor;
    }


    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
//         Populate the week view with some events.
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

        for(int i = 0; i < consultation_list.size(); i += 11) {
//        for (String s: consultation_list){
//            System.out.println(s);
//            System.out.println("data: " + consultation_list);
//            System.out.println(consultation_list.get(i).toString());
            Calendar startTime = Calendar.getInstance();
            startTime.set(Calendar.YEAR, Integer.parseInt(consultation_list.get(i)));
            startTime.set(Calendar.MONTH, Integer.parseInt(consultation_list.get(i + 1)));
            startTime.set(Calendar.DAY_OF_MONTH, Integer.parseInt(consultation_list.get(i + 2)));
            startTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(consultation_list.get(i + 3)));
            startTime.set(Calendar.MINUTE, Integer.parseInt(consultation_list.get(i + 4)));

            Calendar endTime = (Calendar) startTime.clone();
            endTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(consultation_list.get(i + 5)));
            endTime.set(Calendar.MINUTE, Integer.parseInt(consultation_list.get(i + 6)));
            endTime.set(Calendar.MONTH, newMonth - 1);

            WeekViewEvent final_event = new WeekViewEvent(i/10, consultation_list.get(i + 10), startTime, endTime);

            if(consultation_list.get(i + 10).equals("Taken")){
                final_event.setColor(getResources().getColor(R.color.red));
            }

            else if(consultation_list.get(i + 10).equals("Free")){
                final_event.setColor(getResources().getColor(R.color.green));
            }

            else{
                int[] androidColors = getResources().getIntArray(R.array.androidcolors);
                int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
                final_event.setColor(randomAndroidColor);
            }

            events.add(final_event);

        }

//        Calendar startTime = Calendar.getInstance();
//        startTime.set(Calendar.DAY_OF_MONTH, 4);
//        startTime.set(Calendar.HOUR_OF_DAY, 13);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, 11);              //CHange this newMonth --> the month u want 0 is Jan, 11 is december
//        startTime.set(Calendar.YEAR, newYear);          //CHange this newYear --> 2017
//        Calendar endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR, 1);
//        endTime.set(Calendar.MONTH, newMonth - 1);
//        WeekViewEvent event = new WeekViewEvent(1, "Taken", startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_01));
//        events.add(event);
//
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.HOUR_OF_DAY, 3);
//        startTime.set(Calendar.MINUTE, 30);
//        startTime.set(Calendar.MONTH, newMonth-1);      //the event will occur before month, current month and after month
//        startTime.set(Calendar.YEAR, newYear);          //the event will occur every year
//        endTime = (Calendar) startTime.clone();
//        endTime.set(Calendar.HOUR_OF_DAY, 4);
//        endTime.set(Calendar.MINUTE, 30);
//        endTime.set(Calendar.MONTH, newMonth-1);
//        event = new WeekViewEvent(10, "Free", startTime, endTime);
//        event.setColor(getResources().getColor(R.color.darkblue));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.HOUR_OF_DAY, 4);
//        startTime.set(Calendar.MINUTE, 20);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.set(Calendar.HOUR_OF_DAY, 5);
//        endTime.set(Calendar.MINUTE, 0);
//        event = new WeekViewEvent(10, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_03));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.HOUR_OF_DAY, 5);
//        startTime.set(Calendar.MINUTE, 30);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR_OF_DAY, 2);
//        endTime.set(Calendar.MONTH, newMonth-1);
//        event = new WeekViewEvent(2, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_02));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.HOUR_OF_DAY, 5);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth - 1);
//        startTime.set(Calendar.YEAR, newYear);
//        startTime.add(Calendar.DATE, 1);
//        endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR_OF_DAY, 3);
//        endTime.set(Calendar.MONTH, newMonth - 1);
//        event = new WeekViewEvent(3, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_03));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.DAY_OF_MONTH, 15);
//        startTime.set(Calendar.HOUR_OF_DAY, 3);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR_OF_DAY, 3);
//        event = new WeekViewEvent(4, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_04));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.DAY_OF_MONTH, 1);
//        startTime.set(Calendar.HOUR_OF_DAY, 3);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR_OF_DAY, 3);
//        event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_01));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.DAY_OF_MONTH, startTime.getActualMaximum(Calendar.DAY_OF_MONTH));
//        startTime.set(Calendar.HOUR_OF_DAY, 15);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR_OF_DAY, 3);
//        event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_02));
//        events.add(event);
//
//        //AllDay event
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.HOUR_OF_DAY, 0);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR_OF_DAY, 23);
//        event = new WeekViewEvent(7, getEventTitle(startTime),null, startTime, endTime, true);
//        event.setColor(getResources().getColor(R.color.event_color_04));
//        events.add(event);
////        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.DAY_OF_MONTH, 8);
//        startTime.set(Calendar.HOUR_OF_DAY, 2);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.set(Calendar.DAY_OF_MONTH, 10);
//        endTime.set(Calendar.HOUR_OF_DAY, 23);
//        event = new WeekViewEvent(8, getEventTitle(startTime),null, startTime, endTime, true);
//        event.setColor(getResources().getColor(R.color.event_color_03));
//        events.add(event);
//
//        // All day event until 00:00 next day
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.DAY_OF_MONTH, 10);
//        startTime.set(Calendar.HOUR_OF_DAY, 0);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.SECOND, 0);
//        startTime.set(Calendar.MILLISECOND, 0);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.set(Calendar.DAY_OF_MONTH, 11);
//        event = new WeekViewEvent(8, getEventTitle(startTime), null, startTime, endTime, true);
//        event.setColor(getResources().getColor(R.color.event_color_01));
//        events.add(event);

        return events;
    }

}

