package cu.jalexcode.apklis4devs.api.model;

import cu.jalexcode.apklis4devs.api.Session;
import cu.jalexcode.apklis4devs.api.internal.Internal;
import cu.jalexcode.apklis4devs.api.internal.URLConfiguration;
import cu.jalexcode.apklis4devs.api.utils.Constants;
import org.json.JSONObject;

import java.io.Serializable;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Clase que contiene un comentario (y sus respuestas)
 */
public final class Comment extends Review implements Serializable{
    private int rating;
    private int applicationId;
    private String applicationName;
    private boolean lastRelease;

    private URLConfiguration cfg;

    /**Interno*/
    public Comment(String json) throws Exception{
        this(new JSONObject(json));
    }

    /**Interno*/
    public Comment(JSONObject obj) throws Exception{
        super(obj);
        this.rating=obj.getInt("rating");
        this.lastRelease=obj.getBoolean("last_release");
        this.applicationName=obj.getString("application_name");
        this.applicationId=obj.getInt("application");
        cfg= new URLConfiguration(Constants.V1.URL_API_REVIEW(getId()), "POST", "content-type");
    }

    /**
     * Devuelve la valoracion que da este comentario
     */
    public int getRating() {
        return rating;
    }

    /**
     * Obtiene el id de la app a la que se esta comentando. INTERNO
     */
    public int getApplicationId() {
        return applicationId;
    }

    /**
     * El nombre de la app a la que se esta comentanddo
     * @return
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * Devuelve si este comentario se refiere a la ultima version de la app
     */
    public boolean isLastRelease() {
        return lastRelease;
    }

    /**
     * Responde a este comentario. EXPERIMENTAL
     * @param s      Session actual
     * @param packag Paquete de la app (como cu.kareldv.android.apklisadmin)
     * @param msg    Mensaje
     * @param inf    Informacion del usuario que esta comentandio {@link Session#getUserInfo()}
     */
    public void Reply(Session s, String packag, String msg, UserInfo inf){
        try{
            cfg.configureIfNeeded();

            Request.Builder b = new Request.Builder();
            b.url(Constants.V1.URL_API_REPLY);
            b.addHeader("Authorization", s.toAuthorization());
            b.post(RequestBody.create(MediaType.parse("application/json"), reviewBody(msg, packag, inf.getUser()).toString()));
            
            Response res = Internal.req(b.build());
            if(res.code()!=201)throw new Error(res.message());
        }catch(Exception t){
            throw new Error(t);
        }
    }
    
    JSONObject reviewBody(String msg, String pack, String username) throws Exception {
        return new JSONObject()
                .put("review", getId())
                .put("comment", msg)
                .put("application", pack)
                .put("username", username);
    }
}
