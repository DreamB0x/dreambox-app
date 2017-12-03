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
        addSlide(AppIntroFragment.newInstance("Conheça as Instituições", "Veja como é estudar em qualquer lugar pela visão sincera dos alunos.", R.mipmap.ic_launcher, resources.getColor(R.color.colorPrimary)))
        addSlide(AppIntroFragment.newInstance("Avaliação", "Aqui os alunos podem expor os pontos positivos e negativos, além de avaliar detalhadamente de maneira simples pontos como Infraestrutura, Corpo Docente etc", R.mipmap.ic_launcher, resources.getColor(R.color.colorAccent)))
        addSlide(AppIntroFragment.newInstance("Descubra os melhores lugare para fazer um curso", "Conseguimos saber qual os melhores lugares para se fazer um curso a partir das avaliações dos alunos", R.mipmap.ic_launcher, resources.getColor(R.color.colorPrimaryDark)))
        addSlide(AppIntroFragment.newInstance("Encontre as melhores faculdades de uma categoria", "Avaliamos segurança, infraestrutura, corpo docente, comunicação etc", R.mipmap.ic_launcher,resources.getColor(R.color.background_floating_material_dark)))
        addSlide(AppIntroFragment.newInstance("Contribua para uma educação de qualidade", "Avalie sua instituição e contribua para a melhoria dela e ajudando quem ainda não decidiu pra onde ir.", R.mipmap.ic_launcher, resources.getColor(R.color.switch_thumb_disabled_material_dark)))

    }

    override fun onDonePressed(currentFragment: Fragment) {
        super.onDonePressed(currentFragment)
        startActivity(Intent(this@IntroActivity, LoginActivity::class.java))
    }
}