
public class CubeSearchState1 {

	CoordinateLUT cLUT;
	public int move; //UDRLFB  1'2
	public short coc; //corner orientation coordinate
	public short eoc; //edge orientation coordinate
	public short ud1c; //UD slice coordinate

	//root constructor
	public CubeSearchState1(CoordinateLUT cLUT, short coc, short eoc, short udc){
		this.cLUT = cLUT;
		move = -1;
		this.coc = coc;
		this.eoc = eoc;
		this.ud1c = udc;
	}

	//normal constructor
	public CubeSearchState1(int move, CubeSearchState1 prev){
		this.cLUT = prev.cLUT;
		this.move = move;
		coc = cLUT.coc[move][prev.coc];
		eoc = cLUT.eoc[move][prev.eoc];
		ud1c = cLUT.ud1c[move][prev.ud1c];
	}
}
