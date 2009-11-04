//Sample of OptionParserSupport Usage
package deng.jdkexamples;

import deng.jdkexamples.util.OptionsParserSupport;

public class App extends OptionsParserSupport {
    /////////////////////////////////////////////////////////////////////////
    // Setters for OptionsParserSupport
    /////////////////////////////////////////////////////////////////////////
    private boolean debug= false;
    public void debug(String flag) { debug= Boolean.valueOf(flag); }
    
    /////////////////////////////////////////////////////////////////////////
    // Main Class
    /////////////////////////////////////////////////////////////////////////
    public void run(String[] args) {
        if(debug)
            System.out.println("show debug");        
        System.out.println("running");
    }
        
    /////////////////////////////////////////////////////////////////////////
    // Main Program Entry
    /////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) throws Exception {
    	App main = new App();
        String[] newArgs= parseAndSetOptionsObject(main, args);
        main.run(newArgs);
    }
}

