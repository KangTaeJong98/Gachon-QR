package com.taetae98.gachonqr

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

const val TAG = "GACHON_QR"

val Context.userDataStore by preferencesDataStore(name = "USER")