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
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.fish.a1djava.R;
import com.example.fish.a1djava.library.DateTimeInterpreter;
import com.example.fish.a1djava.library.MonthLoader;
import com.example.fish.a1djava.library.WeekView;
import com.example.fish.a1djava.library.WeekViewEvent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;


public class Professor_Consultation extends AppCompatActivity implements
        WeekView.EventClickListener, MonthLoader.MonthChangeListener, WeekView.EventLongPressListener, WeekView.EmptyViewLongPressListener ,
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    private static final int TYPE_DAY_VIEW = 1;
    private static final int TYPE_THREE_DAY_VIEW = 2;
    private static final int TYPE_WEEK_VIEW = 3;
    private int mWeekViewType = TYPE_THREE_DAY_VIEW;
    private WeekView mWeekView;

    private int day, month, year, hour, minute;
    private int dayFinal, monthFinal, yearFinal, hourFinal = -1, minuteFinal = -1;
    private int  end_hour, end_minute;
    private String event_name;
    private ArrayList<String> list_of_timetable = new ArrayList<String>();
    private int count = 0;

    private static final String TAG = "Professor_Consultation";
    private FirebaseAuth mAuth;
    //    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;


    private ArrayList<String> timetable_list = new ArrayList<String>();
    private ArrayList<String> prof_consultation_list = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

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


        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("message");


        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Object value = dataSnapshot.getValue();
                Log.d(TAG, "onDataChange: Added information to database: \n" + dataSnapshot.getValue() + value);

                showData(dataSnapshot);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }


//        mAuth = FirebaseAuth.getInstance();
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    // User is signed in
//                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
//                    Toast.makeText(BasicActivity.this, "Successfully signed in", Toast.LENGTH_SHORT).show();
//
//                } else {
//                    // User is signed out
//                    Log.d(TAG, "onAuthStateChanged:signed_out");
//                    Toast.makeText(BasicActivity.this, "Successfully signed out", Toast.LENGTH_SHORT).show();
//                }
//                // ...
//            }
//        };
//


//    @Override
//    public void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
//    }
//
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (mAuthListener != null) {
//            mAuth.removeAuthStateListener(mAuthListener);
//        }
//    }


    private void showData(DataSnapshot dataSnapshot){
        int index = 0;
        for(DataSnapshot ds: dataSnapshot.getChildren()) {
//            UserInformation uInfo = new UserInformation();
            prof_consultation_list.clear();
            for (HashMap<String, String> map : (ArrayList<HashMap<String, String>>) ds.getValue()) {

                //System.out.println("map: " + map.get("booking_student_name"));
                prof_consultation_list.add(String.valueOf(map.get("prof_year")));
                prof_consultation_list.add(String.valueOf(map.get("prof_month")));
                prof_consultation_list.add(String.valueOf(map.get("prof_day")));
                prof_consultation_list.add(String.valueOf(map.get("prof_start_hour")));
                prof_consultation_list.add(String.valueOf(map.get("prof_start_minute")));
                prof_consultation_list.add(String.valueOf(map.get("prof_end_hour")));
                prof_consultation_list.add(String.valueOf(map.get("prof_end_minute")));
                prof_consultation_list.add(String.valueOf(map.get("prof_name")));
                prof_consultation_list.add(String.valueOf(map.get("booking_student_name")));
                prof_consultation_list.add(String.valueOf(map.get("booking_student_id")));
                prof_consultation_list.add(String.valueOf(map.get("status")));


            }
            break;

        }
//            timetable_list.add(uInfo.getProf_month());
//            timetable_list.add(uInfo.getProf_day());
//            timetable_list.add(uInfo.getProf_start_hour());
//            timetable_list.add(uInfo.getProf_start_minute());
//            timetable_list.add(uInfo.getProf_end_hour());
//            timetable_list.add(uInfo.getProf_end_minute());
//            timetable_list.add(uInfo.getProf_name());
//            timetable_list.add(uInfo.getStudent_name());
//            timetable_list.add(uInfo.getStudent_id());


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

//            timetable_list.add(uInfo.getProf_year());
//            timetable_list.add(uInfo.getProf_month());
//            timetable_list.add(uInfo.getProf_day());
//            timetable_list.add(uInfo.getProf_start_hour());
//            timetable_list.add(uInfo.getProf_start_minute());
//            timetable_list.add(uInfo.getProf_end_hour());
//            timetable_list.add(uInfo.getProf_end_minute());
//            timetable_list.add(uInfo.getProf_name());
//            timetable_list.add(uInfo.getStudent_name());
//            timetable_list.add(uInfo.getStudent_id());
//            System.out.println("LIST: " + timetable_list);
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

            case R.id.add_event:
                // System.out.println("LIST " + list_of_timetable);
                System.out.println("TEMP " + list_of_timetable);

                //list_of_timetable.add(list_of_timetable);
                //list_of_timetable.clear();
                final EditText event= (EditText) findViewById(R.id.event);
                final Button put_the_event = (Button) findViewById(R.id.add);
                final View add_background = (View) findViewById(R.id.add_background);

                add_background.setBackgroundColor(getTransparentColor(Color.BLACK, 0.5f));

                event.setText("");
                event.setVisibility(View.VISIBLE);
                put_the_event.setVisibility(View.VISIBLE);
                add_background.setVisibility(View.VISIBLE);


                put_the_event.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(Professor_Consultation.this, "Event is added successfully", Toast.LENGTH_SHORT).show();
                        event_name = event.getText().toString();

                        event.setVisibility(View.INVISIBLE);
                        put_the_event.setVisibility(View.INVISIBLE);
                        add_background.setVisibility(View.INVISIBLE);

                        Calendar c = Calendar.getInstance();
                        year = c.get(Calendar.YEAR);
                        month = c.get(Calendar.MONTH);
                        day = c.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(Professor_Consultation.this, Professor_Consultation.this,
                                year, month, day);
                        datePickerDialog.show();
                    }

                });

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
        Toast.makeText(this, "Long pressed event: " + event.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmptyViewLongPress(Calendar time) {
        Toast.makeText(this, "Empty view long pressed: " + getEventTitle(time), Toast.LENGTH_SHORT).show();
    }

    public WeekView getWeekView() {
        return mWeekView;
    }


    //after u finish choose the date from CalendarPicker, the timePicker will appear
    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        yearFinal = i;
        monthFinal = i1 + i;
        dayFinal = i2;


//        TextView add_startingtime = (TextView) findViewById(R.id.addStartingtime);
//        add_startingtime.setVisibility(View.VISIBLE);


        Calendar c = Calendar.getInstance();
        hour= c.get(Calendar.HOUR_OF_DAY);
        minute= c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(Professor_Consultation.this, Professor_Consultation.this,
                hour, minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
//        TextView add_endingtime = (TextView) findViewById(R.id.addEndingtime);
//        TextView add_startingtime = (TextView) findViewById(R.id.addStartingtime);

        if (hourFinal == -1 && minuteFinal == -1) {

            hourFinal = i;
            minuteFinal = i1;
//            add_startingtime.setVisibility(View.INVISIBLE);
//            add_endingtime.setVisibility(View.VISIBLE);

            Toast.makeText(this, "Starting Time is added successfully", Toast.LENGTH_SHORT).show();

            Calendar d = Calendar.getInstance();
            end_hour = d.get(Calendar.HOUR_OF_DAY);
            end_minute = d.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog1 = new TimePickerDialog(Professor_Consultation.this, Professor_Consultation.this,
                    hour, minute, DateFormat.is24HourFormat(this));
            timePickerDialog1.show();
        } else if (hourFinal != -1 && minuteFinal != -1) {
            end_hour = i;
            end_minute = i1;
//            add_endingtime.setVisibility(View.INVISIBLE);

            if (end_hour > hourFinal) {
                Toast.makeText(this, "Ending Time is added successfully", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Event is added successfully", Toast.LENGTH_SHORT).show();


                list_of_timetable.add(Integer.toString(yearFinal));
                list_of_timetable.add(Integer.toString(monthFinal - yearFinal));     //monthFinal = yearFinal + the chosen month  --> chosen month is Jan (0) to Dec (11)
                list_of_timetable.add(Integer.toString(dayFinal));
                list_of_timetable.add(Integer.toString(hourFinal));
                list_of_timetable.add(Integer.toString(minuteFinal));
                list_of_timetable.add(Integer.toString(end_hour));
                list_of_timetable.add(Integer.toString(end_minute));
                list_of_timetable.add(event_name);


                Log.d(TAG, "Attempting to submit to database: " +
                        "Year: " + Integer.toString(yearFinal));

                FirebaseUser user = mAuth.getInstance().getCurrentUser();
//                String userID = user.getUid();
                myRef.child("consultation").child(Integer.toString(count)).child("prof_year").setValue(yearFinal);
                myRef.child("consultation").child(Integer.toString(count)).child("prof_month").setValue((monthFinal - yearFinal));
                myRef.child("consultation").child(Integer.toString(count)).child("prof_day").setValue(dayFinal);
                myRef.child("consultation").child(Integer.toString(count)).child("prof_start_hour").setValue(hourFinal);
                myRef.child("consultation").child(Integer.toString(count)).child("prof_start_minute").setValue(minuteFinal);
                myRef.child("consultation").child(Integer.toString(count)).child("prof_end_hour").setValue(end_hour);
                myRef.child("consultation").child(Integer.toString(count)).child("prof_end_minute").setValue(end_minute);
                myRef.child("consultation").child(Integer.toString(count)).child("prof_name").setValue("FISHaaa");
                myRef.child("consultation").child(Integer.toString(count)).child("booking_student_name").setValue("");
                myRef.child("consultation").child(Integer.toString(count)).child("booking_student_id").setValue("");
                myRef.child("consultation").child(Integer.toString(count)).child("status").setValue("Free");

                count ++;

                hourFinal = -1;
                minuteFinal = -1;
                onMonthChange(yearFinal, monthFinal - yearFinal);
                mWeekView.goToToday();


            } else {
                Toast.makeText(this, "ERROR!!! Ending Time must be greater than Starting Time", Toast.LENGTH_SHORT).show();
                hourFinal = -1;
                minuteFinal = -1;
                return;
            }
        }
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
        String name = "";
        for(int i = 0; i < prof_consultation_list.size(); i += 11) {
            System.out.println(prof_consultation_list);
            Calendar startTime = Calendar.getInstance();
            startTime.set(Calendar.YEAR, Integer.parseInt(prof_consultation_list.get(i)));
            startTime.set(Calendar.MONTH, Integer.parseInt(prof_consultation_list.get(i + 1)));
            startTime.set(Calendar.DAY_OF_MONTH, Integer.parseInt(prof_consultation_list.get(i + 2)));
            startTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(prof_consultation_list.get(i + 3)));
            startTime.set(Calendar.MINUTE, Integer.parseInt(prof_consultation_list.get(i + 4)));

            Calendar endTime = (Calendar) startTime.clone();
            endTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(prof_consultation_list.get(i + 5)));
            endTime.set(Calendar.MINUTE, Integer.parseInt(prof_consultation_list.get(i + 6)));
            endTime.set(Calendar.MONTH, newMonth - 1);


            if(prof_consultation_list.get(i + 10).equals("Taken")){
                String requested_student = "Request from " + prof_consultation_list.get(i+8) + " [" + prof_consultation_list.get(i+9) + "].";
                WeekViewEvent final_event = new WeekViewEvent(i/10, requested_student, startTime, endTime);
                final_event.setColor(getResources().getColor(R.color.darkpurple));
                events.add(final_event);

            }

            else if(prof_consultation_list.get(i + 10).equals("Free")){
                WeekViewEvent final_event = new WeekViewEvent(i/10, "Free", startTime, endTime);
                final_event.setColor(getResources().getColor(R.color.green));
                events.add(final_event);

            }

            else{
                WeekViewEvent final_event = new WeekViewEvent(i/10, prof_consultation_list.get(i + 10), startTime, endTime);
                int[] androidColors = getResources().getIntArray(R.array.androidcolors);
                int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
                final_event.setColor(randomAndroidColor);
                events.add(final_event);

            }

//            startTime.set(Calendar.YEAR, Integer.parseInt(timetable_list.get(i)));
//            startTime.set(Calendar.MONTH, Integer.parseInt(timetable_list.get(i + 1)));
//            startTime.set(Calendar.DAY_OF_MONTH, Integer.parseInt(timetable_list.get(i + 2)));
//            startTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timetable_list.get(i + 3)));
//            startTime.set(Calendar.MINUTE, Integer.parseInt(timetable_list.get(i + 4)));
//
//            Calendar endTime = (Calendar) startTime.clone();
//            endTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timetable_list.get(i + 5)));
//            endTime.set(Calendar.MINUTE, Integer.parseInt(timetable_list.get(i + 6)));
//            endTime.set(Calendar.MONTH, newMonth - 1);
//
//            WeekViewEvent final_event = new WeekViewEvent(1, timetable_list.get(i + 7), startTime, endTime);
//
//            int[] androidColors = getResources().getIntArray(R.array.androidcolors);
//            int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
//            final_event.setColor(randomAndroidColor);
//            events.add(final_event);
            System.out.println("---SUCCESSFUL---");
        }


        System.out.println(newMonth);
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 4);
        startTime.set(Calendar.HOUR_OF_DAY, 13);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, 11);              //CHange this newMonth --> the month u want 0 is Jan, 11 is december
        startTime.set(Calendar.YEAR, newYear);          //CHange this newYear --> 2017
        Calendar endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR, 1);
        endTime.set(Calendar.MONTH, newMonth - 1);
        WeekViewEvent event = new WeekViewEvent(1, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_01));
        events.add(event);


        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);      //the event will occur before month, current month and after month
        startTime.set(Calendar.YEAR, newYear);          //the event will occur every year
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 4);
        endTime.set(Calendar.MINUTE, 30);
        endTime.set(Calendar.MONTH, newMonth-1);
        event = new WeekViewEvent(10, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_02));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 4);
        startTime.set(Calendar.MINUTE, 20);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 5);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(10, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_03));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 5);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 2);
        endTime.set(Calendar.MONTH, newMonth-1);
        event = new WeekViewEvent(2, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_02));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 5);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth - 1);
        startTime.set(Calendar.YEAR, newYear);
        startTime.add(Calendar.DATE, 1);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        endTime.set(Calendar.MONTH, newMonth - 1);
        event = new WeekViewEvent(3, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_03));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 15);
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        event = new WeekViewEvent(4, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_04));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 1);
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_01));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, startTime.getActualMaximum(Calendar.DAY_OF_MONTH));
        startTime.set(Calendar.HOUR_OF_DAY, 15);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_02));
        events.add(event);

        //AllDay event
        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 0);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 23);
        event = new WeekViewEvent(7, getEventTitle(startTime),null, startTime, endTime, true);
        event.setColor(getResources().getColor(R.color.event_color_04));
        events.add(event);
//        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 8);
        startTime.set(Calendar.HOUR_OF_DAY, 2);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.DAY_OF_MONTH, 10);
        endTime.set(Calendar.HOUR_OF_DAY, 23);
        event = new WeekViewEvent(8, getEventTitle(startTime),null, startTime, endTime, true);
        event.setColor(getResources().getColor(R.color.event_color_03));
        events.add(event);

        // All day event until 00:00 next day
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 10);
        startTime.set(Calendar.HOUR_OF_DAY, 0);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.SECOND, 0);
        startTime.set(Calendar.MILLISECOND, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.DAY_OF_MONTH, 11);
        event = new WeekViewEvent(8, getEventTitle(startTime), null, startTime, endTime, true);
        event.setColor(getResources().getColor(R.color.event_color_01));
        events.add(event);

        return events;
    }

}

