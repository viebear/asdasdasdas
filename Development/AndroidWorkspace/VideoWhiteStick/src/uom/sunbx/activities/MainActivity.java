package uom.sunbx.activities;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.OpenCVLoader;

import uom.sunbx.combination.AnalyseSaliency;
import uom.sunbx.fucntions.NewSaliency;
import uom.sunbx.fucntions.OpticalFlow;
import uom.sunbx.videowhitestick.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Button btn = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn = (Button) findViewById(R.id.btn1);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int i = 0;
				double time = System.currentTimeMillis();
				while (i < 1) {

					BitmapFactory.Options options = new BitmapFactory.Options();
					options.inSampleSize = 12;
					Bitmap prevBitmap = BitmapFactory.decodeResource(
							getResources(), R.drawable.origintest5, options);
					Bitmap nextBitmap = BitmapFactory.decodeResource(
							getResources(), R.drawable.origintest6, options);
					OpticalFlow.calcOpticalFlow(prevBitmap, nextBitmap);
					NewSaliency.saliency(nextBitmap);
					double time3 = System.currentTimeMillis();
					AnalyseSaliency.calcSaliencyPositions();
					double time4 = System.currentTimeMillis();
					Log.i("saliency ana Time", (time4 - time3) + "");
					i++;
				}
				double time2 = System.currentTimeMillis();
				Log.i("total time", time2 - time + "");
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0,
				getApplicationContext(), mLoaderCallback);
		Log.i("TAG", "onResume sucess load OpenCV...");
	}

	private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {

		@Override
		public void onManagerConnected(int status) {
			// TODO Auto-generated method stub
			switch (status) {
			case BaseLoaderCallback.SUCCESS:
				Log.i("TAG", "Successfully load OpenCV Manager!");
				break;
			default:
				super.onManagerConnected(status);
				Log.i("TAG", "Loading OpenCV Manager failed.");
				break;
			}

		}
	};
}
