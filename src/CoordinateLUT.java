import java.util.LinkedList;
import java.util.Queue;

public class CoordinateLUT {

	// UBR URF UFL ULB DRB DFR DLF (DBL)
	// UB UR UF UL DB DR DF DL BR FR FL (BL)
	// UDRLFB  1'2
	public short[][] coc = new short[18][2187]; //corner orientation coordinate
	public short[][] eoc = new short[18][2048]; //edge orientation coordinate
	public short[][] ud1c = new short[18][495];  //UD slice phase 1 permutation coordinate
	public short[] cocH = new short[2187]; //corner orientation coordinate
	public short[] eocH = new short[2048]; //edge orientation coordinate
	public short[] ud1cH = new short[495];  //UD slice phase 1 permutation coordinate
	public int[][] cpc = new int[10][40320]; //corner orientation coordinate
	public int[][] epc = new int[10][40320]; //edge orientation coordinate
	public short[][] ud2c = new short[10][24];  //UD slice phase 2 permutation coordinate
	public int[] cpcH = new int[40320]; //corner orientation coordinate
	public int[] epcH = new int[40320]; //edge orientation coordinate
	public short[] ud2cH = new short[24];  //UD slice phase 2 permutation coordinate
	static final short[] p3 = {1,3,9,27,81,243,729};
	static final short[] fact = {0,1,2,6,24,120,720,5040};
	static final short[][] cocOffset = {{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},
										{1,2,0,0,2,1,0},{0,0,1,2,0,0,2},
										{0,1,2,0,0,2,1},{2,0,0,1,1,0,0}};
	static final short[][] choose = {{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
									 { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11},
									 { 0, 0, 1, 3, 6,10,15,21,28,36,45,55},
									 { 0, 0, 0, 1, 4,10,20,35,56,84,120,165}};
	static final short[][] cornP = {{0,1,2,3},{7,6,5,4},{0,4,5,1},{2,6,7,3},{1,5,6,2},{0,3,7,4}};
	static final short[][] edgeP = {{0,1,2,3},{7,6,5,4},{1,8,5,9},{3,10,7,11},{2,9,6,10},{0,11,4,8}};
	static final short[] edge2P = {0,0,1,3,2,0};
	static final short[] edgeUD2P = {0,0,0,2,1,3};
	
	
	public CoordinateLUT(){
		cocLUT();
		cleanEdgeArray();
		xorEdgeArrayF();
		xorEdgeArrayB();
		udcLUT();
		generateMoveLengthTables(cocH,coc);
		generateMoveLengthTables(eocH,eoc);
		generateMoveLengthTables(ud1cH,ud1c);
		cpcLUT();
		epcLUT();
		ud2cLUT();
		generateMoveLengthTables(cpcH,cpc);
		generateMoveLengthTables(epcH,epc);
		generateMoveLengthTables(ud2cH,ud2c);
	}
	
	private void makeMoveEdge(boolean[] occupied, int move){
		boolean temp = occupied[edgeP[move][3]];
		occupied[edgeP[move][3]] = occupied[edgeP[move][2]];
		occupied[edgeP[move][2]] = occupied[edgeP[move][1]];
		occupied[edgeP[move][1]] = occupied[edgeP[move][0]];
		occupied[edgeP[move][0]] = temp;
	}
	
	private void makeMoveEdge(short[] occupied, int move){
		short temp = occupied[edgeP[move][3]];
		occupied[edgeP[move][3]] = occupied[edgeP[move][2]];
		occupied[edgeP[move][2]] = occupied[edgeP[move][1]];
		occupied[edgeP[move][1]] = occupied[edgeP[move][0]];
		occupied[edgeP[move][0]] = temp;
	}
	
	private boolean[] getEOCOccupiedArray(short coordinate) {
		boolean[] occupied = new boolean[12];
		int parity=0;
		for(int i=0; i<11; i++){
			occupied[i] = ((coordinate&(1<<i)) != 0);
			if(occupied[i]){
				parity++;
			}
		}
		occupied[11] = ((parity%2) != 0);
		return occupied;
	}
	private short getEOCoordinate(boolean[] occupied) {
		short coordinate = 0;
		for(int i=10; i>=0; i--){
			coordinate *= 2;
			if(occupied[i]){
				coordinate++;
			}
		}
		return coordinate;
	}
	private void cleanEdgeArray(){
		for(short i=0; i<eoc[0].length; i++){
			for(short move=0; move<4; move++){
				boolean[] occupied = getEOCOccupiedArray(i);
				makeMoveEdge(occupied, move);
				eoc[move*3][i] = getEOCoordinate(occupied);
				makeMoveEdge(occupied, move);
				eoc[move*3+1][i] = getEOCoordinate(occupied);
				makeMoveEdge(occupied, move);
				eoc[move*3+2][i] = getEOCoordinate(occupied);
			}
		}
	}
	private void xorEdgeArrayF() {
		for(short i=0; i<eoc[0].length; i++){
			boolean[] occupied = getEOCOccupiedArray(i);
			makeMoveEdge(occupied, 4);
			eoc[12][i] = (short) (getEOCoordinate(occupied)^0x644);
			makeMoveEdge(occupied, 4);
			eoc[13][i] = getEOCoordinate(occupied);
			makeMoveEdge(occupied, 4);
			eoc[14][i] = (short) (getEOCoordinate(occupied)^0x644);
		}
	}
	private void xorEdgeArrayB() {
		for(short i=0; i<eoc[0].length; i++){
			boolean[] occupied = getEOCOccupiedArray(i);
			makeMoveEdge(occupied, 5);
			eoc[15][i] = (short) (getEOCoordinate(occupied)^0x111);
			makeMoveEdge(occupied, 5);
			eoc[16][i] = getEOCoordinate(occupied);
			makeMoveEdge(occupied, 5);
			eoc[17][i] = (short) (getEOCoordinate(occupied)^0x111);
		}
	}
	
	private void makeMoveCorner(short[] occupied, int move){
		short temp = occupied[cornP[move][3]];
		occupied[cornP[move][3]] = occupied[cornP[move][2]];
		occupied[cornP[move][2]] = occupied[cornP[move][1]];
		occupied[cornP[move][1]] = occupied[cornP[move][0]];
		occupied[cornP[move][0]] = temp;
	}
	
	private short[] getCOCOccupiedArray(short coordinate) {
		short[] occupied = new short[8];
		int parity=0;
		for(int i=0; i<7; i++){
			occupied[i] = (short) (coordinate%3);
			coordinate /= 3;
			parity+=occupied[i];
		}
		occupied[7] = (short) ((18-parity)%3);
		return occupied;
	}
	private short getCOCoordinate(short[] occupied) {
		short coordinate = 0;
		for(int i=6; i>=0; i--){
			coordinate *= 3;
			coordinate += occupied[i];
		}
		return coordinate;
	}
	private short offsetCOC(short coordinate, int move){
		short s = 0;
		for(short i=0; i<7; i++){
			s += ((coordinate/p3[i]+cocOffset[move][i])%3)*p3[i];
		}
		return s;
	}
	private void cocLUT(){
		for(short i=0; i<coc[0].length; i++){
			for(short move=0; move<6; move++){
				short[] occupied = getCOCOccupiedArray(i);
				makeMoveCorner(occupied, move);
				coc[move*3][i] = offsetCOC(getCOCoordinate(occupied), move);
				makeMoveCorner(occupied, move);
				coc[move*3+1][i] = getCOCoordinate(occupied);
				makeMoveCorner(occupied, move);
				coc[move*3+2][i] = offsetCOC(getCOCoordinate(occupied), move);
			}
		}
	}

	private void udcLUT() {
		for(short move=0; move<6; move++){
			for(short i=0; i<ud1c[0].length; i++){
				boolean[] occupied = getUDCOccupiedArray(i);
				makeMoveEdge(occupied, move);
				ud1c[move*3][i] = getUDCoordinate(occupied);
				makeMoveEdge(occupied, move);
				ud1c[move*3+1][i] = getUDCoordinate(occupied);
				makeMoveEdge(occupied, move);
				ud1c[move*3+2][i] = getUDCoordinate(occupied);
			}
		}
	}
	private boolean[] getUDCOccupiedArray(short coordinate){
		boolean[] occupied = new boolean[12];
		short udEdges = 3;
		short edgesLeft = 11;
		for(;coordinate>0;edgesLeft--){
			if(coordinate >= choose[udEdges][edgesLeft]){
				coordinate -= choose[udEdges][edgesLeft];
			}else{
				udEdges--;
				occupied[edgesLeft] = true;
			}
		}
		for(;udEdges>=0;udEdges--, edgesLeft--){
			occupied[edgesLeft] = true;
		}
		return occupied;
	}
	private short getUDCoordinate(boolean[] occupied){
		short udEdges = 3;
		short edgesLeft = 11;
		short coordinate = 0;
		for(;udEdges>=0;edgesLeft--){
			if(occupied[edgesLeft]){
				udEdges--;
			}else{
				coordinate += choose[udEdges][edgesLeft];
			}
		}
		return coordinate;
	}
	
	private void cpcLUT(){
		for(int i=0; i<cpc[0].length; i++){
			for(short move=0; move<2; move++){
				short[] occupied = getCPCOccupiedArray(i);
				makeMoveCorner(occupied, move);
				cpc[move*3][i] = getCPCoordinate(occupied);
				makeMoveCorner(occupied, move);
				cpc[move*3+1][i] = getCPCoordinate(occupied);
				makeMoveCorner(occupied, move);
				cpc[move*3+2][i] = getCPCoordinate(occupied);
			}
			for(short move=2; move<6; move++){
				short[] occupied = getCPCOccupiedArray(i);
				makeMoveCorner(occupied, move);
				makeMoveCorner(occupied, move);
				cpc[move+4][i] = getCPCoordinate(occupied);
			}
		}
	}

	private short[] getCPCOccupiedArray(int coordinate) {
		short[] occupied = new short[8];
		for(int i=7; i>0; i--){
			occupied[7-i] = (short) (coordinate/fact[i]);
			coordinate -= occupied[7-i]*fact[i];
			getCubieNub(occupied,i);
		}
		getCubieNub(occupied,0);
		return occupied;
	}

	private void getCubieNub(short[] occupied, int cubie) {
		int length = occupied.length-1;
		int original = occupied[length-cubie];
		loop: for(int j=0, l=0; l<=original; j++){
			for(int k=length; k>cubie; k--){
				if(j == occupied[length-k]){
					occupied[length-cubie]++;
					continue loop;
				}
			}
			l++;
		}
	}

	public static int getCPCoordinate(short[] input) {
		short[] occupied = input.clone();
		int coordinate = 0;
		for(int i=7; i>0; i--){
			coordinate += occupied[7-i]*fact[i];
			for(int j=i-1; j>0; j--){
				if(occupied[7-i] < occupied[7-j]){
					occupied[7-j]--;
				}
			}
		}
		return coordinate;
	}
	
	private void epcLUT(){
		for(int i=0; i<epc[0].length; i++){
			for(short move=0; move<2; move++){
				short[] occupied = getCPCOccupiedArray(i);
				makeMoveEdge(occupied, move);
				epc[move*3][i] = getCPCoordinate(occupied);
				makeMoveEdge(occupied, move);
				epc[move*3+1][i] = getCPCoordinate(occupied);
				makeMoveEdge(occupied, move);
				epc[move*3+2][i] = getCPCoordinate(occupied);
			}
			for(short move=2; move<6; move++){
				short[] occupied = getCPCOccupiedArray(i);
				short temp = occupied[edge2P[move]];
				occupied[edge2P[move]] = occupied[edge2P[move]+4];
				occupied[edge2P[move]+4] = temp;
				epc[move+4][i] = getCPCoordinate(occupied);
			}
		}
	}
	
	private void ud2cLUT(){
		for(short i=0; i<ud2c[0].length; i++){
			for(short j=0; j<6; j++){
				ud2c[j][i] = i;
			}
			for(short move=2; move<6; move++){
				short[] occupied = getUD2COccupiedArray(i);
				short temp = occupied[edgeUD2P[move]];
				occupied[edgeUD2P[move]] = occupied[(edgeUD2P[move]+1)%4];
				occupied[(edgeUD2P[move]+1)%4] = temp;
				ud2c[move+4][i] = getUD2Coordinate(occupied);
			}
		}
	}

	public static short getUD2Coordinate(short[] input) {
		short[] occupied = input.clone();
		short coordinate = 0;
		for(int i=3; i>0; i--){
			coordinate += occupied[3-i]*fact[i];
			for(int j=i-1; j>0; j--){
				if(occupied[3-i] < occupied[3-j]){
					occupied[3-j]--;
				}
			}
		}
		return coordinate;
	}

	private short[] getUD2COccupiedArray(short coordinate) {
		short[] occupied = new short[4];
		for(int i=3; i>0; i--){
			occupied[3-i] = (short) (coordinate/fact[i]);
			coordinate -= occupied[3-i]*fact[i];
			getCubieNub(occupied,i);
		}
		getCubieNub(occupied,0);
		return occupied;
	}

	private void generateMoveLengthTables(short[] lengthTable, short[][] coordTable) {
		Queue<Short> queue = new LinkedList<Short>();
		queue.add((short) 0);
		while(!queue.isEmpty()){
			short node = queue.remove();
			for(int i=0; i<coordTable.length; i++){
				short nextNode = coordTable[i][node];
				if(lengthTable[nextNode] == 0){
					if(nextNode != 0){
						queue.add(nextNode);
						lengthTable[nextNode] = (short) (lengthTable[node]+1);
					}
				}
			}
		}
	}

	private void generateMoveLengthTables(int[] lengthTable, int[][] coordTable) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(0);
		while(!queue.isEmpty()){
			int node = queue.remove();
			for(int i=0; i<coordTable.length; i++){
				int nextNode = coordTable[i][node];
				if(lengthTable[nextNode] == 0){
					if(nextNode != 0){
						queue.add(nextNode);
						lengthTable[nextNode] = lengthTable[node]+1;
					}
				}
			}
		}
	}
}
