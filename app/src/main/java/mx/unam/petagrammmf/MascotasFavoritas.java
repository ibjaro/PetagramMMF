package mx.unam.petagrammmf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;

import java.util.ArrayList;

import mx.unam.petagrammmf.adapter.MascotaAdaptador;
import mx.unam.petagrammmf.pojo.Mascota;

public class MascotasFavoritas extends AppCompatActivity {

    private Toolbar actiontoolbarf;
    private ArrayList<Mascota> mascotasFav, mascotasReci, mascotahardcodeada;
    private RecyclerView listamascotasFav;
    private int cantidad=0, fot, li;
    private String nomb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_favoritas);
        //Agregamos la barra personalizada a esta activity
        actiontoolbarf = findViewById(R.id.miActionToolBarF);
        setSupportActionBar(actiontoolbarf);
        //le cambiamos el titulo a la barra personalizada para MascotasFavoritas
        actiontoolbarf.setTitle(R.string.titulo_actiontoolbarf);
        actiontoolbarf.setTitleTextColor(Color.WHITE);
        //validamos que la barra no este NUll
        if(getSupportActionBar()!=null)
        {
            //habilitamos el boton de subir
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        //hacemos visible la mascota favorita, que es un recyclerView
        listamascotasFav = findViewById(R.id.rvMascotasF);
        //mostraremos la lista de mascotas en forma de Linearlayout
        LinearLayoutManager llmf = new LinearLayoutManager(this);
        llmf.setOrientation(LinearLayoutManager.VERTICAL);

        listamascotasFav.setLayoutManager(llmf);
        //hacemos el llamado al metodo inicializarListaMascotas
        inicializarListaMascotasF();
        //hacemos el llamado al metodo inicializarAdaptador
        inicializarAdaptadorF();

    }
    //funcion para inicializar adartador
    public void inicializarAdaptadorF()
    {
        //creamos el adaptador y le pasamos el arreglo de mascotas favoritas, en que activity estamos
        //y que numero es para saber en la clase MascotaAdaptador como mostrara los datos y si seran clicables o no
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotasFav, this,2);
        listamascotasFav.setAdapter(adaptador);

    }
    //funcion para inicializar los datos de las mascotas, recibidos de la MainActivity
    public void inicializarListaMascotasF()
    { //inicializamos un arreglo para las mascotas favoritas(mascotasF) y mascotas recibidas(mascotasR)
        mascotasFav = new ArrayList<>();
        mascotasReci = new ArrayList<>();
        mascotahardcodeada = new ArrayList<>();
        //creamos un bundle que recibira el arreglo de objetos enviado por la activity anterior
        Bundle objetoBundleMascotas = getIntent().getExtras();
        //preguntamos si este objeto viene con datos o esta vacio
        if(objetoBundleMascotas!=null)
        {//si hay datos estos se los agregamos a un ArrayList de mascotas favorias
            mascotasReci = (ArrayList<Mascota>) objetoBundleMascotas.getSerializable("listamascotas");
            for(int i=0; i<mascotasReci.size(); i++)
            {
                if(mascotasReci.get(i).getCantidad_like()>0)
                {
                    mascotasFav.add(mascotasReci.get(i));
                }else{
                    mascotahardcodeada.add(mascotasReci.get(i));
                    cantidad = cantidad+1;
                }
            }
            //preguntamos si algun objeto no tenia Like
            if(cantidad>0)
            {//for para llenar con datoslas marcotas que no tienen like, hardcodear para asi poder mostar 5 mascotas.
                for(int j=0;j<mascotahardcodeada.size();j++)
                {
                    fot = mascotahardcodeada.get(j).getFoto();
                    nomb = mascotahardcodeada.get(j).getNombre_mascota();
                    li = mascotahardcodeada.get(j).getCantidad_like()+7+j;
                    mascotasFav.add(new Mascota(fot,nomb,li));
                }
            }

        }
    }

    //funcion sobre escrita donde se captura el evento del boton atras de la ActionBar
    @Override
    public boolean onSupportNavigateUp() {
        //Si el boton de atras se selecciona se llamara a la funcion datosBundleIrAtras()
        datosBundleIrAtras();
        return super.onSupportNavigateUp();
    }
    //funcion para enviar los datos en el botos atras
    public void datosBundleIrAtras()
    {
        //creamos un bundle que enviara el arreglo de objetos a la activity gerarquica atras
        Bundle envioBundleDatosAtras = getIntent().getExtras();
        //el arreglo de objetos le enviamos de manera Serializable ya que en la clase Mascota esta el implemente Serializable
        envioBundleDatosAtras.putSerializable("listamascotasatras",mascotasReci);
        //hacemos un intent para indicar que enviaremos datos desde esta activity hacia la siguiente
        Intent intentatras = new Intent(this, MainActivity.class);
        intentatras.putExtras(envioBundleDatosAtras);
        //iniciamos el intent
        startActivity(intentatras);
        finish();//finalizamos( cerramos ) la activity( MascotasFavoritas ), para liberar memoria
    }
    //funcion sobre escrita donde se captura el evento del boton atras de la barra inferior
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            datosBundleIrAtras();
        }
        return super.onKeyDown(keyCode, event);
    }
}