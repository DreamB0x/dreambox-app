package wilderpereira.com.dreambox.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Wilder on 03/12/17.
 */

class CurrencyDTO {

    @SerializedName("product_monthly")
    @Expose
    internal var productMonthly: Float = 0.toFloat()

    @SerializedName("product_value")
    @Expose
    internal var productValue: Float = 0.toFloat()

    @SerializedName("product_x")
    @Expose
    internal var productX: Float = 0.toFloat()
}
