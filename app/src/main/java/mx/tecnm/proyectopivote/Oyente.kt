package mx.tecnm.proyectopivote

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import com.google.firebase.firestore.GeoPoint
import kotlinx.android.synthetic.main.activity_main.*

class Oyente (p:MainActivity) : LocationListener {

    var puntero = p


    override fun onLocationChanged(location: Location) {
        puntero.TeViPosicionGPS.setText("")
        var geoPoscionGPS = GeoPoint(location.latitude, location.longitude)

        for(item in puntero.posicion){

            if(item.estoyEn(geoPoscionGPS)){
                puntero.TeViPosicionGPS.setText("Estas en: ${item.lugar}")
            }else{
                puntero.TeViPosicionGPS.setText("Else")
            }

        }//for
    }//onLocation



    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

    }

    override fun onProviderEnabled(provider: String) {

    }

    override fun onProviderDisabled(provider: String) {

    }


}//class