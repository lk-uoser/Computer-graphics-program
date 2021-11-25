package Scanner;

public class Node {        //ÐÂ±à±í¼ÇÂ¼x,dx,yMax
    public int x;
    public float dx;
    public int yMax;
    public Node next;
    public int ymin;
    public Node(int x, int dx, int yMax){
        this.x=x;
        this.dx=dx;
        this.yMax=yMax;
        
    }
    public void getYmin(int Ymin){
        this.ymin=Ymin;
        
    }

}
