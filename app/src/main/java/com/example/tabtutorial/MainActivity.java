package com.example.tabtutorial;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.tabtutorial.ui.main.BLActivity;
import com.example.tabtutorial.ui.main.SectionsPagerAdapter;

import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_misc);
        /*SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);*/


        }

    public void toBackLine(View v){
        Intent toBack = new Intent(this, FirstPageFragment.class);
        startActivity(toBack);



        //((ViewGroup) tabs.getChildAt(0)).getChildAt(3).setVisibility(View.GONE);
        //((ViewGroup) tabs.getChildAt(0)).getChildAt(4).setVisibility(View.GONE);
        //((ViewGroup) tabs.getChildAt(0)).getChildAt(5).setVisibility(View.GONE);
    }

    /*public void test(View view){
        TabLayout tabs = this.findViewById(R.id.tabs);
        ((ViewGroup) tabs.getChildAt(0)).getChildAt(3).setVisibility(View.VISIBLE);
        TabLayout.Tab tab = tabs.getTabAt(3);
        tab.select();
    }*/

    /*public void showSauceList(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.sauce_menu);
        popup.show();
    }

    public void showCheeseList(View v){
        PopupMenu popup = new PopupMenu(this,v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.cheese_menu);
        popup.show();
    }*/

    /*@Override
    public boolean onMenuItemClick(MenuItem item){
        switch(item.getItemId()){
            case R.id.Nosauce:
                Toast.makeText(this, "No Sauce Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Xtrasauce:
                Toast.makeText(this, "Xtra Sauce Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Garlic:
                Toast.makeText(this, "Garlic Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Nocheese:
                Toast.makeText(this, "No Cheese Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Xtracheese:
                Toast.makeText(this, "Xtra Cheese Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Cheddar:
                Toast.makeText(this, "Cheddar Clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;

        }
    }*/
}