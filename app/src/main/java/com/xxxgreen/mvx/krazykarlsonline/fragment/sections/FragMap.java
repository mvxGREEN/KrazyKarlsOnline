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
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLngBounds;
import com.xxxgreen.mvx.krazykarlsonline.R;
import com.xxxgreen.mvx.krazykarlsonline.data.parcels.Location;
import com.xxxgreen.mvx.krazykarlsonline.utils.MapUtils;

public class FragMap extends Fragment {
    private static final String TAG = "MapOverlayTopFragment";

    public LayoutInflater layoutInflater;
    private GoogleMap gMap;

    // Fragments
    public PlaceAutocompleteFragment autocompleteFragment;

    // Views
    View rootView;
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
        //TODO prevent inflation when frag_map is already inflated OR destroy old frag & reinflate
        View rootView = inflater.inflate(R.layout.frag_map, container,
                false);

        RelativeLayout layoutHeader = rootView.findViewById(R.id.layout_header);
        ImageView imgSectionIcon = rootView.findViewById(R.id.img_section_icon);
        autocompleteFragment = (PlaceAutocompleteFragment)
                getActivity().getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        //autocompleteFragment.setOnPlaceSelectedListener(this);
        autocompleteFragment.setHint(getResources().getString(R.string.autocomplete_search_hint));
        LatLngBounds fortcollinsSearchBounds = new LatLngBounds(MapUtils.SEARCH_CORNER_SOUTHWEST,
                MapUtils.SEARCH_CORNER_NORTHEAST);
        autocompleteFragment.setBoundsBias(fortcollinsSearchBounds);

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
