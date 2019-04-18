package com.xxxgreen.mvx.krazykarlsonline.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLngBounds;

public class LocationSearchBox extends PlaceAutocompleteFragment {
    public LocationSearchBox() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void setBoundsBias(@Nullable LatLngBounds latLngBounds) {
        super.setBoundsBias(latLngBounds);
    }

    @Override
    public void setFilter(@Nullable AutocompleteFilter autocompleteFilter) {
        super.setFilter(autocompleteFilter);
    }

    @Override
    public void setText(CharSequence charSequence) {
        super.setText(charSequence);
    }

    @Override
    public void setHint(CharSequence charSequence) {
        super.setHint(charSequence);
    }

    @Override
    public void setOnPlaceSelectedListener(PlaceSelectionListener placeSelectionListener) {
        super.setOnPlaceSelectedListener(placeSelectionListener);
    }

    @Override
    public void onActivityResult(int i, int i1, Intent intent) {
        super.onActivityResult(i, i1, intent);
    }
}
