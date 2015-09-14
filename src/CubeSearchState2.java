
public class CubeSearchState2 {

	CoordinateLUT cLUT;
	public int move; //UDRLFB  1'2
	public int cpc; //corner orientation coordinate
	public int epc; //edge orientation coordinate
	public short ud2c; //UD slice coordinate

	//root constructor
	public CubeSearchState2(CoordinateLUT cLUT, int cpc, int epc, short ud2c){
		this.cLUT = cLUT;
		move = -1;
		this.cpc = cpc;
		this.epc = epc;
		this.ud2c = ud2c;
	}

	//normal constructor
	public CubeSearchState2(int move, CubeSearchState2 prev){
		this.cLUT = prev.cLUT;
		this.move = move;
		cpc = cLUT.cpc[move][prev.cpc];
		epc = cLUT.epc[move][prev.epc];
		ud2c = cLUT.ud2c[move][prev.ud2c];
	}
}
