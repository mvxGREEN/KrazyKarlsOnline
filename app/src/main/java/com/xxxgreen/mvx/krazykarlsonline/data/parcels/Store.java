package com.xxxgreen.mvx.krazykarlsonline.data.parcels;

import android.os.Parcel;
import android.os.Parcelable;

import com.xxxgreen.mvx.krazykarlsonline.R;

public class Store implements Parcelable {
    public final int id;
    public final double latitude, longitude;
    public final String title, address, phoneNumber;
    public final boolean isStore;
    public final int iconDrawableId, storefrontDrawableId, interiorDrawableId;
    public final int[] storeColorIds;

    // CONSTRUCTORS
    public Store(int id, double latitude, double longitude, String title, String address,
                 boolean isStore, String phoneNumber) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phoneNumber = phoneNumber;
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

    protected Store(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.address = in.readString();
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
        this.phoneNumber = in.readString();
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

    public static final Creator<Store> CREATOR = new Creator<Store>() {
        @Override
        public Store createFromParcel(Parcel in) {
            return new Store(in);
        }

        @Override
        public Store[] newArray(int size) {
            return new Store[size];
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
        dest.writeString(this.address);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
        dest.writeString(this.phoneNumber);
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
