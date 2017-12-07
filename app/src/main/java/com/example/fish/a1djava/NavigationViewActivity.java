package com.example.fish.a1djava;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class NavigationViewActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_calendar) {
            // Handle the camera action
            Toast.makeText(this, "Calendar selected", Toast.LENGTH_SHORT).show();

            //fragment is something similar to activity, but does not edit / add on to your AndroidManifest
            CalendarFragment cameraFragment = new CalendarFragment();
            FragmentManager manager = getSupportFragmentManager();

            //replace(a,b) = replace a with b
            //below example, replace mainconstraintlayout_content (which is the id given to the head of the main content view, with the newly made cameraFragment
            manager.beginTransaction().replace(
                    R.id.mainconstraintlayout_content,
                    cameraFragment,
                    cameraFragment.getTag()
            ).commit();


        } else if (id == R.id.nav_announcements) {
            Toast.makeText(this, "Announcements selected", Toast.LENGTH_SHORT).show();
            AnnouncementsFragment announcementsFragment = new AnnouncementsFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(
                    R.id.mainconstraintlayout_content,
                    announcementsFragment,
                    announcementsFragment.getTag()
            ).commit();

        } else if (id == R.id.nav_consultation) {
            Toast.makeText(this, "Consultation booking selected", Toast.LENGTH_SHORT).show();
            ConsultationFragment consultationFragment = new ConsultationFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(
                    R.id.mainconstraintlayout_content,
                    consultationFragment,
                    consultationFragment.getTag()
            ).commit();

        } else if (id == R.id.nav_enrolment) {
            Toast.makeText(this, "Course enrolment selected", Toast.LENGTH_SHORT).show();
            EnrolmentFragment enrolmentFragment = new EnrolmentFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(
                    R.id.mainconstraintlayout_content,
                    enrolmentFragment,
                    enrolmentFragment.getTag()
            ).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}