
package wilderpereira.com.dreambox.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WebEntity {

    @SerializedName("entityId")
    @Expose
    private String entityId;
    @SerializedName("score")
    @Expose
    private Float score;
    @SerializedName("description")
    @Expose
    private String description;

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
