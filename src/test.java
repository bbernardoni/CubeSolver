import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class test{
	public static void main (String args[]){
		String[] algList={
				"R U R' U' R' F R2 U' R' U' R U R' F'",
		        "(y2) L' U' L U L F' L2 U L U L' U' L F", 
		        "F R U' R' U R U R2 F' R U R U' R'",
		        "(y2) B L U' L' U L U L2 B' L U L U' L'",
		        "L2 U' L2 D F2 R2 U R2 D' F2 U",
		        "R2 U' R2 D B2 L2 U L2 D' B2 U",
		        "R2' u' R2 U R2' (y) R2 u R2' U' R2 U",
		        "R2 U R2' U' R2 U' D R2' U' R2 U R2' D'", 
		        "U2 r' U2 r U2 (x) U2 r U2 r' U2 R (z')",
		        "R U R' U' R2 D R' U' R' U' R U (z') U2 R' U (z)",
		        "R2 U R2 U' R2 F2 U' F2 D R2 D'"};
		String[] ialgList=new String[algList.length];
		String[] moveArray={
				"U", "U'", "U2",    "u", "u'", "u2",
                "R", "R'", "R2",    "r", "r'", "r2",
                "F", "F'", "F2",    "f", "f'", "f2",
                "D", "D'", "D2",    "d", "d'", "d2",
                "B", "B'", "B2",    "b", "b'", "b2",
                "L", "L'", "L2",    "l", "l'", "l2",
                
                "x", "x'", "x2",
                "y", "y'", "y2",
                "z", "z'", "z2",
 
                "M", "M'", "M2",
                "E", "E'", "E2",
                "S", "S'", "S2"};
		List<String> moveList=Arrays.asList(moveArray);
		double[] moveCount={
				.09, .10, .15,    .11, .12, .17,
                .08, .08, .14,    .10, .11, .14,
                .13, .14, .19,    .15, .17, .20,
                .11, .12, .17,    .13, .14, .19,
                .17, .15, .22,    .19, .17, .24,
                .13, .18, .22,    .14, .19, .24,
                
                .18, .18, .24,
                .18, .18, .24,
                .19, .19, .24,
 
                .15, .11, .18,
                .22, .25, .28,
                .22, .25, .28};

		SortedMap<Double, String> map = new TreeMap<Double, String>();
		
		for(int x=0; x<algList.length; x++){
			ialgList[x] = algList[x].replace("(", "");
			ialgList[x] = ialgList[x].replace(")", "");
			ialgList[x] = ialgList[x].replace("2'", "2");
			ialgList[x] = ialgList[x].replace("  ", " ");
			String[] algFiltered = ialgList[x].split(" ");
			double t = 0;
			for(int y=0; y<algFiltered.length; y++){
				t+=moveCount[moveList.indexOf(algFiltered[y])];
			}
			map.put(t, algList[x]);
		}
		Iterator<Double> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
		   Object key = iterator.next();
		   double time = 0;
		   DecimalFormat df = new DecimalFormat("#.##");
		   time = Double.valueOf(df.format(key));
		   System.out.println(time + ": " + map.get(key));
		}
	}
}