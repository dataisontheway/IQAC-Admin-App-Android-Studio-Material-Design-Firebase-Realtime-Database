package info.androidhive.navigationdrawer.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import info.androidhive.navigationdrawer.R;
import info.androidhive.navigationdrawer.other.Event;
import info.androidhive.navigationdrawer.other.EventList;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

public class FridayActivity extends AppCompatActivity implements View.OnClickListener {

    TextView datePicker,txtDate,timePicker,txtTime;
    ImageView dateimage,timeimage;
    Button buttonsubmit,buttonclear;
    EditText editTextProgramme,editTextVenue;
    DatabaseReference databaseevents;
    DatabaseReference rootRef;
    DatabaseReference demoRef;
    ListView listViewEvents;
    List<Event> events;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friday);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseevents = FirebaseDatabase.getInstance().getReference("events/friday/sno");

        datePicker = findViewById(R.id.txtdate);
        dateimage = findViewById(R.id.imgdate);
        txtDate = findViewById(R.id.editdate);

        editTextProgramme = findViewById(R.id.programme);
        editTextVenue = findViewById(R.id.venue);

        timePicker = findViewById(R.id.txttime);
        timeimage = findViewById(R.id.imgtime);
        txtTime = findViewById(R.id.edittime);

        listViewEvents = findViewById(R.id.listViewSunday);

        datePicker.setOnClickListener(this);
        dateimage.setOnClickListener(this);

        timePicker.setOnClickListener(this);
        timeimage.setOnClickListener(this);

        buttonsubmit = findViewById(R.id.button_submit);
        buttonclear = findViewById(R.id.button_clear);

        buttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });

        buttonclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtDate.setText("");
                editTextProgramme.setText("");
                txtTime.setText("");
                editTextVenue.setText("");
            }
        });

        events = new ArrayList<>();

        listViewEvents.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {

                Event event = events.get(i);
                showUpdateDialog(event.getEventId(), event.getEventProgramme());
                return true;
            }
        });

    }

    private void showUpdateDialog(final String eventId, String privacyDescription){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.update_dialog, null);

        dialogBuilder.setView(dialogView);


        final Button buttonCancel = dialogView.findViewById(R.id.dialog_no);
        final Button buttonDelete = dialogView.findViewById(R.id.dialog_yes);


        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteArtist(eventId);
                alertDialog.dismiss();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }

    private void deleteArtist(String eventId) {

        DatabaseReference drEvent = FirebaseDatabase.getInstance().getReference("events/friday/sno").child(eventId);

        drEvent.removeValue();

        Snackbar.make(getWindow().getDecorView().getRootView(), "Hey, Event Is Deleted Successfully", Snackbar.LENGTH_LONG).show();

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseevents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                events.clear();

                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren())
                {
                    Event event = artistSnapshot.getValue(Event.class);
                    events.add(event);
                }

                EventList adapter = new EventList(FridayActivity.this,events);
                listViewEvents.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void add(){

        String date = txtDate.getText().toString().trim();
        String programme = editTextProgramme.getText().toString().trim();
        String time = txtTime.getText().toString().trim();
        String venue = editTextVenue.getText().toString().trim();

        rootRef = FirebaseDatabase.getInstance().getReference();
        demoRef  = rootRef.child("events");

        if (!TextUtils.isEmpty(programme) && !TextUtils.isEmpty(time) && !TextUtils.isEmpty(venue))
        {

            demoRef.child("friday/date").setValue(date);

            String id = databaseevents.push().getKey();
            Event event = new Event(id,programme,time,venue);
            databaseevents.child(id).setValue(event);

            Snackbar.make(getWindow().getDecorView().getRootView(), "Hey, Event Is Added Successfully", Snackbar.LENGTH_LONG).show();

        }
        else
        {
            editTextProgramme.setError("Please Enter Programme Name");
            editTextVenue.setError(" Please Enter Venue");
        }

        editTextProgramme.setText("");
        txtTime.setText("");
        editTextVenue.setText("");
    }

    @Override
    public void onClick(View v) {

        if ((v == datePicker) || (v == dateimage)) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

        if ((v == timePicker) || (v == timeimage)) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            String AM_PM ;
                            if(hourOfDay < 12) {
                                AM_PM = "AM";

                            } else {
                                AM_PM = "PM";
                                mHour=mHour-12;
                                hourOfDay=hourOfDay -12;
                            }

                            txtTime.setText(hourOfDay + ":" + minute + " " +AM_PM);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
