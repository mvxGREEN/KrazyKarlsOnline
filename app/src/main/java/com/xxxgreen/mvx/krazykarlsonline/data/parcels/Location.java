package com.xxxgreen.mvx.krazykarlsonline.data.parcels;

import android.os.Parcel;
import android.os.Parcelable;

import com.xxxgreen.mvx.krazykarlsonline.R;

public class Location implements Parcelable {
    public final int id;
    public final double latitude, longitude;
    public final String title, address_1, address_2;
    public final boolean isStore;
    public final int iconDrawableId, storefrontDrawableId, interiorDrawableId;
    public final int[] storeColorIds;

    // CONSTRUCTORS
    public Location(int id, double latitude, double longitude, String title, String address_1,
                    boolean isStore, String address_2) {
        this.id = id;
        this.title = title;
        this.address_1 = address_1;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address_2 = address_2;
        this.isStore = isStore;
        if (id == 1) {
            this.storeColorIds = new int[] {
                    R.color.elizabethWhite, R.color.elizabethLighter, R.color.elizabethLight,
                    R.color.elizabethOrange, R.color.elizabethDarkish, R.color.elizabethDark,
                    R.color.elizabethDarker
            };
            this.iconDrawableId = R.drawable.logo_kk_170dp;
            this.storefrontDrawableId = R.drawable.photo_elizabeth_storefront;
            this.interiorDrawableId = R.drawable.photo_elizabeth_interior;
        } else if (id == 2) {
            this.storeColorIds = new int[] {
                    R.color.timberlineWhite, R.color.timberlineLighter, R.color.timberlineLight,
                    R.color.timberlineGreen, R.color.timberlineDarkish, R.color.timberlineDark,
                    R.color.timberlineDarker
            };
            this.iconDrawableId = R.drawable.logo_kk_170dp;
            this.storefrontDrawableId = R.drawable.photo_timberline_storefront;
            this.interiorDrawableId = R.drawable.photo_timberline_interior;
        } else {
            this.storeColorIds = new int[] {
                    R.color.colorAzureWhite, R.color.colorAzureLighter, R.color.colorAzureLight,
                    R.color.colorAzure, R.color.colorAzureDarkish, R.color.colorAzureDark,
                    R.color.colorAzureDarker
            };
            this.iconDrawableId = R.drawable.logo_kk_170dp;
            this.storefrontDrawableId = R.drawable.photo_delivery_storefront;
            this.interiorDrawableId = R.drawable.logo_kk_170dp;
        }

    }

    protected Location(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.address_1 = in.readString();
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
        this.address_2 = in.readString();
        int temp = in.readInt();
        if (temp == 1) {
            this.isStore = true;
        } else {
            this.isStore = false;
        }
        this.iconDrawableId = in.readInt();
        this.storefrontDrawableId = in.readInt();
        this.interiorDrawableId = in.readInt();
        this.storeColorIds = new int[7];
        in.readIntArray(this.storeColorIds);
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.address_1);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
        dest.writeString(this.address_2);
        if (this.isStore) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
        }
        dest.writeInt(this.iconDrawableId);
        dest.writeInt(this.storefrontDrawableId);
        dest.writeInt(this.interiorDrawableId);
        dest.writeIntArray(this.storeColorIds);
    }
}
