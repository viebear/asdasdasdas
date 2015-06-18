package uom.sunbx.parameters;

public class Parameters {

	private double angle;
	private double speed;
	private boolean saliency;
	private boolean availability;

	public Parameters() {
		this.angle = 0;
		this.speed = 0;
		this.saliency = true;
		this.availability = true;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public boolean isSaliency() {
		return saliency;
	}

	public void setSaliency(boolean saliency) {
		this.saliency = saliency;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

}
