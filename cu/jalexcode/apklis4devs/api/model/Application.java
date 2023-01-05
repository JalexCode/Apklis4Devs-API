package cu.jalexcode.apklis4devs.api.model;

import cu.jalexcode.apklis4devs.api.internal.Internal;
import cu.jalexcode.apklis4devs.api.internal.URLConfiguration;
import cu.jalexcode.apklis4devs.api.utils.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import cu.jalexcode.apklis4devs.api.Session;
import cu.jalexcode.apklis4devs.api.utils.JSONBody;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Clase que contiene todos los datos pertenecientes a una app
 * @see Session
 */
public final class Application implements Serializable {
    private String background, packageName, name, iconURL, description, lastUpdate;
    private int saleCount, downloadCount, price, rating, id;
    private boolean hasDB, revision;
    private DeveloperInfo developer;
    private ArrayList<Comment> comments;
    private ArrayList<String> screenshots;
    private Categories categories;
    private Session session;
    private transient URLConfiguration monthCfg;

    protected Application(Session s, String json) throws Exception {
        this(s, new JSONObject(json));
    }

    /**
     * Constructor interno, generalmente no deberias usarlo, ya que la clase {@link Session} te devuelve las apps
     */
    public Application(Session s, JSONObject obj) throws Exception {
        if (obj == null) {
            throw new Error("Aplicación no disponible");
        }
        this.session=s;
        id=obj.getInt("id");
        packageName=obj.getString("package_name");
        name=obj.getString("name");
        iconURL=obj.getString("icon");

//        background= obj.get("background") == JSONObject.NULL ? null:obj.get("background");
        description=obj.getString("description");
        lastUpdate=obj.getString("updated");
        saleCount=obj.getInt("sale_count");
        downloadCount=obj.getInt("download_count");
        price=obj.getInt("price");
        rating=obj.getInt("rating");
        revision=obj.getBoolean("revision");
        hasDB=obj.getBoolean("with_db");

        developer=new DeveloperInfo(obj.getJSONObject("developer"));

        categories=new Categories(obj.getJSONArray("categories"));

        screenshots=new ArrayList<>();
        JSONArray scr =obj.getJSONObject("last_release").getJSONArray("screenshots");
        if(scr==null)return;
        for (int i = 0; i < scr.length(); i++) {
            screenshots.add(scr.getJSONObject(i).getString("image"));
        }
    }

    /**
     * Id de la app, Uso interno
     */
    public int getId() {
        return id;
    }

    /**
     * El Package de la app, como {@code cu.kareldv.android.apklisadmin}
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Nombre de la app
     */
    public String getName() {
        return name;
    }

    /**
     * La direccion del icono
     */
    public String getIconURL() {
        return "https://archive.apklis.cu/"+iconURL;
    }

    /**
     * Experimental
     */
    Object getBackground() {
        return background;
    }

    /**
     * La descripcion de tu app en formato HTML
     */
    public String getDescription() {
        return description;
    }

    /**
     * Devuelve el nombre de la version de tu ultima atualizacion, como {@code 2-beta01, 3-alpha, 25.0.1 etc}
     * @return
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Numero de ventas
     */
    public int getSaleCount() {
        return saleCount;
    }

    /**
     * Cantidad de descargas, NO CONFUNDIR CON CANTIDAD DE VENTAS
     */
    public int getDownloadCount() {
        return downloadCount;
    }

    /**
     * Devuelve el precio de la app, 0 si es gratis
     */
    public int getPrice() {
        return price;
    }

    /**
     * Devuelve la valoracion de tu app (promedio, 0-5)
     * @return
     */
    public int getRating() {
        return rating;
    }

    /**
     * Devuelve si la app esta siendo revisada
     * @return
     */
    public boolean inRevision() {
        return revision;
    }

    /**
     * Devuelve si esta app tiene base de datos
     */
    public boolean hasDB() {
        return hasDB;
    }

    /**
     * Informacion del desarrollador, no es util, deberias usar {@link Session#getUserInfo()}
     * @return
     */
    @Deprecated
    public DeveloperInfo getDeveloper() {
        return developer;
    }

    /**
     * Devuelve una lista de urls, de las capturas de esta app
     */
    public ArrayList<String> getScreenshots() {
        return screenshots;
    }

    /**
     * Devuelve las categorias de esta app
     */
    public Categories getCategories() {
        return categories;
    }

    /**
     * Devuelve una lista de comentarios de esta app, a partir de 0 hasta, USA LA RED asi que usar en android en segundo plano
     */
    public ArrayList<Comment> getComments(){
        return getComments(0);
    }

    /**
     * Devuelve una lista de comentarios de esta app, a partir de el valor especificado en el argumento. USA LA RED, asi que usar en otro hilo en android
     * @param lastB El numero de comentarios a saltar (offset)
     */
    public ArrayList<Comment> getComments(int lastB) {
        //if(comments==null){
            resolveComments(lastB);
        //}
        return comments;
    }
    
    void resolveComments(int lastB){
        try{
            if(hasSetupComm)return;
            
            Request.Builder bd = new Request.Builder();
            bd.url(Constants.V1.URL_API_SEE_REVIEWS(lastB, this));
            
            Response r = Internal.req(bd.build());
            
            if(r.code()!=200)throw new Error(r.message());
            final String string = r.body().string();
            
            JSONArray arr= new JSONObject(string).getJSONArray("results");
            comments=new ArrayList<>(arr.length());
            for (int i = 0; i < arr.length(); i++) {
                comments.add(new Comment(arr.getJSONObject(i)));
            }
        }catch(Exception ex){
            throw new Error(ex);
        }
    }

    /**
     * Elimina esta app
     */
    public void delete(){
        try{
            Internal.req(new Request.Builder()
                    .url(Constants.V1.URL_API_CHANGE_APP_DATA(getId()+""))
                    .addHeader("Access-Control-Request-Headers", "authorization")
                    .addHeader("Access-Control-Request-Method", "DELETE")
                    .method("OPTIONS", null)
                    .build());

            Response resp = Internal.req(new Request.Builder()
                    .url(Constants.V1.URL_API_CHANGE_APP_DATA(getId()+""))
                    .addHeader("Authorization", session.toAuthorization())
                    .delete()
                    .build());

            if(resp.isSuccessful()){
                return;
            }
            throw new Error(resp.body().string());
        }catch(Exception e){
            throw new Error(e);
        }
    }

    public String toString() {
        return name;
    }
    
    private boolean hasSetupComm = false;

    public MonthDownloads requestMonthDownloads() throws Exception{
        if(monthCfg==null)monthCfg=new URLConfiguration(Constants.V2.URL_API_MONTH_DOWNLOADS, "POST", "authorization,content-type");
        monthCfg.configureIfNeeded();

        Request r = new Request.Builder()
                .url(Constants.V2.URL_API_MONTH_DOWNLOADS)
                .addHeader("Authorization", "Bearer "+session.getAccessToken())
                .post(new JSONBody(new JSONObject().put("app", getId())))
                .build();

        Response res = Internal.req(r);

        if(!res.isSuccessful()){
            Session.CommonError.throwEx(res);
        }
        return new MonthDownloads(new JSONArray(res.body().string()));
    }

    /**
     * Informacion del desarrollador
     */
    @Deprecated
    public static final class DeveloperInfo implements Serializable{
        private String username, fullname, avatarURL, descritpion, background;

        DeveloperInfo(String json) throws JSONException {
            this(new JSONObject(json));

        }

        DeveloperInfo(JSONObject obj) throws JSONException{
            this.username=obj.getString("username");
            this.fullname=obj.getString("fullname");
            this.avatarURL=obj.getString("avatar");
//            this.background=obj.get("background")==JSONObject.NULL?null:(String)obj.get("background");
            this.descritpion=obj.getString("description");
        }

        public String getUsername() {
            return username;
        }

        public String getFullname() {
            return fullname;
        }

        public String getAvatarURL() {
            return avatarURL;
        }

        public Object getBackground() {
            return background;
        }

        public String getDescritpion() {
            return descritpion;
        }

        public String toString() {
            return "USER:"+username;
        }
    }

    /**
     * Descargas mensuales. EXPERIMENTAL
     */
    public static final class MonthDownloads implements Serializable{
        private ArrayList<Integer> downloads;

        /**
         * Constructor por defecto
         * @param array
         */
        MonthDownloads(JSONArray array) throws Exception {
            downloads=new ArrayList<>(12);
            for(int i = 0; i<array.length(); i++){
                JSONObject obj = array.getJSONObject(i);
                downloads.add(obj.getInt(obj.keys().next()+""));
            }
            Collections.reverse(downloads);
        }

        /**
         * @return Devuelve La cantidad de descargas en los ultimos 12 meses
         */
        public ArrayList<Integer> getDownloads() {
            return downloads;
        }
    }
}
