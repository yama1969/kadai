import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class JPGDisplay extends JFrame{
    private JpegPanelTimer jpgPanel;
    
    public JPGDisplay(){
        super("JPG Display");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        jpgPanel = new JpegPanelTimer(10);
        add(jpgPanel, BorderLayout.CENTER);
        
        setSize(1024, 768);
        setVisible(true);
    }
    
    public static void main(String[] args){
        JPGDisplay jd = new JPGDisplay();
    }
}

class JpegPanel extends JPanel{
    private Image[]  image;
    private String[] fileName;
    private int      dispNo;
    
    public JpegPanel(){
        image = new Image[2];
        fileName = new String[2];
        dispNo = 0;
    }
    
    public void setFile(File f){
        int no = (dispNo + 1) % 2;
        
        fileName[no] = f.getPath();
        Toolkit toolkit     = Toolkit.getDefaultToolkit();
        image[no] = toolkit.getImage(fileName[no]);
    }
    
    public void changeNo(){
        dispNo = (dispNo + 1) % 2;
        repaint();
    }
    
    public void paint(Graphics g){
        if( image[dispNo] == null ){
            return;
        }
        
        int panelWidth  = getWidth();
        int panelHeight = getHeight();
        int imageWidth  = image[dispNo].getWidth(this);
        int imageHeight = image[dispNo].getHeight(this);
        double widthRate  = (double)imageWidth / (double)panelWidth;
        double heightRate = (double)imageHeight / (double)panelHeight;
        
        double rate = 1.0;
        if(widthRate > heightRate){
            rate = widthRate;
        }else{
            rate = heightRate;
        }
        
        if( rate < 1.0 ){
            rate = 1.0;
        }
        g.clearRect(0, 0, panelWidth, panelHeight);
        g.drawImage(image[dispNo], 0, 0, (int)(imageWidth / rate), (int)(imageHeight / rate), this);
        g.drawString(fileName[dispNo], 10, 20);
    }
}

class RandomFile{
    String rootDir;
    File   saveFile;
    
    public RandomFile(String root){
        rootDir = root;
        saveFile = null;
        File file = new File(root);
        if( !file.exists() ){
            rootDir = null;
            throw new RuntimeException("指定されたディレクトリ／ファイルは存在しません。");
        }
    }
    
    public File getFile(){
        if( rootDir == null){
            throw new RuntimeException("ディレクトリが指定されていません。");
        }
        
        File file = new File(rootDir);
        while( file.isDirectory() ){
            String[] list = file.list();
            if( list.length == 0 ){
                return saveFile;
            }
            
            String path = file.getPath();
            ArrayList arrayList = new ArrayList();
            for(int i = 0; i < list.length; i++){
                File f = new File(path + "\\" + list[i]);
                String name = list[i].substring(list[i].length() - 4).toLowerCase();
                if( f.isDirectory() || name.equals(".jpg") ){
                    arrayList.add(list[i]);
                }
            }
            if( arrayList.isEmpty() ){
                return saveFile;
            }
            
            int no = (int)(Math.random() * arrayList.size());
            path = path + "\\" + (String)arrayList.get(no);
            file = new File(path);
        }
        saveFile = file;
        return file;
    }
}

class JpegPanelTimer extends JpegPanel implements Runnable{
    String         dir;
    RandomFile     randomFile;
    int            time;
    
    public JpegPanelTimer(int time){
//        dir = "D:\\airplane\\photo\\";
        dir = "C:\\Documents and Settings\\All Users\\Documents\\My Pictures\\Sample Pictures\\";
//        dir = "C:\\Documents and Settings\\yamada-family\\デスクトップ\\";
//        dir = "Z:\\Documents and Settings\\yamada-family\\My Documents\\写真\\1.デジカメ撮影\\";
        randomFile = new RandomFile(dir);
        setFile(randomFile.getFile());
        
        this.time = time;
        Thread t = new Thread(this);
        t.start();
    }
    
    public void run(){
        while(true){
            changeNo();
            File f = randomFile.getFile();
            setFile(f);
            try{
                Thread.sleep(1000 * time);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
