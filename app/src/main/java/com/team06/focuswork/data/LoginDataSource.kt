package com.team06.focuswork.data

import android.content.res.Resources
import android.util.Log
import com.team06.focuswork.R
import com.team06.focuswork.model.LoggedInUser
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {
    private val TAG = "LOGIN_DATA_SOURCE"
    private val fireStoreUtil = FireBaseFireStoreUtil()
    fun login(username: String, password: String): Result<LoggedInUser> = try {
        val loggedInUser = fireStoreUtil.retrieveUser(username, password)
        Result.Success(loggedInUser)
    } catch (e: Throwable) {
        Log.e(TAG, "Unfortunately the task could not be saved!$username")
        e.printStackTrace()
        Result.Error(IOException("Error logging in", e))
    }

    fun logout() {
        // TODO: revoke authentication
    }

    fun register(
        firstname: String, lastname: String, username: String, password: String
    ): Result<LoggedInUser> = try {
        fireStoreUtil.checkForExistingUser(username)
        val loggedInUser = fireStoreUtil.addUser(firstname, lastname, username, password)
        Result.Success(loggedInUser)
    } catch (e: Throwable) {
        Log.e(TAG, "Unfortunately the task could not be saved!$username")
        e.printStackTrace()
        Result.Error(IOException("Error logging in", e))
    }
}
