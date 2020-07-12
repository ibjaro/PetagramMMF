package mx.unam.petagrammmf.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

import mx.unam.petagrammmf.AcercaDe;
import mx.unam.petagrammmf.Contacto;
import mx.unam.petagrammmf.MainActivity;
import mx.unam.petagrammmf.MascotasFavoritas;
import mx.unam.petagrammmf.R;
import mx.unam.petagrammmf.adapter.MascotaAdaptador;
import mx.unam.petagrammmf.pojo.Mascota;

public class RVMascotasFragment extends Fragment {
    private ArrayList<Mascota> mascotas;
    private RecyclerView listamascotas;
    private int validarBundle=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_rvmascotas,container,false);

        //hacemos visible la listamascotas, que es un recyclerView
        listamascotas =  v.findViewById(R.id.rvMascotas);
        //mostraremos la lista de mascotas en forma de Linearlayout
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listamascotas.setLayoutManager(llm);
        //verificamos si hay datos enviados por otra activity
        datosBundle();
        //si validarBundle es igual a 1, quiere decir que No se han recibido datos de otra activity
        if(validarBundle==1)
        {
            //hacemos el llamado al metodo inicializarListaMascotas
            inicializarListaMascotas();
        }

        //hacemos el llamado al metodo inicializarAdaptador
        inicializarAdaptador();
        return v;
    }
    //funcion para verificar si hay datos Bundle, provenientes de la actividad MascotasFavoritas
    public void datosBundle()
    {
        //creamos un Bundle para recibir los datos de MascotasFavoritas al presionar el boton de atras en la ActionBar
        Bundle datosBundleAtras = getActivity().getIntent().getExtras();
        //preguntamos si este objeto viene con datos o esta vacio
        if(datosBundleAtras!=null)
        {//si hay datos estos se los agregamos a un ArrayList de mascotas
            mascotas = (ArrayList<Mascota>) datosBundleAtras.getSerializable("listamascotasatras");
        }else{
                //si el bundle No trae datos entonces se inicializara la lista con datos
                validarBundle=1;
                //return;
            }
    }

    //funcion para inicializar adartador, con los datos de las mascotas
    public void inicializarAdaptador()
    {
        //creamos el adaptador y le pasamos el arreglo de mascotas, en que activity estamos
        //y el numero es para saber en la clase MascotaAdaptador como mostrara los datos y si seran clicables o no
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, getActivity(), 1);
        listamascotas.setAdapter(adaptador);
        //agregamos iconos a cada TabLayout
    }
    //funcion para inicializar los datos de las mascotas, por defecto o si no hay datos Bundle recibidos de otra Actividad
    public void inicializarListaMascotas()
    {
        //creamos un arreglo de objetos y le cargamos datos
        mascotas = new ArrayList<>();
        mascotas.add(new Mascota(R.drawable.elefante,"Elefantin",0));
        mascotas.add(new Mascota(R.drawable.conejo,"Conejo",0));
        mascotas.add(new Mascota(R.drawable.tortuga,"Tortuga",0));
        mascotas.add(new Mascota(R.drawable.caballo,"Caballo",0));
        mascotas.add(new Mascota(R.drawable.rana,"Rana",0));
    }

    //funcion get que devuelve el ArrayList de mascotas
    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }
}
