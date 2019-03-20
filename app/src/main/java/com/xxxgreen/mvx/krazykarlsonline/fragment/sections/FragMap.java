package com.xxxgreen.mvx.krazykarlsonline.fragment.sections;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.coorchice.library.SuperTextView;
import com.google.android.gms.maps.GoogleMap;
import com.xxxgreen.mvx.krazykarlsonline.R;
import com.xxxgreen.mvx.krazykarlsonline.data.parcels.Location;
import com.xxxgreen.mvx.krazykarlsonline.utils.MapUtils;

public class FragMap extends Fragment {
    private static final String TAG = "MapOverlayTopFragment";

    private Location selectedStore;
    public LayoutInflater layoutInflater;
    private GoogleMap gMap;

    // Containers
    View rootView;

    // Views
    SuperTextView supertextElizabeth, supertextTimberline;

    public FragMap() {

    }

    // Returns new instance of this fragment for the given section number.
    public static FragMap newInstance(int sectionNumber) {
        FragMap fragment = new FragMap();
        Bundle args = new Bundle();
        args.putInt(MapUtils.SECTION_NUMBER_KEY, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public static FragMap newInstance(View rootView, LayoutInflater layoutInflater,
                                      GoogleMap gMap) {
        FragMap fragment = new FragMap();
        fragment.rootView = rootView;
        fragment.layoutInflater = layoutInflater;
        fragment.gMap = gMap;

        /*
        fragment.supertextElizabeth = rootView.findViewById(R.id.supertext_elizabeth);
        fragment.supertextTimberline = rootView.findViewById(R.id.supertext_timberline);
        fragment.supertextElizabeth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMenu(v.getContext(), MapUtils.ELIZABETH);
            }
        });
        fragment.supertextTimberline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMenu(v.getContext(), MapUtils.TIMBERLINE);
            }
        });
        */

        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_map, container,
                false);

        RelativeLayout layoutHeader = rootView.findViewById(R.id.layout_header);
        ImageView imgSectionIcon = rootView.findViewById(R.id.img_section_icon);

        /*
        supertextElizabeth = rootView.findViewById(R.id.supertext_elizabeth);
        supertextTimberline = rootView.findViewById(R.id.supertext_timberline);


        ArrayList<Integer> photoIdList = new ArrayList<>();
        PhotosRecyclerAdapter adapter = new PhotosRecyclerAdapter(photoIdList,
                container.getContext());

        supertextElizabeth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMenu(v.getContext(), MapUtils.ELIZABETH);
            }
        });
        supertextTimberline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMenu(v.getContext(), MapUtils.TIMBERLINE);
            }
        });
        */

        return rootView;
    }

}
