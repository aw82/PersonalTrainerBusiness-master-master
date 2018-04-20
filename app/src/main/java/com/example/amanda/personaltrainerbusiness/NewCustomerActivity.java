package com.example.amanda.personaltrainerbusiness;

import com.example.amanda.personaltrainerbusiness.Database;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


public class NewCustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_customer);
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

    public void GoToCustList(View view) {
        startActivity(new Intent(this, CustomerListActivity.class));
    }

    public void GoToTakePic(View view) {
        startActivity(new Intent(this, PictureActivity.class));
    }
}
