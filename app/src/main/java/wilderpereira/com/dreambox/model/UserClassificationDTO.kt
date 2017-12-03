package wilderpereira.com.dreambox.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Wilder on 03/12/17.
 */

class UserClassificationDTO {
    @Expose
    @SerializedName("buy_status")
    var aGoodGoal: Boolean? = null

    @Expose
    @SerializedName("buy_classification")
    var classification: String = ""
}
