package mx.ipn.evershape;



import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import mx.ipn.evershape.FragmentsActivities.*; 


public class PrincipalActivity extends Activity {
	private DrawerLayout navDrawer;
    private ListView navList;
    String[] objetos;
    CharSequence mTitle;
    
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_activity);
        
        //Drawer Layout
        navDrawer=(DrawerLayout) this.findViewById(R.id.idDrawer);
        //Lista
        this.navList = (ListView) findViewById(R.id.idListView);
        // Load an array of options names
        objetos = getResources().getStringArray(
                R.array.list_options);
 
        // Set previous array as adapter of the list
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, objetos);
        navList.setAdapter(adapter);
        navList.setOnItemClickListener(new DrawerOnClickListener());
    }
	
	
    class DrawerOnClickListener implements ListView.OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			 // Crear nuevo fragmento con posicion a definir
	        Fragment fragment = null;
	        switch(position){
	        case 0:
	        	fragment=new CuentaFragment();
	        break;
	        case 1:
	        	fragment=new ComidasFragment();
	        	break;
	        case 2:
	        	fragment=new CaloriasFragment();
	        	break;
	        case 3:
	        	fragment=new EjerciciosFragment();
	        	break;
	        case 4:
	        	fragment=new ConfiguracionesFragment();
	        	break;
	        case 5:
	        	break;
	        default:
	        	fragment=new CuentaFragment();
	        break;
	        }
	         
	  //Reemplazar contenido
	        FragmentManager fmanager;
	        fmanager=getFragmentManager();
	        fmanager.beginTransaction()
	        .replace(R.id.content_frame, fragment).commit();

	        // Se actualiza el item seleccionado y el título, después de cerrar el drawer
	        navList.setItemChecked(position, true);
	        setTitle(objetos[position]);
	        navDrawer.closeDrawer(navList);
	       

			
		}
		
		 public void setTitle(CharSequence title) {
	            mTitle = title;
	            getActionBar().setTitle(mTitle);
	     }
    	
    }
}