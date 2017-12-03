package wilderpereira.com.dreambox

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_installments.*
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import wilderpereira.com.dreambox.model.UserClassificationDTO

class InstallmentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_installments)

        val gName = intent.getStringExtra("goalName")
        goalName.text = gName

        classifyUser().execute("", "")
        //TODO: get name of goal
        //TODO: get currencies from backend
        //load classificarion dialog?
    }

    inner class classifyUser: AsyncTask<String, String, String>() {

        override fun doInBackground(vararg dreamGoal: String?): String {

            //The vision api returns us the url of similar
            try {

                val client =  OkHttpClient().newBuilder().build()

                val requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "{\"product_url\":\"${dreamGoal[0]}\", \"product_name\":\"${dreamGoal[1]}\"}")

                val request = okhttp3.Request.Builder()
                        .url("http://192.168.3.41:5000/user/canbuyproduct")
                        .post(requestBody)
                        .build()

                return client.newCall(request).execute().body()!!.string()
            } catch(Ex: Exception) {
                Log.d("Async", "Error in doInBackground " + Ex.message);
            }
            return ""
        }


        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            val response = Gson().fromJson(result, UserClassificationDTO::class.java)
            //if user shouldnt pursue this goal
            if (response != null && !response.aGoodGoal!!) {
                //TODO: display dialog
                Toast.makeText(this@InstallmentsActivity, "melhor não heim", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
