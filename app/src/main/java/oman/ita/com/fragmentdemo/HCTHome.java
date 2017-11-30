package oman.ita.com.fragmentdemo;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import oman.ita.com.fragmentdemo.hct_home.HCTHomeFragment;
import oman.ita.com.fragmentdemo.hct_students.HCTStudents_Fragments;

public class HCTHome extends AppCompatActivity implements
        HCTHomeFragment.OnFragmentInteractionListener,
        HCTStudents_Fragments.OnFragmentInteractionListener{
    FrameLayout frame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hcthome);
        //frame=(FrameLayout) findViewById(R.id.main_container);
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        HCTHomeFragment fragment=new HCTHomeFragment();
        transaction.replace(R.id.main_container,fragment);
        transaction.commit();

    }
    @Override
    public void onBackPressed() {
        //((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().show();
        super.onBackPressed();


    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    public void onFragmentBackPressed(View view) {
        Toast.makeText(this, "back", Toast.LENGTH_LONG).show();
        // getSupportFragmentManager().popBackStack();
        onBackPressed();
    }
}
