package model.persons;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import manager.PerimeterManager;
import model.Perimeter;
import model.Position;
import model.Positionable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fluth1 on 30/09/16.
 */
public abstract class Person extends Circle implements Positionable{
	protected ArrayList<Position> oldPositions = new ArrayList<>();
	protected Position currentPosition;
	protected List<Position> path;
	protected Perimeter currentPerimeter;
	protected int age;
	protected double speed;
	public final static int PERSON_RADIUS = 2;
	// protected Character character;

	public Person() {
		super(PERSON_RADIUS, Color.BLUE);
	}

	/**
	 * Calculates the vector and the next position depending on the step size
	 * @return next Position
	 */
	public void doStep() {
		Position p = calculateNextPossiblePosition();
		if(p != null) setPosition(p);
	}

	private Position calculateNextPossiblePosition() {
		int tries = 1;
		while(tries < 5) {
			Position nextTarget = this.path.get(0);
			double x = nextTarget.getXValue() - this.currentPosition.getXValue();
			double y = nextTarget.getYValue() - this.currentPosition.getYValue();
			double length = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
			double lambda = (speed / tries++) / length;
			Position newPosition = new Position(this.currentPosition.getXValue() + x * lambda,
					this.currentPosition.getYValue() + y * lambda);
			if(isNewPositionAllowed(newPosition)) return newPosition;
		}
		return null;
	}

	private boolean isNewPositionAllowed(Position position) {
		if(position == null) return false;
		List<Perimeter> neighPerimeters = this.currentPerimeter.getNeighbors();
		for(Perimeter perimeter : neighPerimeters)
			for(Person person : perimeter.getRegistredPersons()){
				if(person.equals(this)) continue;
				boolean collision = isCollidating(position.getXValue(), position.getYValue(), person);
				if(collision) return false;
			}
		return true;
	}

	/**
	 * Sets a new Position, puts the old position into the list.
	 * moves the person
	 * unregisters in the current perimeter and registers in the new one if needed
	 * @param position
	 */
	private void setPosition(Position position) {
		this.oldPositions.add(new Position(this.currentPosition.getXValue(),
				this.currentPosition.getYValue()));
		this.currentPosition.setX(roundWithDecimalFormat(position.getXValue()));
		this.currentPosition.setY(roundWithDecimalFormat(position.getYValue()));
		if(isInNextPathArea() && !isInGoalArea()) path.remove(0);
		if(!this.currentPerimeter.isInRange(this.getCurrentPosition())) PerimeterManager.getInstance().movePersonRegistration(this);
	}

	public boolean isCollidating(double x, double y, Person otherPerson){
		return (Math.abs(x - otherPerson.getCurrentPosition().getXValue()) < this.getRadius() + otherPerson.getRadius() &&
				Math.abs(y - otherPerson.getCurrentPosition().getYValue()) < this.getRadius() + otherPerson.getRadius() &&
				!otherPerson.isInGoalArea());
	}

	public double roundWithDecimalFormat(double val){
		DecimalFormat df = new DecimalFormat("#0.##");
		return Double.parseDouble(df.format(val));
	}

	public boolean isInNextPathArea(){
		Position nextPosition = path.get(0);
		return nextPosition.isInRange(this.currentPosition, PERSON_RADIUS * 2);
	}
	public boolean isInGoalArea() {
		Position targetPosition = path.get(path.size()-1);
		return targetPosition.isInRange(this.currentPosition, PERSON_RADIUS * 2);
	}

	public ArrayList<Position> getOldPositions() {
		return oldPositions;
	}

	public void setOldPositions(ArrayList<Position> oldPositions) {
		this.oldPositions = oldPositions;
	}

	public Position getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(Position currentPosition) {
		this.currentPosition = currentPosition;
	}

	public List<Position> getPath() {
		return path;
	}

	public void setPath(ArrayList<Position> path) {
		this.path = path;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public boolean intersects(double x, double y) {
		return this.intersects(x, y);
	}

	public void setCurrentPerimeter(Perimeter currentPerimeter) {
		this.currentPerimeter = currentPerimeter;
	}

	public Perimeter getCurrentPerimeter() {
		return this.currentPerimeter;
	}
}
