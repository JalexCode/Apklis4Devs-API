package cu.jalexcode.apklis4devs.api.utils;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

public final class JSONBody extends RequestBody {
    private byte[] content;

    public JSONBody(JSONObject obj){
        this.content=obj.toString().getBytes();
    }

    public JSONBody(String json){
        this.content=json.getBytes();
    }

    public JSONBody(byte[] content) {
        this.content = content;
    }

    @Override
    public MediaType contentType() {
        return MediaType.get("application/json");
    }

    @Override
    public void writeTo(BufferedSink bufferedSink) throws IOException {
        bufferedSink.write(content);
    }
}
