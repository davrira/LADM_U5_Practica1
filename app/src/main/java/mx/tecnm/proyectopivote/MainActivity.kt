package mx.tecnm.proyectopivote

import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    var baseRemota = FirebaseFirestore.getInstance()
    var posicion = ArrayList<Data>()
    lateinit var locacion : LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),1)
        }//if

        baseRemota.collection("MapaTec")
            .addSnapshotListener { value, error ->

                if (error != null) {
                    return@addSnapshotListener
                }

                var resultado = ""

                posicion.clear()

                for (document in value!!){

                    var data = Data()
                    data.lugar = document.getString("lugar").toString()
                    data.p1 = document.getGeoPoint("p1")!!
                    data.p2 = document.getGeoPoint("p2")!!

                    resultado += data.toString()+"\n"
                    posicion.add(data)

                }//for

            }//addSnapshotListener

        locacion = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        var oyente = Oyente(this)
        locacion.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 01f, oyente)

    }//onCreate

}//class