public class Solver {

	public static Double[][] zokusei; //今から判断するデータ
	public static int numdata;
	public static int numzokusei;
	public static int i = 0;
	public static double range = 0.6d;
	

	public static String[] answer() {
		String[] kaitou = new String[Data.numdata]; //予想したクラス(c1~c5)を入れる
		numdata = Data.numdata;
		numzokusei = Data.numzokusei;
		zokusei = Data.zokusei;
		int[] hyoka = new int[numzokusei]; //pointを入れておきますわ
		int point = 0;
		int bangou = 0;

		for(i=0; i<numdata; i++){
			for(var j = 0;j < 6;j++)
			{
				hyoka[j] = 0;
			}
			 p("--------------"+i+"番目-------------------");
			// ---------------C1------------------
			point = getPoint(-0.26, -0.5, 0.5, -0.2, 0.0, 0.2);

			if(zokusei[i][1] < 0.8 &&
					zokusei[i][1] > 0.3 &&
					zokusei[i][2] < -0.3 &&
			zokusei[i][2] > -0.8)
			{
				point+=2;
			}

			p("C1のポインt : " + point);
			hyoka[0] = point;
			point = 0;

			// ---------------C2------------------
			point = getPoint(-0.0,-0.2,0.2,-0.1,-0.1,0.0);
			if(zokusei[i][1] > -0.5d &&
			zokusei[i][1] < 0.5 &&
			zokusei[i][2] < 1.0 &&
			zokusei[i][2] > 0.0 &&
			zokusei[i][3] > -0.6 &&
			zokusei[i][3] < 0.4) {
				point += 2;
				System.out.println("入った!"+i);
			}


			p("C2のポインt : " + point);


			hyoka[1] = point;
			point = 0;

			// ---------------C4------------------
			point = getPoint(0.1,0.1,-0.2,0.0,0.0,-0.0);


			p("C4のポインt : " + point);

			hyoka[3] = point;
			point = 0;
			// ---------------C5------------------

			point = getPoint(0.2,0.5,-0.4,0.2,0.0,-0.2);
			if(zokusei[i][1] < 1.0 &&
					zokusei[i][1] > 0.2 &&
					zokusei[i][2] > -1.0 &&
					zokusei[i][2] < -0.2)
			{
				point+=2;
			}

			p("C5のポインt : " + point);
			hyoka[4] = point;
			// ---------------C3------------------
			point = 0;

			int maxpoint = 0;
			bangou = 0;

			// ---------------終わり------------------


			for(int j=0; j<5; j++){
				if(maxpoint < hyoka[j]){
				    maxpoint = hyoka[j];
				    bangou = j;
			    }
			}
			if(maxpoint < 3)
			{
				kaitou[i] = "C3";
			}
			else {


				if (bangou == 0) {
					kaitou[i] = "C1";
				}
				if (bangou == 1) {
					kaitou[i] = "C2";
				}
				if (bangou == 2) {
					kaitou[i] = "C3";
				}
				if (bangou == 3) {
					kaitou[i] = "C4";
				}
				if (bangou == 4) {
					kaitou[i] = "C5";
				}
			}

		}
		return kaitou;
	}

	public static int getPoint(double p1, double p2, double p3, double p4, double p5, double p6)
	{
		int point = 0;

		if(p1 - range <= zokusei[i][0] && zokusei[i][0] <= p1 + range){
			point = point + 1;
		}
		if((p2 - range<= zokusei[i][1]) && (zokusei[i][1] <= p2 + range)){
			point = point + 1;
		}
		if((p3 - range<= zokusei[i][2]) && (zokusei[i][2] <= p3 +  range)){
			point = point + 1;
		}
		if((p4 - range<= zokusei[i][3]) && (zokusei[i][3] <= p4 + range)){
			point = point + 1;
		}
		if((p5 - range <= zokusei[i][4]) && (zokusei[i][4] <= p5 + range)){
			point = point + 1;
		}
		if((p6 - range< zokusei[i][5]) && (zokusei[i][5] <= p6 + range)){
			point = point + 1;
		}


		return point;
	}

	public static void p(String str)
	{
		if(i < 400 && i > 200)
		{
			//System.out.println(str);
		}
	}
}
