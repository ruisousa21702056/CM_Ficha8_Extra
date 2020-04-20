package com.example.acalculator

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.apache.commons.codec.digest.DigestUtils

@Parcelize
class User(val name: String, val email: String, val password: String) : Parcelable