package mx.ipn.evershape.FragmentsActivities;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import mx.ipn.evershape.R;

public class ComidasFragment extends Fragment {

	public ComidasFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.comidas_fragment_layout, container,false);
	}

}
