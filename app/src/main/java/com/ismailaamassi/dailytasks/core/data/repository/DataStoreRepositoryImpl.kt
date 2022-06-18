package com.ismailaamassi.dailytasks.core.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.ismailaamassi.dailytasks.core.domain.repository.DataStoreRepository
import com.ismailaamassi.dailytasks.core.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(
    name = Constants.USER_PREFERENCES_NAME
)

private object PreferencesKeys {
    val ON_BOARDING_KEY = booleanPreferencesKey(name = "on_boarding_completed")
    val USER_ID_KEY = stringPreferencesKey(name = "user_id")
    val USER_NAME_KEY = stringPreferencesKey(name = "user_name")
    val USER_TOKEN_KEY = stringPreferencesKey(name = "user_token")
}

@Singleton
class DataStoreRepositoryImpl @Inject constructor(
    private val context: Context,
    private val dataStore: DataStore<Preferences> = context.dataStore
) : DataStoreRepository {

    override suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.ON_BOARDING_KEY] = completed
        }
    }

    override suspend fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferencesKeys.ON_BOARDING_KEY] ?: false
                onBoardingState
            }
    }

    override suspend fun saveLoggedUserId(userId: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.USER_ID_KEY] = userId
        }
    }

    override suspend fun readLoggedUserId(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val userId = preferences[PreferencesKeys.USER_ID_KEY] ?: ""
                userId
            }
    }

    override suspend fun saveLoggedUserName(userName: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.USER_NAME_KEY] = userName
        }
    }

    override suspend fun readLoggedUserName(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val userName = preferences[PreferencesKeys.USER_NAME_KEY] ?: ""
                userName
            }
    }

    override suspend fun saveLoggedUserToken(token: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.USER_TOKEN_KEY] = token
        }
    }

    override suspend fun readLoggedUserToken(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val userToken = preferences[PreferencesKeys.USER_TOKEN_KEY] ?: ""
                userToken
            }
    }

    override suspend fun clearData() {

    }
}