import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class JPGDisplay extends JFrame{
    private JpegPanelTimerDispFile jpgPanel;
    
    public JPGDisplay(){
        super("JPG Display");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
//        String dir = "C:\\Documents and Settings\\All Users\\Documents\\My Pictures\\Sample Pictures\\";
        String dir = "C:\\";
        try{
            FileReader f = new FileReader("dir.txt");
            BufferedReader br = new BufferedReader(f);
            String str;
            while( (str = br.readLine()) != null){
                if(str.length() == 0){
                    continue;
                }else if(str.length() >= 2){
                    if(str.substring(0, 2).equals("//")){
                        continue;
                    }
                }
                dir = str;
                break;
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        
        jpgPanel = new JpegPanelTimerDispFile(15, dir);
        add(jpgPanel, BorderLayout.CENTER);
        
        setSize(1024, 768);
        setVisible(true);
    }
    
    public static void main(String[] args){
        JPGDisplay jd = new JPGDisplay();
    }
}

class JpegPanelTimerDispFile extends JPanel implements JpegPanelListener{
    JpegPanelTimer jpegPanel;
    JLabel         lFileName;
    
    public JpegPanelTimerDispFile(int time, String dir){
        lFileName = new JLabel(" ");
        lFileName.setBackground(Color.BLACK);
        lFileName.setForeground(Color.WHITE);
        lFileName.setOpaque(true);
        jpegPanel = new JpegPanelTimer(time, dir, this);
        
        setLayout(new BorderLayout());
        add(lFileName, BorderLayout.NORTH);
        add(jpegPanel, BorderLayout.CENTER);
    }
    
    public void pushFileName(String name){
        lFileName.setText(name);
    }
}

class JpegPanelTimer extends JpegPanel implements Runnable{
    private RandomFile randomFile;
    private int        time;        //インターバル(秒)
    private boolean    bLoop;       //継続ON・OFF
    
    public JpegPanelTimer(int time, String dir, JpegPanelListener jpl){
        super(jpl);
        randomFile = new RandomFile(dir);
        setFile(randomFile.getFile());
        
        bLoop = true;
        this.time = time;
        Thread t = new Thread(this);
        t.start();
    }
    
    public JpegPanelTimer(int time, String dir){
        this(time, dir, null);
    }
    
    public void run(){
        while(bLoop){
            changeDisp();
            setFile(randomFile.getFile());
            try{
                Thread.sleep(1000 * time);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    
    public void stop(){
        bLoop = false;
    }
}

interface JpegPanelListener{
    void pushFileName(String name);
}

class JpegPanel extends JPanel{
    private Image[]           image;
    private String[]          fileName;
    private int               dispNo;
    private JpegPanelListener listener;
    
    public JpegPanel(JpegPanelListener jpl){
        image = new Image[2];
        fileName = new String[2];
        dispNo = 0;
        listener = jpl;
        setBackground(Color.BLACK);
        setOpaque(true);
    }
    
    public JpegPanel(){
        this(null);
    }
    
    public void setFile(File f){
        int no = (dispNo + 1) % 2;
        
        fileName[no] = f.getPath();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        image[no] = toolkit.getImage(fileName[no]);
        
System.out.println("File Set to " + no + " --> " + fileName[no].substring(fileName[no].length() - 12));
    }
    
    public void changeDisp(){
        dispNo = (dispNo + 1) % 2;
System.out.println("Change Disp to " + dispNo + " --> " + fileName[dispNo].substring(fileName[dispNo].length() - 12));
        repaint();
    }
    
    public String getDispFileName(){
        return fileName[dispNo];
    }
    
    public void paint(Graphics g){
        int panelWidth  = getWidth();
        int panelHeight = getHeight();
        
        if( image[dispNo] == null ){
            g.fillRect(0, 0, panelWidth, panelHeight);
            return;
        }
        
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
        
        imageWidth = (int)(imageWidth / rate);
        imageHeight = (int)(imageHeight / rate);
        int x = (panelWidth - imageWidth) / 2;
        int y = (panelHeight - imageHeight) / 2;
        
        Image offscr = createImage(panelWidth, panelHeight);
        Graphics offgr = offscr.getGraphics();
        
        offgr.fillRect(0, 0, panelWidth, panelHeight);
        boolean disp = offgr.drawImage(image[dispNo], x, y, imageWidth, imageHeight, this);
        if(disp){
            g.drawImage(offscr, 0, 0, this);
        }
    }
    
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height){
        if(img == image[dispNo]){
            if((infoflags & ALLBITS) != 0){
System.out.println("描画終了");
                if(listener != null){
                    listener.pushFileName(fileName[dispNo]);
                }
            }
        }
        return super.imageUpdate(img, infoflags, x, y, width, height);
    }
}

class RandomFile{
    private String rootDir;
    private File   saveFile;      //前回のgetFile()で探し当てたJpegファイル
    private final int LOOP = 50;  //最大探索ループ回数
    
    public RandomFile(String root){
        rootDir = null;
        File file = new File(root);
        if( !file.exists() ){
            throw new RuntimeException("指定されたディレクトリは存在しません。");
        }else if( !file.isDirectory() ){
            throw new RuntimeException("ディレクトリを指定してください。");
        }
        rootDir = root;
        saveFile = null;
    }
    
    public File getFile(){
        if( rootDir == null){
            throw new RuntimeException("ディレクトリが指定されていません。");
        }
        
        int count = 0;
        File file = new File(rootDir);
        while( file.isDirectory() ){
            String[] list = file.list();
            String path = file.getPath();
            ArrayList arrayList = new ArrayList();
            if(list != null){
                for(int i = 0; i < list.length; i++){
                    File f = new File(path + "\\" + list[i]);
                    String name;
                    try{
                        name = list[i].substring(list[i].length() - 4).toLowerCase();
                    }catch(IndexOutOfBoundsException e){
                        //e.printStackTrace();
                        name = "";
                    }
                    if( f.isDirectory() || name.equals(".jpg") ){
                        arrayList.add(list[i]);
                    }
                }
            }
            if( arrayList.isEmpty() ){
                if(count++ > LOOP){
                    if(saveFile != null){
                        return saveFile;
                    }else{
                        throw new RuntimeException("Jpegファイルが見つかりませんでした。");
                    }
                }else{
                    file = new File(rootDir);
                }
            }else{
                int no = (int)(Math.random() * arrayList.size());
                path = path + "\\" + (String)arrayList.get(no);
                file = new File(path);
            }
        }
        saveFile = file;
        return file;
    }
}
