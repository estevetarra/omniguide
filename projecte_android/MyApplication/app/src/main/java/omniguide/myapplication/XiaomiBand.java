package omniguide.myapplication;

import android.content.Context;

/**
 * Created by David on 09-Jul-16.
 */
public class XiaomiBand {
    //Private instances and data from the band
    private String position; //left or right hand



    //For debugging...
    public String state = "";


    public XiaomiBand(Context context, String mac){
        //Addres for one device is C8:0F:10:0B:1F:8D
        //this.coms = new BLECommunicationManager(context, mac);
        this.state = "Somehow it worked!!!!";
        //Test sending vibration (temporal code)

    }



}
