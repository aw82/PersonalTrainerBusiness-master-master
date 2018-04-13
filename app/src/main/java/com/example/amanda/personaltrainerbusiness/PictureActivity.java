package com.example.amanda.personaltrainerbusiness;

import android.content.Intent;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

public class PictureActivity extends AppCompatActivity {

    Camera camera;
    FrameLayout frameLayout;
    ShowCamera showCamera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        frameLayout = (FrameLayout)findViewById(R.id.frameLayout);



    //open the camera
    camera = Camera.open();
    showCamera = new ShowCamera(this,camera);
    frameLayout.addView(showCamera);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.LogOff:
                LogOff();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void LogOff() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void GoToNewCust(View view) {
        startActivity(new Intent(this, NewCustomerActivity.class));
    }


}
