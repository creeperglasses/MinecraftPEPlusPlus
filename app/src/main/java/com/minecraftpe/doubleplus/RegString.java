package com.minecraftpe.doubleplus;
import java.util.regex.*;

public class RegString
{
	public static String getStringNoBlank(String str) {    
        if(str!=null && !"".equals(str)) {    
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");    
            Matcher m = p.matcher(str);    
            String strNoBlank = m.replaceAll("");    
            return strNoBlank;    
        }else {    
            return str;    
        }         
    }
}
