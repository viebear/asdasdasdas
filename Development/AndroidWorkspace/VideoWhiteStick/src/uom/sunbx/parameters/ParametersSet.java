package uom.sunbx.parameters;

import java.util.ArrayList;

public class ParametersSet {

	private static ParametersSet instance;
	private Parameters[] values;
	private ArrayList<int[]> positionSet;
	private int size;
	private int rows;
	private int cols;

	public static synchronized ParametersSet getInstance(int rows, int cols,
			boolean initFlag) {
		if (instance == null || initFlag) {
			instance = new ParametersSet(rows, cols);
		}
		return instance;
	}

	public ParametersSet(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.size = rows * cols;
		this.values = new Parameters[size];
		this.positionSet = new ArrayList<int[]>();

		for (int i = 0; i < values.length; i++) {
			values[i] = new Parameters();
		}
	}

	public int[] getPositionSet(int index) {
		return this.positionSet.get(index);
	}

	public int getPositionSetSize() {
		return this.positionSet.size();
	}

	public void addToPositionSet(int[] value) {
		this.positionSet.add(value);
	}

	public Parameters[] getValues() {
		return values;
	}

	public void setValues(Parameters[] values) {
		this.values = values;
	}

	public void setValues(int index, Parameters value) {
		this.values[index] = value;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

}
