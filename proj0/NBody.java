public class NBody {
	public static double readRadius(String filename) {
		In in = new In(filename);

		double radius;

		in.readInt();
		radius = in.readDouble();
		return radius;
	}

	public static Body[] readBodies(String filename) {
		In in = new In(filename);
		int n;

		n = in.readInt();
		in.readDouble();

		Body[] b = new Body[n];

		for (int i = 0; i < n; i++) {
			b[i] = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), 
				            in.readDouble(), "images/" + in.readString());
		}

		return b;
	}

	public static void main(String[] args) {
		double T, dt;
		double radius;
		String filename;
		Body[] b;

		T = Double.parseDouble(args[0]);
		dt = Double.parseDouble(args[1]);
		filename = args[2];

		b = readBodies(filename);
		radius = readRadius(filename);

		//System.out.println(b.length);

		// set the scale of the universe
		StdDraw.setScale(-radius, radius);
		StdDraw.enableDoubleBuffering();

		for (double currentTime = 0; currentTime <= T; currentTime += dt) {
			double[] xForces = new double[b.length];
			double[] yForces = new double[b.length];

			for (int j = 0; j < b.length; j++) {
				xForces[j] = b[j].calcNetForceExertedByX(b);
				yForces[j] = b[j].calcNetForceExertedByY(b);
			}

			StdDraw.clear();
			StdDraw.picture(0, 0, "images/starfield.jpg"); // sets the background image

			for (int j = 0; j < b.length; j++) {
			b[j].update(dt, xForces[j], yForces[j]);
			b[j].draw(); // draws the body
			}
			
			StdDraw.show(); // shows the offscreen buffer
			StdDraw.pause(10); // pause for 10ms
		}

			StdOut.printf("%d\n", b.length);
			StdOut.printf("%.2e\n", radius);
			for (int i = 0; i < b.length; i++) {
				StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
					  		   b[i].xxPos, b[i].yyPos, b[i].xxVel,
					  		   b[i].yyVel, b[i].mass, b[i].imgFileName); 
	        }
    }
}