package com.tankgame2_0.hyz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;


import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * ���ߣ�����־
 * ���ܣ�ʵ��̹�˴�ս
 * ���ڣ�2018.6.7
 * �汾��2.0
 */
public class TankGame2_0 extends JFrame implements Runnable{

	
	/**
	 * �������壬������
	 * ���1��GamePanel
	 * ���2��ScorePanel--��ʾ����
	 * ���3��LifePanel--��ʾ����ֵ
	 * ���4��MenuBar--�˵���,ѡ�������Ϸ��ʼ����Ϸ
	 */
	private static final long serialVersionUID = 2L;
	
	/**
	 * welcome --��ӭ��ѡ�����
	 */
	WelcomePanel welcomePanel = null;
	/**
	 * 
	 */
	StagePanel stagePanel = null;
	/**
	 * GamePanel--̹�����н���
	 */
	GamePanel gamePanel=null;
	/**
	 * scorePanel --�ɼ����棬�Լ����а����ĳɼ�����
	 */
	ScorePanel scorePanel = null;
	Thread twp = null;
	
	Thread tstp = null;
	
	Thread tgp = null;
	
	//
	Thread tsp =null;
	
	/**
	 * 
	 */
	JLabel boundsL = null;
	//
	JLabel boundsU = null;
	
	JLabel boundsD = null;
	/**
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		TankGame2_0 tankgame = new TankGame2_0();
		Thread ttg = new Thread(tankgame);
		ttg.start();
	}
	/**
	 * ���캯�����½�������߳�
	 */
	public TankGame2_0(){

	}
	public void addwelPanel(){
		welcomePanel = new WelcomePanel();
		
		this.setResizable(false);
		
		this.add(welcomePanel);
		this.addKeyListener(welcomePanel);
		this.setTitle("Tank Game");
		this.setSize(800,600);
		setProperties();
		
		//�½�������welcomePanel�߳�tgp��
		twp = new Thread(welcomePanel);
		twp.start();
	}
	
	public void addstagePanel(){
		
		stagePanel = new StagePanel();
		this.remove(welcomePanel);
		this.welcomePanel = null;
		this.add(stagePanel);
		setProperties();
		tstp = new Thread(stagePanel);
		tstp.start();
		//�Ƴ�welcome���
		AePlayWave apw=new AePlayWave("voice/start.wav");
		apw.start();
	}
	
	public void addgasPanel(){
		
		gamePanel = new GamePanel();
		
		scorePanel = new ScorePanel();
		
		boundsL = new JLabel();
		boundsL.setOpaque(true);
		boundsL.setBackground(Color.gray);
		boundsU = new JLabel();
		boundsU.setOpaque(true);
		boundsU.setBackground(Color.gray);
		boundsD = new JLabel();
		boundsD.setOpaque(true);
		boundsD.setBackground(Color.gray);
		//���Action
		this.addKeyListener(gamePanel);
		//�Ƴ�stagePanel���
		this.remove(this.stagePanel);
		//
		
		this.add(boundsL,BorderLayout.WEST);
		this.add(boundsU,BorderLayout.NORTH);
		this.add(boundsD,BorderLayout.SOUTH);
		this.add(gamePanel,BorderLayout.CENTER);
		this.add(scorePanel,BorderLayout.EAST);
		gamePanel.setPreferredSize(new Dimension(680,560));
		scorePanel.setPreferredSize(new Dimension(100,600));
		boundsL.setPreferredSize(new Dimension(20,600));
		boundsU.setPreferredSize(new Dimension(500,20));
		boundsD.setPreferredSize(new Dimension(500,20));
		
		//�½�������gamePanel�߳�tgp��
		tgp = new Thread(gamePanel);
		tgp.start();
		//
		tsp = new Thread(scorePanel);
		tsp.start();
		
		setProperties();
	}
	
	public void setProperties(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		addwelPanel();
		while(true){
			if(gamePanel == null&&scorePanel == null){
				if(!twp.isAlive()&&stagePanel == null ){
					addstagePanel();
				}else if(welcomePanel == null && !tstp.isAlive()){
					addgasPanel();
				}
				
			}
			if(gamePanel != null&&scorePanel != null){
				scorePanel.etNum = gamePanel.ets.size();
				scorePanel.score = gamePanel.enSize-scorePanel.etNum;
			}
		}
	}
}
