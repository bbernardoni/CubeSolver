import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Random;

public class PaintCube {
	public static void Paint(Graphics g, Color[][] c){
		poly(g, new int[]{253,300,267,220}, new int[]{7,7,40,40}, c[0][0]);
        poly(g, new int[]{301,348,315,268}, new int[]{7,7,40,40}, c[0][1]);
        poly(g, new int[]{349,396,363,316}, new int[]{7,7,40,40}, c[0][2]);
        poly(g, new int[]{219,266,233,186}, new int[]{41,41,74,74}, c[0][3]);
        poly(g, new int[]{267,314,281,234}, new int[]{41,41,74,74}, c[0][4]);
        poly(g, new int[]{315,362,329,282}, new int[]{41,41,74,74}, c[0][5]);
        poly(g, new int[]{185,232,199,152}, new int[]{75,75,108,108}, c[0][6]);
        poly(g, new int[]{233,280,247,200}, new int[]{75,75,108,108}, c[0][7]);
        poly(g, new int[]{281,328,295,248}, new int[]{75,75,108,108}, c[0][8]);
        
        poly(g, new int[]{152,152,199,199}, new int[]{255,302,302,255}, c[1][0]);
        poly(g, new int[]{200,200,247,247}, new int[]{255,302,302,255}, c[1][1]);
        poly(g, new int[]{248,248,295,295}, new int[]{255,302,302,255}, c[1][2]);
        poly(g, new int[]{152,152,199,199}, new int[]{303,350,350,303}, c[1][3]);
        poly(g, new int[]{200,200,247,247}, new int[]{303,350,350,303}, c[1][4]);
        poly(g, new int[]{248,248,295,295}, new int[]{303,350,350,303}, c[1][5]);
        poly(g, new int[]{152,152,199,199}, new int[]{351,398,398,351}, c[1][6]);
        poly(g, new int[]{200,200,247,247}, new int[]{351,398,398,351}, c[1][7]);
        poly(g, new int[]{248,248,295,295}, new int[]{351,398,398,351}, c[1][8]);
        
        poly(g, new int[]{7,7,54,54}, new int[]{110,157,157,110}, c[2][0]);
        poly(g, new int[]{55,55,102,102}, new int[]{110,157,157,110}, c[2][1]);
        poly(g, new int[]{103,103,150,150}, new int[]{110,157,157,110}, c[2][2]);
        poly(g, new int[]{7,7,54,54}, new int[]{158,205,205,158}, c[2][3]);
        poly(g, new int[]{55,55,102,102}, new int[]{158,205,205,158}, c[2][4]);
        poly(g, new int[]{103,103,150,150}, new int[]{158,205,205,158}, c[2][5]);
        poly(g, new int[]{7,7,54,54}, new int[]{206,253,253,206}, c[2][6]);
        poly(g, new int[]{55,55,102,102}, new int[]{206,253,253,206}, c[2][7]);
        poly(g, new int[]{103,103,150,150}, new int[]{206,253,253,206}, c[2][8]);
        
        poly(g, new int[]{152,152,199,199}, new int[]{110,157,157,110}, c[3][0]);
        poly(g, new int[]{200,200,247,247}, new int[]{110,157,157,110}, c[3][1]);
        poly(g, new int[]{248,248,295,295}, new int[]{110,157,157,110}, c[3][2]);
        poly(g, new int[]{152,152,199,199}, new int[]{158,205,205,158}, c[3][3]);
        poly(g, new int[]{200,200,247,247}, new int[]{158,205,205,158}, c[3][4]);
        poly(g, new int[]{248,248,295,295}, new int[]{158,205,205,158}, c[3][5]);
        poly(g, new int[]{152,152,199,199}, new int[]{206,253,253,206}, c[3][6]);
        poly(g, new int[]{200,200,247,247}, new int[]{206,253,253,206}, c[3][7]);
        poly(g, new int[]{248,248,295,295}, new int[]{206,253,253,206}, c[3][8]);
        
        poly(g, new int[]{297,330,330,297}, new int[]{109,76,123,156}, c[4][0]);
        poly(g, new int[]{331,364,364,331}, new int[]{75,42,89,122}, c[4][1]);
        poly(g, new int[]{365,398,398,365}, new int[]{41,8,55,88}, c[4][2]);
        poly(g, new int[]{297,330,330,297}, new int[]{157,124,171,204}, c[4][3]);
        poly(g, new int[]{331,364,364,331}, new int[]{123,90,137,170}, c[4][4]);
        poly(g, new int[]{365,398,398,365}, new int[]{89,56,103,136}, c[4][5]);
        poly(g, new int[]{297,330,330,297}, new int[]{205,172,219,252}, c[4][6]);
        poly(g, new int[]{331,364,364,331}, new int[]{171,138,185,218}, c[4][7]);
        poly(g, new int[]{365,398,398,365}, new int[]{137,104,151,184}, c[4][8]);
        
        poly(g, new int[]{400,400,447,447}, new int[]{7,54,54,7}, c[5][0]);
        poly(g, new int[]{448,448,495,495}, new int[]{7,54,54,7}, c[5][1]);
        poly(g, new int[]{496,496,543,543}, new int[]{7,54,54,7}, c[5][2]);
        poly(g, new int[]{400,400,447,447}, new int[]{55,102,102,55}, c[5][3]);
        poly(g, new int[]{448,448,495,495}, new int[]{55,102,102,55}, c[5][4]);
        poly(g, new int[]{496,496,543,543}, new int[]{55,102,102,55}, c[5][5]);
        poly(g, new int[]{400,400,447,447}, new int[]{103,150,150,103}, c[5][6]);
        poly(g, new int[]{448,448,495,495}, new int[]{103,150,150,103}, c[5][7]);
        poly(g, new int[]{496,496,543,543}, new int[]{103,150,150,103}, c[5][8]);
        
	}
	public static void poly(Graphics g, int x[], int y[], Color c){
		g.setColor(c);
		g.fillPolygon(x, y, 4);
	}
	
	public static void StickerClick(int x, int y, Color[][] c, Color cur){
		if(x+y>=260&&x+y<=307 && y>=7&&y<=40)
			c[0][0] = cur;
		else if(x+y>=308&&x+y<=355 && y>=7&&y<=40)
			c[0][1] = cur;
		else if(x+y>=356&&x+y<=403 && y>=7&&y<=40)
			c[0][2] = cur;
		else if(x+y>=260&&x+y<=307 && y>=41&&y<=74)
			c[0][3] = cur;
		else if(x+y>=356&&x+y<=403 && y>=41&&y<=74)
			c[0][5] = cur;
		else if(x+y>=260&&x+y<=307 && y>=75&&y<=108)
			c[0][6] = cur;
		else if(x+y>=308&&x+y<=355 && y>=75&&y<=108)
			c[0][7] = cur;
		else if(x+y>=356&&x+y<=403 && y>=75&&y<=108)
			c[0][8] = cur;
		
		else if(x>=152&&x<=199 && y>=255&&y<=302)
			c[1][0] = cur;
		else if(x>=200&&x<=247 && y>=255&&y<=302)
			c[1][1] = cur;
		else if(x>=248&&x<=295 && y>=255&&y<=302)
			c[1][2] = cur;
		else if(x>=152&&x<=199 && y>=303&&y<=350)
			c[1][3] = cur;
		else if(x>=248&&x<=295 && y>=303&&y<=350)
			c[1][5] = cur;
		else if(x>=152&&x<=199 && y>=351&&y<=398)
			c[1][6] = cur;
		else if(x>=200&&x<=247 && y>=351&&y<=398)
			c[1][7] = cur;
		else if(x>=248&&x<=295 && y>=351&&y<=398)
			c[1][8] = cur;
		
		else if(x>=7&&x<=54 && y>=110&&y<=157)
			c[2][0] = cur;
		else if(x>=55&&x<=102 && y>=110&&y<=157)
			c[2][1] = cur;
		else if(x>=103&&x<=150 && y>=110&&y<=157)
			c[2][2] = cur;
		else if(x>=7&&x<=54 && y>=158&&y<=205)
			c[2][3] = cur;
		else if(x>=103&&x<=150 && y>=158&&y<=205)
			c[2][5] = cur;
		else if(x>=7&&x<=54 && y>=206&&y<=253)
			c[2][6] = cur;
		else if(x>=55&&x<=102 && y>=206&&y<=253)
			c[2][7] = cur;
		else if(x>=103&&x<=150 && y>=206&&y<=253)
			c[2][8] = cur;
		
		else if(x>=152&&x<=199 && y>=110&&y<=157)
			c[3][0] = cur;
		else if(x>=200&&x<=247 && y>=110&&y<=157)
			c[3][1] = cur;
		else if(x>=248&&x<=295 && y>=110&&y<=157)
			c[3][2] = cur;
		else if(x>=152&&x<=199 && y>=158&&y<=205)
			c[3][3] = cur;
		else if(x>=248&&x<=295 && y>=158&&y<=205)
			c[3][5] = cur;
		else if(x>=152&&x<=199 && y>=206&&y<=253)
			c[3][6] = cur;
		else if(x>=200&&x<=247 && y>=206&&y<=253)
			c[3][7] = cur;
		else if(x>=248&&x<=295 && y>=206&&y<=253)
			c[3][8] = cur;
		
		else if(x+y>=406&&x+y<=453 && x>=297&&x<=330)
			c[4][0] = cur;
		else if(x+y>=406&&x+y<=453 && x>=331&&x<=364)
			c[4][1] = cur;
		else if(x+y>=406&&x+y<=453 && x>=365&&x<=398)
			c[4][2] = cur;
		else if(x+y>=456&&x+y<=501 && x>=297&&x<=330)
			c[4][3] = cur;
		else if(x+y>=456&&x+y<=501 && x>=365&&x<=398)
			c[4][5] = cur;
		else if(x+y>=502&&x+y<=549 && x>=297&&x<=330)
			c[4][6] = cur;
		else if(x+y>=502&&x+y<=549 && x>=331&&x<=364)
			c[4][7] = cur;
		else if(x+y>=502&&x+y<=549 && x>=365&&x<=398)
			c[4][8] = cur;
		
		else if(x>=400&&x<=447 && y>=7&&y<=54)
			c[5][0] = cur;
		else if(x>=448&&x<=495 && y>=7&&y<=54)
			c[5][1] = cur;
		else if(x>=496&&x<=543 && y>=7&&y<=54)
			c[5][2] = cur;
		else if(x>=400&&x<=447 && y>=55&&y<=102)
			c[5][3] = cur;
		else if(x>=496&&x<=543 && y>=55&&y<=102)
			c[5][5] = cur;
		else if(x>=400&&x<=447 && y>=103&&y<=150)
			c[5][6] = cur;
		else if(x>=448&&x<=495 && y>=103&&y<=150)
			c[5][7] = cur;
		else if(x>=496&&x<=543 && y>=103&&y<=150)
			c[5][8] = cur;
	}
	
	public static Color CenterClick(int x, int y, Color[][] c, Color cur){
		if(x+y>=308&&x+y<=355 && y>=41&&y<=74)
			cur = c[0][4];
		else if(x>=200&&x<=247 && y>=303&&y<=350)
			cur = c[1][4];
		else if(x>=55&&x<=102 && y>=158&&y<=205)
			cur = c[2][4];
		else if(x>=200&&x<=247 && y>=158&&y<=205)
			cur = c[3][4];
		else if(x+y>=456&&x+y<=501 && x>=331&&x<=364)
			cur = c[4][4];
		else if(x>=448&&x<=495 && y>=55&&y<=102)
			cur = c[5][4];
		return cur;
	}
	
	public static void cycle(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, Color[][] c){
		Color temp;
		temp = c[x4][y4];
		c[x4][y4] = c[x3][y3];
		c[x3][y3] = c[x2][y2];
		c[x2][y2] = c[x1][y1];
		c[x1][y1] = temp;
	}
	public static void cycle2(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, Color[][] c){
		Color temp;
		temp = c[x4][y4];
		c[x4][y4] = c[x2][y2];
		c[x2][y2] = temp;
		temp = c[x3][y3];
		c[x3][y3] = c[x1][y1];
		c[x1][y1] = temp;
	}
	
	public static void U(Color[][] c){
		cycle(3,0,2,0,5,0,4,0,c);
		cycle(3,1,2,1,5,1,4,1,c);
		cycle(3,2,2,2,5,2,4,2,c);
		cycle(0,0,0,2,0,8,0,6,c);
		cycle(0,1,0,5,0,7,0,3,c);
	}
	public static void Ui(Color[][] c){
		cycle(3,0,4,0,5,0,2,0,c);
		cycle(3,1,4,1,5,1,2,1,c);
		cycle(3,2,4,2,5,2,2,2,c);
		cycle(0,0,0,6,0,8,0,2,c);
		cycle(0,1,0,3,0,7,0,5,c);
	}
	public static void U2(Color[][] c){
		cycle2(3,0,4,0,5,0,2,0,c);
		cycle2(3,1,4,1,5,1,2,1,c);
		cycle2(3,2,4,2,5,2,2,2,c);
		cycle2(0,0,0,6,0,8,0,2,c);
		cycle2(0,1,0,3,0,7,0,5,c);
	}
	
	public static void D(Color[][] c){
		cycle(3,6,4,6,5,6,2,6,c);
		cycle(3,7,4,7,5,7,2,7,c);
		cycle(3,8,4,8,5,8,2,8,c);
		cycle(1,6,1,0,1,2,1,8,c);
		cycle(1,7,1,3,1,1,1,5,c);
	}
	public static void Di(Color[][] c){
		cycle(3,6,2,6,5,6,4,6,c);
		cycle(3,7,2,7,5,7,4,7,c);
		cycle(3,8,2,8,5,8,4,8,c);
		cycle(1,6,1,8,1,2,1,0,c);
		cycle(1,7,1,5,1,1,1,3,c);
	}
	public static void D2(Color[][] c){
		cycle2(3,6,2,6,5,6,4,6,c);
		cycle2(3,7,2,7,5,7,4,7,c);
		cycle2(3,8,2,8,5,8,4,8,c);
		cycle2(1,6,1,8,1,2,1,0,c);
		cycle2(1,7,1,5,1,1,1,3,c);
	}
	
	
	public static void R(Color[][] c){
		cycle(3,2,0,2,5,6,1,2,c);
		cycle(3,5,0,5,5,3,1,5,c);
		cycle(3,8,0,8,5,0,1,8,c);
		cycle(4,0,4,2,4,8,4,6,c);
		cycle(4,1,4,5,4,7,4,3,c);
	}
	public static void Ri(Color[][] c){
		cycle(3,2,1,2,5,6,0,2,c);
		cycle(3,5,1,5,5,3,0,5,c);
		cycle(3,8,1,8,5,0,0,8,c);
		cycle(4,0,4,6,4,8,4,2,c);
		cycle(4,1,4,3,4,7,4,5,c);
	}
	public static void R2(Color[][] c){
		cycle2(3,2,1,2,5,6,0,2,c);
		cycle2(3,5,1,5,5,3,0,5,c);
		cycle2(3,8,1,8,5,0,0,8,c);
		cycle2(4,0,4,6,4,8,4,2,c);
		cycle2(4,1,4,3,4,7,4,5,c);
	}
	
	public static void L(Color[][] c){
		cycle(3,0,1,0,5,8,0,0,c);
		cycle(3,3,1,3,5,5,0,3,c);
		cycle(3,6,1,6,5,2,0,6,c);
		cycle(2,0,2,2,2,8,2,6,c);
		cycle(2,1,2,5,2,7,2,3,c);
	}
	public static void Li(Color[][] c){
		cycle(5,8,1,0,3,0,0,0,c);
		cycle(5,5,1,3,3,3,0,3,c);
		cycle(5,2,1,6,3,6,0,6,c);
		cycle(2,0,2,6,2,8,2,2,c);
		cycle(2,1,2,3,2,7,2,5,c);
	}
	public static void L2(Color[][] c){
		cycle2(5,8,1,0,3,0,0,0,c);
		cycle2(5,5,1,3,3,3,0,3,c);
		cycle2(5,2,1,6,3,6,0,6,c);
		cycle2(2,0,2,6,2,8,2,2,c);
		cycle2(2,1,2,3,2,7,2,5,c);
	}
	
	
	public static void F(Color[][] c){
		cycle(2,2,0,8,4,6,1,0,c);
		cycle(2,5,0,7,4,3,1,1,c);
		cycle(2,8,0,6,4,0,1,2,c);
		cycle(3,0,3,2,3,8,3,6,c);
		cycle(3,1,3,5,3,7,3,3,c);
	}
	public static void Fi(Color[][] c){
		cycle(4,6,0,8,2,2,1,0,c);
		cycle(4,3,0,7,2,5,1,1,c);
		cycle(4,0,0,6,2,8,1,2,c);
		cycle(3,0,3,6,3,8,3,2,c);
		cycle(3,1,3,3,3,7,3,5,c);
	}
	public static void F2(Color[][] c){
		cycle2(4,6,0,8,2,2,1,0,c);
		cycle2(4,3,0,7,2,5,1,1,c);
		cycle2(4,0,0,6,2,8,1,2,c);
		cycle2(3,0,3,6,3,8,3,2,c);
		cycle2(3,1,3,3,3,7,3,5,c);
	}
	
	public static void B(Color[][] c){
		cycle(2,0,1,6,4,8,0,2,c);
		cycle(2,3,1,7,4,5,0,1,c);
		cycle(2,6,1,8,4,2,0,0,c);
		cycle(5,0,5,2,5,8,5,6,c);
		cycle(5,1,5,5,5,7,5,3,c);
	}
	public static void Bi(Color[][] c){
		cycle(4,8,1,6,2,0,0,2,c);
		cycle(4,5,1,7,2,3,0,1,c);
		cycle(4,2,1,8,2,6,0,0,c);
		cycle(5,0,5,6,5,8,5,2,c);
		cycle(5,1,5,3,5,7,5,5,c);
	}
	public static void B2(Color[][] c){
		cycle2(4,8,1,6,2,0,0,2,c);
		cycle2(4,5,1,7,2,3,0,1,c);
		cycle2(4,2,1,8,2,6,0,0,c);
		cycle2(5,0,5,6,5,8,5,2,c);
		cycle2(5,1,5,3,5,7,5,5,c);
	}
	
	
	public static void M(Color[][] c){
		cycle(0,1,3,1,1,1,5,7,c);
		cycle(0,4,3,4,1,4,5,4,c);
		cycle(0,7,3,7,1,7,5,1,c);
	}
	public static void Mi(Color[][] c){
		cycle(1,1,3,1,0,1,5,7,c);
		cycle(1,4,3,4,0,4,5,4,c);
		cycle(1,7,3,7,0,7,5,1,c);
	}
	public static void M2(Color[][] c){
		cycle2(1,1,3,1,0,1,5,7,c);
		cycle2(1,4,3,4,0,4,5,4,c);
		cycle2(1,7,3,7,0,7,5,1,c);
	}
	
	public static void E(Color[][] c){
		cycle(2,3,3,3,4,3,5,3,c);
		cycle(2,4,3,4,4,4,5,4,c);
		cycle(2,5,3,5,4,5,5,5,c);
	}
	public static void Ei(Color[][] c){
		cycle(4,3,3,3,2,3,5,3,c);
		cycle(4,4,3,4,2,4,5,4,c);
		cycle(4,5,3,5,2,5,5,5,c);
	}
	public static void E2(Color[][] c){
		cycle2(4,3,3,3,2,3,5,3,c);
		cycle2(4,4,3,4,2,4,5,4,c);
		cycle2(4,5,3,5,2,5,5,5,c);
	}
	
	public static void S(Color[][] c){
		cycle(1,3,2,1,0,5,4,7,c);
		cycle(1,4,2,4,0,4,4,4,c);
		cycle(1,5,2,7,0,3,4,1,c);
	}
	public static void Si(Color[][] c){
		cycle(0,5,2,1,1,3,4,7,c);
		cycle(0,4,2,4,1,4,4,4,c);
		cycle(0,3,2,7,1,5,4,1,c);
	}
	public static void S2(Color[][] c){
		cycle2(0,5,2,1,1,3,4,7,c);
		cycle2(0,4,2,4,1,4,4,4,c);
		cycle2(0,3,2,7,1,5,4,1,c);
	}
	
	public static void makeMove(Color[][] c, int move){
		switch (move){
		case 0:	U(c);	break;
		case 1:	U2(c);	break;
		case 2:	Ui(c);	break;
		case 3: D(c);	break;
		case 4: D2(c);	break;
		case 5: Di(c);	break;
		case 6:	R(c);	break;
		case 7:	R2(c);	break;
		case 8:	Ri(c);	break;
		case 9:	L(c);	break;
		case 10:L2(c);	break;
		case 11:Li(c);	break;
		case 12:F(c);	break;
		case 13:F2(c);	break;
		case 14:Fi(c);	break;
		case 15:B(c);	break;
		case 16:B2(c);	break;
		case 17:Bi(c);	break;
		}
	}
	
	public static void Scramble(Color[][] c){
		Random randomGenerator = new Random();
		for (int i = 1; i <= 25; i++){
			makeMove(c, randomGenerator.nextInt(17));
		}
	}
	
	public static void Blank(Color[][] c) {
		Arrays.fill(c[0], Color.GRAY);
		Arrays.fill(c[1], Color.GRAY);
        Arrays.fill(c[2], Color.GRAY);
        Arrays.fill(c[3], Color.GRAY);
        Arrays.fill(c[4], Color.GRAY);
        Arrays.fill(c[5], Color.GRAY);
        c[0][4] = Color.WHITE;
		c[1][4] = Color.YELLOW;
		c[2][4] = Color.GREEN;
		c[3][4] = Color.RED;
		c[4][4] = Color.BLUE;
		c[5][4] = new Color(255,140,0);
	}
	
	public static void Solve(Color[][] c) {
		Arrays.fill(c[0], Color.WHITE);
		Arrays.fill(c[1], Color.YELLOW);
        Arrays.fill(c[2], Color.GREEN);
        Arrays.fill(c[3], Color.RED);
        Arrays.fill(c[4], Color.BLUE);
        Arrays.fill(c[5], new Color(255,140,0));
	}
}
