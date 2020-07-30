public class testfunc {
	
	public static double define (double[] p) {
		double best = 0.0;
		double[] pbest = {100.0, 100.0};
		double f = 1.0e-5*( Math.pow(p[0]-pbest[0],2) + Math.pow(p[1]-pbest[1],2) - best);
		return f;
	}

}
