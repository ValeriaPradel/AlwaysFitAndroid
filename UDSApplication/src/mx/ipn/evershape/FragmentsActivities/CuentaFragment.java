package mx.ipn.evershape.FragmentsActivities;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import mx.ipn.evershape.SQLiteHelper;
import mx.ipn.evershape.R;



public class CuentaFragment extends Fragment {
TextView tv1,tv2,tv3,tv4;
SQLiteDatabase database;
SQLiteHelper sqliteHelper;
Cursor cr;
	public CuentaFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View vista=inflater.inflate(R.layout.cuenta_fragment_layout, container,false);
		tv1=(TextView) vista.findViewById(R.id.tvNombreCuentaFragment);
		tv2=(TextView) vista.findViewById(R.id.tvPesoCuentaFragment);
		tv3=(TextView) vista.findViewById(R.id.tvAlturaCuentaFragment);
		sqliteHelper = new SQLiteHelper(getActivity(), "UDSDB", null, 1);
		Intent del=getActivity().getIntent();
		String nombre=del.getExtras().getString("nombres");
		int alturadb=0;
		int pesodb=0;
		
        database=sqliteHelper.getReadableDatabase();
        cr=database.rawQuery("SELECT ALTURA,PESO FROM USUARIOS WHERE NOMBRE='"+nombre+"'", null);
        if(cr.moveToFirst()){
        	do{
    			alturadb=cr.getInt(0);
    			pesodb=cr.getInt(1);
    		}while(cr.moveToNext());
        	tv1.setText("Hola "+nombre+"\n");
    		tv2.setText("Este es tu peso actual:"+pesodb+" kg \n");
    		tv3.setText("Y tu altura actual:"+alturadb+" cm \n");
    		database.close();
    		cr.close();
        }
        calcularIMC(alturadb,pesodb,vista);
        return vista;
	}
	
	
	int calcularIMC(int altura,int peso,View vista){
		tv4=(TextView) vista.findViewById(R.id.tvIMCCuentaFragment);
		int alturamt=altura/100;
		int imc=peso/(alturamt^2);
		if(imc<18){
			tv4.setText("Tu imc es:"+imc+" \n Tienes un Peso bajo,esto podría significar signos de desnutrición.");
		}if(imc>=18 && imc<=24.9 ){
			tv4.setText("Tu imc es:"+imc+" \n Felicidades tienes un peso normal.");
		}if(imc>=25 && imc<=26.9){
			tv4.setText("Tu imc es:"+imc+" \n Tienes Sobrepeso.");
		}if(imc>=27 && imc<=29.9){
			tv4.setText("Tu imc es:"+imc+" \n Tienes Obesidad,esto es un Riesgo relativamente alto para desarrollar enfermedades cardiovasculares.");
		}if(imc>=30 && imc<=39.9){
			tv4.setText("Tu imc es:"+imc+" \n Tienes Obesidad tipo II,esto es un muy alto para el desarrollo de enfermedades cardiovasculares.");
		}if(imc>=40){
			tv4.setText("Tu imc es:"+imc+" \n Tienes Obesidad grado III Extrema o Mórbida.El Riesgo es extremadamente "
					+ "alto para el desarrollo de enfermedades cardiovasculares.Asiste a un Especialista");
		}else{
			tv4.setText("Tu imc no fue calculado :(");
		}
		return imc;
	}
}
