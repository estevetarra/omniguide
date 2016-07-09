package omniguide.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
/**
 * Created by David on 09-Jul-16.
 */
public class XiaomiBand {
    //Private instances and data from the band
    private String position; //left or right hand
    private BluetoothAdapter Badapter = BluetoothAdapter.getDefaultAdapter();
    private BluetoothDevice device;


    public XiaomiBand(){
        //this.device = new android.bluetooth.BluetoothDevice();
    }


    public XiaomiBand(String mac){
        //Addres for one device is C8:0F:10:0B:1F:8D
        /*Termporal*/ mac = "C8:0F:10:0B:1F:8D";
        //Check for correct mac adress
        if(Badapter.checkBluetoothAddress(mac)) {

            //if true add device to class
            try {
                this.device = Badapter.getRemoteDevice(mac);
            }catch (Exception e){
                //do something
            }

        }else{
            //notify somehow
        }



    }

}
