public class Planet{
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;

/**
First constructor, to initialize a object and fill variables in it.
*/
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}

/**	
Second constructor, to initializ and copy an exist object.
Using the first constructor to fill in all of the instance variables on this planet.
*/
	public Planet(Planet p){
		this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
	}

/** 
Caculating the distance of two planets,take in one planet and return a double.
using Dis^2 = xDis^2 + yDis^2
*/
	public double calcDistance(Planet p){
		double distance;
		distance = Math.sqrt(Math.pow((this.xxPos - p.xxPos),2) + Math.pow((this.yyPos - p.yyPos),2));
		return distance;
	}

/**
This method is to caculate the force between two planets, take in one planet and return a double.
using F = G * (m1 + m2) / Distance
*/
	public double calcForceExertedBy(Planet p){
		double distance = this.calcDistance(p);
		double G = 6.67 * Math.pow(10,-11);
		double Force;
		Force = G * this.mass * p.mass / Math.pow(distance,2);
		return Force;
	}

/**
This two methods are to describe the force exerted in X and Y directions, respectively
using F = F1 * xDis / Dis
*/
	public double calcForceExertedByX(Planet p){
		double distance = this.calcDistance(p);
		double Force;
		Force = this.calcForceExertedBy(p);
		double xForce;
		xForce = Force*(p.xxPos-this.xxPos)/distance;
		return xForce;
	}
	public double calcForceExertedByY(Planet p){
		double distance = this.calcDistance(p);
		double Force;
		Force = this.calcForceExertedBy(p);
		double yForce;
		yForce = Force*(p.yyPos-this.yyPos)/distance;
		return yForce;
	}

/**
This two methods are to caculate the force of the current planet by all planets except itself,
using for loop to add the force by each planet repectively, and continue when allPlanets[n] is itself.
*/ 
	public double calcNetForceExertedByX(Planet[] allPlanets){
		int m = allPlanets.length;
		double xForce = 0;
		for(int n = 0; n < m; n++){
			if(this.equals(allPlanets[n]))
			continue;
			xForce = xForce + this.calcForceExertedByX(allPlanets[n]);
		}
		return xForce;	
	}
	public double calcNetForceExertedByY(Planet[] allPlanets){
		int m = allPlanets.length;
		double yForce = 0;
		for(int n = 0; n < m; n++){
			if(this.equals(allPlanets[n]))
			continue;
			yForce = yForce + this.calcForceExertedByY(allPlanets[n]);
		}
		return yForce;	
	}

/**
You must compute the movement of the Planet using the following steps:

1.Calculate the acceleration using the provided x- and y-forces.
2.Calculate the new velocity by using the acceleration and current velocity. Recall that acceleration describes the change in velocity per unit time, so the new velocity is (vx+dt⋅ax,vy+dt⋅ay).
3.Calculate the new position by using the velocity computed in step 2 and the current position. The new position is (px+dt⋅vx,py+dt⋅vy).
*/
	public void update(double t, double xF, double yF){
		double xA = xF / this.mass;
		double yA = yF / this.mass;
		this.xxVel = this.xxVel + t * xA;
		this.yyVel = this.yyVel + t * yA;
		this.xxPos = this.xxPos + t * this.xxVel;
		this.yyPos = this.yyPos + t * this.yyVel;
	}
	
/**
this method is to draw the single planet, so it's an instance method, and return nothing.
*/
	public void draw(){
		String imageToDraw = "./images/" + this.imgFileName;
		StdDraw.picture(this.xxPos,this.yyPos,imageToDraw);
		
	}
} 