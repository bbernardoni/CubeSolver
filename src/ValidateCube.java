import java.awt.Color;

public class ValidateCube{
	
	public int valid = 0;
	public Color[][] c=new Color[6][9];
	public int[] cornersP = new int[8];//WGO WOB WRG WBR YGR YRB YOG YBO W Y R O B G X
	public int[] edgesP = new int[12]; //WO WG WB WR YR YG YB YO RG RB OB OG W Y R O B G X
	// WOB WBR WRG WGO YBO YRB YGR YOG
	// WO WB WR WG YO YB YR YG OB RB RG OG
	public int[] cornersO = new int[8];//W/Y/colored		up cw ccw
	public int[] edgesO = new int[12]; //W/Y/R/O/colored	up flipped
	//[color][piece]
	static final boolean[][] coloredCorners = {{ true, true, true, true,false,false,false,false, true,false,false,false,false,false,false},
											   {false,false,false,false, true, true, true, true,false, true,false,false,false,false,false},
											   {false,false, true, true, true, true,false,false,false,false, true,false,false,false,false},
											   { true, true,false,false,false,false, true, true,false,false,false, true,false,false,false},
											   {false, true,false, true,false, true,false, true,false,false,false,false, true,false,false},
											   { true,false, true,false, true,false, true,false,false,false,false,false,false, true,false}};
	static final boolean[][] coloredEdges = {{ true, true, true, true,false,false,false,false,false,false,false,false, true,false,false,false,false,false,false},
											 {false,false,false,false, true, true, true, true,false,false,false,false,false, true,false,false,false,false,false},
											 {false,false,false, true, true,false,false,false, true, true,false,false,false,false, true,false,false,false,false},
											 { true,false,false,false,false,false,false, true,false,false, true, true,false,false,false, true,false,false,false},
											 {false,false, true,false,false,false, true,false,false, true, true,false,false,false,false,false, true,false,false},
											 {false, true,false,false,false, true,false,false, true,false,false, true,false,false,false,false,false, true,false}};
	static final int[][] wind = {{0,0,6,5,3,4},{0,0,5,6,4,3},{5,6,0,0,2,1},{6,5,0,0,1,2},{4,3,1,2,0,0},{3,4,2,1,0,0}};
	static final int[][] cornersId = {{0,0,2,1,3,0},{0,0,5,6,7,4},{3,4,0,0,5,2},{0,7,0,0,1,6},{1,5,3,7,0,0},{2,6,4,0,0,0}};
	static final int[][] edgesId = {{0,0,3,0,2,1},{0,0,4,7,6,5},{3,4,0,0,9,8},{0,7,0,0,10,11},{2,6,9,10,0,0},{1,5,8,11,0,0}};
	static final Color[] colors = {Color.WHITE, Color.YELLOW, Color.RED, new Color(255,140,0), Color.BLUE, Color.GREEN};

	public ValidateCube(Color[][] c){
		for(int i = 0; i < c.length; i++){
			this.c[i] = c[i].clone();
		}
	}
	
	public int validate(){
		//rotate in position
		rotate();
		//make sure all pieces are possible
		checkCubies();
		if(valid!=0)	return valid;
		//test for duplicate cubies
		checkDuplicates();
		if(valid!=0)	return valid+(1<<20);
		//test for right number per color
		checkNumber();
		if(valid!=0)	return valid+(2<<20);
		//test for bad permutation or orientation
		checkPO();
		if(valid!=0)	return valid+(3<<20);
		return -1;
	}

	private void rotate() {
		if(c[0][4] != Color.WHITE || c[3][4] != Color.RED){
			if(c[1][4] == Color.WHITE){
				PaintCube.R2(c);PaintCube.M2(c);PaintCube.L2(c);
			}else if(c[2][4] == Color.WHITE){
				PaintCube.F(c);	PaintCube.S(c);	PaintCube.Bi(c);
			}else if(c[3][4] == Color.WHITE){
				PaintCube.R(c);	PaintCube.Mi(c);PaintCube.Li(c);
			}else if(c[4][4] == Color.WHITE){
				PaintCube.Fi(c);PaintCube.Si(c);PaintCube.B(c);
			}else if(c[5][4] == Color.WHITE){
				PaintCube.Ri(c);PaintCube.M(c);	PaintCube.L(c);
			}if(c[2][4] == Color.RED){
				PaintCube.Ui(c);PaintCube.E(c);	PaintCube.D(c);
			}else if(c[4][4] == Color.RED){
				PaintCube.U(c);	PaintCube.Ei(c);PaintCube.Di(c);
			}else if(c[5][4] == Color.RED){
				PaintCube.U2(c);PaintCube.E2(c);PaintCube.D2(c);
			}
		}
	}
	
	private void checkCubies() {
		addCubie(0 ,c[0][0],c[2][0],c[5][2]);
		addCubie(1 ,c[0][2],c[5][0],c[4][2]);
		addCubie(2 ,c[0][6],c[3][0],c[2][2]);
		addCubie(3 ,c[0][8],c[4][0],c[3][2]);
		addCubie(4 ,c[1][0],c[2][8],c[3][6]);
		addCubie(5 ,c[1][2],c[3][8],c[4][6]);
		addCubie(6 ,c[1][6],c[5][8],c[2][6]);
		addCubie(7 ,c[1][8],c[4][8],c[5][6]);
		addCubie(0 ,c[0][1],c[5][1]);
		addCubie(1 ,c[0][3],c[2][1]);
		addCubie(2 ,c[0][5],c[4][1]);
		addCubie(3 ,c[0][7],c[3][1]);
		addCubie(4 ,c[1][1],c[3][7]);
		addCubie(5 ,c[1][3],c[2][7]);
		addCubie(6 ,c[1][5],c[4][7]);
		addCubie(7 ,c[1][7],c[5][7]);
		addCubie(8 ,c[3][3],c[2][5]);
		addCubie(9 ,c[3][5],c[4][3]);
		addCubie(10,c[5][3],c[4][5]);
		addCubie(11,c[5][5],c[2][3]);
	}
	public void addCubie(int nub, Color c1, Color c2, Color c3){
		int cc1 = 14;	int cc2 = 16;	int cc3 = 18;
		if		(c1 == Color.WHITE)				 cc1=1;	else if(c1 == Color.YELLOW)cc1=2;	else if(c1 == Color.RED)  cc1=3;
		else if	(c1.equals(new Color(255,140,0)))cc1=4;	else if(c1 == Color.BLUE)  cc1=5;	else if(c1 == Color.GREEN)cc1=6;
		if		(c2 == Color.WHITE)				 cc2=1;	else if(c2 == Color.YELLOW)cc2=2;	else if(c2 == Color.RED)  cc2=3;
		else if	(c2.equals(new Color(255,140,0)))cc2=4;	else if(c2 == Color.BLUE)  cc2=5;	else if(c2 == Color.GREEN)cc2=6;
		if		(c3 == Color.WHITE)				 cc3=1;	else if(c3 == Color.YELLOW)cc3=2;	else if(c3 == Color.RED)  cc3=3;
		else if	(c3.equals(new Color(255,140,0)))cc3=4;	else if(c3 == Color.BLUE)  cc3=5;	else if(c3 == Color.GREEN)cc3=6;
		if(cc1+(cc1%2) == cc2+(cc2%2) || cc1+(cc1%2) == cc3+(cc3%2) || cc2+(cc2%2) == cc3+(cc3%2)){
			valid |= 1<<nub;//check for corners containing same or opposite colors
		}else if((cc1+cc2+cc3)%2 == 0 && ((cc1>cc2&&cc2>cc3)||(cc2>cc3&&cc3>cc1)||(cc3>cc1&&cc1>cc2))){
			if(c1!=Color.GRAY && c2!=Color.GRAY && c3!=Color.GRAY)
				valid |= 1<<nub;//check is corners wound correctly
		}else if((cc1+cc2+cc3)%2 == 1 && ((cc1<cc2&&cc2<cc3)||(cc2<cc3&&cc3<cc1)||(cc3<cc1&&cc1<cc2))){
			if(c1!=Color.GRAY && c2!=Color.GRAY && c3!=Color.GRAY)
				valid |= 1<<nub;//check is corners wound correctly
		}
		int count = 0;
		if(c1 != Color.GRAY)	count+=2;
		if(c2 != Color.GRAY)	count+=3;
		if(c3 != Color.GRAY)	count+=4;
		if(count==0){
			cornersP[nub] = 14;
			cornersO[nub] = 3;
		}else if(count==2){
			cornersP[nub] = 7+cc1;
			cornersO[nub] = 0;
		}else if(count==3){
			cornersP[nub] = 7+cc2;
			cornersO[nub] = 1;
		}else if(count==4){
			cornersP[nub] = 7+cc3;
			cornersO[nub] = 2;
		}else{
			if(count==5){
				cc3 = wind[cc1-1][cc2-1];
				c3 = colors[cc3-1];
			}
			if(count==6){
				cc2 = wind[cc3-1][cc1-1];
				c2 = colors[cc2-1];
			}
			if(count==7){
				cc1 = wind[cc2-1][cc3-1];
				c1 = colors[cc1-1];
			}
			cornersP[nub] = cornersId[cc1-1][cc2-1];
			if(cc1<=2){
				cornersO[nub] = 0;
			}else if(cc2<=2){
				cornersO[nub] = 1;
			}else if(cc3<=2){
				cornersO[nub] = 2;
			}
		}
	}
	public void addCubie(int nub, Color c1, Color c2){
		int cc1 = 14;	int cc2 = 16;
		if		(c1 == Color.WHITE)				 cc1=1;	else if(c1 == Color.YELLOW)cc1=2;	else if(c1 == Color.RED)  cc1=3;
		else if	(c1.equals(new Color(255,140,0)))cc1=4;	else if(c1 == Color.BLUE)  cc1=5;	else if(c1 == Color.GREEN)cc1=6;
		if		(c2 == Color.WHITE)				 cc2=1;	else if(c2 == Color.YELLOW)cc2=2;	else if(c2 == Color.RED)  cc2=3;
		else if	(c2.equals(new Color(255,140,0)))cc2=4;	else if(c2 == Color.BLUE)  cc2=5;	else if(c2 == Color.GREEN)cc2=6;
		if(cc1+(cc1%2) == cc2+(cc2%2)){
			valid |= 1<<(nub+8);
		}
		int count = 0;
		if(c1 != Color.GRAY)	count++;
		if(c2 != Color.GRAY)	count+=2;
		if(count==0){
			edgesP[nub] = 18;
			edgesO[nub] = 2;
		}else if(count==1){
			edgesP[nub] = 11+cc1;
			edgesO[nub] = 0;
		}else if(count==2){
			edgesP[nub] = 11+cc2;
			edgesO[nub] = 1;
		}else{
			edgesP[nub] = edgesId[cc1-1][cc2-1];
			if(cc1<=2||(cc2>2&&(cc1==3||cc1==4))){
				edgesO[nub] = 0;
			}else if(cc2<=2||(cc1>2&&(cc2==3||cc2==4))){
				edgesO[nub] = 1;
			}
		}
	}

	private void checkDuplicates() {
		for(int i=0; i<cornersP.length-1; i++){
			if(cornersP[i]<8){
				for(int j=i+1; j<cornersP.length; j++){
					if(cornersP[i]==cornersP[j]){
						valid |= 1<<cornersP[i];
					}
				}
			}
		}
		for(int i=0; i<edgesP.length-1; i++){
			if(edgesP[i]<12){
				for(int j=i+1; j<edgesP.length; j++){
					if(edgesP[i]==edgesP[j]){
						valid |= 1<<(edgesP[i]+8);
					}
				}
			}
		}
	}

	private void checkNumber() {
		for(int i=0; i<coloredCorners.length; i++){
			int defCount = 0;
			int undefCount = 0;
			for(int j=0; j<cornersP.length; j++){
				if(coloredCorners[i][cornersP[j]]){
					if(cornersP[j]>=8){
						undefCount++;
					}else{
						defCount++;
					}
				}
			}
			if(defCount+undefCount>4){
				valid |= 1<<i;
			}else if(undefCount==1 && defCount==3){
				int undefIndex = 14;
				int[] defCorners = new int[3];
				int defCornersIndex = 0;
				for(int j=0; j<cornersP.length; j++){
					if(coloredCorners[i][cornersP[j]]){
						if(cornersP[j]>=8){
							undefIndex=j;
						}else{
							defCorners[defCornersIndex]=cornersP[j];
							defCornersIndex++;
						}
					}
				}
				for(int j=0; j<8; j++){
					if(coloredCorners[i][j]){
						if(j!=defCorners[0]&&j!=defCorners[1]&&j!=defCorners[2]){
							int color = cornersP[undefIndex]-8;
							cornersP[undefIndex]=j;
							if(color>=2){
								int offset = ((cornersP[undefIndex]+1)/2+color/2)%2+1;
								cornersO[undefIndex]=(cornersO[undefIndex]+offset)%3;
							}
						}
					}
				}
			}
		}
		for(int i=0; i<coloredEdges.length; i++){
			int defCount = 0;
			int undefCount = 0;
			for(int j=0; j<edgesP.length; j++){
				if(coloredEdges[i][edgesP[j]]){
					if(edgesP[j]>=12){
						undefCount++;
					}else{
						defCount++;
					}
				}
			}
			if(defCount+undefCount>4){
				valid |= 1<<(i+6);
			}else if(undefCount==1 && defCount==3){
				int undefIndex = 18;
				int[] defEdges = new int[3];
				int defEdgesIndex = 0;
				for(int j=0; j<edgesP.length; j++){
					if(coloredEdges[i][edgesP[j]]){
						if(edgesP[j]>=12){
							undefIndex=j;
						}else{
							defEdges[defEdgesIndex]=edgesP[j];
							defEdgesIndex++;
						}
					}
				}
				for(int j=0; j<12; j++){
					if(coloredEdges[i][j]){
						if(j!=defEdges[0]&&j!=defEdges[1]&&j!=defEdges[2]){
							int color = edgesP[undefIndex]-12;
							edgesP[undefIndex]=j;
							if(j<8){
								if(color>=2){
									edgesO[undefIndex]=(edgesO[undefIndex]+1)%2;
								}
							}else{
								if(color!=2&&color!=3){
									edgesO[undefIndex]=(edgesO[undefIndex]+1)%2;
								}
							}
						}
					}
				}
			}
		}
	}

	private void checkPO() {
		boolean cornersDefined = cornersDefined();
		boolean edgesDefined = edgesDefined();
		if(cornersDefined){
			int orientationSum = 0;
			for(int i=0; i<cornersO.length; i++){
				orientationSum += cornersO[i];
			}
			if(orientationSum%3 != 0){
				valid |= 1;
			}
		}
		if(edgesDefined){
			int orientationSum = 0;
			for(int i=0; i<edgesO.length; i++){
				orientationSum += edgesO[i];
			}
			if(orientationSum%2 != 0){
				valid |= 2;
			}
		}
		if(cornersDefined&&edgesDefined){
			int evenCycles = 0;
			boolean[] cornersCount = new boolean[8];
			int count = 0;
			int init = 0;
			int cur = 0;
			int cycleCount;
			while(count<8){
				for(int i=0; i<cornersCount.length; i++){
					if(!cornersCount[i]){
						init = i;
						cur = i;
						break;
					}
				}
				cycleCount = 0;
				do{
					cycleCount++;
					cornersCount[cur] = true;
					cur = cornersP[cur];
				}while(init!=cur);
				if(cycleCount%2==0){
					evenCycles++;
				}
				count+=cycleCount;
			}
			boolean[] edgesCount = new boolean[12];
			count = 0;
			init = 0;
			cur = 0;
			while(count<12){
				for(int i=0; i<edgesCount.length; i++){
					if(!edgesCount[i]){
						init = i;
						cur = i;
						break;
					}
				}
				cycleCount = 0;
				do{
					cycleCount++;
					edgesCount[cur] = true;
					cur = edgesP[cur];
				}while(init!=cur);
				if(cycleCount%2==0){
					evenCycles++;
				}
				count+=cycleCount;
			}
			if(evenCycles%2==1){
				valid |= 4;
			}
		}
	}
	public boolean cornersDefined(){
		for(int i=0; i<cornersP.length; i++){
			if(cornersP[i]>=8){
				return false;
			}
		}
		return true;
	}
	public boolean edgesDefined(){
		for(int i=0; i<edgesP.length; i++){
			if(edgesP[i]>=12){
				return false;
			}
		}
		return true;
	}

	public String getErrorMessage(int valid) {
		int stage = valid>>20;
		int error = valid & 0xFFFFF;
		String out = null;
		switch(stage){
		case 0://make sure all pieces are possible
			out = "The following pieces are not possible\nImpossible corners="+getCornerStr(error)+
				"\nImpossible edges="+getEdgesStr(error);
			
			break;
		case 1://test for duplicate cubies
			out = "The following pieces duplicated\nDuplicated corners="+getCornerStr(error)+
					"\nDuplicated edges="+getEdgesStr(error);
			break;
		case 2://test for right number per color
			out = "The following sides have too many people\nSides with too many corners="+getCornerColorStr(error)+
					"\nSides with too many edges="+getEdgesColorStr(error);
			break;
		case 3://test for bad permutation or orientation
			if((error&1) != 0){
				out = "Corners have invaild orientation";
			}
			if((error&2) != 0){
				out = "Edges have invaild orientation";
			}
			if((error&4) != 0){
				out = "There is invaild piece permutation";
			}
			break;
		}
		return out;
	}
	private String getPieceStr(int piece){
		switch(piece){
		case 0:		return "WGO";
		case 1:		return "WOB";
		case 2:		return "WRG";
		case 3:		return "WBR";
		case 4:		return "YGR";
		case 5:		return "YRB";
		case 6:		return "YOG";
		case 7:		return "YOB";
		case 8:		return "WO";
		case 9:		return "WG";
		case 10:	return "WB";
		case 11:	return "WR";
		case 12:	return "YR";
		case 13:	return "YG";
		case 14:	return "YB";
		case 15:	return "YO";
		case 16:	return "RG";
		case 17:	return "RB";
		case 18:	return "OB";
		case 19:	return "OG";
		default:	return "Invalid piece";
		}
	}
	private String getColorStr(int color){
		switch(color){
		case 0:		return "White";
		case 1:		return "Yellow";
		case 2:		return "Red";
		case 3:		return "Orange";
		case 4:		return "Blue";
		case 5:		return "Green";
		default:	return "Invalid color";
		}
	}
	private String getCornerStr(int error){
		String out = "";
		boolean first = true;
		for(int i=0; i<8; i++){
			if((error&(1<<i)) != 0){
				if(!first){
					out += ", ";
				}else{
					first = false;
				}
				out += getPieceStr(i);
			}
		}
		return out;
	}
	private String getEdgesStr(int error){
		String out = "";
		boolean first = true;
		for(int i=8; i<20; i++){
			if((error&(1<<i)) != 0){
				if(!first){
					out += ", ";
				}else{
					first = false;
				}
				out += getPieceStr(i);
			}
		}
		return out;
	}
	private String getCornerColorStr(int error){
		String out = "";
		boolean first = true;
		for(int i=0; i<6; i++){
			if((error&(1<<i)) != 0){
				if(!first){
					out += ", ";
				}else{
					first = false;
				}
				out += getColorStr(i);
			}
		}
		return out;
	}
	private String getEdgesColorStr(int error){
		String out = "";
		boolean first = true;
		for(int i=6; i<12; i++){
			if((error&(1<<i)) != 0){
				if(!first){
					out += ", ";
				}else{
					first = false;
				}
				out += getColorStr(i-6);
			}
		}
		return out;
	}
}
