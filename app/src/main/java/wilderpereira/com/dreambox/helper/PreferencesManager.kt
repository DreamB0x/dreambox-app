package wilderpereira.com.dreambox.helper

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import wilderpereira.com.dreambox.model.Goal

/**
 * Created by Wilder on 03/12/17.
 */
class PreferencesManager (context: Context) {
    val PREFS_FILENAME = "dreambox.prefs"
    val NEW_GOAL = "new_goal_key"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0);

    //used to help saving a new user goal
    var goal: Goal?
        get() = Gson().fromJson(prefs.getString(NEW_GOAL, ""), Goal::class.java)
        set(goal) = prefs.edit().putString(NEW_GOAL, Gson().toJson(goal)).apply()

}