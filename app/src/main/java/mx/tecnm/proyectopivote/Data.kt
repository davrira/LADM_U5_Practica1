package mx.tecnm.proyectopivote

import com.google.firebase.firestore.GeoPoint

class Data {

    var lugar = ""
    var p1 : GeoPoint = GeoPoint(0.0, 0.0)
    var p2 : GeoPoint = GeoPoint(0.0, 0.0)

    override fun toString(): String {

        return lugar +"\n" +
                p1.latitude + ","+ p2.longitude + "\n"+
                p2.latitude + ","+ p2.longitude +"\n"

    }//toString

    fun estoyEn(posicionActual : GeoPoint) : Boolean{

        if(posicionActual.latitude >= p1.latitude && posicionActual.latitude <= p2.latitude){

            if(invertir(posicionActual.longitude) >= invertir(p1.longitude)
                && invertir(posicionActual.longitude) <= invertir(p2.longitude)){

                return true

            }//if

        }//if

        return false

    }//estoyEn

    fun invertir (valor : Double) : Double{
        return  valor *-1
    }//invertir

}//class