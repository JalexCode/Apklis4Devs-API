package cu.jalexcode.apklis4devs.api.internal;

import java.io.Serializable;

import okhttp3.Request;

public class RequestImage implements Serializable {
    private String url;
    private String method;
    private String[] headers;

    public RequestImage() {
    }

    public RequestImage(String url, String method, String[] headers) {

        this.url = url;
        this.method = method;
        this.headers = headers;
    }

    public RequestImage url(String url){
        this.url=url;
        return this;
    }

    public RequestImage method(String method){
        this.method=method;
        return this;
    }

    public RequestImage headers(String... args){
        this.headers=args;
        return this;
    }

    public Request toRequest(){
        Request.Builder t = new Request.Builder();
        t.url(url);
        if(method!=null)t.method(method, null);
        if(headers!=null){
            for (int i = 0; i < headers.length; i+=2) {
                t.addHeader(headers[i], headers[i+1]);
            }
        }
        return t.build();
    }
}
