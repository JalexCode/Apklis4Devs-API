package cu.jalexcode.apklis4devs.api.utils;

public final class LockerHelper<T> {
    private T value;
    private boolean ready;
    private Throwable err;

    public T getValue() {
        return value;
    }

    public boolean isReady() {
        return ready;
    }

    public synchronized void setValue( T value) {
        this.value=value;
        this.ready = true;
        notifyAll();
    }
    
    public synchronized void onError(Throwable e){
        err=e;
        ready=true;
        notifyAll();
    }
    
    public T getWhenPossible() throws Exception{
        waitUntil();
        if(err!=null) {
            throw new ErrorWrapper(err);
        }
        return value;
    }
    
    public synchronized void waitUntil(){
        try{
            while(!isReady()){
                wait(500);
            }
        }catch(Throwable e){
            e.printStackTrace();
        }
    }

    public static final class ErrorWrapper extends Exception{
        public ErrorWrapper() {
        }

        public ErrorWrapper(String message) {
            super(message);
        }

        public ErrorWrapper(String message, Throwable cause) {
            super(message, cause);
        }

        public ErrorWrapper(Throwable cause) {
            super(cause);
        }

        public ErrorWrapper(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }
}
