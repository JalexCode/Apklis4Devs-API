package cu.jalexcode.apklis4devs.api.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import cu.jalexcode.apklis4devs.api.Version;
import cu.jalexcode.apklis4devs.api.internal.Internal;
import cu.jalexcode.apklis4devs.api.internal.URLConfiguration;
import cu.jalexcode.apklis4devs.api.utils.Constants;
import cu.jalexcode.apklis4devs.api.exceptions.ServerErrorException;
import cu.jalexcode.apklis4devs.api.exceptions.ServiceUnavailableException;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

/**
 * Publica una app. EXPERIMENTAL. NO_RECOMENDADO
 */
public final class PushAppTask implements Serializable {
    private URLConfiguration cfg=new URLConfiguration(Constants.V1.URL_API_PUSH_APP, "POST", "authorization");
    private File stream;
    private String description;
    private Categories categories;
    private int price;
    private OnStateChangedListenerMultiplexer listener = new OnStateChangedListenerMultiplexer();
    private String auth;

    /**
     * Constructor por defecto
     */
    public PushAppTask(String auth) {
        this.auth=auth;
    }

    /**
     * Registra un escuchante de eventos
     * @param event     Escuchante de eventos duramte la subida
     */
    public void add(OnStateChangedListener event){
        listener.add(event);
    }

    /**
     * Elimina un escuchante de eventos
     * @param event    Escuchante de eventos duramte la subida
     */
    public void rem(OnStateChangedListener event){
        listener.rem(event);
    }

    /**
     * @return Devuelve la descripcion
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description Descripcion a usar
     */
    public PushAppTask setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * @return Devuelve El Archivo a usar
     */
    public File getStream() {
        return stream;
    }

    /**
     * @param stream Establece el archivo a usar
     */
    public PushAppTask setStream(File stream) {
        this.stream = stream;
        return this;
    }

    /**
     * @return Devuelve las Categorias de la app
     */
    public Categories getCategories() {
        return categories;
    }

    /**
     * @param categories Establece las categorias de la app
     */
    public PushAppTask setCategories(Categories categories) {
        this.categories = categories;
        return this;
    }

    /**
     * @return Devuelve el precio
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price Precio a establecer
     */
    public PushAppTask setPrice(int price) {
        this.price = price;
        return this;
    }

    /**
     * Ejecuta la subida de la aplicacion, IMPORTANTE, se debe revisar que todos los valores no sean nulos y esten bien configurados
     */
    public void execute(){
        checkVariables();
        try{
            listener.onStart();
            long start = System.currentTimeMillis();

            baseExecute();

            listener.onFinish(System.currentTimeMillis()-start);
        }catch(Throwable err){
            listener.onError(err);
        }
    }

    void checkVariables(){
        if(stream==null)throw new NullPointerException("La aplicacion a subir no puede ser nula");
        if(categories==null)throw new NullPointerException("Las categorias no pueden ser nulas");

        if(categories.getCategories().size()==0)throw new NullPointerException("Las categorias no pueden ser nulas");

        if(price<0)throw new IllegalArgumentException("El precio no puede ser nulo");

        if(description==null)throw new NullPointerException("La Descripcion no puede ser nula");
    }

    void baseExecute() throws Exception{
        //TODOed: Completar la subida de la aplicacion
        cfg.configureIfNeeded();

        Request request = new Request.Builder()
                .url(Constants.V1.URL_API_PUSH_APP)
                .addHeader("Authorization", auth)
                .post(new MultipartBody.Builder()
                        .addFormDataPart("application.price", price+"")
                        .addFormDataPart("application.categories", categories.print())
                        .addFormDataPart("application.description", description)
                        .addFormDataPart("application.public", "true")
                        .addFormDataPart("apk_file", "APKLIS_API_V" + Version.VERSION + "_BY_" + Version.AUTHOR + "_" + UUID.randomUUID() + ".apk", new RequestBody() {
                            public MediaType contentType() {
                                return MediaType.parse("application/octet-stream");
                            }

                            public void writeTo(BufferedSink bufferedSink) throws IOException {
                                long size = stream.length(),
                                    readLen = 1024000;
                                FileInputStream in = new FileInputStream(stream);

                                int outed = 0;

                                byte[] arr = new byte[(int) readLen];
                                int r = -1;

                                while((r=in.read())!=-1){
                                    bufferedSink.write(arr, 0, r);
                                    outed+=r;

                                    listener.onProgress((short) ((outed*100)/size));
                                }
                            }
                        })
                        .build())
                .build();

        Response resp = Internal.req(request);

        switch(resp.code()){
            case 500:
                throw new ServerErrorException();
            case 503:
                throw new ServiceUnavailableException();
        }
        if(resp.isSuccessful()){
            return;
        }
        throw new Error(resp.code()+": "+resp.body().string());
    }

    /**
     * Clase de escucha de los eventos ocurridos durante la subida de la aplicacion
     */
    public static interface OnStateChangedListener{
        /**
         * Llamado siempre antes de subir la aplicacion
         */
        void onStart();

        /**
         * Llamado cuando la aplicacion se ha subido con exito
         * @param millis
         */
        void onFinish(long millis);

        /**
         * Llamado cuando ocurrio un error durante la subida
         * @param err
         */
        void onError(Throwable err);

        /**
         * Llamado para actualizar el progreso de la subida de la app
         * @param progress Progreso
         */
        void onProgress(short progress);
    }

    class OnStateChangedListenerMultiplexer implements OnStateChangedListener, Serializable{
        private ArrayList<OnStateChangedListener> list = new ArrayList<>();

        public void add(OnStateChangedListener ev){
            list.add(ev);
        }

        public void rem(OnStateChangedListener ev){
            list.remove(ev);
        }

        public void onStart() {
            for (OnStateChangedListener ev:
                    list) {
                ev.onStart();
            }
        }

        public void onFinish(long millis) {
            for (OnStateChangedListener ev:
                    list) {
                ev.onFinish(millis);
            }
        }

        public void onError(Throwable err) {
            for (OnStateChangedListener ev:
                 list) {
                ev.onError(err);
            }
        }

        public void onProgress(short progress) {
            for (OnStateChangedListener ev:
                    list) {
                ev.onProgress(progress);
            }
        }
    }
}
