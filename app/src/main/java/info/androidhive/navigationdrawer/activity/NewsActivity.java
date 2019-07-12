package info.androidhive.navigationdrawer.activity;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import info.androidhive.navigationdrawer.R;
import info.androidhive.navigationdrawer.other.About;
import info.androidhive.navigationdrawer.other.AboutList;
import info.androidhive.navigationdrawer.other.Event;
import info.androidhive.navigationdrawer.other.ImageUploadInfo;
import info.androidhive.navigationdrawer.other.News;
import info.androidhive.navigationdrawer.other.NewsImageUploadInfo;
import info.androidhive.navigationdrawer.other.NewsList;
import info.androidhive.navigationdrawer.other.Privacy;
import info.androidhive.navigationdrawer.other.PrivacyList;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

public class NewsActivity extends AppCompatActivity {

    private EditText editTextTitle,editTextImage,editTextDescription;
    private Button chooseimage,generateimageurl;
    private Button submit,clear;

    DatabaseReference databaseNews;

    String Storage_Path = "All_Image_Uploads/";

    public static final String Database_Path = "All_Image_Uploads_Database";

    Uri FilePathUri;

    StorageReference storageReference;
    DatabaseReference databaseReference;

    int Image_Request_Code = 7;

    ProgressDialog progressDialog ;

    ListView listViewNews;
    List<News> newss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseNews = FirebaseDatabase.getInstance().getReference("news");

        editTextTitle = (EditText) findViewById(R.id.title);
        editTextImage = (EditText) findViewById(R.id.image);
        editTextDescription = (EditText) findViewById(R.id.description);
        submit = (Button) findViewById(R.id.button_submit);
        clear = (Button) findViewById(R.id.button_clear);
        listViewNews = findViewById(R.id.listViewNews);

        newss = new ArrayList<>();

        storageReference = FirebaseStorage.getInstance().getReference();

        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);

        chooseimage = (Button)findViewById(R.id.buttonchooseimage);
        generateimageurl = (Button)findViewById(R.id.buttongenerateimageurl);

        progressDialog = new ProgressDialog(NewsActivity.this);

        chooseimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Please Select Image"), Image_Request_Code);
            }
        });

        generateimageurl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadImageFileToFirebaseStorage();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editTextTitle.setText("");
                editTextImage.setText("");
                editTextDescription.setText("");
            }
        });

        listViewNews.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {

                News news = newss.get(i);
                showUpdateDialog(news.getNewsId(), news.getNewsDescription());
                return true;
            }
        });

    }

    private void showUpdateDialog(final String newsId, String privacyDescription){

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
                deleteArtist(newsId);
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

    private void deleteArtist(String newsId) {

        DatabaseReference drNews = FirebaseDatabase.getInstance().getReference("news").child(newsId);

        drNews.removeValue();

        Snackbar.make(getWindow().getDecorView().getRootView(), "Hey, News Is Deleted Successfully", Snackbar.LENGTH_LONG).show();

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseNews.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                newss.clear();

                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren())
                {
                    News news = artistSnapshot.getValue(News.class);
                    newss.add(news);
                }

                NewsList adapter = new NewsList(NewsActivity.this,newss);
                listViewNews.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void add(){

        String title = editTextTitle.getText().toString().trim();
        String image = editTextImage.getText().toString().trim();
        String description =  editTextDescription.getText().toString().trim();

        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(image) && !TextUtils.isEmpty(description))
        {

            String id = databaseNews.push().getKey();
            News news = new News(id,title,image,description);
            databaseNews.child(id).setValue(news);

            Snackbar.make(getWindow().getDecorView().getRootView(), "Hey, News Is Added Successfully", Snackbar.LENGTH_LONG).show();

        }
        else
        {
            editTextTitle.setError("Please Enter Title");
            editTextImage.setError("Please Paste/Generate Image URL");
            editTextDescription.setError("Please Enter Description");
        }

        editTextTitle.setText("");
        editTextImage.setText("");
        editTextDescription.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);

                chooseimage.setText("Image Selected");

            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }

    public void UploadImageFileToFirebaseStorage() {

        if (FilePathUri != null) {

            progressDialog.setTitle("Generating Image URL...");

            progressDialog.show();

            StorageReference storageReference2nd = storageReference.child(Storage_Path + System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));

            storageReference2nd.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            progressDialog.dismiss();

                            Snackbar.make(getWindow().getDecorView().getRootView(), "Image URL Generated Successfully", Snackbar.LENGTH_LONG).show();

                            @SuppressWarnings("VisibleForTests")
                            ImageUploadInfo imageUploadInfo = new ImageUploadInfo(taskSnapshot.getDownloadUrl().toString());

                            String link = imageUploadInfo.getImageURL();

                            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("text", link);
                            clipboard.setPrimaryClip(clip);

                            editTextImage.setText(link);

                            String ImageUploadId = databaseReference.push().getKey();

                            databaseReference.child(ImageUploadId).setValue(imageUploadInfo);

                        }
                    })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {

                            progressDialog.dismiss();

                            Toast.makeText(NewsActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })

                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            progressDialog.setTitle("Generating Image URL...");

                        }
                    });
        }
        else {

            Toast.makeText(NewsActivity.this, "Please Select Image", Toast.LENGTH_LONG).show();

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