public class Body {
	public double xxPos, yyPos, xxVel, yyVel, mass;
	public String imgFileName;
	public static final double G = 6.67e-11;

	public Body(double xP, double yP, double xV,
			      double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(Body b) {
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Body b) {
		double dx, dy;
		dx = b.xxPos - this.xxPos;
		dy = b.yyPos - this.yyPos;
		return Math.sqrt(dx * dx + dy * dy);
	}

	public double calcForceExertedBy(Body b) {
		double r = this.calcDistance(b);
		return (G * this.mass * b.mass) / (r * r);
	}

	public double calcForceExertedByX(Body b) {
		double dx = b.xxPos - this.xxPos;
		double r = this.calcDistance(b);
	    double force = this.calcForceExertedBy(b);
	    double fx;
		fx = (force * dx) / r;
		return fx;
	}

	public double calcForceExertedByY(Body b) {
		double dy = b.yyPos - this.yyPos;
		double r = this.calcDistance(b);
		double force = this.calcForceExertedBy(b);
		double fy;
		fy = (force * dy) / r;
		return fy;
	}

	public double calcNetForceExertedByX(Body[] b) {
		double netForceX = 0;
		for (int i = 0; i < b.length; i++) {
			if(!this.equals(b[i])) {
				netForceX += this.calcForceExertedByX(b[i]);
			}
		}
		return netForceX;
	}

	public double calcNetForceExertedByY(Body[] b) {
		double netForceY = 0;
		for (int i = 0; i < b.length; i++) {
			if (!this.equals(b[i])) {
				netForceY += this.calcForceExertedByY(b[i]);
			}
		}
		return netForceY;
	}

	public void update(double dt, double fX, double fY) {
		double aX, aY;
		aX = fX / this.mass;
		aY = fY / this.mass;
		this.xxVel = this.xxVel + dt * aX;
		this.yyVel = this.yyVel + dt * aY;
		this.xxPos = this.xxPos + dt * this.xxVel;
		this.yyPos = this.yyPos + dt * this.yyVel;
	}

	public void draw() {
		StdDraw.picture(xxPos, yyPos, imgFileName);
	}
}
