package mx.ipn.evershape;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity implements OnClickListener {
Button btR,btI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btR=(Button)this.findViewById(R.id.AMbtR);
        btI=(Button)this.findViewById(R.id.AMbtI);
        btR.setOnClickListener(this);
        btI.setOnClickListener(this);
    }
    @Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.AMbtR:
			Intent itR=new Intent(this,RegistroActivity.class);
			startActivity(itR);
			break;
		case R.id.AMbtI:
			Intent itI=new Intent(this,SesionActivity.class);
			startActivity(itI);
			break;
		}
		
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

	
}
