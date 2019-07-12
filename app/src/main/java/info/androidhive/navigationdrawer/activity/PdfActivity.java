package info.androidhive.navigationdrawer.activity;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import info.androidhive.navigationdrawer.R;
import info.androidhive.navigationdrawer.other.About;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

public class PdfActivity extends AppCompatActivity {

    private EditText january,february,march,april,may,june,july,august,september,october,november,december;

    private Button submitjanuary,submitfebruary,submitmarch,submitapril,submitmay,submitjune;
    private Button submitjuly,submitaugust,submitseptember,submitoctober,submitnovember,submitdecember;

    private Button clearjanuary,clearfebruary,clearmarch,clearapril,clearmay,clearjune;
    private Button clearjuly,clearaugust,clearseptember,clearoctober,clearnovember,cleardecember;

    DatabaseReference rootRef;
    DatabaseReference demoRef1,demoRef2,demoRef3,demoRef4,demoRef5,demoRef6,demoRef7,demoRef8,demoRef9,demoRef10,demoRef11,demoRef12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        january = findViewById(R.id.januarypdf);
        february = findViewById(R.id.februarypdf);
        march = findViewById(R.id.marchpdf);
        april = findViewById(R.id.aprilpdf);
        may = findViewById(R.id.maypdf);
        june = findViewById(R.id.junepdf);
        july = findViewById(R.id.julypdf);
        august = findViewById(R.id.augustpdf);
        september = findViewById(R.id.septemberpdf);
        october = findViewById(R.id.octoberpdf);
        november = findViewById(R.id.novemberpdf);
        december = findViewById(R.id.decemberpdf);

        submitjanuary = findViewById(R.id.button_submit_january);
        submitfebruary = findViewById(R.id.button_submit_february);
        submitmarch = findViewById(R.id.button_submit_march);
        submitapril = findViewById(R.id.button_submit_april);
        submitmay = findViewById(R.id.button_submit_may);
        submitjune = findViewById(R.id.button_submit_june);
        submitjuly = findViewById(R.id.button_submit_july);
        submitaugust = findViewById(R.id.button_submit_august);
        submitseptember = findViewById(R.id.button_submit_september);
        submitoctober = findViewById(R.id.button_submit_october);
        submitnovember = findViewById(R.id.button_submit_november);
        submitdecember = findViewById(R.id.button_submit_december);

        clearjanuary = findViewById(R.id.button_clear_january);
        clearfebruary = findViewById(R.id.button_clear_february);
        clearmarch = findViewById(R.id.button_clear_march);
        clearapril = findViewById(R.id.button_clear_april);
        clearmay = findViewById(R.id.button_clear_may);
        clearjune = findViewById(R.id.button_clear_june);
        clearjuly = findViewById(R.id.button_clear_july);
        clearaugust = findViewById(R.id.button_clear_august);
        clearseptember = findViewById(R.id.button_clear_september);
        clearoctober = findViewById(R.id.button_clear_october);
        clearnovember = findViewById(R.id.button_clear_november);
        cleardecember = findViewById(R.id.button_clear_december);

        rootRef = FirebaseDatabase.getInstance().getReference();

        demoRef1 = demoRef2 = demoRef3 = demoRef4 = demoRef5 = demoRef6 = demoRef7 = demoRef8 = demoRef9 = demoRef10 = demoRef11 = demoRef12 = rootRef.child("Download/Pdf");

        submitjanuary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = january.getText().toString();

                if (!TextUtils.isEmpty(value))
                {
                    demoRef1.child("January").setValue(value);
                    january.setText("");
                    Snackbar.make(getWindow().getDecorView().getRootView(), "Hey, Pdf Link Is Added Successfully", Snackbar.LENGTH_LONG).show();
                }
                else
                {
                    january.setError("Please Add Pdf Link");
                }

            }
        });

        submitfebruary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = february.getText().toString();

                if (!TextUtils.isEmpty(value))
                {
                    demoRef2.child("February").setValue(value);
                    february.setText("");
                    Snackbar.make(getWindow().getDecorView().getRootView(), "Hey, Pdf Link Is Added Successfully", Snackbar.LENGTH_LONG).show();
                }
                else
                {
                    february.setError("Please Add Pdf Link");
                }

            }
        });

        submitmarch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = march.getText().toString();

                if (!TextUtils.isEmpty(value))
                {
                    demoRef3.child("March").setValue(value);
                    march.setText("");
                    Snackbar.make(getWindow().getDecorView().getRootView(), "Hey, Pdf Link Is Added Successfully", Snackbar.LENGTH_LONG).show();
                }
                else
                {
                    march.setError("Please Add Pdf Link");
                }

            }
        });

        submitapril.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = april.getText().toString();

                if (!TextUtils.isEmpty(value))
                {
                    demoRef4.child("April").setValue(value);
                    april.setText("");
                    Snackbar.make(getWindow().getDecorView().getRootView(), "Hey, Pdf Link Is Added Successfully", Snackbar.LENGTH_LONG).show();
                }
                else
                {
                    april.setError("Please Add Pdf Link");
                }

            }
        });

        submitmay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = may.getText().toString();

                if (!TextUtils.isEmpty(value))
                {
                    demoRef5.child("May").setValue(value);
                    may.setText("");
                    Snackbar.make(getWindow().getDecorView().getRootView(), "Hey, Pdf Link Is Added Successfully", Snackbar.LENGTH_LONG).show();
                }
                else
                {
                    may.setError("Please Add Pdf Link");
                }

            }
        });

        submitjune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = june.getText().toString();

                if (!TextUtils.isEmpty(value))
                {
                    demoRef6.child("June").setValue(value);
                    june.setText("");
                    Snackbar.make(getWindow().getDecorView().getRootView(), "Hey, Pdf Link Is Added Successfully", Snackbar.LENGTH_LONG).show();
                }
                else
                {
                    june.setError("Please Add Pdf Link");
                }

            }
        });

        submitjuly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = july.getText().toString();

                if (!TextUtils.isEmpty(value))
                {
                    demoRef7.child("July").setValue(value);
                    july.setText("");
                    Snackbar.make(getWindow().getDecorView().getRootView(), "Hey, Pdf Link Is Added Successfully", Snackbar.LENGTH_LONG).show();
                }
                else
                {
                    july.setError("Please Add Pdf Link");
                }

            }
        });

        submitaugust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = august.getText().toString();

                if (!TextUtils.isEmpty(value))
                {
                    demoRef8.child("August").setValue(value);
                    august.setText("");
                    Snackbar.make(getWindow().getDecorView().getRootView(), "Hey, Pdf Link Is Added Successfully", Snackbar.LENGTH_LONG).show();
                }
                else
                {
                    august.setError("Please Add Pdf Link");
                }

            }
        });

        submitseptember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = september.getText().toString();

                if (!TextUtils.isEmpty(value))
                {
                    demoRef9.child("September").setValue(value);
                    september.setText("");
                    Snackbar.make(getWindow().getDecorView().getRootView(), "Hey, Pdf Link Is Added Successfully", Snackbar.LENGTH_LONG).show();
                }
                else
                {
                    september.setError("Please Add Pdf Link");
                }

            }
        });

        submitoctober.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = october.getText().toString();

                if (!TextUtils.isEmpty(value))
                {
                    demoRef10.child("October").setValue(value);
                    october.setText("");
                    Snackbar.make(getWindow().getDecorView().getRootView(), "Hey, Pdf Link Is Added Successfully", Snackbar.LENGTH_LONG).show();
                }
                else
                {
                    october.setError("Please Add Pdf Link");
                }

            }
        });

        submitnovember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = november.getText().toString();

                if (!TextUtils.isEmpty(value))
                {
                    demoRef11.child("November").setValue(value);
                    november.setText("");
                    Snackbar.make(getWindow().getDecorView().getRootView(), "Hey, Pdf Link Is Added Successfully", Snackbar.LENGTH_LONG).show();
                }
                else
                {
                    november.setError("Please Add Pdf Link");
                }

            }
        });

        submitdecember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = december.getText().toString();

                if (!TextUtils.isEmpty(value))
                {
                    demoRef12.child("December").setValue(value);
                    december.setText("");
                    Snackbar.make(getWindow().getDecorView().getRootView(), "Hey, Pdf Link Is Added Successfully", Snackbar.LENGTH_LONG).show();
                }
                else
                {
                    december.setError("Please Add Pdf Link");
                }

            }
        });

        clearjanuary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                january.setText("");

            }
        });

        clearfebruary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                february.setText("");

            }
        });

        clearmarch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                march.setText("");

            }
        });

        clearapril.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                april.setText("");

            }
        });

        clearmay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                may.setText("");

            }
        });

        clearjune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                june.setText("");

            }
        });

        clearjuly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                july.setText("");

            }
        });

        clearaugust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                august.setText("");

            }
        });

        clearseptember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                september.setText("");

            }
        });

        clearoctober.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                october.setText("");

            }
        });

        clearnovember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                november.setText("");

            }
        });

        cleardecember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                december.setText("");

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
