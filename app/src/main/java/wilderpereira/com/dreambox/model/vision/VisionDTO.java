
package wilderpereira.com.dreambox.model.vision;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VisionDTO {

    @SerializedName("requests")
    @Expose
    private List<Request> requests = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public VisionDTO() {
    }

    /**
     * 
     * @param requests
     */
    public VisionDTO(List<Request> requests) {
        super();
        this.requests = requests;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

}
