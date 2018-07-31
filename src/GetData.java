//Generic purpose class for user input

import javax.swing.JOptionPane;

public class GetData {
    
    public static double getDouble(String s){
        
        return Double.parseDouble(getString(s));
    }
    
    public static int getInt(String s){
        
        return Integer.parseInt(getString(s));
    }
    
    public static String getString(String s){
        
        return JOptionPane.showInputDialog(s);
    }
    
}