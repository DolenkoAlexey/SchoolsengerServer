package json;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class ResponseJson implements Serializable{

    @SerializedName("response")
    private boolean response;

    public ResponseJson(boolean response) {
        this.response = response;
    }

    public boolean getResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }
}
