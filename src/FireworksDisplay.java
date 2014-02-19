import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.util.ArrayList;

/**
 * while this class is being tested, there will be extra STATIC declarations added
 * as well as test methods and test objects. make sure they are cleaned up later.
 * 
 * @author Leif Raptis-Firth
 *
 */
public class FireworksDisplay extends JFrame{
		
	private static ArrayList<ParticleManager> managerList = new ArrayList<ParticleManager>();
	private static ArrayList<Particle> particleList = new ArrayList<Particle>();
	private DrawPanel displayPanel;
	private static Timer timer;
	private Environment environment;
	//Place holder velocity
	private static double windVelocity = 0;
	private static final int TIME_INTERVAL = 20;
	long startTime = System.currentTimeMillis();
	static long firmStartTime = System.currentTimeMillis();
	private static String timeString = "";
	private static String displayMessage = "";
	private static int starsRemaining ;
	private static double timeUntilLaunch;
	private static double runningTime;
	private static boolean launchFlag = false;
	
	
	public FireworksDisplay(){
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocation(100,100);
		setSize(800, 600);
		setTitle("Fireworks Display");
		displayPanel = new DrawPanel();
		add(displayPanel);
		add(new ToolPanel(), BorderLayout.EAST);
	
		timer = new Timer(TIME_INTERVAL, new TimerListener());
	}
	
	private void updateDisplay(){
		particleList.clear();
		ParticleManager manager;
		for (int i = 0; i < managerList.size(); i++){
			manager = managerList.get(i);
			manager.update();
			particleList.addAll(manager.getParticles());
			starsRemaining = manager.getStarsRemaining();
			if(manager.getStarsRemaining() == 0 && particleList.size() == 0){
				System.out.println("Removing manager");
				managerList.remove(i);
				i--;
			}
		}
		displayPanel.repaint();
	}
	
	private static void addFirework(Firework firework){
		managerList.add(new ParticleManager(windVelocity, firework));	
	}
	
	private class TimerListener implements ActionListener {
		TimerListener(){
			super();
		}
		public void actionPerformed(ActionEvent e) {
			updateDisplay();
			long currentTime = System.currentTimeMillis();
			runningTime = (currentTime - firmStartTime) / 1000.0;
			timeString = "Time: " + String.valueOf(runningTime);
			if (currentTime - 15000 > startTime){
				System.out.println("Firing a new firework");
				addFirework(new RomanCandle(0));
				startTime = currentTime;
			}
		}
	}
	
	
	
	
	private static class DrawPanel extends JPanel {
		DrawPanel(){
			super();
			super.setBackground(new Color(10,10,10));
		}
		
		public void paint(Graphics g){
			int x;
			int y;
			int size;
			super.paint(g);
			g.setColor(Color.WHITE);
			g.drawString(timeString, 5 , 15 );
			g.drawString("Stars Remaining: " + starsRemaining, 5 , 30 );
			for(Particle particle: particleList){
				size = particle.getRenderSize() ;
				x = (int)(particle.getPositions()[0] * getWidth() / 50) + getWidth() / 2;
				y = - (int)(particle.getPositions()[1] * getHeight() / 50) + getHeight() - size;
				g.fillOval(x, y, size, size);
				g.setColor(particle.getColour());	
			}
			/*
			if (launchFlag){
				timeUntilLaunch = 15.0 - runningTime % 15.0;
				displayMessage = "Launching Firework in: " + timeUntilLaunch;
				g.drawString(displayMessage, getWidth() / 2 - displayMessage.length() / 2, getHeight() / 2);
			}
			*/
		}
	}

	private static class ToolPanel extends JPanel {
		JLabel jlblStarsRemaining = new JLabel("Stars Remaining: ");
		JLabel jlblFireworksRemaining = new JLabel("Fireworks Remaining: ");
		JLabel jlblCurrentTime = new JLabel("Current Time: "); 
		JLabel jlblTimeUntilLaunch = new JLabel("Next Firework in: ");
		JButton jbtStart = new JButton("Start");
		JButton jbtConfigure = new JButton("Configure");
		JButton jbtQuit = new JButton("Quit");
		
		ToolPanel(){
			super.setLayout(new GridLayout(4, 2));
			add(jlblStarsRemaining);
			add(jlblFireworksRemaining);
			add(jlblCurrentTime);
			add(jlblTimeUntilLaunch);
			add(jbtStart);
			add(jbtConfigure);
			add(jbtQuit);
		}
	}
	
	
	public static void main(String[] args){
		 
		FireworksDisplay testDisplay = new FireworksDisplay();
		
		addFirework(new RomanCandle(Math.toRadians(-15)));
		addFirework(new RomanCandle(Math.toRadians(15)));
		
		timer.start();
		
	}
	
}
