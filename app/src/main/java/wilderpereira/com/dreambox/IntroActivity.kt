package wilderpereira.com.dreambox

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.View
import com.github.paolorotolo.appintro.AppIntro
import com.github.paolorotolo.appintro.AppIntroFragment

/**
 * Created by Wilder on 03/12/17.
 */

class IntroActivity : AppIntro() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.TRANSPARENT
        }

        if (ContextCompat.checkSelfPermission(this@IntroActivity,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {


        } else {


            ActivityCompat.requestPermissions(this@IntroActivity,
                    arrayOf<String>(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1);

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }

        setDepthAnimation()
        addSlide(AppIntroFragment.newInstance("Sonhe Alto", "Coloque todos seus sonhos dentro de nossa caixa e veja eles serem realizados.", R.drawable.foguete, resources.getColor(R.color.colorPrimary)))
        addSlide(AppIntroFragment.newInstance("Tenha Ajuda de especialistas", "Usamos inteligência artificial para lhe entregar de fato o que você precisa e cabe no seu bolso.", R.drawable.robo, resources.getColor(R.color.colorAccent)))
        addSlide(AppIntroFragment.newInstance("Nunca mais fique no vermelho", "Vamos te auxiliar para que tenha uma vida financeira saudável", R.drawable.cofre, resources.getColor(R.color.colorPrimaryDark)))

    }

    override fun onDonePressed(currentFragment: Fragment) {
        super.onDonePressed(currentFragment)
        startActivity(Intent(this@IntroActivity, LoginActivity::class.java))
    }
}