
package wilderpereira.com.dreambox.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LabelAnnotation {

    @SerializedName("mid")
    @Expose
    private String mid;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("score")
    @Expose
    private Float score;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

}
