import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.HashMap;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.LinkedHashMap;
public class Solver {
	private static int limit = Gold.getEvalLimit(); // 10000
	private static HashMap<Vector2, Double> map = new HashMap<Vector2, Double>();
	private static int range = 100;
	private static int count=  0;
	private static int n = 50;
	public static void answer() {
		double[] p = new double[2];		//	(x,y) = (p[0], p[1])
		setStandardValue(n);
		map = map.entrySet().
		 stream().
		  sorted(java.util.Map.Entry.comparingByValue()). 
		  collect(Collectors.toMap(Map.Entry::getKey, 
		  Map.Entry::getValue, 
		  (e1,e2) -> e1, 
		  LinkedHashMap::new));
		var first = map.entrySet().iterator().next();
		
	
		var firstPosition = first.getKey();

		var next = getNextPosition(firstPosition);
	
		var minValue = Double.MAX_VALUE;
		while(count < limit-3)
		{
						
			var eval = eval(next);
			print(next);
			print(eval);
			if(eval < minValue)
			{
				p = new double[]{next.X, next.Y};
				minValue = eval;
			}
			next = getNextPosition(next);
		}
		Gold.submit(p);
	}

	private static Vector2 getNextPosition(Vector2 vector2)
	{
		double minX = 0;
		double minY = 0;
		double minValue = Double.MAX_VALUE;
		double tempValue = 0d;
		var right = new Vector2(vector2.X + range, vector2.Y);
		minValue = eval(right);
		minX = right.X;
		minY = right.Y;

		var left = new Vector2(vector2.X + (-1 * range), vector2.Y);
		tempValue = eval(left);
		if(tempValue < minValue)
		{
			minValue = tempValue;
			minX = left.X;
			minY = left.Y;
		}
		var up = new Vector2(vector2.X, vector2.Y + range);
		tempValue = eval(up);
		if(tempValue < minValue)
		{
			minValue = tempValue;
			minX = up.X;
			minY = up.Y;
		}
		var down = new Vector2(vector2.X, vector2.Y + (-1 * range));
		tempValue = eval(down);
		if(tempValue < minValue)
		{
			minValue = tempValue;
			minX = down.X;
			minY = down.Y;
		}
		
		return new Vector2(minX, minY);
	}

	private static void print(Object object)
	{
		System.out.println(object);
	}



	private static double eval(double[] point)
	{
		count++;
		return Gold.evaluate(point);
	}
	private static double eval(Vector2 vector2)
	{
		return eval(new double[]{vector2.X, vector2.Y});
	}
	private static void setStandardValue(int n)
	{
		n--; // 0オリジンのため
		for(double i  = -1000000; i <= 1000000; i += 2000000/n)
		{
			for(double j = -1000000; j <= 1000000;j += 2000000/n)
			{
				var pos = new Vector2(i, j);
				map.put(pos, eval(new double[]{pos.X, pos.Y}));
			}
			
		}
	}
}

class Vector2
{
	public double X;
	public double Y;
	public Vector2(double x, double y)
	{
		X = x;
		Y = y;
	}

	@Override
	public String toString() {
		return "X : " + Math.floor(X)+ ", Y : " + Math.floor(Y);
	}
}