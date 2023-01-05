package cu.jalexcode.apklis4devs.api.model;

import cu.jalexcode.apklis4devs.api.internal.Internal;
import cu.jalexcode.apklis4devs.api.utils.Constants;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

import cu.jalexcode.apklis4devs.api.Session;
import cu.jalexcode.apklis4devs.api.utils.Dates;
import okhttp3.*;

/**
 * Clase que contiene una valoracion (es la clase que forma al comentario {@link Comment})
 */
public class Review implements Serializable{
    private int id;
    private String comment;
    private Date published;
    private Person user;
    private boolean _public;
    private boolean isDeleted;

    /**Interno*/
    public Review(String json) throws Exception{
        this(new JSONObject(json));
    }

    /**Interno*/
    public Review(JSONObject obj) throws Exception{
        this.id=obj.getInt("id");
        this.comment=obj.getString("comment");
        final String pubTmp = obj.getString("published");
        this.published= Dates.parse(pubTmp.trim());
        this._public=obj.getBoolean("public");
        this.user=new Person(obj.getJSONObject("user"));
    }

    /**Interno*/
    public int getId() {
        return id;
    }

    /**
     * Obtiene el texto del comentario
     */
    public String getComment() {
        return comment;
    }

    /**
     * Obtiene la fecha en la que se publicó
     */
    public Date getPublished() {
        return published;
    }

    /**
     * Devuelve el usuario que lo publicó
     */
    public Person getUser() {
        return user;
    }

    /**
     * Devuelve si el comentario es publico
     */
    public boolean isPublic() {
        return _public;
    }

    /**
     * Devuelve si esta eliminado
     */
    public boolean isDeleted() {
        return isDeleted;
    }

    /**
     * Elimina este comentario
     * @param s Sesion actual
     */
    public void Delete(Session s){
        try{
            setupDelete();

            Request.Builder b = new Request.Builder();
            b.url(Constants.V1.URL_API_REVIEW(id));
            b.addHeader("Authorization", s.toAuthorization());
            b.delete();

            Response r = Internal.req(b.build());
            if(r.code()!=200)throw new Error(r.message());
            isDeleted=true;
        }catch(Exception e){
            throw new Error(e);
        }
    }

    void setupDelete(){
        try{
            Request.Builder b = new Request.Builder();
            b.url(Constants.V1.URL_API_REVIEW(id));
            b.addHeader("Access-Control-Request-Headers", "authorization");
            b.addHeader("Access-Control-Request-Method", "DELETE");

            Internal.req(b.build());
        }catch(Exception ex){
            throw new Error(ex);
        }
    }

    /*public String toJsonReply(String msg, UserInfo inf){
    return new JSONObject()
    .put("review", id)
    .put("", clctn).toString();
    }*/

    /**
     * Clase que contiene a la persona que comento
     */
    public static final class Person implements Serializable{
        private String username, first_name, last_name, avatarUrl;

        /**Interno*/
        public Person(String json) throws Exception{
            this(new JSONObject(json));
        }

        /**Interno*/
        public Person(JSONObject obj) throws Exception{
            this.username=obj.getString("username");
            this.avatarUrl=obj.getString("avatar");
            this.first_name=obj.getString("first_name");
            this.last_name=obj.getString("last_name");
        }

        /**
         * Devuelve el nombre de usuario
         */
        public String getUsername() {
            return username;
        }

        /**
         * Devuelve el nombre de la persona
         */
        public String getFirst_name() {
            return first_name;
        }

        /**
         * Devuelve los apellidos de la persona
         */
        public String getLast_name() {
            return last_name;
        }

        /**
         * Devuelve la url del icono de la persona
         */
        public String getAvatarUrl() {
            return avatarUrl;
        }
    }
}
