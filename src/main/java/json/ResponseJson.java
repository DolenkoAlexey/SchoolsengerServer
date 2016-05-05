package json;

import com.google.gson.annotations.SerializedName;

public class ResponseJson {

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
