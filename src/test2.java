import java.lang.Math;
import java.text.DecimalFormat;

class test2{
	public static void main(String args[]){
		DecimalFormat df = new DecimalFormat("#.#####");
		int x=0;
		int y=0;
		double z=0;
		for (x = 2; x < 36; x++){
			for (y = x; y < 36; y++){
				z = Math.sqrt(x*x+y*y);
			}
		}
        z = Double.valueOf(df.format(z));
		//if(z-((int)z) == 0)
			System.out.println("leg 1:" + x + " leg 2:" + y + " hyp:" + z);
	}
}