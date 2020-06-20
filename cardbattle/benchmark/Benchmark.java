import java.util.Random;

public class Benchmark {
	static long timelimit = 1000 * 1; // msec
	static int averoop = 10;

	public static void main(String args[]) {
		int count;
		int sumcount = 0;
		long start, end;
		double rd;
		Random rr;

		for(int i=0; i<averoop; i++){
			count = 0;
			start = System.currentTimeMillis();
			end = System.currentTimeMillis();
			while( (end-start) < timelimit ){
				rr = new Random();
				rd = rr.nextGaussian() * rr.nextGaussian();
				count++;
				end = System.currentTimeMillis();
			}
			sumcount += count;
			System.out.print(i);
		}
		System.out.println("\n" + 1.0*sumcount/averoop);
	}
}
