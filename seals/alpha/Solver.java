import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.lang.Integer;
import java.lang.Number;
import java.lang.Object;
import java.util.Random;

public class Solver {

	private static int _length = Map.p.length;
	private static int[][] _p = Map.p;
	private static List<Vector2> _positions = new ArrayList<Vector2>();
	private static List<Integer> _alreadyPositionNums = new ArrayList<Integer>();
	private static int[] x = new int [_length];

	public static void answer() 
	{
		var random = new Random();
		for(var i  = 0 ;i < 50;i++)
		{
			System.out.println("{	"+random.nextInt(100)+"	,	"+random.nextInt(100)+"	},");
		}

		for(var i = 0; i < _length; i++)
		{
			_positions.add(new Vector2(i, _p[i][0], _p[i][0]));
		}

		var current = 0;
		x[0] = 0;
		_alreadyPositionNums.add(0);
		for(var i = 0; i < _length-1; i++)
		{
			var neighbor = getMin(current);
			x[i+1] = neighbor;
			current = _positions.get(neighbor).Index;
		}

		TSP2D.submit(x);
	}



	private static int distance(int a, int b)
	{
		return TSP2D.distance(a,b);
	}

	// 現在の場所から一番近い場所のindexを取得
	private static int getMin(int currentNum)
	{
		var current = _positions.get(currentNum);
		var min = Integer.MAX_VALUE;
		var minIndex = Integer.MAX_VALUE;
		for(var i = 0; i < _positions.size(); i++)
		{
			if(i == currentNum || _alreadyPositionNums.contains(i))
			{
				continue;
			}
			var distance = distance(currentNum, i);
			if(distance < min)
			{
				min = distance;
				minIndex = i;
			}

		}
		_alreadyPositionNums.add(minIndex);
		return minIndex;
	}

}


class Vector2
{
	public int Index;
	public int X;
	public int Y;
	public Vector2(int index, int x, int y)
	{
		Index = index;
		X = x;
		Y = y;
	}
}