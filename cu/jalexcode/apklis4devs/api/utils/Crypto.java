package cu.jalexcode.apklis4devs.api.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public final class Crypto {
    public static String Encrypt(Serializable ser){
        try{
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bout);
            out.writeObject(ser);
            out.close();
            return Base64.getEncoder().encodeToString(bout.toByteArray());
        }catch(Throwable e){
            throw new Error(e);
        }
    }
    
    public static <T extends Serializable> T Decrypt(String s){
        try{
            ByteArrayInputStream in = new ByteArrayInputStream(Base64.getDecoder().decode(s));
            ObjectInputStream bin = new ObjectInputStream(in);
            return (T) bin.readObject();
        }catch(Throwable e){
            throw new Error(e);
        }
    }
}
