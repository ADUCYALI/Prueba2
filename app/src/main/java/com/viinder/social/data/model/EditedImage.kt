package com.viinder.social.data.model

import android.os.Parcel
import android.os.Parcelable

data class EditedImage(val image: String? = "", val filter: String? = "Normal") : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(image)
        parcel.writeString(filter)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EditedImage> {
        override fun createFromParcel(parcel: Parcel): EditedImage {
            return EditedImage(parcel)
        }

        override fun newArray(size: Int): Array<EditedImage?> {
            return arrayOfNulls(size)
        }
    }
}

