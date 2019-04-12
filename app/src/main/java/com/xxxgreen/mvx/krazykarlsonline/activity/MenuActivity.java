package com.xxxgreen.mvx.krazykarlsonline.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.xxxgreen.mvx.krazykarlsonline.R;
import com.xxxgreen.mvx.krazykarlsonline.fragment.menu.PageEntreeFragment;
import com.xxxgreen.mvx.krazykarlsonline.fragment.menu.PageSidesFragment;
import com.xxxgreen.mvx.krazykarlsonline.fragment.menu.PageDrinkDessertFragment;
import com.xxxgreen.mvx.krazykarlsonline.fragment.menu.PageGrinderFragment;
import com.xxxgreen.mvx.krazykarlsonline.fragment.menu.PageSaladFragment;

public class MenuActivity extends AppCompatActivity {
    private static final String TAG = "MenuActivity";
    public static String[] MENU_PAGE_TITLES = {
            "Pizza", "Sides", "Grinders", "Salads", "More"
    };

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private String locationTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("location_name")) {
            locationTitle = extras.getString("location_name");
        } else {
            locationTitle = "";
        }

        // Create the adapter that will return a fragment for each of the primary sections
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.view_pager_menu);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = findViewById(R.id.tab_layout_menu);
        tabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }



    // Adapter instantiates proper fragment, given index of page
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a Fragment (defined as a static inner class below).
            Log.i(TAG, "Instantiating fragment for page #" + (position+1));

            if (position == 0) {
                return PageEntreeFragment.newInstance(position);
            } else if (position == 1){
                return PageSidesFragment.newInstance(position);
            } else if (position == 2){
                return PageGrinderFragment.newInstance(position);
            } else if (position == 3){
                return PageSaladFragment.newInstance(position);
            } else if (position == 4){
                return PageDrinkDessertFragment.newInstance(position);
            } else {
                Log.w(TAG, "Unhandled section #: " + position);
                return PageEntreeFragment.newInstance(0);
            }
        }

        @Override
        public int getCount() {
            return MENU_PAGE_TITLES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position >= 0 && position < MENU_PAGE_TITLES.length) {
                return MENU_PAGE_TITLES[position];
            }
            Log.w(TAG, "Index, " + position + ", is outside array length, "
                    + MENU_PAGE_TITLES.length);
            return getPageTitle(0);
        }
    }
}
