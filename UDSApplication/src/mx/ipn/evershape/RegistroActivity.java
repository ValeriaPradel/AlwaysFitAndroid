package mx.ipn.evershape;

//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
//import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegistroActivity extends Activity implements OnClickListener {
EditText etN,etA,etE,etP,etAl,etPs;
Button bt;
SQLiteDatabase database;
SQLHelper hp;
Cursor cr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_activity);
        etN=(EditText)this.findViewById(R.id.ARetN);
        etA=(EditText)this.findViewById(R.id.ARetA);
        etE=(EditText)this.findViewById(R.id.ARetE);
        etP=(EditText)this.findViewById(R.id.ARetW);
        etAl=(EditText)this.findViewById(R.id.ARetH);
        etPs=(EditText)this.findViewById(R.id.ARetp);
        bt=(Button)this.findViewById(R.id.ARbtS);
    bt.setOnClickListener(this);
    }
    @Override
	public void onClick(View v) {
		
		this.registrar();
		
	}
    void registrar(){
    	String nombre=etN.getText().toString();
        String apellido=etA.getText().toString();
        String edad=etE.getText().toString();
        String peso=etP.getText().toString();
        String altura=etAl.getText().toString();
        String pass=etPs.getText().toString();
        Boolean con=true;
        if(nombre.length()==0){
        	etN.setError("Campo vacio");
        	con=false;
        }if(apellido.length()==0){
        	etA.setError("Campo vacio");
        	con=false;
        }if(edad.length()==0){
        	etE.setError("Campo vacio");
        	con=false;
        }if(peso.length()==0){
        	etP.setError("Campo vacio");
        	con=false;
        }if(altura.length()==0){
        	etAl.setError("Campo vacio");
        	con=false;
        }if(pass.length()==0){
        	etPs.setError("Campo vacio");
        	con=false;
        }if(con==false){
        	AlertDialog.Builder alert=new AlertDialog.Builder(this);
        	alert.setIcon(R.drawable.alert_ico);
        	alert.setTitle("Errores en los campos");
        	alert.setMessage("Algunos de tus campos tienen errores verificalos");
        	alert.show();
        }else{
        	hp= new SQLHelper(this,"UDSDB",null,1);
            database=hp.getWritableDatabase();
            ContentValues values;
            values = new ContentValues();
            values.put("NOMBRE", nombre);
            values.put("APELLIDO", apellido);
            values.put("EDAD", edad);
            values.put("PESO", peso);
            values.put("ALTURA", altura);
            values.put("PASS", pass);
            database.insert("USUARIOS", null, values);
            values = null;
            database.close();
            Toast.makeText(this,"Registro Exitoso",Toast.LENGTH_SHORT ).show();
            this.finish();
        }    
    } 
    
  /*  private boolean isValidNumber(String email) {
		String NUMBER_PATTERN = "[0-9]";
		Pattern pattern = Pattern.compile(NUMBER_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}*/
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        ActionBar ab = getActionBar();
        ab.setHomeButtonEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id){
        case R.id.action_settings:
        	return true;
        case android.R.id.home:
        	this.finish();
        default:
        	return super.onOptionsItemSelected(item);
        }
        
        
    }
    class SQLHelper extends SQLiteOpenHelper{

		public SQLHelper(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			String sql="CREATE TABLE IF NOT EXISTS USUARIOS(_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
					"NOMBRE TEXT," +
					"APELLIDO TEXT," +
					"EDAD INT," +
					"PESO INT," +
					"ALTURA INT," +
					"PASS TEXT);";
			db.execSQL(sql);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		}
    	
    }
	
}