package com.xxxgreen.mvx.krazykarlsonline.fragment.sections;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.coorchice.library.SuperTextView;
import com.google.android.gms.maps.GoogleMap;
import com.xxxgreen.mvx.krazykarlsonline.R;
import com.xxxgreen.mvx.krazykarlsonline.activity.MapActivity;
import com.xxxgreen.mvx.krazykarlsonline.utils.MapUtils;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class FragHome extends Fragment {
    private static final String TAG = "MapOverlayTopFragment";

    public LayoutInflater layoutInflater;
    private GoogleMap gMap;

    // Containers
    View rootView;

    // Views
    public SuperTextView superbtnUser;
    ImageView icSketchHome;
    AutoScrollViewPager autoScrollViewPager;
    MapActivity.FeaturePagerAdapter featurePagerAdapter;

    public FragHome() {

    }

    // Returns new instance of this fragment for the given section number.
    public static FragHome newInstance(int sectionNumber) {
        FragHome fragment = new FragHome();
        Bundle args = new Bundle();
        args.putInt(MapUtils.SECTION_NUMBER_KEY, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public static FragHome newInstance(View rootView, LayoutInflater layoutInflater,
                                                     GoogleMap gMap) {
        FragHome fragment = new FragHome();
        fragment.rootView = rootView;
        fragment.layoutInflater = layoutInflater;
        fragment.gMap = gMap;
        fragment.superbtnUser = rootView.findViewById(R.id.superbutton_user);
        fragment.icSketchHome = rootView.findViewById(R.id.ic_page);


        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_home, container,
                false);

        // TODO: Instantiate elements

        return rootView;
    }



}
