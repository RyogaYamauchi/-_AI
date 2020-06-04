import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;
import java.util.Arrays;
import java.util.Comparator;

/**
 * AI演習 マスターマインド 2020.5.30
 * @author 854003 山内龍我
 */

public class Solver {
	public static void answer() {
		var main = new Main();
		var result = main.run();
		MasterMind.submit(result);
	}
}

class Main
{
	private int _limit = MasterMind.getlimit();
	private int _zigen = MasterMind.getzigen();
	private Integer[] _result = new Integer[_zigen];
	private Integer[] _score = {0,0};
	private Integer[] _hint = new Integer[2];
	private HashMap<Integer, Integer> _map = new HashMap<Integer,Integer>();
	private boolean _endFlag = false;
	private Integer _endCount = 0;


	public int[] run() {
		setup();
		sortMap();

		var min = _map.entrySet().stream().min(Comparator.comparingInt(x -> x.getValue() )).get().getKey();
		var list = new ArrayList<Integer>();

		_endCount = 10; // 固定で10のため
		Integer[] temp = new Integer[_zigen];
		for(var a : _map.entrySet())
		{
			if(a.getKey() == min)
			{
				arrayUnify(min, temp);
			}
			else{
				dividedCase(a, list, temp);
			}
			if(_endFlag)
			{
				break;
			}
		}
		addRemaining(list, min);
		return parseIntArray(_result);
	}

	/**
	 * 0 ~ 9までで一番数の少ない値を取得し配列の初期化を行う。
	 * mapにそれぞれの数値がいくつ入っているか取得する。
	 **/
	private void setup()
	{
		for (Integer i = 0; i <= 9; i++)
		{
			for(Integer j = 0; j < _zigen;j++)
			{
				_result[j] = i;
			}
			_hint = parseIntegerArray(MasterMind.evaluate(parseIntArray(_result)));
			_map.put(i, _hint[0]);
		}
	}

	/**
	 * mapのvalueから昇順に
	 */
	private void sortMap()
	{
		_map =  _map.entrySet().
				stream().
				sorted(java.util.Map.Entry.comparingByValue()).
				collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1,e2) -> e1, LinkedHashMap::new));
	}


	/**
	 * 引数のtempの要素全てにnumを代入する
	 * @param num
	 * @param temp
	 */
	private void arrayUnify(Integer num, Integer[] temp)
	{
		for(var i = 0; i < _zigen; i++)
		{
			temp[i] = num;
		}
	}

	/**
	 * evaluate関数で変更した数値が大きい場合は保存し、小さい場合は前回の値が正しかったためそちらを保存する。
	 * 間違っている場合は次に進む。
	 * @param entry
	 * @param list
	 * @param temp
	 */
	private void dividedCase(Map.Entry<Integer, Integer> entry, ArrayList<Integer> list, Integer[] temp)
	{
		Integer cnt = 0;
		for(var i = 0; i < _zigen;i++)
		{
			if(_endCount >= _limit)
			{
				_endFlag = true;
				return;
			}
			if(list.contains(i))
			{
				continue;
			}
			if(entry.getValue() <= cnt)
			{
				break;
			}
			var beforeTemp = temp[i];
			temp[i] = entry.getKey();
			var before = _hint[0];
			_endCount++;
			_hint = parseIntegerArray(MasterMind.evaluate(parseIntArray(temp)));
			if(_hint[0] > before)
			{
				//正解
				_result[i] = entry.getKey();
				list.add(i);
				cnt++;
			}
			else if(_hint[0] < before)
			{
				// 元々入ってたのが正解
				_result[i] = beforeTemp;
				list.add(i);
			}
		}
		cnt = 0;
	}

	/**
	 * 残った数値を残った配列に代入する
	 * @param list
	 * @param min
	 */
	private void addRemaining(ArrayList<Integer> list, Integer min)
	{
		for(var i  = 0; i < _zigen;i++)
		{
			if(!list.contains(i))
			{
				_result[i] = min;
			}
		}
	}

	/**
	 * Integerの配列からintの配列に変換する
	 * @param arr
	 * @return
	 */
	private int[] parseIntArray(Integer[] arr)
	{
		return Arrays.stream(arr).mapToInt(i -> i).toArray();
	}

	/**
	 * intの配列からIntegerの配列に変換する
	 * @param arr
	 * @return
	 */
	private Integer[] parseIntegerArray(int[] arr)
	{
		return  Arrays.stream(arr).mapToObj(i -> i).toArray(Integer[]::new);
	}
}

