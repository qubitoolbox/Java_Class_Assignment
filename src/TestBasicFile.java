/*Osman D Morales*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StreamTokenizer;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JFileChooser;



public class TestBasicFile_1 {
    
    
    public static void main (String[] args) throws IOException{
       
        boolean done  = false;
        BasicFile f = null ;
        FileOutputStream fos = null;
        
        
        do{
            //menu displayed to user
            String s = "\t\t*******\n"
                    + "BEFORE CHOOSING ANY OPTION "
                    + "FILE MUST BE CHOSEN FIRST\n"
                    + "\t\t*******"
                    + "\n1. Open File\n2 Copy File\n3. Append to File \n4. Display file attributes" +
                "\n5. Display Contents\n6. Search\n7. Tokenize input file\n8. Quit";//JOptionPane.showInputDialog(menu);
            try{
            
                int i = GetData.getInt(s);
                
                switch(i){
                
                    case 1:
                        //initializes a new file
                        f = new BasicFile();
                           
                    break;
                    case 2:
                        
                        try{
                            
                            int copyMenu = GetData.getInt("1.Copy Text\n2.Copy Image");
                            
                            switch(copyMenu){
                                
                                case 1:
                                    
                                    //Code that will copy text files
                                    JFileChooser choose = new JFileChooser(".");
                                    int status = choose.showSaveDialog(null);
                                    if(status!= JFileChooser.APPROVE_OPTION)
                                        throw new IOException();
                            
                                    //File f_cp = choose.getCurrentDirectory();
                                    File f_cp1 = choose.getSelectedFile();
                        
                                    BufferedWriter bf = new BufferedWriter(new FileWriter(f_cp1));
                                    //BufferedWriter bf1 = new BufferedWriter(new FileWriter(f_cp));
                                    LineNumberReader lnr = new LineNumberReader(new FileReader(f.getPath()));
                                    
                                    String line = "";
                                    while((line = lnr.readLine()) != null){
                                        
                                        bf.write(line, 0, line.length());
                                        bf.newLine();
                        
                                    
                                    bf.flush();
                                    bf.close();}
                                    
                                break;
                                case 2:
                                
                                try{
                                    //Code will copy image files
                                    FileInputStream fil1 = new FileInputStream(f.getPath());
                                    DataInputStream dis = new DataInputStream(fil1);
                                    fos = new FileOutputStream(GetData.getString("What is filename + "
                                            + "extension you would like to save:" + "\n"
                                            + "eg. type, 'file.jpg'"
                                            + "\n*Input ext must match output ext*\n"),false);
            
                                    int length = dis.available(); //available stream to be read
                                    byte[] buf = new byte[length];//create buffer
                                    dis.readFully(buf);//read the full data into the buffer
            
                                    fos.write(buf,0,buf.length);
                            
                                    }
        
                                catch(FileNotFoundException e){
                                    JOptionPane.showMessageDialog(null, "Error: while opening file:\n"+e);
        
                                }
                                
                                catch(IOException e){
                                    JOptionPane.showMessageDialog(null, "Error while reading stream:\n"+e);
        
                                }
                                
                                break;
                                
                                }
                            }
                            catch(FileNotFoundException e){
                            
                            JOptionPane.showMessageDialog(null, "Error: while opening file:\n"+e);
        
                            }
                            catch(IOException e){
                            
                            JOptionPane.showMessageDialog(null, "Error while reading stream:\n"+e);
        
                        }
                        
                    break;
                    case 3:
                        
                        //This case appends data to a text
                        //it also overwrites data to an existing file
                        try{
            
                           
                            FileInputStream fil1 = new FileInputStream(f.getPath());
                            
                            DataInputStream dis = new DataInputStream(fil1);
                            
                            int option_to_append = GetData.getInt("\t\tWould you like to Append or OverWrite\n"
                                    + "1. Append" + "\n2. Overwrite");
                            
                            switch(option_to_append){
                            
                                case 1:
                                    fos = new FileOutputStream(GetData.getString("What is filename + extension you would like to save:" + "\n"
                                    + "eg. type, 'file.txt'"), true);
                                break;
                                case 2:
                                    fos = new FileOutputStream(GetData.getString("What is filename + extension you would like to save:" + "\n"
                                    + "eg. type, 'file.txt'"), false);
                                break;
                                
                            }
                            
                            int length = dis.available(); //available stream to be read
                            byte[] buf = new byte[length];//create buffer
                            dis.readFully(buf);//read the full data into the buffer
            
                            fos.write(buf,0,buf.length);
                            
                        }
        
                        catch(FileNotFoundException e){
        
                            System.out.println("Error: while opening file:\n"+e);
        
                        }
                        catch(IOException e){
                            System.out.println("Error while reading stream:\n"+e);
        
                        }
                    break;
                    case 4:
                        
                        //Displays files properties
                        String ss = "";
                        
                      
                        LineNumberReader lnr = new LineNumberReader(new FileReader(f.getPath()));
                        
                        displayScroll("Parent Folder: " + f.getFileParent() +"\n" +"File Path: " + f.getPath() + "\n"
                                + " File Size" + f.length() / 1000 + " KB" + "\n" 
                                +  lnr.getLineNumber(), "File Atributes\n"
                                        + "", JOptionPane.INFORMATION_MESSAGE);
                        
                        File dir =new File(f.getPath());
                        listRecursive(dir);
                        
                    break;
                    case 5:
                        //object that reads text line by line
                        LineNumberReader ln_r = null;
            
                        try {
            
                            RandomAccessFile raf = new RandomAccessFile(f.getPath(), "r");
                            
                            System.out.println(raf.getFilePointer());
                            ln_r = new LineNumberReader(new FileReader(f.getPath()));
                            int st = 0;
                            String line = "";
                
                            while((line = ln_r.readLine()) != null)
                            {
                                line += "Line" +  ln_r.getLineNumber() + ":" + line;
                                
                                st++;
                            }
                            //System.out.println(line);
                            //System.out.println(st);
                            }
                        
            
                        finally{
                            try{
                                ln_r.close();
                
                                }
                            catch(IOException e){
                                e.printStackTrace();
                
                                }
                        //object that parses elements/tokens    
                        StreamTokenizer st = new StreamTokenizer(new FileReader(f.getPath()));
                        
                            while(st.nextToken() != StreamTokenizer.TT_EOF){
                                String s_ = "";        
                                switch(st.ttype){
                                    
                                    case StreamTokenizer.TT_WORD:
                                        s_ += st.sval;
                                    break;
                                    case StreamTokenizer.TT_NUMBER:
                                        s_ += "\tNew line ++> " +
                                                st.sval + (char)st.ttype;
                                        break;
                                    case StreamTokenizer.TT_EOL: 
                                    break;
                                    default:
                                        
                                        s_ += ((char)st.ttype + " ---> not recognized");
                                    break;
                                   
                                }
                            
                            displayScroll(s_, "", JOptionPane.INFORMATION_MESSAGE);
                            }
            
            }
                        
                        
                    break;
                    case 6:
                        //searches that a specific work exists or not
                        
                        String user_inp = GetData.getString("What would you like to search? ");

                        try{
                            BufferedReader bf = new BufferedReader(new FileReader(f.getPath()));
                            int index = 0;
                            String lnr_;
                            
                            while (( lnr_ = bf.readLine()) != null){
                                index++;
       
                            if(lnr_.contains(user_inp))
                                JOptionPane.showMessageDialog(null, index + ": " + lnr_);
                            else
                                    JOptionPane.showMessageDialog(null, "Nothing was found");
                                    break;

                            }
                            bf.close();

                            }
                            catch (IOException e){
                                JOptionPane.showMessageDialog(null, "IO Error Occurred: " + e.toString());
                        }
                    break;
                    case 7:
                        
                        StreamTokenizer st = new StreamTokenizer(new FileReader(f.getPath()));
                        st.eolIsSignificant(true);
                        //st.wordChars(''), '');
                        st.wordChars('@', '@');
                        st.wordChars(',', ',');
                        st.wordChars('!','!');
                        st.lowerCaseMode(true);
                        
                            while(st.nextToken() != StreamTokenizer.TT_EOF){
                            
                                switch(st.ttype){
                                
                                    case StreamTokenizer.TT_WORD:
                                        System.out.println(st.sval);
                                    break;
                                    case StreamTokenizer.TT_NUMBER:
                                        System.out.println("\tNew line ++> " +
                                                st.sval + (char)st.ttype);
                                    break;
                                    case StreamTokenizer.TT_EOL:
                                        System.out.println("\tNew line ++> " + st.sval + (char)st.ttype);
                                    break;
                                    default:
                                        System.out.println((char)st.ttype + " --> not recognized");
                                    break;
                                   
                                }
                            
                            
                            }
                        
                        
                        
                    break;
                    case 8:
                        done = true;
                    break;
                    default:
                        display("This option is undefined", "Error");
                    break;
                
                }
            
            
            }
            catch(NumberFormatException | NullPointerException e){
            
                display(e.toString(), "Error");
            
            }
        
        }
        while(!done);
    
    }
    
    static void display(String s, String err){
    
        JOptionPane.showMessageDialog(null,s ,err , JOptionPane.ERROR_MESSAGE);
    
    
    }
    
    static void displayScroll(String s, String heading, int MESSAGE_TYPE){
    
        JTextArea text = new JTextArea(s, 20, 30);
        JScrollPane pane = new JScrollPane(text);
        JOptionPane.showMessageDialog(null,pane, heading, MESSAGE_TYPE);
    
    
    }
    
    
   //mutator retrieves the path of root folder
    static void listRecursive(File f){
    
        if(f.isDirectory()){
            
            File[] f_ = f.listFiles();
            for(File j:f_){
            
                if(j.isFile()){
                    System.out.println("\tFile: " + j.getAbsoluteFile() + "\t"
                            + j.length() + "bytes");}
                else 
                    {
                        
                        System.out.println("Directory:" + j.getName());
                        listRecursive(j);
                }
            }
        }
    
    
    
    }
    
        
    
    }
    

