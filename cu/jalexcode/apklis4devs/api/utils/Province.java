package cu.jalexcode.apklis4devs.api.utils;

public enum Province{
    Isla_de_la_juventud,
    Pinar_Del_Rio,
    Mayabeque,
    La_Habana,
    Artemisa,
    Matanzas,
    Villa_Clara,
    Cienfuegos,
    Sancti_Spiritus,
    Ciego_De_Avila,
    Camaguey,
    Las_Tunas,
    Granma,
    Holguin,
    Santiago_de_Cuba,
    Guantanamo;
    
    public int toNumber(){
        return ordinal();
    }
    
    public static Province fromNumber(int i){
        return values()[i];
    }

    public String toString() {
        return name();
    }
}
