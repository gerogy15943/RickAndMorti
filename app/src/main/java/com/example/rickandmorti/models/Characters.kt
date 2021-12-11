package com.example.rickandmorti.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Characters(
    val id: Int,
    val name: String?,
    val species: String?,
    val gender: String?,
    val image: String?,
    val status: String?,
    val location: Location?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Location::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(species)
        parcel.writeString(gender)
        parcel.writeString(image)
        parcel.writeString(status)
        parcel.writeParcelable(location, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Characters> {
        override fun createFromParcel(parcel: Parcel): Characters {
            return Characters(parcel)
        }

        override fun newArray(size: Int): Array<Characters?> {
            return arrayOfNulls(size)
        }
    }
}

data class Location(
    val name: String?
): Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Location> {
        override fun createFromParcel(parcel: Parcel): Location {
            return Location(parcel)
        }

        override fun newArray(size: Int): Array<Location?> {
            return arrayOfNulls(size)
        }
    }
}

data class PageInfo(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

data class CharactersResponse(
    @SerializedName("info") val pageInfo: PageInfo,
    @SerializedName("results") val results: List<Characters>
)