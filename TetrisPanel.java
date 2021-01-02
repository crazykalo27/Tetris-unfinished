
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.JPanel;


//FIX COLUMMS


@SuppressWarnings("serial")
public class TetrisPanel extends JPanel implements ActionListener{

	//need to be divisible by 2 and unit size
	static final int SCREEN_WIDTH = 500;
	static final int SCREEN_HEIGHT = 700;
	
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	
	//possible different delay speeds?
	static int DELAY = 200;
	
	static final int MAX_PEICE_PARTS = 1;
	
	int randStart1;
	int randStart2;
	
	int permPeices = 0;
	int column = 11;
	int peices = 1;
	int peiceNum;
	int peiceHeight = SCREEN_HEIGHT/UNIT_SIZE;
	int score;
	
	
	//arrays
	final int y[] = new int [GAME_UNITS];
	
	 	//think this was dumb
			/*
		{
				0, 25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700
		};   */
	
	final int x[] = new int [GAME_UNITS];
	
	int permX[] = new int[GAME_UNITS];
	int permY[] = new int[GAME_UNITS];
	int done[] = new int[GAME_UNITS];
	
	boolean running = false;
	
	Timer timer;
	Random rand;
	
	boolean touchLEdge = false;
	boolean touchREdge = false;
	
	
	TetrisPanel()
	{
		rand = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.blue);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		x[0] = SCREEN_WIDTH/2;
		startGame();
	}
	
	private void startGame()
	{
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
	}

	private void newPeice() 
	{
		System.out.println("got here "+ column);
		peices++;
			
		randStart1 = rand.nextInt(1);
		randStart2 = rand.nextInt(10);
		switch(randStart1)
		{
		case '0':
			switch(randStart2)
			{
			case '0':
				x[peices-1] = 0;
				column = 1;
				break;
			case '1':
				x[peices-1] = 25;
				column = 2;
				break;
			case '2':
				x[peices-1] = 50;
				column = 3;
				break;
			case '3':
				x[peices-1] = 75;
				column = 4;
				break;
			case '4':
				x[peices-1] = 100;
				column = 5;
				break;
			case '5':
				x[peices-1] = 125;
				column = 6;
				break;
			case '6':
				x[peices-1] = 150;
				column = 7;
				break;
			case '7':
				x[peices-1] = 175;
				column = 8;
				break;
			case '8':
				x[peices-1] = 200;
				column = 9;
				break;
			case '9':
				x[peices-1] = 225;
				column = 10;
				break;
			}
			break;
		case '1':
			switch(randStart2)
			{
			case '0':
				x[peices-1] = 250;
				column = 11;
				break;
			case '1':
				x[peices-1] = 275;
				column = 12;
				break;
			case '2':
				x[peices-1] = 300;
				column = 13;
				break;
			case '3':
				x[peices-1] = 325;
				column = 14;
				break;
			case '4':
				x[peices-1] = 350;
				column = 15;
				break;
			case '5':
				x[peices-1] = 375;
				column = 16;
				break;
			case '6':
				x[peices-1] = 400;
				column = 17;
				break;
			case '7':
				x[peices-1] = 425;
				column = 18;
				break;
			case '8':
				x[peices-1] = 450;
				column = 19;
				break;
			case '9':
				x[peices-1] = 475;
				column = 20;
				break;
			}
			break;
			
		}
		peiceHeight = SCREEN_HEIGHT/UNIT_SIZE;
		System.out.println("now "+column);
		System.out.println(peices+" peices");
		
			
			//hide old peice lol?
			/*
			x[peices-1] = 0;
			y[peices-1] = 0;
			*/
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g)
	{
		if(running)
		{
			//draw lines
			
			for(int i=0; i < SCREEN_HEIGHT/UNIT_SIZE; i++)
			{
				g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
				g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
			}
			

			//draw current
			for(int i = 0; i < peices; i++)
			{
				g.setColor(Color.black);
				//g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
				g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				peiceHeight = y[i]/25;
		//		System.out.println(y[i]);
			}	
			
			//draw permanent
			for(int i = 0; i < permPeices; i++)
			{
				g.fillRect(permX[i], permY[i], UNIT_SIZE, UNIT_SIZE);
			}
			
			//draw score
			g.setColor(Color.white);
			g.setFont(new Font("Comic Sans", Font.BOLD, 30));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: "+score, (SCREEN_WIDTH - metrics.stringWidth
					("Score: "+score))/2, g.getFont().getSize());
		}	
	}
	
	private int findTop(int column, int height)
	{
		//TODO HELP
		
		return 27;
	}
	
	private void move()
	{
		//sets to previous?
		//this might actually be important
		/*
		for(int i = peiceParts; i > 0; i--)
		 {
			 x[i] = x[i-1];
			 y[i] = y[i-1];
		 }
		 */
		
		 if(peiceHeight < findTop(column, peiceHeight))
		 {
			 y[permPeices] = y[permPeices] + UNIT_SIZE; 
		 }
		 else
		 {
			 storePeice();
			 newPeice();
		 }	 
	}
	
	private void storePeice() 
	{
		permPeices++;
		for(int i = 0; i < permPeices; i++)
		{
			if(done[i] != 1)
			{
				done[i] = 1;
				permX[i] = x[i];
				permY[i] = y[i];
			}
		}
	}

	private void checkAll() {
		
		
		
		if(!running)
		{
			timer.stop();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(running)
		{
			move();
			checkAll();
		}
		repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter
	{
		@Override
		public void keyPressed(KeyEvent e)
		{
			switch(e.getKeyCode())
			{
			case KeyEvent.VK_LEFT:
				if(column != 1)
				{
					x[peices-1] = x[peices-1] - UNIT_SIZE;
					 column--;
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(column != 20)
				{
					x[peices-1] = x[peices-1] + UNIT_SIZE;
					 column++;
				}
				break;
			}
		}
	}
}
