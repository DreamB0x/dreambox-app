package wilderpereira.com.dreambox

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_goals_list.*
import wilderpereira.com.dreambox.model.Goal

class GoalsListActivity : AppCompatActivity() {

    lateinit var goalsAdapter :GoalsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goals_list)

        //TODO: get goals list here
        val goalsList = arrayListOf<Goal>()
        for (i in 1..10) {
            val goal = Goal()
            goal.amaoutDeposited = i*100f/3
            goal.value = i*100f
            goal.name = "Objetivo ${i}"
            goal.image = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/2014_MINI_Cooper_Hardtop_--_NHTSA_test_8883_-_front.jpg/1200px-2014_MINI_Cooper_Hardtop_--_NHTSA_test_8883_-_front.jpg"
            goalsList.add(goal)
        }
        laodGoals(goalsList)
    }


    fun laodGoals(goals: List<Goal>) {
        goalsAdapter = GoalsAdapter(goals)
        goalsList.layoutManager = LinearLayoutManager(GoalsListActivity@this)
        goalsList.adapter = goalsAdapter;
    }

}
