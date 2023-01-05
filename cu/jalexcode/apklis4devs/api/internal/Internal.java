package cu.jalexcode.apklis4devs.api.internal;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import cu.jalexcode.apklis4devs.api.utils.LockerHelper;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public final class Internal {
    private static OkHttpClient client;

    static{
		client= new OkHttpClient();
        /*OkHttpClient.Builder tmpClient= new OkHttpClient.Builder();
        //tmpClient.interceptors().add(new UserAgentInterceptor("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:91.0) Gecko/20100101 Firefox/90.0"));
        client=tmpClient.build();*/

    }

    public static Response req(Request r) throws Exception {
        Lock l = new ReentrantLock();
        l.lock();
        final LockerHelper<Response> response =new LockerHelper<>();
		
        client.newCall(r).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                response.onError(e);
            }
            public void onResponse(Call call, Response r2) throws IOException {
                response.setValue(r2);
            }
        });

        Response result=null;
        result=response.getWhenPossible();
        l.unlock();
        return result;
    }

    public static Response req(String url) throws Exception{
        return req(new Request.Builder()
                .url(url)
                .build());
    }

    /*public static class UserAgentInterceptor implements Interceptor {

        private final String userAgent;

        public UserAgentInterceptor(String userAgent) {
            this.userAgent = userAgent;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request requestWithUserAgent = originalRequest.newBuilder()
                    .header("User-Agent", userAgent)
                    .build();
            return chain.proceed(requestWithUserAgent);
        }
    }*/
}
