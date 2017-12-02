package wilderpereira.com.dreambox

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ChooseGoalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_goal)
    }

    fun goalContinue(view: View) {
        //TODO: display dialog: "analisando seu extrato, verificamos que essa pode não ser a sua melhor opção - mostrar extrato etc"
    }

    fun photoClick(view: View) {

    }

    fun audioClick(view: View) {

    }
}
