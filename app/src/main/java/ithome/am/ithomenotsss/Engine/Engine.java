package ithome.am.ithomenotsss.Engine;

import android.content.Context;

public class Engine {
    private static Engine engine=null;
    private DBServices dbServices=null;
    public static Engine getInstance(){
        return (engine==null)?engine=new Engine():engine;
    }
    public DBServices getDbServices(Context context){
        return (dbServices==null)?dbServices=new DBServices(context):dbServices;
    }
}
