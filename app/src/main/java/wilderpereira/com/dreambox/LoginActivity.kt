package wilderpereira.com.dreambox

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

    fun doLogin(view: View) {
        startActivity(Intent(this@LoginActivity, GoalsListActivity::class.java))
    }

}
