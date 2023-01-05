package cu.jalexcode.apklis4devs.api.internal;

import java.io.Serializable;

public final class URLConfiguration implements Serializable{
    private boolean configured=false;
    private RequestImage request;

    /**
     * Ejecuta una peticion especificando su URL, Metodos y Cabeceras
     * @param url            URL Objetivo
     * @param method         Metodos Pedidos
     * @param req_headers    Cabeceras Pedidas
     */
    public URLConfiguration(String url, String method, String req_headers){
        this(new RequestImage().url(url).method("OPTIONS").headers("Access-Control-Request-Headers", req_headers, "Access-Control-Request-Method", method));
    }

    /**
     * Ejecuta una peticion ya creada
     * @param request Peticion Ya Creada
     */
    public URLConfiguration(RequestImage request) {
        this.request = request;
    }

    /**
     * Ejecuta la peticion SOLO si no he ha realizado antes
     * @return Devuelve si ya ha sido configurado o si tuvo exito
     */
    public boolean configureIfNeeded(){
        if(configured)return true;
        return configure();
    }

    /**
     * Ejecuta la peticion incluso siya se ha ejecutado antes
     * @return Devuelve si tuvo exito la peticion
     */
    public boolean configure(){
        try{
            okhttp3.Response res = Internal.req(request.toRequest());
            return res.isSuccessful();
        }catch(Exception ex){
            throw new Error(ex);
        }
    }
}
