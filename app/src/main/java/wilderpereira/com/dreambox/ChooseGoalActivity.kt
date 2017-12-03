package wilderpereira.com.dreambox

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.mvc.imagepicker.ImagePicker
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import wilderpereira.com.dreambox.model.vision.Feature
import wilderpereira.com.dreambox.model.vision.Image
import wilderpereira.com.dreambox.model.vision.Request
import wilderpereira.com.dreambox.model.vision.VisionDTO
import wilderpereira.com.dreambox.model.response.VisionResponseDTO
import android.graphics.Bitmap
import android.content.Intent
import android.util.Base64
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_choose_goal.*
import java.io.ByteArrayOutputStream


class ChooseGoalActivity : AppCompatActivity() {

    val Tag: String = "Async"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_goal)
    }

    fun goalContinue(view: View) {

        val goal = goalName.text.toString()

        if (!goal.isNullOrBlank()) {
            val intent = Intent(this@ChooseGoalActivity, InstallmentsActivity::class.java)
            intent.putExtra("goalName", goal)

            //TODO: pass goal name
            startActivity(intent)
        }
    }

    fun photoClick(view: View) {
        ImagePicker.pickImage(this, "Escolha a imagem do seu objetivo");
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        val bitmap = ImagePicker.getImageFromResult(this, requestCode, resultCode, data)
        if (bitmap != null){
            val baos = ByteArrayOutputStream()
            bitmap!!.compress(Bitmap.CompressFormat.PNG, 100, baos)
            val b = baos.toByteArray()
            val imageEncoded = Base64.encodeToString(b, Base64.DEFAULT)
            Log.d(Tag, imageEncoded)
            ivFromCamera.setImageBitmap(bitmap)
            Toast.makeText(this@ChooseGoalActivity, "Analisando imagem...", Toast.LENGTH_LONG).show()
            vision().execute(imageEncoded)
        }
    }

    fun audioClick(view: View) {

    }

    inner class vision: AsyncTask<String, String, String>() {

        override fun doInBackground(vararg imgAsBase64: String?): String {

            //calling Google Vision API
            try {
                val image = Image()
                image.content = imgAsBase64[0]

                val labelDetectionFeature = Feature()
                labelDetectionFeature.type = "LABEL_DETECTION"

                val webDetectionFeature = Feature()
                webDetectionFeature.type = "WEB_DETECTION"

                val requests = Request()
                requests.features = mutableListOf(labelDetectionFeature, webDetectionFeature)
                requests.image = image

                val visionDto = VisionDTO()
                visionDto.requests = arrayListOf(requests)


//                val client =  OkHttpClient().newBuilder().hostnameVerifier { s, sslSession -> true}.build()
                val client =  OkHttpClient().newBuilder().build()

                val requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), Gson().toJson(visionDto))

                val request = okhttp3.Request.Builder()
                        .url("https://vision.googleapis.com/v1/images:annotate?key=AIzaSyAVl9Z6AsGdrv-wnmZGXvDzJCGjbnRg42g")
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
            val response = Gson().fromJson(result, VisionResponseDTO::class.java)
            Log.d(Tag, response.toString())
            goalName.setText(response.responses[0].labelAnnotations[0].description)
        }
    }
}
