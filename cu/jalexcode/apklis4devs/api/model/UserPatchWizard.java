package cu.jalexcode.apklis4devs.api.model;

import java.io.Serializable;
import java.util.Random;

import cu.jalexcode.apklis4devs.api.Session;
import cu.jalexcode.apklis4devs.api.internal.Internal;
import cu.jalexcode.apklis4devs.api.internal.URLConfiguration;
import cu.jalexcode.apklis4devs.api.utils.Constants;
import cu.jalexcode.apklis4devs.api.utils.Province;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Asistente para realizar cambios a el usuario
 */
public class UserPatchWizard implements Serializable {
    private String username, first_name, last_name, email;
    private Province province;
    private Session session;

    /**Interno*/
    UserPatchWizard(Session session) {
        this.session = session;
        cfg=new URLConfiguration(Constants.V1.URL_API_CHANGE_USER_INFO(session.getUserName()), "PATCH", "authorization");
    }

    /**
     * Establece el nombre de usuario
     * @param username El Nuevo nombre de usuario
     */
    public UserPatchWizard setUsername(String username){
        this.username=username;
        return this;
    }

    /**
     * Establece el Primer nombre
     * @param first_name Nuevo nombre de usuario
     */
    public UserPatchWizard setFirst_name(String first_name) {
        this.first_name = first_name;
        return this;
    }

    /**
     * Establece los apellidos
     * @param last_name Los nuevos apellidos
     */
    public UserPatchWizard setLast_name(String last_name) {
        this.last_name = last_name;
        return this;
    }

    /**
     * Establece un nuevo correo .EXPERIMENTAL
     */
    public UserPatchWizard setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * Establece la provincia del usuario
     */
    public UserPatchWizard setProvince(Province province) {
        this.province = province;
        return this;
    }

    /**
     * Ejecuta los cambios
     */
    public void execute(){
        try{
            cfg.configureIfNeeded();
            Request req = new Request.Builder()
                    .url(Constants.V1.URL_API_CHANGE_USER_INFO(session.getUserName()))
                    .addHeader("Authorization", session.toAuthorization())
                    .patch(getBody())
                    .build();
            Response resp = Internal.req(req);
            if(resp.code()!=200){
                throw new Error(resp.code()+": "+resp.body().string());
            }
        }catch(Exception ex){
            ex.printStackTrace();
            throw new Error(ex);
        }
    }

    MultipartBody getBody(){
        Random r = new Random();
        MultipartBody.Builder body = new MultipartBody.Builder(r.nextInt(9999999)+""+r.nextInt(9999999));
        body.setType(MultipartBody.FORM);
        if(username!=null){
            body.addFormDataPart("username", username);
        }

        if(first_name!=null){
            body.addFormDataPart("first_name", first_name);
        }

        if(last_name!=null){
            body.addFormDataPart("last_name", last_name);
        }

        if(email!=null){
            body.addFormDataPart("email", email);
        }

        if(province!=null){
            body.addFormDataPart("province", province.toNumber()+"");
        }

        return body.build();
    }
    String randomFileName(Random r){
        return toHex(r.nextInt(Integer.MAX_VALUE))+".tmp";
    }

    String toHex(int i){
        return Integer.toString(i, 16);
    }

    private URLConfiguration cfg;
}
