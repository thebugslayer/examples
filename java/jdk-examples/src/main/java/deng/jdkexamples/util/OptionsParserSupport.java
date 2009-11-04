//OptionsParserSupport
package deng.jdkexamples.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class OptionsParserSupport {
    protected static String[] parseAndSetOptionsObject(Object obj, String[] args) {
        Class<?> objClass = obj.getClass();
        List<String> newArgs = new ArrayList<String>();
        String option = null;
        for(int i = 0; i < args.length; i++) {
            String arg = args[i];
            if(arg.equals("--")) { // escape all options parsing.
                for(int j = i; j < args.length; j++)
                    newArgs.add(args[j]);
                break; // break out of all arguments parsing!
            }else if(arg.startsWith("--")) {
                option = arg.substring(2);
            } else {
                newArgs.add(arg);
                continue;
            }
            
            //Process the option value.
            String methodName = option;
            Object value = "true";
            Method method;
            if(option.indexOf("=") > 0) {
                String[] words = option.split("=");
                methodName = words[0];
                value = words[1];
            }
            try { 
                method = objClass.getMethod(methodName, String.class);
                //System.out.println("Invoking " + option);
                method.invoke(obj, value);
            } catch (Exception e) {
                throw new RuntimeException("Failed to set option " + arg, e);
            }
        }
        return newArgs.toArray(new String[newArgs.size()]);
    }
}
