package uom.sunbx.fucntions;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import uom.sunbx.parameters.ParametersSet;
import android.graphics.Bitmap;

public class NewSaliency {

	private static final int KERNAL_SIZE = 3;
	private static final int NORMAL_RANGE = 255;

	public static void saliency(Bitmap res) {
		Mat srcMat = new Mat();

		double avgs[] = null;
		double lavg = 0.0;
		double aavg = 0.0;
		double bavg = 0.0;

		Utils.bitmapToMat(res, srcMat);
		int rows = srcMat.rows();
		int cols = srcMat.cols();
		int size = rows * cols;
		ParametersSet group = ParametersSet.getInstance(rows,cols,false);
		Imgproc.cvtColor(srcMat, srcMat, Imgproc.COLOR_RGB2Lab);
		double srcArray[] = new double[size * srcMat.channels()];
		srcMat.convertTo(srcMat, CvType.CV_64FC3);
		srcMat.get(0, 0, srcArray);

		Scalar meanScalar = Core.mean(srcMat);
		avgs = meanScalar.val;
		aavg = avgs[0];
		bavg = avgs[1];
		lavg = avgs[2];

		Imgproc.GaussianBlur(srcMat, srcMat,
				new Size(KERNAL_SIZE, KERNAL_SIZE), 0, 0);

		srcMat.convertTo(srcMat, CvType.CV_64FC3);
		srcMat.get(0, 0, srcArray);

		double saliencyMap[] = new double[size];
		double minValue = Double.MAX_VALUE;
		double maxValue = 0;
		double range = 0;

		for (int i = 0; i < size; i++) {
			double val1 = srcArray[i * 3];
			double val2 = srcArray[i * 3 + 1];
			double val3 = srcArray[i * 3 + 2];

			double temp = (val1 - aavg) * (val1 - aavg) + (val2 - bavg)
					* (val2 - bavg) + (val3 - lavg) * (val3 - lavg);
			saliencyMap[i] = temp;
			if (temp < minValue)
				minValue = temp;
			if (temp > maxValue)
				maxValue = temp;
		}

		range = maxValue - minValue;
		if (range == 0)
			range = 1;

//		for (int i = 0; i < size; i++) {
//			double temp = NORMAL_RANGE * ((saliencyMap[i] - minValue) / range);
//			int val = (int) (temp + 0.5);
//			if (val <= 99)
//				val = 0;
//			else
//				val = NORMAL_RANGE;
//			saliencyMap[i] = val;
//		}
//
//		int index = 0;
//		for (int i = 0; i < rows; i++) {
//			for (int j = 0; j < cols; j++) {
//				resArray[i][j] = saliencyMap[index++];
//			}
//		}

		
		for (int i = 0; i < size; i++) {
			double temp = NORMAL_RANGE * ((saliencyMap[i] - minValue) / range);
			int val = (int) (temp + 0.5);
			if (val <= 99)
				group.getValues()[i].setSaliency(false);
			else
				group.getValues()[i].setSaliency(true);
			//saliencyMap[i] = val;
		}
	}
}
