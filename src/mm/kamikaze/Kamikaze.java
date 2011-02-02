package mm.kamikaze;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.hardware.SensorManager;

/* MM: Warning!! The following imports will work only when simulating on emulator!! */
import org.openintents.sensorsimulator.hardware.Sensor;
import org.openintents.sensorsimulator.hardware.SensorEvent;
import org.openintents.sensorsimulator.hardware.SensorEventListener;
import org.openintents.sensorsimulator.hardware.SensorManagerSimulator;

public class Kamikaze extends Activity implements SensorEventListener, Runnable {
	private final static int CHANGE = 30;
	
	LinearLayout screen;
	private Draw d;
	private Thread t; 
	private SensorManagerSimulator sensorManager;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        /** MM: Not in real life, only in emulator */
        sensorManager = SensorManagerSimulator.getSystemService(this, SENSOR_SERVICE);
        sensorManager.connectSimulator();
        
        /** MM: prepare the interface */
        screen = (LinearLayout)findViewById(R.id.canvas);
        d = new Draw(screen.getContext());
        d.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        d.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				d.reset();
				return false;
			}
		});
        screen.addView(d);
        t = new Thread(this);
        t.start();
    }
    
    @Override
    protected void onResume(){
    	super.onResume();
    	sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);
    }
    
    @Override
    protected void onStop(){
    	sensorManager.unregisterListener(this);
    	super.onStop();
    }

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {}

	@Override
	public void onSensorChanged(SensorEvent event) {
		int sensor = event.type;
		float[] values = event.values;
		int _z;
		_z = (int) values[2];
		switch (sensor){
			case Sensor.TYPE_ORIENTATION:
				if (Math.abs(_z) > CHANGE){
					if (_z < 0) d.moveLeft(Math.abs(_z)/CHANGE);
					else d.moveRight(Math.abs(_z)/CHANGE);
				}
				break;
		}
	}

	@Override
	public void run() {
		while (true){
			try {
				Thread.sleep(100);
				d.run();
				//d.invalidate();
			} catch (InterruptedException e) {}
		}
	}
}