import java.awt.event.*;
import javax.swing.*;
import java.awt.*;


public class paint extends JFrame implements ActionListener {

	JButton b1,b2,b3,b4;
	JLabel lbl1, lbl2, lbl3, lbl4, l1, l2, l3, l5, l6;
	JTextField t1, t2, t3, t4, t01, t02, t03, t12, t13;
	JPanel p1, p2;

	public paint() {  //Frame大体构造
		super();
		this.setTitle("基本图元绘制");
		this.setLayout(new BorderLayout());
		b1 = new JButton("DDA绘制");  //增加按钮并添加响应事件
		b1.addActionListener(this);
		b2 = new JButton("Bre绘制");
		b2.addActionListener(this);
		b3 = new JButton("中点圆绘制");
		b3.addActionListener(this);
		b4 = new JButton("中点椭圆绘制");
		b4.addActionListener(this);
		l1=new JLabel("请输入圆的半径：");  //添加Label提示输入
		l2=new JLabel("请输入圆/椭圆心横坐标：");
		l3=new JLabel("请输入圆/椭圆心纵坐标：");
		l5=new JLabel("请输入椭圆的a值：");
		l6=new JLabel("请输入椭圆的b值：");
		t01=new JTextField(3);  //TextField保存输入信息
		t02=new JTextField(3);
		t03=new JTextField(3);
		t12=new JTextField(3);
		t13=new JTextField(3);
		lbl1 = new JLabel("输入起点横坐标:");  //添加Label提示输入
		lbl2 = new JLabel("输入起点纵坐标:");
		lbl3 = new JLabel("输入终点横坐标:");
		lbl4 = new JLabel("输入终点纵坐标:");
		t1 = new JTextField(3);   //TextField保存输入信息
		t2 = new JTextField(3);
		t3 = new JTextField(3);
		t4 = new JTextField(3);
		p1 = new JPanel();
		p1.setLayout(new GridLayout(4, 6));  //Panel p1设置GridLayout布局4行*6列
		p1.add(lbl1);               //加入这些控件进入Panel p1
		p1.add(t1);
		p1.add(lbl2);
		p1.add(t2);
		p1.add(new JLabel());
		p1.add(new JLabel());
		p1.add(lbl3);
		p1.add(t3);
		p1.add(lbl4);
		p1.add(t4);
		p1.add(new JLabel());
		p1.add(new JLabel());
		p1.add(l1);
		p1.add(t01);
		p1.add(l2);
		p1.add(t02);
		p1.add(l3);
		p1.add(t03);
		p1.add(new JLabel());
		p1.add(new JLabel());
		p1.add(l5);
		p1.add(t12);
		p1.add(l6);
		p1.add(t13);
		this.add(p1, BorderLayout.SOUTH);  //将Panel p1加入Frame的南部（SOUTH）

		p2 = new JPanel();
		p2.setLayout(new GridLayout(4, 1));  //Panel p2设置GridLayout布局4行*1列
		p2.add(b1);                          //加入这些控件进入Panel p2
		p2.add(b2);
		p2.add(b3);
		p2.add(b4);
		this.add(p2, BorderLayout.EAST);  //将Panel p1加入Frame的东部（EAST）
		this.setBackground(Color.WHITE);  //设置Frame北京为白色
	}

 

	public void DDAdrawline(Graphics g) {  //DDA算法画直线，并且实现任意斜率

		/*
		 * 700*800区域画线形成70*80的表格
		 */
		for(int i=0;i<700;i+=10)    
		{
			g.drawLine(0, i, 800, i);
		}
		for(int i=0;i<800;i+=10)
		{
			g.drawLine(i, 0, i, 700);
		}
		/*
		 * 获取开始点的x，y坐标和终止点的x，y坐标
		 */
		int x0, x1, y0, y1;
		String s1, s2, s3, s4;
		float dx, dy, k, x,y;
		s1 = t1.getText();
		s2 = t2.getText();
		s3 = t3.getText();
		s4 = t4.getText();
		x0 = Integer.parseInt(s1);
		y0 = Integer.parseInt(s2);
		x1 = Integer.parseInt(s3);
		y1 = Integer.parseInt(s4);
		x0=x0*10;    //输入的坐标值乘10（坐标格子的单位长度是10）
		y0=y0*10;
		x1=x1*10;
		y1=y1*10;
		int op=0;
		if((x1-x0) >= 0 && (y1-y0) >= 0)           //根据开始点和终止点的坐标判断直线画线的方向
			op=1;
		else if((x1-x0) <= 0 && (y1-y0) >= 0)
			op=2;
		else if((x1-x0) <= 0 && (y1-y0) <= 0)
			op=3;
		else if((x1-x0) >= 0 && (y1-y0) <= 0)
			op=4;
		dy = Math.abs(y1 - y0);        //取两点的x变化和y变化的绝对值
		dx = Math.abs(x1 - x0);
		if (dx != 0) {              //保证计算斜率时分母不为0
			k = dy / dx;
			if(k<1){               //斜率小于1时
				y = y0;
				/*
				 * 根据op分出四个方向，分别进行讨论
				 */
				if(op==1)
				{
					for (int xx = x0; xx <= x1; xx+=10) {   //x坐标每次加10单位
						//g.drawString(".", xx, (int) (y));
						if(y%10>=5)//对浮点数y进行四舍五入
						{
							g.fillRect(xx, (int)(y-y%10)+10, 10, 10);
						}
						else
						g.fillRect(xx, (int)(y-y%10), 10, 10);
						y = y + k*10;                      //y每次加 k*10单位
						try
						{
							Thread.currentThread().sleep(100);  //创建线程休眠0.1s
						}
						catch(Exception e)
						{
							
						}
					}
				}
				if(op==2)
				{
					for (int xx = x0; xx >= x1; xx-=10) {
						//g.drawString(".", xx, (int) (y));
						if(y%10>=5)
						{
							g.fillRect(xx, (int)(y-y%10)+10, 10, 10);
						}
						else
						g.fillRect(xx, (int)(y-y%10), 10, 10);
						y = y + k*10;
						try
						{
							Thread.currentThread().sleep(100);
						}
						catch(Exception e)
						{
							
						}
					}
				}
				if(op==3)
				{
					for (int xx = x0; xx >= x1; xx-=10) {
						//g.drawString(".", xx, (int) (y));
						if(y%10>=5)
						{
							g.fillRect(xx, (int)(y-y%10)+10, 10, 10);
						}
						else
						g.fillRect(xx, (int)(y-y%10), 10, 10);
						y = y - k*10;
						try
						{
							Thread.currentThread().sleep(100);
						}
						catch(Exception e)
						{
							
						}
					}
				}
				if(op==4)
				{
					for (int xx = x0; xx <= x1; xx+=10) {
						//g.drawString(".", xx, (int) (y));
						if(y%10>=5)
						{
							g.fillRect(xx, (int)(y-y%10)+10, 10, 10);
						}
						else
						g.fillRect(xx, (int)(y-y%10), 10, 10);
						y = y - k*10;
						try
						{
							Thread.currentThread().sleep(100);
						}
						catch(Exception e)
						{
							
						}
					}
				}
				
			}
			else{  //斜率大于1时
				x = x0;
				if(op==1)
				{
					for(int yy=y0;yy<=y1;yy+=10){  //y坐标每次加10单位
						//g.drawString(".", (int)(x),  yy);
						if(x%10>=5)  //对浮点数x进行四舍五入
						{
							g.fillRect((int) (x-x%10)+10, yy , 10, 10);
						}
						else
						g.fillRect((int) (x-x%10), yy , 10, 10);
						x = x + (dx/dy)*10;
						try
						{
							Thread.currentThread().sleep(100); //同上
						}
						catch(Exception e)
						{
							
						}
					}
				}
				if(op==2)
				{
					for(int yy=y0;yy<=y1;yy+=10){
						//g.drawString(".", (int)(x),  yy);
						if(x%10>=5)
						{
							g.fillRect((int) (x-x%10)+10, yy , 10, 10);
						}
						else
						g.fillRect((int) (x-x%10), yy , 10, 10);
						x = x - (dx/dy)*10;
						try
						{
							Thread.currentThread().sleep(100);
						}
						catch(Exception e)
						{
							
						}
					}
				}
				if(op==3)
				{
					for(int yy=y0;yy>=y1;yy-=10){
						//g.drawString(".", (int)(x),  yy);
						if(x%10>=5)
						{
							g.fillRect((int) (x-x%10)+10, yy , 10, 10);
						}
						else
						g.fillRect((int) (x-x%10), yy , 10, 10);
						x = x - (dx/dy)*10;
						try
						{
							Thread.currentThread().sleep(100);
						}
						catch(Exception e)
						{
							
						}
					}
				}
				if(op==4)
				{
					for(int yy=y0;yy>=y1;yy-=10){
						//g.drawString(".", (int)(x),  yy);
						if(x%10>=5)
						{
							g.fillRect((int) (x-x%10)+10, yy , 10, 10);
						}
						else
						g.fillRect((int) (x-x%10), yy , 10, 10);
						x = x + (dx/dy)*10;
						try
						{
							Thread.currentThread().sleep(100);
						}
						catch(Exception e)
						{
							
						}
					}
				}
				
			}
		} 
		else {  //dx等于0时，x不动，y上升或者下降
			for (int i = y0; i <= y1; i+=10) {
				//g.drawString(".", x0, i);
				g.fillRect(x0, i, 10, 10);
			}
		}
	}
	
	public void Bresenhamdrawline(Graphics g) {  //Bresenham算法实现任意斜率画线

		/*
		 * 画出坐标系表格
		 */
		for(int i=0;i<700;i+=10)  
		{
			g.drawLine(0, i, 800, i);
		}
		for(int i=0;i<800;i+=10)
		{
			g.drawLine(i, 0, i, 700);
		}
		
		/*
		 * 获取起点坐标以及终点坐标进行画线
		 */
		int x0, x1, y0, y1;
		String s1, s2, s3, s4;
		float dx, dy, k, x,y;
		s1 = t1.getText();
		s2 = t2.getText();
		s3 = t3.getText();
		s4 = t4.getText();
		x0 = Integer.parseInt(s1);
		y0 = Integer.parseInt(s2);
		x1 = Integer.parseInt(s3);
		y1 = Integer.parseInt(s4);
		x0=x0*10;  //乘以表格长10
		y0=y0*10;
		x1=x1*10;
		y1=y1*10;
		int op=0;
		if((x1-x0) >= 0 && (y1-y0) >= 0)      //分四个方向画线
			op=1;
		else if((x1-x0) <= 0 && (y1-y0) >= 0)
			op=2;
		else if((x1-x0) <= 0 && (y1-y0) <= 0)
			op=3;
		else if((x1-x0) >= 0 && (y1-y0) <= 0)
			op=4;
		dy = Math.abs(y1 - y0);  //dx与dy取绝对值
		dx = Math.abs(x1 - x0);
		float p,pa,pb;
		if (dx != 0) {  //同上进行dx不是0和是0的讨论
			k = dy / dx;
			if(k<1){  //斜率小于1，同时进行p参数的设定
				pa=2*dy;
				pb=2*dy - 2*dx;
				p=2*dy - dx;
				y=y0;
				g.fillRect(x0, y0, 10, 10); //首先填充起点
				if(op==1)  //根据op分不同方向画线讨论
				{
					for(int xx = x0+10; xx <= x1; xx += 10)  //以x递进画线
					{
						if(p>0)             //当参数p大于0，x和，y都增加一单元格（10）
						{
							p+=pb;          //p大于0时，p加2*dy - 2*dx，否则，加2*dy
							y+=10;
							g.fillRect(xx, (int)(y), 10, 10);
							try
							{
								Thread.currentThread().sleep(100);
							}
							catch(Exception e)
							{
								
							}
						}                 
						else               //当参数p小于等于0，x增加一单元格（10），而y不变
						{
							p+=pa;
							g.fillRect(xx, (int)(y), 10, 10);
							try
							{
								Thread.currentThread().sleep(100);  //同上睡眠
							}
							catch(Exception e)
							{
								
							}
						}
					}
				}
				if(op==2)
				{
					for(int xx = x0-10; xx >= x1; xx -= 10)
					{
						if(p>0)
						{
							p+=pb;
							y+=10;
							g.fillRect(xx, (int)(y), 10, 10);
							try
							{
								Thread.currentThread().sleep(100);
							}
							catch(Exception e)
							{
								
							}
						}
						else
						{
							p+=pa;
							g.fillRect(xx, (int)(y), 10, 10);
							try
							{
								Thread.currentThread().sleep(100);
							}
							catch(Exception e)
							{
								
							}
						}
					}
				}
				if(op==3)
				{
					for(int xx = x0-10; xx >= x1; xx -= 10)
					{
						if(p>0)
						{
							p+=pb;
							y-=10;
							g.fillRect(xx, (int)(y), 10, 10);
							try
							{
								Thread.currentThread().sleep(100);
							}
							catch(Exception e)
							{
								
							}
						}
						else
						{
							p+=pa;
							g.fillRect(xx, (int)(y), 10, 10);
							try
							{
								Thread.currentThread().sleep(100);
							}
							catch(Exception e)
							{
								
							}
						}
					}
				}
				if(op==4)
				{
					for(int xx = x0+10; xx <= x1; xx += 10)
					{
						if(p>0)
						{
							p+=pb;
							y-=10;
							g.fillRect(xx, (int)(y), 10, 10);
							try
							{
								Thread.currentThread().sleep(100);
							}
							catch(Exception e)
							{
								
							}
						}
						else
						{
							p+=pa;
							g.fillRect(xx, (int)(y), 10, 10);
							try
							{
								Thread.currentThread().sleep(100);
							}
							catch(Exception e)
							{
								
							}
						}
					}
				}
			}
			else{      //斜率大于等于1时，p参数的设定以及pa，pb参数设定发生变化
				pa=2*dx;
				pb=2*dx - 2*dy;
				p=2*dx - dy;
				x=x0;
				g.fillRect(x0, y0, 10, 10);
				if(op==1)           //根据op的值判断画线方向
				{
					for(int yy = y0+10; yy <= y1; yy += 10) //斜率大于1，故以y递进单位格
					{
						if(p>0)  //p参数大于0时，x，y都加一单位格
						{
							p+=pb;  //若参数p大于0，加上2*dx - 2*dy，否则，加2*dx
							x+=10;
							g.fillRect((int)(x), yy, 10, 10);
							try
							{
								Thread.currentThread().sleep(100);
							}
							catch(Exception e)
							{
							
							}
						}
						else  //p参数小于等0时，y加一单位格，x不变
						{
							p+=pa;
							g.fillRect((int)(x), yy, 10, 10);
							try
							{
								Thread.currentThread().sleep(100);
							}
							catch(Exception e)
							{
								
							}
						}
					}
				}
				if(op==2)
				{
					for(int yy = y0+10; yy <= y1; yy += 10)
					{
						if(p>0)
						{
							p+=pb;
							x-=10;
							g.fillRect((int)(x), yy, 10, 10);
							try
							{
								Thread.currentThread().sleep(100);
							}
							catch(Exception e)
							{
								
							}
						}
						else
						{
							p+=pa;
							g.fillRect((int)(x), yy, 10, 10);
							try
							{
								Thread.currentThread().sleep(100);
							}
							catch(Exception e)
							{
								
							}
						}
					}
				}
				if(op==3)
				{
					for(int yy = y0-10; yy >= y1; yy -= 10)
					{
						if(p>0)
						{
							p+=pb;
							x-=10;
							g.fillRect((int)(x), yy, 10, 10);
							try
							{
								Thread.currentThread().sleep(100);
							}
							catch(Exception e)
							{
								
							}
						}
						else
						{
							p+=pa;
							g.fillRect((int)(x), yy, 10, 10);
							try
							{
								Thread.currentThread().sleep(100);
							}
							catch(Exception e)
							{
								
							}
						}
					}
				}
				if(op==4)
				{
					for(int yy = y0-10; yy >= y1; yy -= 10)
					{
						if(p>0)
						{
							p+=pb;
							x+=10;
							g.fillRect((int)(x), yy, 10, 10);
							try
							{
								Thread.currentThread().sleep(100);
							}
							catch(Exception e)
							{
								
							}
						}
						else
						{
							p+=pa;
							g.fillRect((int)(x), yy, 10, 10);
							try
							{
								Thread.currentThread().sleep(100);
							}
							catch(Exception e)
							{
								
							}
						}
					}
				}
				
				
			}
		} 
		else {  //当dx等于0，仅变化y即可
			for (int i = y0; i <= y1; i+=10)
				g.fillRect(x0, i, 10, 10);
		}
	}

	public void cirpaint(Graphics g)  //中点法画圆
	{
		/*
		 * 画坐标表格
		 */
		for(int i=0;i<500;i+=10)
		{
			g.drawLine(0, i, 500, i);
		}
		for(int i=0;i<500;i+=10)
		{
			g.drawLine(i, 0, i, 500);
		}
		/*
		 * 获取半径r和中点坐标x和y值
		 */
		int x, y, r ,x0, y0;
		String sx,sy,sr;
		sr=t01.getText();
		r=Integer.parseInt(sr);
		sx=t02.getText();
		x=Integer.parseInt(sx);
		sy=t03.getText();
		y=Integer.parseInt(sy);
		x=x*10;  //乘单位格长
		y=y*10;
		r=r*10;
		
		double p=5.0/4.0-r/10;  //设置初始决策参数p值
		x0=0;
		y0=r/10;  //假设圆心在（0，0），画圆起始点为（0，r）
		/*
		 * 画八分之一圆，并且通过圆的八对称画出整圆
		 */
		while(x0<=y0)
		{
			/*
			 * 一下八个fillRect即画八个部分相互对称的八分圆
			 */
			g.fillRect(x0*10+x, y0*10+y, 10, 10);  //画出圆心为（0，0）的弧线后根据实际圆圆心平移
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			g.fillRect(y0*10+x, x0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			g.fillRect(-x0*10+x, y0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			g.fillRect(-x0*10+x, y0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			g.fillRect(y0*10+x, -x0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			g.fillRect(x0*10+x, -y0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			g.fillRect(-x0*10+x, -y0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			g.fillRect(-y0*10+x, -x0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			g.fillRect(-y0*10+x, x0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			g.fillRect(-x0*10+x, y0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			/*
			 * 中点法画圆推导的决策参数p的变化：
			 * 若p大于0，p加2*(x0+1)+1，y不变；
			 * 否则，p加2*(x0+1)-2*(y0)+1，并且y0减1
			 */
			if(p<0)
			{
				p+=2*(x0+1)+1;
			}
			else
			{
				y0=y0-1;
				p+=2*(x0+1)-2*(y0)+1;
			}
			x0++;  //第一个八分之一圆的切线斜率均小于1，故始终以x0递进即可
			
		}
		
		
		
	}
	
	public void cirtpaint(Graphics g)  //中点法画椭圆
	{
		/*
		 * 画出坐标格（50*50）（单位格长10）
		 */
		for(int i=0;i<500;i+=10)
		{
			g.drawLine(0, i, 500, i);
		}
		for(int i=0;i<500;i+=10)
		{
			g.drawLine(i, 0, i, 500);
		}
		/*
		 * 获取椭圆的长轴a，短轴b值，以及圆心坐标x，和y值
		 */
		int x, y, x0, y0, a, b;
		String sx,sy,sr,sa,sb;
		sx=t02.getText();
		x=Integer.parseInt(sx);
		sy=t03.getText();
		y=Integer.parseInt(sy);
		sa=t12.getText();
		a=Integer.parseInt(sa);
		sb=t13.getText();
		b=Integer.parseInt(sb);
		x=x*10;  //乘单位格长10
		y=y*10;
		a=a*10;
		b=b*10;
		
		double p=(b/10)*(b/10)-(a/10)*(a/10)*(b/10)+(a/10)*(a/10)/4.0;//设置决策参数初始值
		x0=0;
		y0=b/10;  //设置初始画点位置（0，b），并且圆心假设在（0，0）
		/*
		 * 画四分之一椭圆切线斜率小于等于1的一部分弧线
		 */
		while((b/10)*(b/10)*x0<=(a/10)*(a/10)*y0)  //判断切线斜率小于等于1，则执行循环
		{
			/*
			 * 以下四个fillRect分别画四分之一椭圆切线斜率绝对值小于1的一部分弧线（根据椭圆的四对称性）
			 */
			g.fillRect(x0*10+x, y0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			g.fillRect(x0*10+x, -y0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			g.fillRect(-x0*10+x, -y0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			g.fillRect(-x0*10+x, y0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			/*
			 * 根据中点画椭圆算法推导决策参数p的变化：
			 * 当p小于0，x0加1，y0不变，p加2*(b/10)*(b/10)*x0+(b/10)*(b/10)
			 * 否则，x0加1，y0减1，p加2*(b/10)*(b/10)*x0+(b/10)*(b/10)-2*(a/10)*(a/10)*y0
			 */
			if(p<0)
			{
				x0++;         //切线斜率小于1，始终以x0递进
				p+=2*(b/10)*(b/10)*x0+(b/10)*(b/10);
			}
			else
			{
				x0++;
				y0--;
				p+=2*(b/10)*(b/10)*x0+(b/10)*(b/10)-2*(a/10)*(a/10)*y0;
			}
			
		}
		p=(b/10)*(b/10)*(x0+0.5)*(x0+0.5)+(a/10)*(a/10)*(y0-1)-(b/10)*(b/10)*(a/10)*(a/10); //开始点即上一段弧线的最后一个点（x0，y0），并设置初始参数p
		/*
		 * 画四分之一椭圆切线斜率大于等于1的一部分弧线
		 */
		while(y0>=0)
		{
			/*
			 * 以下四个fillRect分别画四分之一椭圆切线斜率绝对值大于等于1的一部分弧线（根据椭圆的四对称性）
			 */
			g.fillRect(x0*10+x, y0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			g.fillRect(x0*10+x, -y0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			g.fillRect(-x0*10+x, -y0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			g.fillRect(-x0*10+x, y0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			/*
			 * 根据中点画椭圆算法推导决策参数p的变化：
			 * 当p小于0，x0加1，y0减1，p加2*(b/10)*(b/10)*x0+(a/10)*(a/10)-2*(a/10)*(a/10)*y0
			 * 否则，x0不变，y0减1，p加-2*(a/10)*(a/10)*y0+(a/10)*(a/10)
			 */
			if(p<0)
			{
				y0--;  //斜率大于1，始终y0递进
				x0++;
				p+=2*(b/10)*(b/10)*x0+(a/10)*(a/10)-2*(a/10)*(a/10)*y0;
				
			}
			else
			{
				y0--;
				p+=-2*(a/10)*(a/10)*y0+(a/10)*(a/10);
			}
		}
		
		
		
		
	}
	
	public void actionPerformed(ActionEvent e) {  //四个绘制按钮点击事件
		if (e.getSource()==b1) {            //DDA绘制，调用DDAdrawline(g)实现DDA画线
			Graphics g = this.getGraphics();
			g.clearRect(0, 0, 800, 700);
			DDAdrawline(g);
			
		}
		if (e.getSource()==b2) {          //Bresenham绘制，调用Bresenhamdrawline(g)实现Bresenham绘制
			Graphics g = this.getGraphics();
			g.clearRect(0, 0, 800, 700);
			Bresenhamdrawline(g);
			
		}
		if (e.getSource()==b3) {          //中点画圆，调用cirpaint(g)实现画圆
			Graphics g = this.getGraphics();
			g.clearRect(0, 0, 800, 700);
			cirpaint(g);
			
		}
		if (e.getSource()==b4) {         //中点画椭圆，调用cirtpaint(g)实现画椭圆
			Graphics g = this.getGraphics();
			g.clearRect(0, 0, 800, 700);
			cirtpaint(g);
			
		}
	}
 

	public static void main(String[] args) {

		paint frame = new paint();         //创建paint类对象
		frame.setSize(1000, 800);           //设置Frame大小，位置，以及视图可见等
		frame.setLocation(400, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}