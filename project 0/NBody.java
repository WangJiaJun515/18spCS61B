public class NBody{
	
/**
this method is to read the radius of the universe the project will simulate from the file, and we are given the filename in a string, return the double.
*/
	public static double readRadius(String fileName){
		In in = new In(fileName);
		int number = in.readInt();
		double radius = in.readDouble();
		return radius;
	}


/**
this method is the hardest method in this project.
the difficulties are:
1. I'm not sure can I set [] as the return of the method and write it in the defination
2. array should use [] to represent size.
3.about how to put objection into array, can use consturctor method.
*/
	public static Planet[] readPlanets(String fileName){
		In in = new In(fileName);
		int number = in.readInt();
		Planet[] planets =new Planet[number];
		double radius = in.readDouble();
		int i = 0;
		while(!in.isEmpty() && i < number){
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
			i++;
		}
		return planets;
	}


/**
this method is to draw the background of picture, and use (0,0,x) can fill full of screen, 
also having to use show and pause to control the picture showing
the picture I draw is given in main.
*/
	public static void drawBackground(String imageToDraw, double radius){
		StdDraw.setScale(-(radius),radius);
		StdDraw.enableDoubleBuffering();
		StdDraw.clear();
		StdDraw.picture(0,0,imageToDraw);

	}



/**
the main method
1. getting all input, and store them, use the method before.
2. method of drawing background is above.
3. store the information of planets in an array, and using a loop to draw all planets
*/
	public static void main(String[] Args){
		double T = Double.parseDouble(Args[0]);
		double dt = Double.parseDouble(Args[1]);
		String filename = Args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		String imageToDraw = "./images/starfield.jpg";
		drawBackground(imageToDraw, radius);
		int n = 0;
		while(n < planets.length){
			planets[n].draw();
			n++;
		}
		StdDraw.show();
		StdDraw.pause(2000);
		StdDraw.enableDoubleBuffering();
		double t = 0;
		while(t != T){
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			for(int i = 0;i < xForces.length; i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for(int i = 0;i < xForces.length; i++){
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.clear();
			drawBackground(imageToDraw, radius);
			for(int i = 0; i < xForces.length; i++){
				planets[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			t = t + dt;
		}while(t == T);
		System.out.printf("%d\n", planets.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  	planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  	planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
		
	}
}