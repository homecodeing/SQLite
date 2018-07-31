package ithome.am.ithomenotsss.Engine;

import java.util.List;

public class Utils {
    public static String getStringSelectedItems(List<Model> models){
        StringBuilder returnValue= new StringBuilder();
        for (Model s:models) {
            returnValue.append(s.getName()).append(",");
        }
        return returnValue.toString();
    }
}
