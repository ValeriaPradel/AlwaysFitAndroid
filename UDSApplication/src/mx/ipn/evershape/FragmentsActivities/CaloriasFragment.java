package mx.ipn.evershape.FragmentsActivities;

import android.app.Fragment;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import mx.ipn.evershape.R;

public class CaloriasFragment extends Fragment implements SensorEventListener {
SensorManager sm;
String servicio;
TextView tv1;
	

	public CaloriasFragment() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View vista=inflater.inflate(R.layout.calorias_fragment_layout,container,false);
		servicio=getActivity().SENSOR_SERVICE;
		sm=(SensorManager) getActivity().getSystemService(servicio);
		sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_STEP_COUNTER), SensorManager.SENSOR_DELAY_NORMAL);
		tv1=(TextView) vista.findViewById(R.id.ctv2);
		return vista;
	}
	@Override
	public void onSensorChanged(SensorEvent event) {
		float values[]=event.values;
		float val;
		if(event.sensor.getType()==Sensor.TYPE_STEP_COUNTER){
			val=values[0];
			tv1.setText("Pasos dados:\n "+String.valueOf(val));
	}
	}
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	public void onPause(){
		super.onPause();
		sm.unregisterListener(this);
	}
	public void onResume(){
		super.onResume();
		sm.registerListener(this,sm.getDefaultSensor(Sensor.TYPE_STEP_COUNTER), SensorManager.SENSOR_DELAY_NORMAL);
	}
}
