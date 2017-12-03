package wilderpereira.com.dreambox

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_installments.*
import okhttp3.FormBody
import okhttp3.OkHttpClient
import wilderpereira.com.dreambox.helper.PreferencesManager
import wilderpereira.com.dreambox.model.CurrencyDTO
import wilderpereira.com.dreambox.model.Goal
import wilderpereira.com.dreambox.model.UserClassificationDTO


class InstallmentsActivity : AppCompatActivity() {

    var goalValue: Float = 0f
    lateinit var imageUrl: String
    lateinit var goalNameStr: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_installments)

        val gName = intent.getStringExtra("goalName")
        val pUrl = intent.getStringExtra("imageUrl")
        val gUrl = intent.getStringExtra("productUrl")
        imageUrl = pUrl
        goalName.text = gName
        goalNameStr = gName

        //real request
        getCurrenciesFromApi().execute(gUrl)


        classify().execute(gUrl)
        //classifyUser().execute("", "")

    }

    /**
     * MOCK - will only be used if the internet conditions are bad
     * get the goal value from API
     */
    fun getCurernciesMock(goalName: String) : Float{
        //just in case of bad connections
        return 143.5f
    }

    /**
     * MOCK - will only be used if the internet conditions are bad
     * get the monthly amount for the user based in his historic
     */
    fun getMonthlyDepositMock(totalAmout: Float) : Float {
        return totalAmout/12f
    }

    /**
     * saves the user goal
     */
    fun invest(view: View) {

        val goal = Goal()
        goal.amaoutDeposited = 2f
        goal.image = imageUrl
        goal.value = goalValue
        goal.name = goalNameStr
        PreferencesManager(this@InstallmentsActivity).goal = goal
        val intent = Intent(this@InstallmentsActivity, GoalsListActivity::class.java)
        startActivity(intent)
    }

    /**
     * displays a suggestion dialog when the classifier
     * detects that the user's goal is not recommended
     * for him
     */
    fun displayDialog(context: Context) {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_suggestion)
        dialog.setTitle("Sugestão")

        val dialogButton = dialog.findViewById<Button>(R.id.confirmbtn)
        val cancel = dialog.findViewById<Button>(R.id.cancelbtn)
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener({
            dialog.dismiss()
        })

        cancel.setOnClickListener({
            dialog.dismiss()
        })

        dialog.show()
    }

    /**
     * get the catergory of the user from the API based in his
     * debit history
     */
    inner class classify: AsyncTask<String, String, String>() {

        override fun doInBackground(vararg dreamGoal: String?): String {

            //The vision api returns us the url of similar
            try {

                val client =  OkHttpClient().newBuilder().build()

                val formBody = FormBody.Builder().add("product_url", dreamGoal[0]).build()

                val request = okhttp3.Request.Builder()
                        .url("http://original-dreambox.herokuapp.com/user/canbuyproduct")
                        .post(formBody)
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
                displayDialog(context = this@InstallmentsActivity)
            }
        }
    }


    /**
     * requests the values of the goal from the api
     */
    inner class getCurrenciesFromApi: AsyncTask<String, String, String>() {

        override fun doInBackground(vararg dreamGoal: String?): String {

            //The vision api returns us the url of similar
            try {

                val client =  OkHttpClient().newBuilder().build()

                val formBody = FormBody.Builder().add("product_url", dreamGoal[0]).build()

                val request = okhttp3.Request.Builder()
                        .url("http://original-dreambox.herokuapp.com/product/info")
                        .post(formBody)
                        .build()

                return client.newCall(request).execute().body()!!.string()
            } catch(Ex: Exception) {
                Log.d("Async", "Error in doInBackground " + Ex.message);
            }
            return ""
        }


        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            val response = Gson().fromJson(result, CurrencyDTO::class.java)
            //if user shouldnt pursue this goal
            if (response != null) {
                //TODO: display dialog
                goalValue = response.productValue

                monthlyValue.text = "%.2f".format(response.productMonthly)
                totalValue.text = "%.2f".format(response.productValue)
            }
        }
    }

}
