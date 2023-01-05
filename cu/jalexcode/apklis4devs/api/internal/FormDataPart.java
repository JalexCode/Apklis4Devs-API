package cu.jalexcode.apklis4devs.api.internal;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public final class FormDataPart{
    private String header, value;

    public FormDataPart(String header, String value) {
        this.header = header;
        this.value = value;
    }

    public Headers headers() {
        return Headers.of("name", header);
    }

    public RequestBody body() {
        return RequestBody.create(MediaType.get("text/plain"), value.getBytes());
    }

    public MultipartBody.Part get(){
        return MultipartBody.Part.create(headers(), body());
    }
}
