import java.io.*;

public class Data {

	public static int numdata = 1000;
	public static int numzokusei = 6;
	public static Double[][] zokusei = new Double[numdata][numzokusei];
	public static String datafile = "data.csv";
	static String[][] loadtmp = new String[numdata][numzokusei];

  static {
	try{
		FileReader fr = new FileReader(datafile);
		BufferedReader reader = new BufferedReader(fr);
		String line;
		for (int i=0; i<numdata; i++){
			line = reader.readLine();
			loadtmp[i] = line.split(",", 0);
		}
		reader.close();
		for (int i=0; i<numdata; i++){
			for (int j=0; j<numzokusei; j++){
				zokusei[i][j] = Double.parseDouble(loadtmp[i][j]);
			}
		}
	} catch (Exception e) {
			System.out.println("IOError " + e.getMessage());
			System.exit(1);
	}
  }

}
