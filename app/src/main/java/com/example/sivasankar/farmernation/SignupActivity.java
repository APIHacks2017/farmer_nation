package com.example.sivasankar.farmernation;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText edtPwd, edtUser, edtMobile, edtEmail, edtCity, edtState;
    private RadioGroup radioGroup;
    private Button btnSignUp;
    private TextView txtUpload;
    private ImageView imgUpload;

    protected static final int CAMERA_REQUEST = 1888;
    protected static final int GALLERY_PICTURE = 1;
    private final int REQUEST_CAMERA_PERMISSION = 300;
    long millis;

    Bitmap bitmap;
    String selectedImagePath;

    private File finalFile = null;
    int selectedUpload = -1;

    public final String APP_TAG = "MyCustomApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initView();
    }

    private void initView() {
        txtUpload = (TextView) findViewById(R.id.txtUpload);
        imgUpload = (ImageView) findViewById(R.id.imgUpload);
        edtUser = (TextInputEditText) findViewById(R.id.edtUser);
        edtPwd = (TextInputEditText) findViewById(R.id.edtPwd);
        edtMobile = (TextInputEditText) findViewById(R.id.edtMobile);
        edtEmail = (TextInputEditText) findViewById(R.id.edtEmail);
        edtCity = (TextInputEditText) findViewById(R.id.edtCity);
        edtState = (TextInputEditText) findViewById(R.id.edtState);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(this);
        txtUpload.setOnClickListener(this);
        imgUpload.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignUp:
                LoginValidation();
                break;
            case R.id.imgUpload:
                startDialog();
                break;
            case R.id.txtUpload:
                startDialog();
                break;
            default:
                break;

        }
    }

    private void startDialog() {

        final AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
        myAlertDialog.setTitle("Upload Pictures Option");
        myAlertDialog.setMessage("How do you want to set your picture?");

        myAlertDialog.setPositiveButton("Gallery",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent pictureActionIntent = null;

                        pictureActionIntent = new Intent(
                                Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(
                                pictureActionIntent,
                                GALLERY_PICTURE);

                    }
                });

        myAlertDialog.setNegativeButton("Camera",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        if (PermissionChecker.checkSelfPermission(SignupActivity.this, Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(SignupActivity.this,
                                    new String[]{Manifest.permission.CAMERA},
                                    REQUEST_CAMERA_PERMISSION);
                        } else {
                            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, CAMERA_REQUEST);
                        }


                    }
                });

        myAlertDialog.setNeutralButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });
        myAlertDialog.show();
    }

    public Uri getPhotoFileUri(String fileName) {
        // Only continue if the SD Card is mounted
        if (isExternalStorageAvailable()) {
            // Get safe storage directory for photos
            // Use `getExternalFilesDir` on Context to access package-specific directories.
            // This way, we don't need to request external read/write runtime permissions.
            File mediaStorageDir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), APP_TAG);

            // Create the storage directory if it does not exist
            if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
                Log.d(APP_TAG, "failed to create directory");
            }

            // Return the file target for the photo based on filename
            return Uri.fromFile(new File(mediaStorageDir.getPath() + File.separator + fileName + ".jpg"));
        }
        return null;
    }


    private boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int grantResult : grantResults) {
            if ((grantResult == PackageManager.PERMISSION_GRANTED) && (requestCode == REQUEST_CAMERA_PERMISSION)) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File f = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                startActivityForResult(intent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "unable to access the camera. permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bitmap = null;
        selectedImagePath = null;

        //ImageView img_logo = (ImageView) findViewById(R.id.sing_up_document_image);
        if (resultCode == RESULT_OK && requestCode == CAMERA_REQUEST) {

            try {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imgUpload.setImageBitmap(photo);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (resultCode == RESULT_OK && requestCode == GALLERY_PICTURE) {
            if (data != null) {
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                    //img_logo.setImageBitmap(bitmap);


                    imgUpload.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void LoginValidation() {

        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        String User = edtUser.getText().toString();
        String Pwd = edtPwd.getText().toString();
        if (User.length() == 0 || User.equals(" ")) {
            edtUser.setError("Please Enter User Name");
        } else if (edtMobile.getText().toString().length() < 10) {
            edtMobile.setError("Please Enter Valid Mobile No ");
        } else if (Pwd.length() == 0 || Pwd.equals(" ")) {
            edtPwd.setError("Please Enter Password ");
        } else if (edtEmail.getText().toString().length() == 0 || edtEmail.getText().toString().equals(" ")) {
            edtEmail.setError("Please Enter Email Id");
        } else if (edtCity.getText().toString().length() == 0 || edtCity.getText().toString().equals(" ")) {
            edtCity.setError("Please Enter City");
        } else if (edtState.getText().toString().length() == 0 || edtState.getText().toString().equals(" ")) {
            edtState.setError("Please Enter State");
        } else if (checkedRadioButtonId == -1) {
            Toast.makeText(this, "Please Choose Your Type", Toast.LENGTH_SHORT).show();
        } else {
            //finish();
            Toast.makeText(this, "Success ", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SignupActivity.this, MainActivity.class));
        }
    }
}
