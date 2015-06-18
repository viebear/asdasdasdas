package uom.sunbx.fucntions;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.video.Video;

import uom.sunbx.parameters.ParametersSet;
import android.graphics.Bitmap;
import android.util.Log;

public class OpticalFlow {

	public static void calcOpticalFlow(Bitmap prev, Bitmap next) {

		Mat prevMat = new Mat();
		Mat nextMat = new Mat();
		Mat flowMat = new Mat();
		int rows, cols;
		double flows[] = null;

		Utils.bitmapToMat(prev, prevMat);
		Utils.bitmapToMat(next, nextMat);
		Imgproc.cvtColor(prevMat, prevMat, Imgproc.COLOR_RGB2GRAY);
		Imgproc.cvtColor(nextMat, nextMat, Imgproc.COLOR_RGB2GRAY);

		Video.calcOpticalFlowFarneback(prevMat, nextMat, flowMat, 0.5, 3, 5, 3,
				5, 1.2, 0);

		rows = flowMat.rows();
		cols = flowMat.cols();
		double time1 = System.nanoTime();
		ParametersSet group = ParametersSet.getInstance(rows, cols, true);
		double time2 = System.nanoTime();
		Log.i("single Time", time2 - time1 + "");
		flows = new double[rows * cols * flowMat.channels()];
		flowMat.convertTo(flowMat, CvType.CV_64FC2);
		flowMat.get(0, 0, flows);

		// double[][] angleArray = new double[rows][cols];
		// double[][] speedArray = new double[rows][cols];
		//
		// for (int i = 0; i < rows; i++) {
		// for (int j = 0; j < cols; j++) {
		//
		// double x = flows[2 * i + 2 * j];
		// double y = flows[2 * i + 2 * j + 1];
		// angleArray[i][j] = Math.atan(x / y);
		// speedArray[i][j] = Math.sqrt(x * x + y * y);
		//
		// }
		// }

		for (int i = 0; i < cols * rows; i++) {
			double x = flows[2 * i];
			double y = flows[2 * i + 1];

			group.getValues()[i].setAngle(Math.atan(x / y));
			group.getValues()[i].setSpeed(Math.sqrt(x * x + y * y));
		}

	}
}
