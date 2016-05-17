package mx.ipn.evershape.FragmentsActivities;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import mx.ipn.evershape.R;

public class EjerciciosFragment extends Fragment {

	

	public EjerciciosFragment() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.ejercicios_fragment_layout,container,false);
	}

}
