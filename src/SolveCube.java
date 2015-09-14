import java.awt.Color;

public class SolveCube{
	
	// UBR URF UFL ULB DRB DFR DLF (DBL)
	// UB UR UF UL DB DR DF DL BR FR FL (BL)
	// UDRLFB  1'2
	public Color[][] c = new Color[6][9];
	CoordinateLUT cLUT;
	byte[] phase1Solution;
	byte[] phase2Solution;
	static final short[][] edgeStickers = {{0,1,5,1},{0,5,4,1},{0,7,3,1},{0,3,2,1},{1,7,5,7},{1,5,4,7},
										   {1,1,3,7},{1,3,2,7},{5,3,4,5},{3,5,4,3},{3,3,2,5},{5,5,2,3}};
	static final short[][] cornStickers = {{0,2,5,0,4,2},{0,8,4,0,3,2},{0,6,3,0,2,2},{0,0,2,0,5,2},
										   {1,8,4,8,5,6},{1,2,3,8,4,6},{1,0,2,8,3,6},{1,6,5,8,2,6}};
	static final short[] p3 = {1,3,9,27,81,243,729};
	static final short[][] choose = {{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
									 { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11},
									 { 0, 0, 1, 3, 6,10,15,21,28,36,45,55},
									 { 0, 0, 0, 1, 4,10,20,35,56,84,120,165}};

	public SolveCube(Color[][] c, CoordinateLUT cLUT){
		for(int i = 0; i < c.length; i++){
			this.c[i] = c[i].clone();
		}
		this.cLUT = cLUT;
	}
	
	public void solve(){
		CubeSearchState1 root = findPhase1Root();
		ida_star1(root);
		for(int i=0; i<phase1Solution.length; i++){
			PaintCube.makeMove(c, phase1Solution[i]);
		}
		CubeSearchState2 root2 = findPhase2Root();
		ida_star2(root2);
	}
	
	public CubeSearchState1 findPhase1Root(){
		short coc = 0;
		short eoc = 0;
		short udc = 0;
		for(short corners=0; corners<7; corners++){
			Color sticker = c[cornStickers[corners][2]][cornStickers[corners][3]];
			if(sticker == Color.WHITE || sticker == Color.YELLOW){
				coc += p3[corners];
			}
			sticker = c[cornStickers[corners][4]][cornStickers[corners][5]];
			if(sticker == Color.WHITE || sticker == Color.YELLOW){
				coc += 2*p3[corners];
			}
		}
		for(short edges=0; edges<11; edges++){
			Color sticker0 = c[edgeStickers[edges][0]][edgeStickers[edges][1]];
			Color sticker1 = c[edgeStickers[edges][2]][edgeStickers[edges][3]];
			if(sticker1 == Color.WHITE || sticker1 == Color.YELLOW){
				eoc += 1<<edges;
			}else if(sticker0 != Color.WHITE && sticker0 != Color.YELLOW){
				if(sticker1 == Color.RED || sticker1.equals(new Color(255,140,0))){
					eoc += 1<<edges;
				}
			}
		}
		short udEdges = 3;
		short edgesLeft = 11;
		for(;udEdges>=0;edgesLeft--){
			Color sticker0 = c[edgeStickers[edgesLeft][0]][edgeStickers[edgesLeft][1]];
			Color sticker1 = c[edgeStickers[edgesLeft][2]][edgeStickers[edgesLeft][3]];
			if(sticker0 != Color.WHITE && sticker0 != Color.YELLOW && sticker1 != Color.WHITE && sticker1 != Color.YELLOW){
				udEdges--;
			}else{
				udc += choose[udEdges][edgesLeft];
			}
		}
		return new CubeSearchState1(cLUT,coc,eoc,udc);
	}
	
	public CubeSearchState2 findPhase2Root(){
		int cpc = 0;
		int epc = 0;
		short ud2c = 0;
		short[] cornArray = new short[8];
		for(short corners=0; corners<8; corners++){
			Color sticker = c[cornStickers[corners][0]][cornStickers[corners][1]];
			Color sticker2 = c[cornStickers[corners][2]][cornStickers[corners][3]];
			if(sticker == Color.WHITE){
				if(sticker2 == Color.RED)			cornArray[corners] = 2;
				else if(sticker2.equals(new Color(255,140,0)))	cornArray[corners] = 0;
				else if(sticker2 == Color.BLUE)		cornArray[corners] = 1;
				else if(sticker2 == Color.GREEN)	cornArray[corners] = 3;
			}
			else if(sticker == Color.YELLOW){
				if(sticker2 == Color.RED)			cornArray[corners] = 5;
				else if(sticker2.equals(new Color(255,140,0)))	cornArray[corners] = 7;
				else if(sticker2 == Color.BLUE)		cornArray[corners] = 4;
				else if(sticker2 == Color.GREEN)	cornArray[corners] = 6;
			}
		}
		cpc = CoordinateLUT.getCPCoordinate(cornArray);
		short[] edgeArray = new short[8];
		for(short edges=0; edges<8; edges++){
			Color sticker = c[edgeStickers[edges][0]][edgeStickers[edges][1]];
			Color sticker2 = c[edgeStickers[edges][2]][edgeStickers[edges][3]];
			if(sticker == Color.WHITE){
				if(sticker2 == Color.RED)			edgeArray[edges] = 2;
				else if(sticker2.equals(new Color(255,140,0)))	edgeArray[edges] = 0;
				else if(sticker2 == Color.BLUE)		edgeArray[edges] = 1;
				else if(sticker2 == Color.GREEN)	edgeArray[edges] = 3;
			}
			else if(sticker == Color.YELLOW){
				if(sticker2 == Color.RED)			edgeArray[edges] = 6;
				else if(sticker2.equals(new Color(255,140,0)))	edgeArray[edges] = 4;
				else if(sticker2 == Color.BLUE)		edgeArray[edges] = 5;
				else if(sticker2 == Color.GREEN)	edgeArray[edges] = 7;
			}
		}
		epc = CoordinateLUT.getCPCoordinate(edgeArray);
		short[] udEdgeArray = new short[4];
		for(short edges=0; edges<4; edges++){
			Color sticker = c[edgeStickers[edges+8][0]][edgeStickers[edges+8][1]];
			Color sticker2 = c[edgeStickers[edges+8][2]][edgeStickers[edges+8][3]];
			if(sticker == Color.RED){
				if(sticker2 == Color.BLUE)		udEdgeArray[edges] = 1;
				else if(sticker2 == Color.GREEN)udEdgeArray[edges] = 2;
			}
			else if(sticker.equals(new Color(255,140,0))){
				if(sticker2 == Color.BLUE)		udEdgeArray[edges] = 0;
				else if(sticker2 == Color.GREEN)udEdgeArray[edges] = 3;
			}
		}
		ud2c = CoordinateLUT.getUD2Coordinate(udEdgeArray);
		return new CubeSearchState2(cLUT,cpc,epc,ud2c);
	}

	public int ida_star1(CubeSearchState1 root){
		int bound = h1(root);
		while(true){
			int t = search1(root, 0, bound);
			if(t == -1){
				return -1;//return found
			}
			if(t == Integer.MAX_VALUE){
				return -2;//return not found
			}
			bound = t;
		}
	}

	public int ida_star2(CubeSearchState2 root){
		int bound = h2(root);
		while(true){
			int t = search2(root, 0, bound);
			if(t == -1){
				return -1;//return found
			}
			if(t == Integer.MAX_VALUE){
				return -2;//return not found
			}
			bound = t;
		}
	}

	public int search1(CubeSearchState1 node, int g, int bound){
		int f = g + h1(node);
		if (f > bound){
			return f;
		}
		if (node.coc==0 && node.eoc==0 && node.ud1c==0){
			phase1Solution = new byte[g];
			return -1;//solution found
		}
		int min = Integer.MAX_VALUE;
		for(CubeSearchState1 succ: successors1(node)){
			int t = search1(succ, g+1, bound);
			if(t == -1){
				phase1Solution[g] = (byte) succ.move;
				return -1;
			}
			if(t < min){
				min = t;
			}
		}
		return min;
	}

	public int search2(CubeSearchState2 node, int g, int bound){
		int f = g + h2(node);
		if (f > bound){
			return f;
		}
		if (node.cpc==0 && node.epc==0 && node.ud2c==0){
			phase2Solution = new byte[g];
			return -1;//solution found
		}
		int min = Integer.MAX_VALUE;
		for(CubeSearchState2 succ: successors2(node)){
			int t = search2(succ, g+1, bound);
			if(t == -1){
				phase2Solution[g] = (byte) succ.move;
				return -1;
			}
			if(t < min){
				min = t;
			}
		}
		return min;
	}
	
	private int h1(CubeSearchState1 node) {
		int max = cLUT.cocH[node.coc];
		if(max<cLUT.eocH[node.eoc]){
			max = cLUT.eocH[node.eoc];
		}
		if(max<cLUT.ud1cH[node.ud1c]){
			max = cLUT.ud1cH[node.ud1c];
		}
		return max;
	}
	
	private int h2(CubeSearchState2 node) {
		int max = cLUT.cpcH[node.cpc];
		if(max<cLUT.epcH[node.epc]){
			max = cLUT.epcH[node.epc];
		}
		if(max<cLUT.ud2cH[node.ud2c]){
			max = cLUT.ud2cH[node.ud2c];
		}
		return max;
	}

	private CubeSearchState1[] successors1(CubeSearchState1 node) {
		CubeSearchState1[] succs = new CubeSearchState1[15];
		int prevMove = node.move/3*3;
		for(int i=0, j=0; i<15; i++, j++){
			if(j==prevMove){
				j+=3;
			}
			succs[i] = new CubeSearchState1(j, node);
		}
		return succs;
	}
	//UDRLFB  1'2
	private CubeSearchState2[] successors2(CubeSearchState2 node) {
		CubeSearchState2[] succs;
		if(node.move<6){
			succs = new CubeSearchState2[7];
			int prevMove = node.move/3*3;
			for(int i=0, j=0; i<7; i++, j++){
				if(j==prevMove){
					j+=3;
				}
				succs[i] = new CubeSearchState2(j, node);
			}
		}else{
			succs = new CubeSearchState2[9];
			int prevMove = node.move;
			for(int i=0, j=0; i<9; i++, j++){
				if(j==prevMove){
					j++;
				}
				succs[i] = new CubeSearchState2(j, node);
			}
		}
		return succs;
	}
	
	public byte[] getSolution(){
		byte[] solution = new byte[phase1Solution.length+phase2Solution.length];
		for(int i=0; i<phase1Solution.length; i++){
			solution[i] = phase1Solution[i];
		}
		for(int i=0; i<phase2Solution.length; i++){
			int move = phase2Solution[i];
			if(move>=6){
				move = move*3-11;
			}
			solution[i+phase1Solution.length] = (byte) move;
		}
		return solution;
	}
}
