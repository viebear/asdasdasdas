package uom.sunbx.combination;

import uom.sunbx.parameters.ParametersSet;

public class AnalyseSaliency {
	static int positions[] = null;
	static int count = 0;

	public static void checkNeighbours(int index, int rows, int cols) {
		ParametersSet values = ParametersSet.getInstance(0, 0, false);

		if (values.getValues()[index].isAvailability()) {
			values.getValues()[index].setAvailability(false);
			if (values.getValues()[index].isSaliency()) {
				// int leftBound = index % cols - 1;
				int rightBound = index % cols + 1;
				positions[count++] = index;

				if (rightBound < cols)
					checkNeighbours(index + 1, rows, cols);

				if ((rightBound < cols) && ((index + cols) < (rows * cols)))
					checkNeighbours(index + 1 + cols, rows, cols);

				if ((index + cols) < (rows * cols))
					checkNeighbours(index + cols, rows, cols);
			} else
				return;
		} else
			return;

	}

	public static void calcSaliencyPositions() {
		int rows = 0;
		int cols = 0;
		int size = 0;
		ParametersSet values = ParametersSet.getInstance(0, 0, false);

		rows = values.getRows();
		cols = values.getCols();
		size = values.getSize();

		positions = new int[size];
		for (int i = 0; i < size; i++) {
			count = 0;
			if (values.getValues()[i].isAvailability()) {
				if (values.getValues()[i].isSaliency()) {
					checkNeighbours(i, rows, cols);

					if (count >= 500) {
						int[] temp = new int[count];
						for (int j = 0; j < temp.length; j++) {
							temp[i] = positions[i];
						}
						values.addToPositionSet(temp);
					}
				} else
					values.getValues()[i].setAvailability(false);
			} else
				continue;
		}

	}
}
