package com.taetae98.gachonqr.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.taetae98.gachonqr.di.UserDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    @UserDataStore
    private val store: DataStore<Preferences>
) {
    private val studentIdKey by lazy { stringPreferencesKey("STUDENT_ID") }

    suspend fun getStudentId(): String? {
        return store.data.map {
            it[studentIdKey]
        }.first()
    }

    suspend fun setStudentId(studentId: String?) {
        store.edit {
            if (studentId == null) {
                it.remove(studentIdKey)
            } else {
                it[studentIdKey] = studentId
            }
        }
    }
}