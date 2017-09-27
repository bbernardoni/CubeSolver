import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GUI extends JFrame{
	
	JPanel PanPCC = new JPanel();
	//[UDLFRB][ul..dr]
	Color c[][]=new Color[6][9];
	JTextArea txtM = new JTextArea();
	JTextArea txtO = new JTextArea();
	PatternTable pt = new PatternTable();
	Image pcImage;
	
	public GUI(){
		super();
		PaintCube.Solve(c);
		try {
			pcImage = ImageIO.read(new File("paintcube.png"));
		} catch (IOException e) {e.printStackTrace();}
		
		JMenuBar mb = new JMenuBar();
		JMenu mFile = new JMenu("File");
		JMenuItem mExit = new JMenuItem("Exit");
		setJMenuBar(mb);
		mb.add(mFile);
		mFile.add(mExit);
		mevent1 me1 = new mevent1();
		mExit.addActionListener(me1);
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;
		
		JPanel PanP = new JPanel();
		PanP.setLayout(new GridBagLayout());
		JPanel PanPW = new JPanel();	JPanel PanPY = new JPanel();
		JPanel PanPB = new JPanel();	JPanel PanPG = new JPanel();
		JPanel PanPR = new JPanel();	JPanel PanPO = new JPanel();
		JPanel PanPC = new JPanel();
		
		PanPW.setBackground(Color.WHITE);	PanPW.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		PanPY.setBackground(Color.YELLOW);	PanPY.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		PanPB.setBackground(Color.BLUE);	PanPB.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		PanPG.setBackground(Color.GREEN);	PanPG.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		PanPR.setBackground(Color.RED);		PanPR.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		PanPO.setBackground(new Color(255,140,0));	PanPO.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		PanPC.setBackground(Color.GRAY);	PanPC.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		PanPCC.setBackground(Color.WHITE);	PanPCC.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		gc.insets = new Insets(1,3,5,3);
		gc.gridx = 0;	gc.gridy = 0;	PanP.add(new JLabel("White", SwingConstants.CENTER), gc);
		gc.gridx = 1;	gc.gridy = 0;	PanP.add(new JLabel("Yellow", SwingConstants.CENTER), gc);
		gc.gridx = 0;	gc.gridy = 2;	PanP.add(new JLabel("Blue", SwingConstants.CENTER), gc);
		gc.gridx = 1;	gc.gridy = 2;	PanP.add(new JLabel("Green", SwingConstants.CENTER), gc);
		gc.gridx = 0;	gc.gridy = 4;	PanP.add(new JLabel("Red", SwingConstants.CENTER), gc);
		gc.gridx = 1;	gc.gridy = 4;	PanP.add(new JLabel("Orange", SwingConstants.CENTER), gc);
		gc.gridx = 0;	gc.gridy = 6;	PanP.add(new JLabel("Clear", SwingConstants.CENTER), gc);
		gc.gridx = 1;	gc.gridy = 6;	PanP.add(new JLabel("Current", SwingConstants.CENTER), gc);
		gc.ipadx = 61;	gc.ipady = 61;
		gc.gridx = 0;	gc.gridy = 1;	PanP.add(PanPW, gc);
		gc.gridx = 1;	gc.gridy = 1;	PanP.add(PanPY, gc);
		gc.gridx = 0;	gc.gridy = 3;	PanP.add(PanPB, gc);
		gc.gridx = 1;	gc.gridy = 3;	PanP.add(PanPG, gc);
		gc.gridx = 0;	gc.gridy = 5;	PanP.add(PanPR, gc);
		gc.gridx = 1;	gc.gridy = 5;	PanP.add(PanPO, gc);
		gc.insets = new Insets(1,3,6,3);
		gc.gridx = 0;	gc.gridy = 7;	PanP.add(PanPC, gc);
		gc.gridx = 1;	gc.gridy = 7;	PanP.add(PanPCC, gc);
		
		PanPW.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				PanPCC.setBackground(Color.WHITE);
			}
		});
		PanPY.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				PanPCC.setBackground(Color.YELLOW);
			}
		});
		PanPB.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				PanPCC.setBackground(Color.BLUE);
			}
		});
		PanPG.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				PanPCC.setBackground(Color.GREEN);
			}
		});
		PanPR.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				PanPCC.setBackground(Color.RED);
			}
		});
		PanPO.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				PanPCC.setBackground(new Color(255,140,0));
			}
		});
		PanPC.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				PanPCC.setBackground(Color.GRAY);
			}
		});
		
		PanP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Paints"));
		gc.gridx = 0;	gc.gridy = 0;
		gc.insets = new Insets(0,0,0,0);
		gc.ipadx = 0;	gc.ipady = 0;
		gc.gridheight = 2;
		add(PanP, gc);
		
		JPanel PntP = new JPanel();
		PntP.setLayout(new GridBagLayout());
		Painting Pnt = new Painting();
		gc.ipadx = 540;	gc.ipady = 395;
		PntP.add(Pnt, gc);
		PntP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Cube"));
		gc.gridx = 1;	gc.gridy = 0;
		gc.ipadx = 0;	gc.ipady = 0;
		add(PntP, gc);
		
		JPanel OptP = new JPanel();
		OptP.setLayout(new GridLayout(6,3));
		JCheckBox cbU = new JCheckBox("U");		JCheckBox cbD = new JCheckBox("D");
		JCheckBox cbUi = new JCheckBox("U'");	JCheckBox cbDi = new JCheckBox("D'");
		JCheckBox cbU2 = new JCheckBox("U2");	JCheckBox cbD2 = new JCheckBox("D2");
		JCheckBox cbF = new JCheckBox("F");		JCheckBox cbB = new JCheckBox("B");
		JCheckBox cbFi = new JCheckBox("F'");	JCheckBox cbBi = new JCheckBox("B'");
		JCheckBox cbF2 = new JCheckBox("F2");	JCheckBox cbB2 = new JCheckBox("B2");
		JCheckBox cbR = new JCheckBox("R");		JCheckBox cbL = new JCheckBox("L");
		JCheckBox cbRi = new JCheckBox("R'");	JCheckBox cbLi = new JCheckBox("L'");
		JCheckBox cbR2 = new JCheckBox("R2");	JCheckBox cbL2 = new JCheckBox("L2");
		cbU.setSelected(true);		cbD.setSelected(true);
		cbUi.setSelected(true);		cbDi.setSelected(true);
		cbU2.setSelected(true);		cbD2.setSelected(true);
		cbF.setSelected(true);		cbB.setSelected(true);
		cbFi.setSelected(true);		cbBi.setSelected(true);
		cbF2.setSelected(true);		cbB2.setSelected(true);
		cbR.setSelected(true);		cbL.setSelected(true);
		cbRi.setSelected(true);		cbLi.setSelected(true);
		cbR2.setSelected(true);		cbL2.setSelected(true);
		cbU.setFocusable(false);	cbD.setFocusable(false);
		cbUi.setFocusable(false);	cbDi.setFocusable(false);
		cbU2.setFocusable(false);	cbD2.setFocusable(false);
		cbF.setFocusable(false);	cbB.setFocusable(false);
		cbFi.setFocusable(false);	cbBi.setFocusable(false);
		cbF2.setFocusable(false);	cbB2.setFocusable(false);
		cbR.setFocusable(false);	cbL.setFocusable(false);
		cbRi.setFocusable(false);	cbLi.setFocusable(false);
		cbR2.setFocusable(false);	cbL2.setFocusable(false);
		OptP.add(cbU);		OptP.add(cbUi);		OptP.add(cbU2);
		OptP.add(cbD);		OptP.add(cbDi);		OptP.add(cbD2);
		OptP.add(cbF);		OptP.add(cbFi);		OptP.add(cbF2);
		OptP.add(cbB);		OptP.add(cbBi);		OptP.add(cbB2);
		OptP.add(cbR);		OptP.add(cbRi);		OptP.add(cbR2);
		OptP.add(cbL);		OptP.add(cbLi);		OptP.add(cbL2);
		OptP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Move Restrictions"));
		gc.gridx = 0;	gc.gridy = 2;
		gc.weighty = 1;
		add(OptP, gc);
		
		JPanel OutP = new JPanel();
		OutP.setLayout(new BorderLayout());
		txtO.setMargin(new Insets(3,3,3,3));
		txtO.setEditable(false);
		txtO.setLineWrap(true);
		txtO.setWrapStyleWord(true);
		JScrollPane scrO = new JScrollPane(txtO); 
		OutP.add(scrO);
		OutP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Output"));
		gc.gridx = 1;	gc.gridy = 2;
		add(OutP, gc);
		
		JPanel MoveP = new JPanel();
		MoveP.setLayout(new GridLayout(6,6));
		JButton btnU = new JButton("U");	JButton btnUi = new JButton("U'");	JButton btnU2 = new JButton("U2");
		JButton btnD = new JButton("D");	JButton btnDi = new JButton("D'");	JButton btnD2 = new JButton("D2");
		JButton btnF = new JButton("F");	JButton btnFi = new JButton("F'");	JButton btnF2 = new JButton("F2");
		JButton btnB = new JButton("B");	JButton btnBi = new JButton("B'");	JButton btnB2 = new JButton("B2");
		JButton btnR = new JButton("R");	JButton btnRi = new JButton("R'");	JButton btnR2 = new JButton("R2");
		JButton btnL = new JButton("L");	JButton btnLi = new JButton("L'");	JButton btnL2 = new JButton("L2");
		JButton btnx = new JButton("x");	JButton btnxi = new JButton("x'");	JButton btnx2 = new JButton("x2");
		JButton btny = new JButton("y");	JButton btnyi = new JButton("y'");	JButton btny2 = new JButton("y2");
		JButton btnz = new JButton("z");	JButton btnzi = new JButton("z'");	JButton btnz2 = new JButton("z2");
		JButton btnM = new JButton("M");	JButton btnMi = new JButton("M'");	JButton btnM2 = new JButton("M2");
		JButton btnE = new JButton("E");	JButton btnEi = new JButton("E'");	JButton btnE2 = new JButton("E2");
		JButton btnS = new JButton("S");	JButton btnSi = new JButton("S'");	JButton btnS2 = new JButton("S2");
		MoveE ME = new MoveE();
		btnU.addActionListener(ME);	btnUi.addActionListener(ME);	btnU2.addActionListener(ME);
		btnD.addActionListener(ME);	btnDi.addActionListener(ME);	btnD2.addActionListener(ME);
		btnF.addActionListener(ME);	btnFi.addActionListener(ME);	btnF2.addActionListener(ME);
		btnB.addActionListener(ME);	btnBi.addActionListener(ME);	btnB2.addActionListener(ME);
		btnR.addActionListener(ME);	btnRi.addActionListener(ME);	btnR2.addActionListener(ME);
		btnL.addActionListener(ME);	btnLi.addActionListener(ME);	btnL2.addActionListener(ME);
		btnx.addActionListener(ME);	btnxi.addActionListener(ME);	btnx2.addActionListener(ME);
		btny.addActionListener(ME);	btnyi.addActionListener(ME);	btny2.addActionListener(ME);
		btnz.addActionListener(ME);	btnzi.addActionListener(ME);	btnz2.addActionListener(ME);
		btnM.addActionListener(ME);	btnMi.addActionListener(ME);	btnM2.addActionListener(ME);
		btnE.addActionListener(ME);	btnEi.addActionListener(ME);	btnE2.addActionListener(ME);
		btnS.addActionListener(ME);	btnSi.addActionListener(ME);	btnS2.addActionListener(ME);
		btnU.setMargin(new Insets(8,12,8,12));	btnUi.setMargin(new Insets(8,12,8,12));	btnU2.setMargin(new Insets(8,12,8,12));
		btnD.setMargin(new Insets(8,12,8,12));	btnDi.setMargin(new Insets(8,12,8,12));	btnD2.setMargin(new Insets(8,12,8,12));
		btnF.setMargin(new Insets(8,12,8,12));	btnFi.setMargin(new Insets(8,12,8,12));	btnF2.setMargin(new Insets(8,12,8,12));
		btnB.setMargin(new Insets(8,12,8,12));	btnBi.setMargin(new Insets(8,12,8,12));	btnB2.setMargin(new Insets(8,12,8,12));
		btnR.setMargin(new Insets(8,12,8,12));	btnRi.setMargin(new Insets(8,12,8,12));	btnR2.setMargin(new Insets(8,12,8,12));
		btnL.setMargin(new Insets(8,12,8,12));	btnLi.setMargin(new Insets(8,12,8,12));	btnL2.setMargin(new Insets(8,12,8,12));
		btnx.setMargin(new Insets(8,12,8,12));	btnxi.setMargin(new Insets(8,12,8,12));	btnx2.setMargin(new Insets(8,12,8,12));
		btny.setMargin(new Insets(8,12,8,12));	btnyi.setMargin(new Insets(8,12,8,12));	btny2.setMargin(new Insets(8,12,8,12));
		btnz.setMargin(new Insets(8,12,8,12));	btnzi.setMargin(new Insets(8,12,8,12));	btnz2.setMargin(new Insets(8,12,8,12));
		btnM.setMargin(new Insets(8,12,8,12));	btnMi.setMargin(new Insets(8,12,8,12));	btnM2.setMargin(new Insets(8,12,8,12));
		btnE.setMargin(new Insets(8,12,8,12));	btnEi.setMargin(new Insets(8,12,8,12));	btnE2.setMargin(new Insets(8,12,8,12));
		btnS.setMargin(new Insets(8,12,8,12));	btnSi.setMargin(new Insets(8,12,8,12));	btnS2.setMargin(new Insets(8,12,8,12));
		btnU.setFocusable(false);	btnUi.setFocusable(false);	btnU2.setFocusable(false);
		btnD.setFocusable(false);	btnDi.setFocusable(false);	btnD2.setFocusable(false);
		btnF.setFocusable(false);	btnFi.setFocusable(false);	btnF2.setFocusable(false);
		btnB.setFocusable(false);	btnBi.setFocusable(false);	btnB2.setFocusable(false);
		btnR.setFocusable(false);	btnRi.setFocusable(false);	btnR2.setFocusable(false);
		btnL.setFocusable(false);	btnLi.setFocusable(false);	btnL2.setFocusable(false);
		btnx.setFocusable(false);	btnxi.setFocusable(false);	btnx2.setFocusable(false);
		btny.setFocusable(false);	btnyi.setFocusable(false);	btny2.setFocusable(false);
		btnz.setFocusable(false);	btnzi.setFocusable(false);	btnz2.setFocusable(false);
		btnM.setFocusable(false);	btnMi.setFocusable(false);	btnM2.setFocusable(false);
		btnE.setFocusable(false);	btnEi.setFocusable(false);	btnE2.setFocusable(false);
		btnS.setFocusable(false);	btnSi.setFocusable(false);	btnS2.setFocusable(false);
		MoveP.add(btnU);	MoveP.add(btnD);	MoveP.add(btnF);	MoveP.add(btnB);	MoveP.add(btnR);	MoveP.add(btnL);
		MoveP.add(btnUi);	MoveP.add(btnDi);	MoveP.add(btnFi);	MoveP.add(btnBi);	MoveP.add(btnRi);	MoveP.add(btnLi);
		MoveP.add(btnU2);	MoveP.add(btnD2);	MoveP.add(btnF2);	MoveP.add(btnB2);	MoveP.add(btnR2);	MoveP.add(btnL2);
		MoveP.add(btnx);	MoveP.add(btny);	MoveP.add(btnz);	MoveP.add(btnM);	MoveP.add(btnE);	MoveP.add(btnS);
		MoveP.add(btnxi);	MoveP.add(btnyi);	MoveP.add(btnzi);	MoveP.add(btnMi);	MoveP.add(btnEi);	MoveP.add(btnSi);
		MoveP.add(btnx2);	MoveP.add(btny2);	MoveP.add(btnz2);	MoveP.add(btnM2);	MoveP.add(btnE2);	MoveP.add(btnS2);
		MoveP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Moves"));
		gc.gridx = 2;	gc.gridy = 0;
		gc.weighty = 0;	gc.gridheight = 1;
		add(MoveP, gc);
		
		JPanel ManP = new JPanel();
		ManP.setLayout(new BorderLayout());
		JButton btnM1 = new JButton("Apply Moves");
		AMoves AM = new AMoves();
		btnM1.addActionListener(AM);
		btnM1.setFocusable(false);
		txtM.setMargin(new Insets(3,3,3,3));
		txtM.setLineWrap(true);
		txtM.setWrapStyleWord(true);
		JScrollPane scrM = new JScrollPane(txtM); 
		ManP.add(scrM, BorderLayout.CENTER);
		ManP.add(btnM1, BorderLayout.PAGE_END);
		ManP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Manual Input"));
		gc.gridx = 2;	gc.gridy = 1;
		add(ManP, gc);
		
		JPanel ResP = new JPanel();
		ResP.setLayout(new GridLayout(3,1));
		JButton btnRB = new JButton("Blank");
		JButton btnRS = new JButton("Solved");
		JButton btnRC = new JButton("Scramble");
		btnRB.addActionListener(ME);
		btnRS.addActionListener(ME);
		btnRC.addActionListener(ME);
		btnRB.setFocusable(false);
		btnRS.setFocusable(false);
		btnRC.setFocusable(false);
		ResP.add(btnRB);
		ResP.add(btnRS);
		ResP.add(btnRC);
		ResP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Reset To"));
		gc.gridx = 2;	gc.gridy = 2;
		add(ResP, gc);
		
		JPanel SolP = new JPanel();
		SolP.setLayout(new GridLayout(2,1));
		JButton btnSS = new JButton("Solve");
		JButton btnSG = new JButton("Generate");
		btnSS.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				/*ValidateCube vc = new ValidateCube(c);
				int valid = vc.validate();
				if(valid!=-1){
					txtO.setText(vc.getErrorMessage(valid));
				}else{
					txtO.setText(""+valid);
				}*/
				SolveCube sc = new SolveCube(c,pt);
				byte[] solution = sc.solve();
				String solutionStr = "";
				for(int i=0; i<solution.length; i++){
					solutionStr += getMoveString(solution[i]);
				}
				txtO.setText(solutionStr);
				GUI.this.repaint();
			}
		});
		btnSG.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				SolveCube sc = new SolveCube(c,pt);
				byte[] solution = sc.solve();
				String solutionStr = "";
				for(int i=solution.length-1; i>=0; i--){
					int move = solution[i] - 2*((solution[i]%3)-1);
					solutionStr += getMoveString(move);
				}
				txtO.setText(solutionStr);
				GUI.this.repaint();
			}
		});
		btnSS.setFocusable(false);
		btnSG.setFocusable(false);
		SolP.add(btnSS);
		SolP.add(btnSG);
		SolP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Solve"));
		gc.gridx = 2;	gc.gridy = 3;
		add(SolP, gc);
		
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				GUI.this.requestFocusInWindow();
			}
		});
		
		this.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e) {
		    	int keyCode = e.getKeyCode();
		        switch (keyCode){
		        case KeyEvent.VK_ESCAPE:
		        	System.exit(0);
		        	break;
		        case KeyEvent.VK_J:
		        	PaintCube.U(c);
		        	break;
		        case KeyEvent.VK_F:
		        	PaintCube.Ui(c);
		        	break;
		        case KeyEvent.VK_S:
		        	PaintCube.D(c);
		        	break;
		        case KeyEvent.VK_L:
		        	PaintCube.Di(c);
		        	break;
		        case KeyEvent.VK_I:
		        	PaintCube.R(c);
		        	break;
		        case KeyEvent.VK_K:
		        	PaintCube.Ri(c);
		        	break;
		        case KeyEvent.VK_D:
		        	PaintCube.L(c);
		        	break;
		        case KeyEvent.VK_E:
		        	PaintCube.Li(c);
		        	break;
		        case KeyEvent.VK_H:
		        	PaintCube.F(c);
		        	break;
		        case KeyEvent.VK_G:
		        	PaintCube.Fi(c);
		        	break;
		        case KeyEvent.VK_W:
		        	PaintCube.B(c);
		        	break;
		        case KeyEvent.VK_O:
		        	PaintCube.Bi(c);
		        	break;
		        case KeyEvent.VK_B:
		        	PaintCube.M(c);
		        	break;
		        case KeyEvent.VK_T:
		        	PaintCube.Mi(c);
		        	break;
		        case KeyEvent.VK_SEMICOLON:
		        	PaintCube.U(c);
		        	PaintCube.Ei(c);
		        	PaintCube.Di(c);
		        	break;
		        case KeyEvent.VK_A:
		        	PaintCube.Ui(c);
		        	PaintCube.E(c);
		        	PaintCube.D(c);
		        	break;
		        case KeyEvent.VK_Y:
		        	PaintCube.R(c);
		        	PaintCube.Mi(c);
		        	PaintCube.Li(c);
		        	break;
		        case KeyEvent.VK_N:
		        	PaintCube.Ri(c);
		        	PaintCube.M(c);
		        	PaintCube.L(c);
		        	break;
		        case KeyEvent.VK_P:
		        	PaintCube.F(c);
		        	PaintCube.S(c);
		        	PaintCube.Bi(c);
		        	break;
		        case KeyEvent.VK_Q:
		        	PaintCube.Fi(c);
		        	PaintCube.Si(c);
		        	PaintCube.B(c);
		        	break;
		        case KeyEvent.VK_U:
		        	PaintCube.R(c);
		        	PaintCube.Mi(c);
		        	break;
		        case KeyEvent.VK_M:
		        	PaintCube.Ri(c);
		        	PaintCube.M(c);
		        	break;
		        case KeyEvent.VK_V:
		        	PaintCube.L(c);
		        	PaintCube.M(c);
		        	break;
		        case KeyEvent.VK_R:
		        	PaintCube.Li(c);
		        	PaintCube.Mi(c);
		        	break;
		        case KeyEvent.VK_SPACE:
		        	PaintCube.Scramble(c);
		        	break;
		        case KeyEvent.VK_BACK_SPACE:
		        	PaintCube.Solve(c);
		        	break;
		        case KeyEvent.VK_DELETE:
		        	PaintCube.Blank(c);
		        	break;
		        case KeyEvent.VK_1:
		        	PanPCC.setBackground(Color.WHITE);
		        	break;
		        case KeyEvent.VK_2:
		        	PanPCC.setBackground(Color.YELLOW);
		        	break;
		        case KeyEvent.VK_3:
		        	PanPCC.setBackground(Color.BLUE);
		        	break;
		        case KeyEvent.VK_4:
		        	PanPCC.setBackground(Color.GREEN);
		        	break;
		        case KeyEvent.VK_5:
		        	PanPCC.setBackground(Color.RED);
		        	break;
		        case KeyEvent.VK_6:
		        	PanPCC.setBackground(new Color(255,140,0));
		        	break;
		        case KeyEvent.VK_7:
		        	PanPCC.setBackground(Color.GRAY);
		        	break;
		        }
	        	GUI.this.repaint();
		        e.consume();
		    }
		});
	}
	
	private String getMoveString(int move){
		switch(move){
		case 0:	return "U ";
		case 1:	return "U2 ";
		case 2:	return "U' ";
		case 3:	return "D ";
		case 4:	return "D2 ";
		case 5:	return "D' ";
		case 6:	return "R ";
		case 7:	return "R2 ";
		case 8:	return "R' ";
		case 9:	return "L ";
		case 10:return "L2 ";
		case 11:return "L' ";
		case 12:return "F ";
		case 13:return "F2 ";
		case 14:return "F' ";
		case 15:return "B ";
		case 16:return "B2 ";
		case 17:return "B' ";
		default:return null;
		}
	}
	
	public class mevent1 implements ActionListener{
		public void actionPerformed(ActionEvent me1){
			System.exit(0);
		}
	}
	
	public class MoveE implements ActionListener{
		public void actionPerformed(ActionEvent ME){
			if(ME.getActionCommand() == "U"){		PaintCube.U(c);}
			else if(ME.getActionCommand() == "U'"){	PaintCube.Ui(c);}
			else if(ME.getActionCommand() == "U2"){	PaintCube.U2(c);}
			else if(ME.getActionCommand() == "D"){	PaintCube.D(c);}
			else if(ME.getActionCommand() == "D'"){	PaintCube.Di(c);}
			else if(ME.getActionCommand() == "D2"){	PaintCube.D2(c);}
			else if(ME.getActionCommand() == "F"){	PaintCube.F(c);}
			else if(ME.getActionCommand() == "F'"){	PaintCube.Fi(c);}
			else if(ME.getActionCommand() == "F2"){	PaintCube.F2(c);}
			else if(ME.getActionCommand() == "B"){	PaintCube.B(c);}
			else if(ME.getActionCommand() == "B'"){	PaintCube.Bi(c);}
			else if(ME.getActionCommand() == "B2"){	PaintCube.B2(c);}
			else if(ME.getActionCommand() == "R"){	PaintCube.R(c);}
			else if(ME.getActionCommand() == "R'"){	PaintCube.Ri(c);}
			else if(ME.getActionCommand() == "R2"){	PaintCube.R2(c);}
			else if(ME.getActionCommand() == "L"){	PaintCube.L(c);}
			else if(ME.getActionCommand() == "L'"){	PaintCube.Li(c);}
			else if(ME.getActionCommand() == "L2"){	PaintCube.L2(c);}
			else if(ME.getActionCommand() == "M"){	PaintCube.M(c);}
			else if(ME.getActionCommand() == "M'"){	PaintCube.Mi(c);}
			else if(ME.getActionCommand() == "M2"){	PaintCube.M2(c);}
			else if(ME.getActionCommand() == "E"){	PaintCube.E(c);}
			else if(ME.getActionCommand() == "E'"){	PaintCube.Ei(c);}
			else if(ME.getActionCommand() == "E2"){	PaintCube.E2(c);}
			else if(ME.getActionCommand() == "S"){	PaintCube.S(c);}
			else if(ME.getActionCommand() == "S'"){	PaintCube.Si(c);}
			else if(ME.getActionCommand() == "S2"){	PaintCube.S2(c);}
			else if(ME.getActionCommand() == "x"){	PaintCube.R(c);	PaintCube.Mi(c);	PaintCube.Li(c);}
			else if(ME.getActionCommand() == "x'"){	PaintCube.Ri(c);	PaintCube.M(c);	PaintCube.L(c);}
			else if(ME.getActionCommand() == "x2"){	PaintCube.R2(c);	PaintCube.M2(c);	PaintCube.L2(c);}
			else if(ME.getActionCommand() == "y"){	PaintCube.U(c);	PaintCube.Ei(c);	PaintCube.Di(c);}
			else if(ME.getActionCommand() == "y'"){	PaintCube.Ui(c);	PaintCube.E(c);	PaintCube.D(c);}
			else if(ME.getActionCommand() == "y2"){	PaintCube.U2(c);	PaintCube.E2(c);	PaintCube.D2(c);}
			else if(ME.getActionCommand() == "z"){	PaintCube.F(c);	PaintCube.S(c);	PaintCube.Bi(c);}
			else if(ME.getActionCommand() == "z'"){	PaintCube.Fi(c);	PaintCube.Si(c);	PaintCube.B(c);}
			else if(ME.getActionCommand() == "z2"){	PaintCube.F2(c);	PaintCube.S2(c);	PaintCube.B2(c);}
			else if(ME.getActionCommand() == "Solved"){		PaintCube.Solve(c);}
			else if(ME.getActionCommand() == "Scramble"){	PaintCube.Scramble(c);}
			else if(ME.getActionCommand() == "Blank"){		PaintCube.Blank(c);}
			GUI.this.repaint();
		}
	}
	
	public class AMoves implements ActionListener{
		public void actionPerformed(ActionEvent ME){
			String s = txtM.getText() + " ";
			for(int x=0; x<txtM.getDocument().getLength(); x++){
				switch(s.charAt(x)){
				case 'U':
					if(s.charAt(x+1) == '\'' || s.charAt(x+1) == '-' || s.charAt(x+1) == 'i'){
						PaintCube.Ui(c);	x++;}
					else if(s.charAt(x+1) == '2'){
						PaintCube.U2(c);	x++;}
					else if(s.charAt(x+1) == 'w' || s.charAt(x+1) == 'W'){
						if(s.charAt(x+2) == '\'' || s.charAt(x+1) == '-' || s.charAt(x+1) == 'i'){
							PaintCube.Ui(c);	PaintCube.E(c);	x+=2;}
						else if(s.charAt(x+2) == '2'){
							PaintCube.U2(c);	PaintCube.E2(c);	x+=2;}
						else{
							PaintCube.U(c);	PaintCube.Ei(c);	x++;}
					}
					else{
						PaintCube.U(c);}
					break;
				case 'D':
					if(s.charAt(x+1) == '\'' || s.charAt(x+1) == '-' || s.charAt(x+1) == 'i'){
						PaintCube.Di(c);	x++;}
					else if(s.charAt(x+1) == '2'){
						PaintCube.D2(c);	x++;}
					else if(s.charAt(x+1) == 'w' || s.charAt(x+1) == 'W'){
						if(s.charAt(x+2) == '\'' || s.charAt(x+1) == '-' || s.charAt(x+1) == 'i'){
							PaintCube.Di(c);	PaintCube.Ei(c);	x+=2;}
						else if(s.charAt(x+2) == '2'){
							PaintCube.D2(c);	PaintCube.E2(c);	x+=2;}
						else{
							PaintCube.D(c);	PaintCube.E(c);	x++;}
					}
					else{
						PaintCube.D(c);}
					break;
				case 'F':
					if(s.charAt(x+1) == '\'' || s.charAt(x+1) == '-' || s.charAt(x+1) == 'i'){
						PaintCube.Fi(c);	x++;}
					else if(s.charAt(x+1) == '2'){
						PaintCube.F2(c);	x++;}
					else if(s.charAt(x+1) == 'w' || s.charAt(x+1) == 'W'){
						if(s.charAt(x+2) == '\'' || s.charAt(x+1) == '-' || s.charAt(x+1) == 'i'){
							PaintCube.Fi(c);	PaintCube.Si(c);	x+=2;}
						else if(s.charAt(x+2) == '2'){
							PaintCube.F2(c);	PaintCube.S2(c);	x+=2;}
						else{
							PaintCube.F(c);	PaintCube.S(c);	x++;}
					}
					else{
						PaintCube.F(c);}
					break;
				case 'B':
					if(s.charAt(x+1) == '\'' || s.charAt(x+1) == '-' || s.charAt(x+1) == 'i'){
						PaintCube.Bi(c);	x++;}
					else if(s.charAt(x+1) == '2'){
						PaintCube.B2(c);	x++;}
					else if(s.charAt(x+1) == 'w' || s.charAt(x+1) == 'W'){
						if(s.charAt(x+2) == '\'' || s.charAt(x+1) == '-' || s.charAt(x+1) == 'i'){
							PaintCube.Bi(c);	PaintCube.S(c);	x+=2;}
						else if(s.charAt(x+2) == '2'){
							PaintCube.B2(c);	PaintCube.S2(c);	x+=2;}
						else{
							PaintCube.B(c);	PaintCube.Si(c);	x++;}
					}
					else{
						PaintCube.B(c);}
					break;
				case 'R':
					if(s.charAt(x+1) == '\'' || s.charAt(x+1) == '-' || s.charAt(x+1) == 'i'){
						PaintCube.Ri(c);	x++;}
					else if(s.charAt(x+1) == '2'){
						PaintCube.R2(c);	x++;}
					else if(s.charAt(x+1) == 'w' || s.charAt(x+1) == 'W'){
						if(s.charAt(x+2) == '\'' || s.charAt(x+1) == '-' || s.charAt(x+1) == 'i'){
							PaintCube.Ri(c);	PaintCube.M(c);	x+=2;}
						else if(s.charAt(x+2) == '2'){
							PaintCube.R2(c);	PaintCube.M2(c);	x+=2;}
						else{
							PaintCube.R(c);	PaintCube.Mi(c);	x++;}
					}
					else{
						PaintCube.R(c);}
					break;
				case 'L':
					if(s.charAt(x+1) == '\'' || s.charAt(x+1) == '-' || s.charAt(x+1) == 'i'){
						PaintCube.Li(c);	x++;}
					else if(s.charAt(x+1) == '2'){
						PaintCube.L2(c);	x++;}
					else if(s.charAt(x+1) == 'w' || s.charAt(x+1) == 'W'){
						if(s.charAt(x+2) == '\'' || s.charAt(x+1) == '-' || s.charAt(x+1) == 'i'){
							PaintCube.Li(c);	PaintCube.Mi(c);	x+=2;}
						else if(s.charAt(x+2) == '2'){
							PaintCube.L2(c);	PaintCube.M2(c);	x+=2;}
						else{
							PaintCube.L(c);	PaintCube.M(c);	x++;}
					}
					else{
						PaintCube.L(c);}
					break;
				case 'M':
					if(s.charAt(x+1) == '\'' || s.charAt(x+1) == '-' || s.charAt(x+1) == 'i'){
						PaintCube.Mi(c);	x++;}
					else if(s.charAt(x+1) == '2'){
						PaintCube.M2(c);	x++;}
					else{
						PaintCube.M(c);}
					break;
				case 'E':
					if(s.charAt(x+1) == '\'' || s.charAt(x+1) == '-' || s.charAt(x+1) == 'i'){
						PaintCube.Ei(c);	x++;}
					else if(s.charAt(x+1) == '2'){
						PaintCube.E2(c);	x++;}
					else{
						PaintCube.E(c);}
					break;
				case 'S':
					if(s.charAt(x+1) == '\'' || s.charAt(x+1) == '-' || s.charAt(x+1) == 'i'){
						PaintCube.Si(c);	x++;}
					else if(s.charAt(x+1) == '2'){
						PaintCube.S2(c);	x++;}
					else{
						PaintCube.S(c);}
					break;
				case 'x':
					if(s.charAt(x+1) == '\'' || s.charAt(x+1) == '-' || s.charAt(x+1) == 'i'){
						PaintCube.Ri(c);	PaintCube.M(c);	PaintCube.L(c);	x++;}
					else if(s.charAt(x+1) == '2'){
						PaintCube.R2(c);	PaintCube.M2(c);	PaintCube.L2(c);	x++;}
					else{
						PaintCube.R(c);	PaintCube.Mi(c);	PaintCube.Li(c);}
					break;
				case 'y':
					if(s.charAt(x+1) == '\'' || s.charAt(x+1) == '-' || s.charAt(x+1) == 'i'){
						PaintCube.Ui(c);	PaintCube.E(c);	PaintCube.D(c);	x++;}
					else if(s.charAt(x+1) == '2'){
						PaintCube.U2(c);	PaintCube.E2(c);	PaintCube.D2(c);	x++;}
					else{
						PaintCube.U(c);	PaintCube.Ei(c);	PaintCube.Di(c);}
					break;
				case 'z':
					if(s.charAt(x+1) == '\'' || s.charAt(x+1) == '-' || s.charAt(x+1) == 'i'){
						PaintCube.Fi(c);	PaintCube.Si(c);	PaintCube.B(c);	x++;}
					else if(s.charAt(x+1) == '2'){
						PaintCube.F2(c);	PaintCube.S2(c);	PaintCube.B2(c);	x++;}
					else{
						PaintCube.F(c);	PaintCube.S(c);	PaintCube.Bi(c);}
					break;
				case 'u':
					if(s.charAt(x+1) == '\'' || s.charAt(x+1) == '-' || s.charAt(x+1) == 'i'){
						PaintCube.Ui(c);	PaintCube.E(c);	x++;}
					else if(s.charAt(x+1) == '2'){
						PaintCube.U2(c);	PaintCube.E2(c);	x++;}
					else{
						PaintCube.U(c);	PaintCube.Ei(c);}
					break;
				case 'd':
					if(s.charAt(x+1) == '\'' || s.charAt(x+1) == '-' || s.charAt(x+1) == 'i'){
						PaintCube.Di(c);	PaintCube.Ei(c);	x++;}
					else if(s.charAt(x+1) == '2'){
						PaintCube.D2(c);	PaintCube.E2(c);	x++;}
					else{
						PaintCube.D(c);	PaintCube.E(c);}
					break;
				case 'f':
					if(s.charAt(x+1) == '\'' || s.charAt(x+1) == '-' || s.charAt(x+1) == 'i'){
						PaintCube.Fi(c);	PaintCube.Si(c);	x++;}
					else if(s.charAt(x+1) == '2'){
						PaintCube.F2(c);	PaintCube.S2(c);	x++;}
					else{
						PaintCube.F(c);	PaintCube.S(c);}
					break;
				case 'b':
					if(s.charAt(x+1) == '\'' || s.charAt(x+1) == '-' || s.charAt(x+1) == 'i'){
						PaintCube.Bi(c);	PaintCube.S(c);	x++;}
					else if(s.charAt(x+1) == '2'){
						PaintCube.B2(c);	PaintCube.S2(c);	x++;}
					else{
						PaintCube.B(c);	PaintCube.Si(c);}
					break;
				case 'r':
					if(s.charAt(x+1) == '\'' || s.charAt(x+1) == '-' || s.charAt(x+1) == 'i'){
						PaintCube.Ri(c);	PaintCube.M(c);	x++;}
					else if(s.charAt(x+1) == '2'){
						PaintCube.R2(c);	PaintCube.M2(c);	x++;}
					else{
						PaintCube.R(c);	PaintCube.Mi(c);}
					break;
				case 'l':
					if(s.charAt(x+1) == '\'' || s.charAt(x+1) == '-' || s.charAt(x+1) == 'i'){
						PaintCube.Li(c);	PaintCube.Mi(c);	x++;}
					else if(s.charAt(x+1) == '2'){
						PaintCube.L2(c);	PaintCube.M2(c);	x++;}
					else{
						PaintCube.L(c);	PaintCube.M(c);}
					break;
				}
			}
			GUI.this.repaint();
		}
	}
	
	public class Painting extends JPanel{
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(pcImage, 5, 5, null);
			PaintCube.Paint(g, c);
			
			this.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					PaintCube.StickerClick(e.getX(), e.getY(), c, PanPCC.getBackground());
					PanPCC.setBackground(PaintCube.CenterClick(e.getX(), e.getY(), c, PanPCC.getBackground()));
					Painting.this.repaint();
					GUI.this.requestFocusInWindow();
				}
			});
		}
	}
}