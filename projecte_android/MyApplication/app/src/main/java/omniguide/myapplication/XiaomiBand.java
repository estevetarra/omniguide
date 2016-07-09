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

    public String state = "";


    public XiaomiBand(){
        //this.device = new android.bluetooth.BluetoothDevice();
    }


    public XiaomiBand(String mac){
        //Addres for one device is C8:0F:10:0B:1F:8D

        //Check for correct mac adress
        if(Badapter.checkBluetoothAddress(mac)) {

            //if true add device to class
            try {
                this.device = Badapter.getRemoteDevice(mac);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    this.device.createBond();
                }
                while(this.device.getBondState() == BluetoothDevice.BOND_BONDING);
                if(this.device.getBondState() == BluetoothDevice.BOND_BONDED) {
                    this.device.getName();

                    state = "Worked!, bonded";
                }else{
                    state = "didn't bond";
                }
            }catch (Exception e){
                //do something
                state = "Didn't work";
            }

        }else{
            //notify somehow
            state = "Wrong ID!!";
        }



    }

}
