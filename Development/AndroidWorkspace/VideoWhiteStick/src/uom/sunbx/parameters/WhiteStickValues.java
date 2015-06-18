package uom.sunbx.parameters;

import java.util.ArrayList;

public class WhiteStickValues {
	private static WhiteStickValues instance;

	private int size;
	private double[] opticalFlowAngles;
	private double[] opticalFlowSpeeds;
	private double[] saliencyMap;
	private boolean[] availabilities;
	private ArrayList<int[]> positionSets;

	public static synchronized WhiteStickValues getInstance(int size) {
		if (instance == null) {
			instance = new WhiteStickValues(size);
		}
		return instance;
	}

	public static WhiteStickValues getInstance() {
		return instance;
	}

	public WhiteStickValues(int size) {
		this.size = size;
		this.opticalFlowAngles = new double[size];
		this.opticalFlowSpeeds = new double[size];
		this.saliencyMap = new double[size];
		this.availabilities = new boolean[size];
		this.positionSets = new ArrayList<int[]>();
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public double[] getOpticalFlowAngles() {
		return opticalFlowAngles;
	}

	public void setOpticalFlowAngles(double[] opticalFlowAngles) {
		this.opticalFlowAngles = opticalFlowAngles;
	}

	public void setOpticalFlowAngles(int position, double value) {
		this.opticalFlowAngles[position] = value;
	}

	public double[] getOpticalFlowSpeeds() {
		return opticalFlowSpeeds;
	}

	public void setOpticalFlowSpeeds(double[] opticalFlowSpeeds) {
		this.opticalFlowSpeeds = opticalFlowSpeeds;
	}

	public void setOpticalFlowSpeeds(int position, double value) {
		this.opticalFlowSpeeds[position] = value;
	}

	public double[] getSaliencyMap() {
		return saliencyMap;
	}

	public void setSaliencyMap(double[] saliencyMap) {
		this.saliencyMap = saliencyMap;
	}

	public void setSaliencyMap(int position, double value) {
		this.saliencyMap[position] = value;
	}

	public boolean[] getAvailabilities() {
		return availabilities;
	}

	public void setAvailabilities(boolean[] availabilities) {
		this.availabilities = availabilities;
	}

	public void setAvailabilities(int position, boolean value) {
		this.availabilities[position] = value;
	}

	public ArrayList<int[]> getPositionSets() {
		return positionSets;
	}

	public void setPositionSets(ArrayList<int[]> positionSets) {
		this.positionSets = positionSets;
	}

	public void addToPositionSets(int[] set) {
		this.positionSets.add(set);
	}

	public int[] getPositionSets(int index) {
		return this.positionSets.get(index);
	}
}
