package Z;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;

public class Play implements P.Player {

	private ArrayList<Integer> remainingYourCards = new ArrayList<Integer>();
	private ArrayList<Integer> remainingMyCards = new ArrayList<Integer>();
	private int[] beforeYourCards = new int[13];
	public int call (
		int[] mycard, int[] yourcard, 
		int[] mylog, int[] yourlog, 
		double mylastscore, double yourlastscore, 
		double mytotalscore, double yourtotalscore, 
		int count, long timeleft
	)
	{
		if(count == 1)
		{
			for(var i = 1; i < 14;i++)
			{
				remainingYourCards.add(i);
				remainingMyCards.add(i);
			}
			return 10;
		}

		remainingYourCards.remove(remainingYourCards.indexOf(yourlog[count-1]));
		remainingMyCards.remove(remainingMyCards.indexOf(mylog[count-1]));


		int index = 0;
		var random = new Random();
		// 相手が２のカードを持っている場合、３〜９のカードを使う
		if(remainingYourCards.contains(2))
		{
			if(hasFToS(remainingMyCards, 2, 10))
			{
				index = random.nextInt((int)remainingMyCards.stream().filter(x -> x > 2 && x < 10).count());
				var a= remainingMyCards.stream().filter(x -> x > 2 && x < 10).collect(Collectors.toList()).get(index);
				return a;
			}
		}
		// 相手が2のカードを持っていない場合、絵札を使う
		else {
			if(hasFToS(remainingMyCards, 10, 14))
			{
				index = random.nextInt((int) remainingMyCards.stream().filter(x -> x > 10 && x < 14).count());
				var a = remainingMyCards.stream().filter(x -> x > 10 && x < 14).collect(Collectors.toList()).get(index);
				return a;
			}
		}

		// 相手が２のカードを持っておらず、自身が絵札を使い切った場合、そのたを使う
		index = random.nextInt(remainingMyCards.size());
		return remainingMyCards.get(index);
	}

	private void p(String str)
	{
		System.out.println(str);
	}
	private void p(int i)
	{
		System.out.println(i);
	}
	private boolean hasFToS(ArrayList<Integer> list, Integer f, Integer s)
	{
		return list.stream().anyMatch(x -> x > f && x < s);
	}
}
