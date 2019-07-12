package info.androidhive.navigationdrawer.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import info.androidhive.navigationdrawer.R;

public class EventsActivity extends AppCompatActivity {

    ImageView sun,mon,tue,wed,thu,fri,sat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sun = (ImageView) findViewById(R.id.imgsun);
        mon = (ImageView) findViewById(R.id.imgmon);
        tue = (ImageView) findViewById(R.id.imgtue);
        wed = (ImageView) findViewById(R.id.imgwed);
        thu = (ImageView) findViewById(R.id.imgthu);
        fri = (ImageView) findViewById(R.id.imgfri);
        sat = (ImageView) findViewById(R.id.imgsat);

        sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventsActivity.this,SunActivity.class);
                startActivity(intent);
            }
        });

        mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventsActivity.this,MonActivity.class);
                startActivity(intent);
            }
        });

        tue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventsActivity.this,TueActivity.class);
                startActivity(intent);
            }
        });

        wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventsActivity.this,WedActivity.class);
                startActivity(intent);
            }
        });

        thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventsActivity.this,ThuActivity.class);
                startActivity(intent);
            }
        });

        fri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventsActivity.this,FridayActivity.class);
                startActivity(intent);
            }
        });

        sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventsActivity.this,SaturdayActivity.class);
                startActivity(intent);
            }
        });
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
