
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;
import java.io.*;


class Main  implements ActionListener
{
 JFrame f;
 JTabbedPane tabPane;
 JPanel mainPanel,introPanel,gamePanel,playerPassPanel,diePanel; 
 JPanel gameCenter,gameEast,gameWest,gameNorth,gameSouth;
 

 Icon p1,p2,header;

 Icon icon[][]= new Icon[10][10];
 Icon winericon[][]= new Icon[10][10];

 Icon dieIcon;



 JButton b[][]=new JButton[10][10];
 JButton start,restart;
 JButton JBplayer,JBcomputer;  

JLabel die;


 
 Random randomNo;


 int imageFlag;
 int i,j,num;
 int prevIp1,prevJp1;

 int path;
 int p1value,p2value;

 int player,computer;

 int gameover;
 int cimageFlag=0;
 int cnoFlag=0;


 String str;






 int game[][]={							//game sequence pattern
	       {100,99,98,97,96,95,94,93,92,91},
	        {81,82,83,84,85,86,87,88,89,90},
		{80,79,78,77,76,75,74,73,72,71},
		{61,62,63,64,65,66,67,68,69,70},
		{60,59,58,57,56,55,54,53,52,51},
		{41,42,43,44,45,46,47,48,49,50},
		{40,39,38,37,36,35,34,33,32,31},
		{21,22,23,24,25,26,27,28,29,30},
		{20,19,18,17,16,15,14,13,12,11},
		{ 1, 2, 3, 4, 5, 6, 7, 8, 9,10},
	      };
	

 int winer[][]={							//winner sequence pattern
		{ 1, 2, 3, 4, 5, 6, 7, 8, 9,10},
		{11,12,13,14,15,16,17,18,19,20},
		{21,22,23,24,25,26,27,28,29,30},
		{31,32,33,34,35,36,37,38,39,40},
		{41,42,43,44,45,46,47,48,49,50},
		{51,52,53,54,55,56,57,58,59,60},
		{61,62,63,64,65,66,67,68,69,70},
		{71,72,73,74,75,76,77,78,79,80},
		{81,82,83,84,85,86,87,88,89,90},
		{91,92,93,94,95,96,97,98,99,100},
	      };

 Main()
 {

 f= new JFrame("Snakes and Ladders");
 f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
 mainPanel= new JPanel();
 tabPane =new JTabbedPane(JTabbedPane.LEFT);


 f.setLayout(new BorderLayout());


 randomNo= new Random();

 JBplayer= new JButton("player1");
 JBcomputer= new JButton("player2");

 JBplayer.setEnabled(false);
 JBcomputer.setEnabled(false);

 
 JBplayer.addActionListener(this);
 JBcomputer.addActionListener(this); 
 
 die = new JLabel();

game();		//game tab
introduction();	//help tab
  
  
   f.add(tabPane,BorderLayout.CENTER);
 f.setResizable(false);
   f.setSize(750,700);

  f.setVisible(true);
  

}


void introduction()
{

 introPanel= new JPanel();
  
 JPanel main= new JPanel();

       
 JLabel l2=new JLabel("<html><body><Font color='red' Size='14'> Snake and Ladders<br></Font><Font color='green' Size='4'> Welcome <br><br><hr></Font> <Font color='green' Size='6'><b>RULES</b><br></Font><Font color='green'><ul><li>Two players can play this game.<li>At the start of the game, the player's  pieces are placed in the start area.<li>Each of the active players shall have to wait for their turn one by one to roll the dice.<li>The buttons should be moved according to the number appearing on the dice.<li>Whenever the button of one player  reaches the bottom of a ladder then the player can climb up the ladder to the respective position at the top of the ladder.<li>If the button of one player  reaches the square with a snake, the player has to retrace back to the square at the tail of the snake.<li>The game is won when the button of a particular player  reaches the finish point first.</ul><hr></Font><Font color='Blue'Size='6'><i><b>HOW TO PLAY</b></i></Font><Font color='blue'><ul><li>select the player character you desire to play with.<li>Roll the dice by clicking the image of the dice on the center bottom of the window.<li>The button will be moved automatically, when clicked on it, to the desired block as per the number on the dice.<li>Whenever the button of one player reaches the bottom of a ladder then the player can climb up the ladder to the respective position at the top of the ladder.<li>If the button of one player reaches the square with the head of a snake, the player has to retrace back to the square at the tail of the snake.<li>The game is won when the button of a particular player reaches the finish point first.</ul><hr></Font><Font color='red' size='14'>THANK YOU</Font></body></html>");

introPanel.setLayout(new BorderLayout()); 

introPanel.add(l2,BorderLayout.NORTH); 

tabPane.addTab("Help",introPanel);


}




void game()
{
 JLabel gameheader= new JLabel();
 JLabel logo = new JLabel(); 
 JLabel l1= new JLabel(); 
 JLabel l2= new JLabel(); 
 
 restart= new JButton("Restart");
 restart.setEnabled(false);
 restart.addActionListener(this);
 
 start= new JButton("Start");
 start.addActionListener(this);
 
 gamePanel= new JPanel();
 

 gameCenter=new JPanel();
 gameWest= new JPanel();
 gameNorth=new JPanel();
 gameSouth= new JPanel();

  gameCenter.setLayout(new GridLayout(10,10));
//  gameEast= new JPanel(new GridLayout(2,1));

  gameWest.setLayout(new FlowLayout());
 gameNorth=new JPanel(new FlowLayout());
 gameSouth= new JPanel(new FlowLayout());

 gamePanel.setLayout(new BorderLayout());
 
 p1= new ImageIcon("images/p1.gif");        
 p2= new ImageIcon("images/alchase.gif");       

 
 l1.setIcon(p1);
 l2.setIcon(p2);
 
 header=new ImageIcon("images/header.jpg");        
 gameheader.setIcon(header);
  
  for(int i=0;i<10;i++)				//to set images on buttons
	for(int j=0;j<10;j++)
	{
			b[i][j]= new JButton();
        	      
			path=game[i][j];
			str=Integer.toString(path);
			icon[i][j]=new ImageIcon("images/"+str+".jpg");
	        b[i][j].setIcon(icon[i][j]);
                 
	}



for(int i=0;i<10;i++)		//to  set winner images
	for(int j=0;j<10;j++)
	{
			path=winer[i][j];
			str=Integer.toString(path);
			winericon[i][j]=new ImageIcon("images/winer/"+str+".jpg");
           
              
	}


   
 str=Integer.toString(1);		//to set dice at initially one
 dieIcon= new ImageIcon("images/Game dies/"+str+".jpg");
 die.setIcon(dieIcon);
	 
  
 gameSouth.add(l1); 
 gameSouth.add(JBplayer);
 gameSouth.add(die);
 gameSouth.add(JBcomputer); 
 gameSouth.add(l2); 

 gameNorth.add(start);
 gameNorth.add(gameheader);
 gameNorth.add(restart);
  
  for(int i=0;i<10;i++)		//to add buttons on panel
	{
	for(int j=0;j<10;j++)
	{
		gameCenter.add(b[i][j]);

             
                
	}
		//System.out.println();
	}


  
 gamePanel.add(gameCenter,BorderLayout.CENTER);
 gamePanel.add(gameWest,BorderLayout.WEST);
 gamePanel.add(gameNorth,BorderLayout.NORTH);
 gamePanel.add(gameSouth,BorderLayout.SOUTH);

  tabPane.addTab("Game",gamePanel);
  
}



void chance()				//to see who has the next chance to roll the dice
{
 int n=randomNo.nextInt(3);
 int i=0;

 //System.out.println("Chacne is :"+n) ;
 if(n==0)
	chance();
 
 if(n==1)
	{
	player=1;	
	computer=0;
	}
  else if(n==2)
	{
	 player=0;
 	 computer=1;
	}

 
    

  if(n==1)
	{
	JBcomputer.setEnabled(false);
	JBplayer.setEnabled(true);
    
	}
 else if(n==2)
	{
	JBcomputer.setEnabled(true);
	JBplayer.setEnabled(false);
    
 	}

 
  
}






public void actionPerformed(ActionEvent e)			//action of buttons
{
 
 int n=0;
try{
 if(e.getSource()==JBplayer)
	{
 
  //System.out.println("JB Player");
		do{
		n =playerPassNumber();
		}while(n==0);

		//System.out.println("UR playerPass value is :"+n);	        
		p1value=p1value+n;
         
		 if(p1value>=100)
		 {
			p1value=100;
		 }
		 
		playerimageTraval(p1value);		
		
		JBcomputer.setEnabled(true);
		JBplayer.setEnabled(false);
		setBothImage();
 	}
  else if(e.getSource()==JBcomputer)
	{
	//System.out.println("JB Computer");
		do{
		n =playerPassNumber();
		}while(n==0);
	
		//System.out.println("Computer Pass Value is "+n);	
		p2value=p2value+n;
		if(p2value>=100)
		{
		 p2value=100;
		}
		computerimageTraval(p2value);
		JBcomputer.setEnabled(false);
		JBplayer.setEnabled(true);
	
		setBothImage();	
	}
  else if(e.getSource()==restart)
	{
  	 p1value=0;
	 p2value=0;
	
	cnoFlag=0;
	cimageFlag=0;

	 chance();
	 rePrint();
	}
  else if(e.getSource()==start)
	{
		gameover=0;
		p1value=0;
		p2value=0;
		cnoFlag=0;
	cimageFlag=0;
		rePrint();
	  start.setEnabled(false);
	  restart.setEnabled(true);
	  chance();
	}
	else
	{
	imageChange(e);	
	}
}catch(Exception ee)
	{
		//System.out.println("Error in BUTTON:"+ee);
	}
}



int playerPassNumber()			// number at where the player has to move.
{
 int i=	randomNo.nextInt(7);

         str=Integer.toString(i);
	 dieIcon= new ImageIcon("images/Game dies/"+str+".jpg");
	 die.setIcon(dieIcon);
	
     return i;
 
}





void winerdraw()				//to set pictures of winner on buttons
{
int i=0;
		 start.setEnabled(true);
		 restart.setEnabled(false);
		 JBplayer.setEnabled(false);
		 JBcomputer.setEnabled(true);
		 JBcomputer.setEnabled(false);
		 
for(int k=0;k<10;k++)
	for(int l=0;l<10;l++)
	{
		gameover=1;
         b[k][l].setIcon(winericon[k][l]);             
	}
	
		     
}


void rePrint()					// to again refresh the game
{
try{

for( i=0;i<10;i++)
	{
	for(j=0;j<10;j++)
	  {
		    // b[i][j].setIcon(null);
			 b[i][j].setIcon(icon[i][j]);
	  }
	}
	
   }catch(Exception e)
	{
	//System.out.println("error in repaint"+e);
	}

}




void playerimageTraval(int n)			// moves the image at a number returned by playerPassNumber() for first player
{
int i=j=0;
int imageFlag=0;
int noFlag=0;

if(gameover==0)
{
try{
	rePrint();	
	  for(i=0;i<10;i++)
	       {
		 for(j=0;j<10;j++)
			{
			 if(n==game[i][j])
			    {
                noFlag=1;
				break;
			    }
			}
 		if(noFlag==1)
			break;
	       }

   if(noFlag==1)
		{
		if(imageFlag==0)
		{
		 Thread.sleep(200);
		 b[i][j].setIcon(p1);
		 imageFlag=1;
		 prevIp1=i;
		 prevJp1=j;
	        }
        else
		{
	 	path=game[prevIp1][prevJp1];
		str=Integer.toString(path);
 		Thread.sleep(200);
		icon[prevIp1][prevJp1]=new ImageIcon("images/"+str+".jpg");
		Thread.sleep(200);
	        b[prevIp1][prevJp1].setIcon(icon[prevIp1][prevJp1]);
		b[i][j].setIcon(p1);
		prevIp1=i;
		prevJp1=j;
	        }

	/*****************checks for staires**********************/
		if(n==10)
			{
			n=28;
			playerimageTraval(n);
			p1value=n;
			}
		else if(n==17)
			{
			n=37;
			playerimageTraval(n);
			p1value=n;
			}
		else if(n==31)
			{
			n=70;
			playerimageTraval(n);
			p1value=n;
			}
		else if(n==45)
			{
			n=84;
			playerimageTraval(n);
			p1value=n;
			}
		else if(n==78)
			{
			n=97;
			playerimageTraval(n);
			p1value=n;
			}


		/*****************Checks for Snakes***************************/
		if(n==95)
			{
		         n=73;
			 playerimageTraval(n);
			 p1value=n;			
			}
		else if(n==79)
			{
		         n=59;
			 playerimageTraval(n);
			 p1value=n;			
			}
		else if(n==68)
			{
		         n=48;
			 playerimageTraval(n);
			 p1value=n;			
			}
		else if(n==44)
			{
		         n=21;
			 playerimageTraval(n);
			 p1value=n;			
			}
		else if(n==34)
			{
		         n=16;
			 playerimageTraval(n);
			 p1value=n;			
			}

		}	
		
	if(n>=100)
	{   
		//System.out.println("Congr u r win");
		winerdraw();
		
	}
}
catch(Exception e)
{
 
}
}

}

void setBothImage()				// if both players are on same number 
{
int i=0;
int j=0;
int noFlag=0;

if(gameover==0)
{
for(i=0;i<10;i++)
	       {
		 for(j=0;j<10;j++)
			{
			 if(p1value==game[i][j])
			    {
                                noFlag=1;
				break;
			    }
			}
 		if(noFlag==1)
			break;
	       }

if(noFlag==1)
{
b[i][j].setIcon(p1);  
}

noFlag=0;

for(i=0;i<10;i++)
	       {
		 for(j=0;j<10;j++)
			{
			 if(p2value==game[i][j])
			    {
                                noFlag=1;
				break;
			    }
			}
 		if(noFlag==1)
			break;
	       }

if(noFlag==1)
{
b[i][j].setIcon(p2);
}
}
}


void computerimageTraval(int n)			// moves the image at a number returned by playerPassNumber() for second player
{
int i=j=0;
int imageFlag=0;
int noFlag=0;

try{
	rePrint();	
	  for(i=0;i<10;i++)
      {
		 for(j=0;j<10;j++)
			{
			 if(n==game[i][j])
			    {
                noFlag=1;
				break;
			    }
			}
 		if(noFlag==1)
			break;
	       }

	   if(noFlag==1)
		{
		if(imageFlag==0)
		{
		 Thread.sleep(200);
		 b[i][j].setIcon(p2);
		 imageFlag=1;
		 prevIp1=i;
		 prevJp1=j;
	        }
        else
		{
	 	path=game[prevIp1][prevJp1];
		str=Integer.toString(path);
 		Thread.sleep(200);
		icon[prevIp1][prevJp1]=new ImageIcon("images/"+str+".jpg");
		Thread.sleep(200);
	        b[prevIp1][prevJp1].setIcon(icon[prevIp1][prevJp1]);
		b[i][j].setIcon(p1);
		prevIp1=i;
		prevJp1=j;
	        }
           

              /*****************checks for staires**********************/

		if(n==10)
			{
			n=28;
			computerimageTraval(n);
			p2value=n;
			}
		else if(n==17)
			{
			n=37;
			computerimageTraval(n);
			p2value=n;
			}
		else if(n==31)
			{
			n=70;
			computerimageTraval(n);
			p2value=n;
			}
		else if(n==45)
			{
			n=84;
			computerimageTraval(n);
			p2value=n;
			}
		else if(n==78)
			{
			n=97;
			computerimageTraval(n);
			p2value=n;
			}


		/*****************Checks for Snakes***************************/
		if(n==95)
			{
		         n=73;
			 computerimageTraval(n);
			 p2value=n;			
			}
		else if(n==79)
			{
		         n=59;
			 computerimageTraval(n);
			 p2value=n;			
			}
		else if(n==68)
			{
		         n=48;
			 computerimageTraval(n);
			 p2value=n;			
			}
		else if(n==44)
			{
		         n=21;
			 computerimageTraval(n);
			 p2value=n;			
			}
		else if(n==34)
			{
		         n=16;
			 computerimageTraval(n);
			 p2value=n;			
			}

		}	
		

	if(n>=100)
	{   
		//System.out.println("Congr u r win");
		gameover=1;	
		winerdraw();
	}
		


}
catch(Exception e)
{
 
}


}

void imageChange(ActionEvent e)		
{
int i=j=0;
int flag=0;

try{
 for( i=0;i<10;i++)
    {
	for( j=0;j<10;j++)
	{
	 if(e.getSource()==b[i][j])
		{
			//System.out.println("Button Found");
			//System.out.println("b["+i+"]["+j+"]");
			flag=1;
			break;
		}
	//System.out.println(j);
	 }

	 if(flag==1)
		break;
     }


	if(imageFlag==0)
	{ 
		 Thread.sleep(200);
		 b[i][j].setIcon(p1);
		 imageFlag=1;
		prevIp1=i;
		prevJp1=j;
		}
    else
	{
		path=game[prevIp1][prevJp1];
		str=Integer.toString(path);
		Thread.sleep(200);
		icon[prevIp1][prevJp1]=new ImageIcon("images/"+str+".jpg");
			 Thread.sleep(200);
	        b[prevIp1][prevJp1].setIcon(icon[prevIp1][prevJp1]);
		b[i][j].setIcon(p1);
		prevIp1=i;
		prevJp1=j; 
      }
    }
catch(Exception ee)
	{
		ee.printStackTrace();
	}
}





public static void main(String args[])
{
 Main m= new Main();
}

}