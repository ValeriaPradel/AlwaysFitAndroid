package mx.ipn.evershape;


import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


@SuppressLint("ShowToast")
public class SesionActivity extends Activity implements OnClickListener{
EditText etN,etP;
Button bt;
SQLiteDatabase database;
SQLiteHelper sqliteHelper;
Cursor cr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iniciar_activity);
        etN=(EditText)this.findViewById(R.id.IAetN);
        etP=(EditText)this.findViewById(R.id.IAetP);
        bt=(Button)this.findViewById(R.id.IAbtL);
        sqliteHelper = new SQLiteHelper(this, "UDSDB", null, 1);
        database=sqliteHelper.getReadableDatabase();
        bt.setOnClickListener(this);
    }

    @Override
	public void onClick(View v) {
    	this.validar(etN, etP);
		
	}

	private void validar(EditText etN, EditText etP) {
		String nombre=etN.getText().toString();
		String pass=etP.getText().toString();
		cr=database.rawQuery("SELECT NOMBRE,PASS FROM USUARIOS WHERE NOMBRE='"+nombre+"'", null);
		String nomdb = null;
		String passdb = null;
		if(cr.moveToFirst()){
			do{
				nomdb=cr.getString(0);
				passdb=cr.getString(1);
			}while(cr.moveToNext());
			
			if(nombre.equals(nomdb) && pass.equals(passdb)){
				Toast.makeText(this, "¡Inicio de Sesion Correcto!", Toast.LENGTH_SHORT).show();
				Intent it=new Intent(this,PrincipalActivity.class);
				it.putExtra("nombres", nomdb);
				startActivity(it);
				database.close();
				cr.close();
				this.finish();
			}else{
				AlertDialog.Builder alert=new AlertDialog.Builder(this);
				alert.setIcon(R.drawable.sad_ico);
				alert.setTitle("Usuario No Encontrado");
				alert.setMessage("¿Estas seguro que te registraste?");
				alert.show();
			}
		
		}else{
			Toast.makeText(this,"No hay Registros", Toast.LENGTH_SHORT).show();
		}
		
	}



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
        	return true;
 
        	default:
        		return super.onOptionsItemSelected(item);
        		
        		
        }
  
        
    }	
}