package com.xxxgreen.mvx.krazykarlsonline.fragment.sections;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xxxgreen.mvx.krazykarlsonline.R;
import com.xxxgreen.mvx.krazykarlsonline.fragment.menu.PageDrinkDessertFragment;
import com.xxxgreen.mvx.krazykarlsonline.fragment.menu.PageGrinderFragment;
import com.xxxgreen.mvx.krazykarlsonline.fragment.menu.PagePizzaFragment;
import com.xxxgreen.mvx.krazykarlsonline.fragment.menu.PageSaladFragment;
import com.xxxgreen.mvx.krazykarlsonline.fragment.menu.PageSidesFragment;
import com.xxxgreen.mvx.krazykarlsonline.utils.MapUtils;

import static com.xxxgreen.mvx.krazykarlsonline.utils.MenuUtils.MENU_PAGE_TITLES;

public class FragMenu extends Fragment {
    private static final String TAG = "MapOverlayTopFragment";

    FragmentManager fm;
    LinearLayout layoutPanel;
    TabLayout tabLayoutMenu;
    ViewPager viewPagerMenu;
    MenuPagerAdapter menuPagerAdapter;

    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;

    public FragMenu() {

    }

    // Returns new instance of this fragment for the given section number.
    public static FragMenu newInstance(int sectionNumber, FragmentManager fragManager) {
        FragMenu fragment = new FragMenu();

        fragment.fm = fragManager;

        Bundle args = new Bundle();
        args.putInt(MapUtils.SECTION_NUMBER_KEY, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_menu, container,
                false);

        mScaleDetector = new ScaleGestureDetector(rootView.getContext(), new ScaleListener());
        layoutPanel = rootView.findViewById(R.id.layout_panel);
        layoutPanel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mScaleDetector.onTouchEvent(event);
                return false;
            }
        });

        // Init view pager
        menuPagerAdapter = new MenuPagerAdapter(fm);
        viewPagerMenu = rootView.findViewById(R.id.view_pager_menu);
        if (menuPagerAdapter != null) {
            Log.i(TAG, "MenuPagerAdapter is not null :)");
            viewPagerMenu.setAdapter(menuPagerAdapter);
        } else {
            Log.e(TAG, "MenuPagerAdapter == null");
        }

        // Connect tab layout to view pager
        tabLayoutMenu = rootView.findViewById(R.id.tab_layout_menu);
        tabLayoutMenu.setupWithViewPager(viewPagerMenu);

        return rootView;
    }

    // Adapter instantiates proper fragment, given index of page
    private class MenuPagerAdapter extends FragmentPagerAdapter {

        public MenuPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a Fragment (defined as a static inner class below).
            Log.i(TAG, "Instantiating fragment for page #" + (position+1));

            if (position == 0) {
                return PagePizzaFragment.newInstance(position);
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
                return PagePizzaFragment.newInstance(0);
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

    private class ScaleListener
            extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();

            // Don't let the object get too small or too large.
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));

            layoutPanel.invalidate();
            return true;
        }
    }

}
