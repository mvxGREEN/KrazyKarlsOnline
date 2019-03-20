package com.xxxgreen.mvx.krazykarlsonline.fragment.sections;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.xxxgreen.mvx.krazykarlsonline.R;
import com.xxxgreen.mvx.krazykarlsonline.utils.MapUtils;

public class FragOrder extends Fragment {
    private static final String TAG = "MapOverlayTopFragment";

    public LayoutInflater layoutInflater;
    private GoogleMap gMap;

    // Containers
    View rootView;

    // Views TODO

    public FragOrder() {

    }

    // Returns new instance of this fragment for the given section number.
    public static FragOrder newInstance(int sectionNumber) {
        FragOrder fragment = new FragOrder();
        Bundle args = new Bundle();
        args.putInt(MapUtils.SECTION_NUMBER_KEY, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public static FragOrder newInstance(View rootView, LayoutInflater layoutInflater,
                                        GoogleMap gMap) {
        FragOrder fragment = new FragOrder();
        fragment.rootView = rootView;
        fragment.layoutInflater = layoutInflater;
        fragment.gMap = gMap;

        // TODO: Instantiate elements

        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_order, container,
                false);

        // TODO: Instantiate elements

        return rootView;
    }

}
