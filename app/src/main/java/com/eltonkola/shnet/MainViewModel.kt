package com.eltonkola.shnet

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import java.util.*
import android.content.Context

import androidx.lifecycle.ViewModelProvider

data class WorkoutStep(val title: String, val done: Boolean = false, val moment: Date? = null)

data class DailyDrink(val title: String, val done: Boolean= false, val icon: Int, val moment: Date? = null, val sizeMl: Int)

data class DailyRun(val title: String, val done: Boolean = false, val moment: Date? = null, val length: Int)

data class UserData(val workouts: List<WorkoutStep>, val drinks: List<DailyDrink>, val dailyRun: DailyRun)


class MainViewModelFactory(private val context: Context) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(DataRepo(context)) as T
    }
}

class MainViewModel(private val repo: DataRepo) : ViewModel() {

    var lastValues = repo.loadData()

    var workoutSteps by mutableStateOf(lastValues.workouts)
        private set

    var dailyDrinks by mutableStateOf(lastValues.drinks)
        private set

    var dailyRun by mutableStateOf(lastValues.dailyRun)
        private set

    var streaks by mutableStateOf(repo.loadStreak())
        private set


    fun onWorkoutClick(step: WorkoutStep){
        workoutSteps = workoutSteps.toMutableList().also {
            val done =  !step.done
            val moment = if(done) Date() else null
            it[it.indexOf(step)] =  step.copy(done = done, moment = moment)
        }
        save()
    }

    fun onDrinkClick(drink: DailyDrink){
        dailyDrinks = dailyDrinks.toMutableList().also {
            val done =  !drink.done
            val moment = if(done) Date() else null
            it[it.indexOf(drink)] =  drink.copy(done = done, moment = moment)
        }
        save()
    }

    fun onRunClick(run: DailyRun){
        val done =  !run.done
        val moment = if(done) Date() else null
        dailyRun = run.copy(done = done, moment = moment)
        save()
    }

    private fun save(){
        repo.saveData(UserData(dailyRun = dailyRun, drinks = dailyDrinks, workouts = workoutSteps))
    }

    fun reset() {
        val template = repo.loadTemplate()
        workoutSteps = template.workouts
        dailyDrinks = template.drinks
        dailyRun = template.dailyRun
        save()
    }

    fun saveWorkoutSteps(nrOfSteps: Int, stepsTitle: String) {
        val template = repo.loadTemplate().copy(workouts =  (1..nrOfSteps).map { WorkoutStep(title = "$stepsTitle $it") })
        repo.saveTemplate(template)
        lastValues = template
        reset()
    }

    fun deleteDrink(drink: DailyDrink) {
        val template = repo.loadTemplate().copy(drinks =  dailyDrinks.filter { it != drink } )
        repo.saveTemplate(template)
        lastValues = template
        reset()
    }

    fun addDrink(drinkIcon: Int, sizeMilliGram: Int, newDrinkText: String) {
        val template = repo.loadTemplate().copy(drinks =  dailyDrinks.toMutableList().apply {
            add(DailyDrink(title = newDrinkText, icon = drinkIcon, sizeMl = sizeMilliGram))
        }.toList())
        repo.saveTemplate(template)
        lastValues = template
        reset()
    }

    fun resetAll() {
        val template = repo.defaultTemplate()
            repo.saveTemplate(template)
            lastValues = template
            reset()
    }

}
