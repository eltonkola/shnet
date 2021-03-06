package com.eltonkola.shnet

import android.content.Context
import com.google.gson.Gson
import android.content.SharedPreferences.Editor

class DataRepo(private val context: Context) {

    companion object{
        const val PREF = "dataz"
        const val DATAZ = "user_data"
        const val TEMPLATE = "user_template"
        const val STREAK = "user_streak"
    }

    private val sharedPreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
    private val gson = Gson()

    fun loadData(): UserData {
        val json = sharedPreferences.getString(DATAZ, null)
        return try{
            gson.fromJson(json, UserData::class.java)
        } catch (e:Exception){
            loadTemplate()
        }
    }

    fun saveData(data: UserData?) {
        val prefsEditor: Editor = sharedPreferences.edit()
        val json = gson.toJson(data)
        prefsEditor.putString(DATAZ, json)
        prefsEditor.apply()
    }

    fun loadTemplate(): UserData {
        val json = sharedPreferences.getString(TEMPLATE, null)
        return try{
            gson.fromJson(json, UserData::class.java)
        } catch (e:Exception){
            defaultTemplate()
        }
    }

    fun saveTemplate(data: UserData?) {
        val prefsEditor: Editor = sharedPreferences.edit()
        val json = gson.toJson(data)
        prefsEditor.putString(TEMPLATE, json)
        prefsEditor.apply()
    }

    fun defaultTemplate(): UserData {
        return UserData(
            workouts = (1..14).map { WorkoutStep(title = "Workout $it") },
            drinks = listOf(
                DailyDrink(title = "Coffee", icon = R.drawable.ic_coffee, sizeMl = 350),
                DailyDrink(title = "Watter 1", icon = R.drawable.ic_bottle_water, sizeMl = 750),
                DailyDrink(title = "Watter 2", icon = R.drawable.ic_bottle_water, sizeMl = 750),
                DailyDrink(
                    title = "Protein shake",
                    icon = R.drawable.ic_bottle_shaker,
                    sizeMl = 850
                ),
                DailyDrink(
                    title = "Electrolytes",
                    icon = R.drawable.ic_bottle_shaker,
                    sizeMl = 850
                ),
            ),
            dailyRun = DailyRun(title = "Daily run x", length = 5)
        )
    }

    fun loadStreak(): Int {
        return sharedPreferences.getInt(STREAK , 0)
    }

    fun saveStreak(streak: Int) {
        val prefsEditor: Editor = sharedPreferences.edit()
        prefsEditor.putInt(STREAK, streak)
        prefsEditor.apply()
    }

}
