import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class JPGDisplay extends JFrame implements JpegPanelListener{
    private JpegPanelTimer jpgPanel;
    
    public JPGDisplay(String confFile){
        super("JPG Display");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        if(confFile == null){
            confFile = "dir.txt";
        }
        
        String dir = "C:\\";
        int    time = 15;
        try{
            FileReader f = new FileReader(confFile);
            BufferedReader br = new BufferedReader(f);
            String str;
            while( (str = br.readLine()) != null){
                if(str.length() >= 4){
                    if(str.substring(0, 4).equals("dir ")){
                        dir = str.substring(4, str.length());
                    }
                }
                if(str.length() >= 5){
                    if(str.substring(0, 5).equals("time ")){
                        time = Integer.valueOf(str.substring(5, str.length()));
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        
        jpgPanel = new JpegPanelTimer(time, dir, this);
        add(jpgPanel, BorderLayout.CENTER);
        
        setSize(1024, 768);
        setVisible(true);
    }
    
    public JPGDisplay(){
        this(null);
    }
    
    public void pushFileName(String name){
        setTitle("JPG Display : " + name);
    }
    
    public static void main(String[] args){
        JPGDisplay jd;
        if(args.length == 0){
            jd = new JPGDisplay();
        }else{
            jd = new JPGDisplay(args[0]);
        }
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
            try{
                Thread.sleep(1000 * time);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            setFile(randomFile.getFile());
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
    private Image             image;
    private String            fileName;
    private JpegPanelListener listener;
    private boolean           firstDisp;
    
    public JpegPanel(JpegPanelListener jpl){
        image = null;
        fileName = null;
        listener = jpl;
        firstDisp = true;
        setBackground(Color.BLACK);
        setOpaque(true);
    }
    
    public JpegPanel(){
        this(null);
    }
    
    public void setFile(File f){
        fileName = f.getPath();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        image = toolkit.getImage(fileName);
        
        prepareImage(image, this);
        
System.out.println("File Set --> " + fileName.substring(fileName.length() - 12));
    }
    
    public String getDispFileName(){
        return fileName;
    }
    
    public void paint(Graphics g){
        int panelWidth  = getWidth();
        int panelHeight = getHeight();
        
        if( firstDisp ){
            g.fillRect(0, 0, panelWidth, panelHeight);
            return;
        }
        
        int imageWidth  = image.getWidth(this);
        int imageHeight = image.getHeight(this);
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
        boolean disp = offgr.drawImage(image, x, y, imageWidth, imageHeight, this);
        if(disp){
            g.drawImage(offscr, 0, 0, this);
        }
    }
    
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height){
        if(img == image){
            if((infoflags & ALLBITS) != 0){
System.out.println("描画終了");
                firstDisp = false;
                if(listener != null){
                    listener.pushFileName(fileName);
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
            ArrayList<String> arrayList = new ArrayList<String>();
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
                path = path + "\\" + arrayList.get(no);
                file = new File(path);
            }
        }
        saveFile = file;
        return file;
    }
}
