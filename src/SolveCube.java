import java.awt.Color;

public class SolveCube{
	
	// UBR URF UFL ULB DRB DFR DLF (DBL)
	// UB UR UF UL DB DR DF DL BR FR FL (BL)
	// UDRLFB  1'2
	CubeState state;
	PatternTable pt;
	static final short[][] choose = {{ 1, 0},
			 { 1, 1, 0},
			 { 1, 2, 1, 0},
			 { 1, 3, 3, 1, 0},
			 { 1, 4, 6, 4, 1, 0},
			 { 1, 5,10,10, 5, 1, 0},
			 { 1, 6,15,20,15, 6, 1},
			 { 1, 7,21,35,35,21, 7},
			 { 1, 8,28,56,70,56,28},
			 { 1, 9,36,84,126,126,84},
			 { 1,10,45,120,210,252,210},
			 { 1,11,55,165,330,462,462}};

	public SolveCube(Color[][] c, PatternTable pt){
		//[UDLFRB][ul..dr]
		for(int i = 0; i < c.length; i++){
			for(int j = 0; j < c[i].length; j++){
				c[i][j] = Color.RED;
			}
		}
		state = null;
		this.pt = pt;
	}
	
	public byte[] solve(){
		byte[] asdf = {1};
		return asdf;
	}
}
