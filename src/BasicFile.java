/*Osman D Morales*/

import javax.swing.*;
import java.io.*;


public class BasicFile 
{
    
    //reserves a var of type FILE
    File f;
    
    
    
    public BasicFile()
    {
        // object will point to root of directory
        JFileChooser choose = new JFileChooser(".");
        int status = choose.showOpenDialog(null);
        
        try{
            //user must choose okay else error
            if (status != JFileChooser.APPROVE_OPTION)
                throw new IOException();
            f = choose.getSelectedFile(); // add selected to file to object F
            
            
            if (!f.exists())
                throw new FileNotFoundException();
                    
                    display(f.getName(), "File has been chosen",JOptionPane.INFORMATION_MESSAGE);
                
         }
        catch(FileNotFoundException e){
            display("File not found....",e.toString(),JOptionPane.WARNING_MESSAGE);
        
        }
        catch(IOException e){
        
            display("Approve option was not selected", e.toString(), JOptionPane.ERROR_MESSAGE);
        
        }
    }
        //displays to user
        void display(String msg, String s, int t)
        {
        
            JOptionPane.showMessageDialog(null,msg,s,t);
    
    
    }
        //returns the path of a file
        String getPath()
        {
        
            return f.getAbsolutePath();
        
        }
        //returns the size of a file
        long getFileSize()
        {
        
            return f.length();
        
        }
        //return the folder when file resides
        String getFileParent()
        {
        
            return f.getParent();
        
        }
        //
        long length()
        {
        
            return f.length();
        }
        //returns if chosen is a file
        boolean isFile()
        {
        
            return isFile();
        
        }
        //permissions of file
        String canRead()
        {
        
            return (f.canRead())?"This file can be opened for reading":"Cannot read this file";
        
        }
        //returns if directory is true
        String directoryOrFile()
        {
        
            return(f.isDirectory())?"This is a directory and not an ordinary file":"This is a file and not a directory";
        
        }
        //returns if the file es existant or not
        String exists()
        {
            return (f.exists())?"The physical file exists":"The physical file does not exists";
        }
        
        
        /*
        void printWriter() throws IOException
        {
        
           // int option = GetData.getInt(s)
           PrintWriter pw = new PrintWriter(f);
           String filename = GetData.getString("What is file name to save?");
           pw.print(filename);
           pw.println();
           pw.flush();
           pw.close();
        
        }*/
        //buffer method calculation starts
        void unbufferedTime() throws IOException
        {
    
            FileInputStream fis = new FileInputStream(f);
            System.out.println(f.getName() + " - " + f.length() + " bytes");
        
            long startTime = System.nanoTime();
        
            while(fis.read() != -1)
        
            long endTime = System.nanoTime();
            System.out.println("Time    elapse   when   unbuffered" + (endTime-startTime)/1000000.0+ "msec" );
        }
    
        void bufferedTime()throws IOException{
    
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f),4096);
        
            System.out.println(f.getName() + " - " + f.length() + "bytes");
            long startTime = System.nanoTime();
        
            while(bis.read() != -1)
            ;
        
            long endTime = System.nanoTime();
            System.out.println("Time elapse when buffered " + (endTime - startTime)/1000000.0 + "msec");
    
    
    }
        //read a text file, line by line of character
        void readLineNumberByLine(File f) throws IOException
        {
            LineNumberReader lnr = null;
            try {
                lnr = new LineNumberReader(new FileReader(f));
                String line = "";
                while((line = lnr.readLine()) != null)
                    JOptionPane.showMessageDialog(null, "Line" +  lnr.getLineNumber() + ":" + line);
            }
            
            finally
            {
                try
                {
                    lnr.close();
                
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                
                }
            
            }
        
        
        
        }
        
        
    
            }
