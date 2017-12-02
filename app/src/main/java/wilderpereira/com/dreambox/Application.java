package wilderpereira.com.dreambox;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Wilder on 02/12/17.
 */

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
