
package wilderpereira.com.dreambox.model.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("labelAnnotations")
    @Expose
    private List<LabelAnnotation> labelAnnotations = null;
    @SerializedName("webDetection")
    @Expose
    private WebDetection webDetection;

    public List<LabelAnnotation> getLabelAnnotations() {
        return labelAnnotations;
    }

    public void setLabelAnnotations(List<LabelAnnotation> labelAnnotations) {
        this.labelAnnotations = labelAnnotations;
    }

    public WebDetection getWebDetection() {
        return webDetection;
    }

    public void setWebDetection(WebDetection webDetection) {
        this.webDetection = webDetection;
    }

}
