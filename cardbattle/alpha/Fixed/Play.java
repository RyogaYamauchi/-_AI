package Fixed;

public class Play implements P.Player {

int[] deck = {
1,2,3,4,5,6,7,8,9,10,11,12,13
};

	public int call (
		int[] mycard, int[] yourcard, 
		int[] mylog, int[] yourlog, 
		double mylastscore, double yourlastscore, 
		double mytotalscore, double yourtotalscore, 
		int count, long timeleft
	)
	{
		return deck[count-1];
	}

}
