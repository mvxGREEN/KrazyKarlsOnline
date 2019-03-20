package com.xxxgreen.mvx.krazykarlsonline.fragment.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xxxgreen.mvx.krazykarlsonline.R;
import com.xxxgreen.mvx.krazykarlsonline.utils.MapUtils;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class FeatureFrag extends Fragment {
    private static final String TAG = "FeatureFrag";
    AutoScrollViewPager autoScrollViewPager;

    LinearLayout layoutFeatureFrag;

    public FeatureFrag() {

    }

    // Returns new instance of this fragment for the given section number.
    public static FeatureFrag newInstance(int sectionNumber) {
        FeatureFrag fragment = new FeatureFrag();
        Bundle args = new Bundle();
        args.putInt(MapUtils.SECTION_NUMBER_KEY, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_feature, container,
                false);

        layoutFeatureFrag = rootView.findViewById(R.id.layout_fetaure_fragment);
        autoScrollViewPager = rootView.findViewById(R.id.autoscroll_viewpager);
        //TODO: init autoscroll viewpager

        return rootView;
    }

}
