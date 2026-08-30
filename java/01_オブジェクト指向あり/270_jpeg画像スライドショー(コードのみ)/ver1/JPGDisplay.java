import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class JPGDisplay implements RandomJpegUser{
    private JFrame          frame;
    private JpegPanel       jpgPanel;
    private SelectJpegTimer selectJpeg;
    
    public JPGDisplay(){
        frame = new JFrame("JPG Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        jpgPanel = new JpegPanel();
        frame.add(jpgPanel, BorderLayout.CENTER);
        
        selectJpeg = new SelectJpegTimer(15, this);
        
        frame.setSize(1024, 768);
        frame.setVisible(true);
    }
    
    public void setFile(File f){
        jpgPanel.setFile(f);
        jpgPanel.repaint();
    }
    
    public static void main(String[] args){
        JPGDisplay jd = new JPGDisplay();
    }
}

class JpegPanel extends JPanel{
    private File file;
    
    public void setFile(File f){
        file = f;
    }
    
    public void paint(Graphics g){
        if( file == null ){
            return;
        }
        String fileName = file.getPath();
        
        Toolkit toolkit     = Toolkit.getDefaultToolkit();
        Image   image       = toolkit.getImage(fileName);
        int     imageWidth  = image.getWidth(this);
        int     imageHeight = image.getHeight(this);
        int     panelWidth  = getWidth();
        int     panelHeight = getHeight();
        
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
        g.drawImage(image, 0, 0, (int)(imageWidth / rate), (int)(imageHeight / rate), this);
        g.drawString(fileName, 10, 20);
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

class SelectJpegTimer extends Thread{
    String         dir;
    RandomFile     randomFile;
    RandomJpegUser client;
    int            timer;
    
    public SelectJpegTimer(int time, RandomJpegUser rju){
//        dir = "D:\\airplane\\photo\\";
        dir = "C:\\Documents and Settings\\All Users\\Documents\\My Pictures\\Sample Pictures\\";
//        dir = "C:\\Documents and Settings\\yamada-family\\デスクトップ\\";
//        dir = "Z:\\Documents and Settings\\yamada-family\\My Documents\\写真\\1.デジカメ撮影\\";
        client = rju;
        timer = time;
        randomFile = new RandomFile(dir);
        start();
    }
    
    public void run(){
        while(true){
            File f = randomFile.getFile();
            client.setFile(f);
            try{
                Thread.sleep(1000 * timer);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

interface RandomJpegUser{
    void setFile(File f);
}
