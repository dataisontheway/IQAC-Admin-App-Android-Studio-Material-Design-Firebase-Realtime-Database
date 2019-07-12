package info.androidhive.navigationdrawer.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.navigationdrawer.R;
import info.androidhive.navigationdrawer.other.Privacy;
import info.androidhive.navigationdrawer.other.PrivacyList;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

public class PrivacyPolicyActivity extends AppCompatActivity {

    EditText editTextDescription;
    Button buttonSubmit,buttonClear;

    DatabaseReference rootRef,databasePrivacy;
    DatabaseReference demoRef;

    ListView listViewPrivacy;
    List<Privacy> privacys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databasePrivacy = FirebaseDatabase.getInstance().getReference("Privacy/Description");

        editTextDescription = findViewById(R.id.editTextdescription);
        buttonSubmit = findViewById(R.id.button_submit);
        buttonClear = findViewById(R.id.button_clear);
        listViewPrivacy = findViewById(R.id.listViewPrivacy);

        privacys = new ArrayList<>();

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextDescription.setText("");
            }
        });

        listViewPrivacy.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {

                Privacy artist = privacys.get(i);
                showUpdateDialog(artist.getPrivacyId(), artist.getPrivacyDescription());
                return true;
            }
        });
    }

    private void showUpdateDialog(final String privacyId, String privacyDescription){

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
                deleteArtist(privacyId);
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

    private void deleteArtist(String privacyId) {

        DatabaseReference drPrivacy = FirebaseDatabase.getInstance().getReference("Privacy/Description").child(privacyId);
        drPrivacy.removeValue();

        Snackbar.make(getWindow().getDecorView().getRootView(), "Hey, Description Is Deleted Successfully", Snackbar.LENGTH_LONG).show();

    }

    @Override
    protected void onStart() {
        super.onStart();

        databasePrivacy.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                privacys.clear();

                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren())
                {
                    Privacy privacy = artistSnapshot.getValue(Privacy.class);
                    privacys.add(privacy);
                }

                PrivacyList adapter = new PrivacyList(PrivacyPolicyActivity.this,privacys);
                listViewPrivacy.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void add(){

        String description = editTextDescription.getText().toString().trim();

        rootRef = FirebaseDatabase.getInstance().getReference();
        demoRef  = rootRef.child("Privacy");

        if (!TextUtils.isEmpty(description))
        {
            String id = databasePrivacy.push().getKey();
            Privacy privacy = new Privacy(id,description);
            databasePrivacy.child(id).setValue(privacy);

            Snackbar.make(getWindow().getDecorView().getRootView(), "Hey, Description Is Added Successfully", Snackbar.LENGTH_LONG).show();

        }
        else
        {
            editTextDescription.setError("Please Enter Description");
        }

        editTextDescription.setText("");
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
