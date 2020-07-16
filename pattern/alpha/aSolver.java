public class Solver {
	private static Double[][] zokusei;
	private static Integer numzokusei;
	private static Integer numdata;
	private static String[] kaitou;
	public static String[] answer() {
		numdata = Data.numdata;
		zokusei = Data.zokusei;
		numzokusei = Data.numzokusei;
		kaitou = new String[numdata];

		for(var i = 0;i < numdata;i++)
		{
			var result = "";
			for(var j  =0; j < numzokusei;j++)
			{
				var model = new Model(zokusei[i]);
				var eval = 0;
				var temp = 0;

				eval = C1Case(model);
				result = "C1";
				//例外のC1独特の特徴、通常の評価より高い数値を出す
				if(model.b < -0.35d && model.c > 0.35d)
				{
					eval+=2;
				}

				temp = C2Case(model);
				//例外のC2独特の特徴、通常の評価より高い数値を出す
				if(model.b < 0d && model.b > -0.1d && model.c > 0.2d && model.c < 0.3d)
				{
					temp+=2;
				}
				if(temp > eval)
				{
					eval = temp;
					result = "C2";
				}

				temp = C4Case(model);
				//例外のC4独特の特徴、通常の評価より高い数値を出す
				if(model.b > 0.1d && model.c < -0.25d && model.c  > 0.5d)
				{
					temp+=2;
				}
				if(temp > eval)
				{
					eval = temp;
					result = "C4";
				}

				temp = C5Case(model);
				//例外のC5独特の特徴、通常の評価より高い数値を出す
				if(model.b > 0.35d && model.c < -0.35d)
				{
					temp+=2;
				}

				if(temp > eval)
				{
					eval = temp;
					result = "C5";
				}

				// C3の特徴はバラバラででたらめなイメージ
				temp = C3Case(model);
				if(eval < 5) // この段階で評価値が
				{
					temp+=2;
				}
				if(temp > eval)
				{
					eval = temp;
					result = "C3";
				}
			}
			kaitou[i] = result;
		}


		return kaitou;
	}

	private static Integer C1Case(Model model)
	{
		var eval = 0;
		if(InOfRange(model, 0, -0.370))
		{
			eval++;
		}
		if(InOfRange(model,1,-0.632))
		{
			eval++;
		}
		if(InOfRange(model, 2,0.587))
		{
			eval++;
		}
		if(InOfRange(model, 3,-0.371))
		{
			eval++;
		}
		if(InOfRange(model, 4,0.033))
		{
			eval++;
		}
		if(InOfRange(model,5,0.311))
		{
			eval++;
		}
		return eval;
	}
	private static Integer C2Case(Model model)
	{
		var eval = 0;
		if(InOfRange(model, 0, -0.137))
		{
			eval++;
		}
		if(InOfRange(model,1,-0.276))
		{
			eval++;
		}
		if(InOfRange(model, 2,0.304))
		{
			eval++;
		}
		if(InOfRange(model, 3,-0.179))
		{
			eval++;
		}
		if(InOfRange(model, 4,-0.096))
		{
			eval++;
		}
		if(InOfRange(model,5,0.111))
		{
			eval++;
		}
		return eval;
	}
	private static Integer C3Case(Model model)
	{
		var eval = 0;
		if(InOfRange(model, 0, -0.148))
		{
			eval++;
		}
		if(InOfRange(model,1,0.003))
		{
			eval++;
		}
		if(InOfRange(model, 2,0.025))
		{
			eval++;
		}
		if(InOfRange(model, 3,-0.034))
		{
			eval++;
		}
		if(InOfRange(model, 4,-0.104))
		{
			eval++;
		}
		if(InOfRange(model,5,-0.055))
		{
			eval++;
		}
		return eval;
	}
	private static Integer C4Case(Model model)
	{
		var eval = 0;
		if(InOfRange(model, 0, 0.205))
		{
			eval++;
		}
		if(InOfRange(model,1,0.158))
		{
			eval++;
		}
		if(InOfRange(model, 2,-0.338))
		{
			eval++;
		}
		if(InOfRange(model, 3,0.121))
		{
			eval++;
		}
		if(InOfRange(model, 4,-0.014))
		{
			eval++;
		}
		if(InOfRange(model,5,-0.105))
		{
			eval++;
		}
		return eval;
	}
	private static Integer C5Case(Model model)
	{
		var eval = 0;
		if(InOfRange(model, 0, 0.326))
		{
			eval++;
		}
		if(InOfRange(model,1,0.627))
		{
			eval++;
		}
		if(InOfRange(model, 2,-0.594))
		{
			eval++;
		}
		if(InOfRange(model, 3,0.290))
		{
			eval++;
		}
		if(InOfRange(model, 4,0.093))
		{
			eval++;
		}
		if(InOfRange(model,5,-0.346))
		{
			eval++;
		}
		return eval;
	}


	private static Boolean InOfRange(Model model, int index,  double num)
	{
		var range = 0.7d;
		switch(index)
		{
			case 0:
				return model.a < num + range && model.a > num - range;
			case 1:
				return model.b < num + range && model.b > num - range;
			case 2:
				return model.c < num + range && model.c > num - range;
			case 3:
				return model.d < num + range && model.d > num - range;
			case 4:
				return model.e < num + range && model.e > num - range;
			case 5:
				return model.f < num + range && model.f > num - range;
			default:
				return false;
		}
	}

}

class Model{
	public Double a;
	public Double b;
	public Double c;
	public Double d;
	public Double e;
	public Double f;
	public Model(Double[] nums)
	{
		a = nums[0];
		b = nums[1];
		c = nums[2];
		d = nums[3];
		e = nums[4];
		f = nums[5];
	}
}


