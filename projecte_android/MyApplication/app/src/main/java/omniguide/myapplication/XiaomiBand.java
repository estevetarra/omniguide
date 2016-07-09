package omniguide.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Created by David on 09-Jul-16.
 */
public class XiaomiBand {
    //Private instances and data from the band
    private String position; //left or right hand
    private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private BluetoothDevice device;

    public String state = "";

    private boolean mScanning;
    private Handler mHandler;
    private BluetoothSocket bTSocket;

    public static final String BASE_UUID = "0000%s-0000-1000-8000-00805F9B34FB"; //%s

    public XiaomiBand(){
        //this.device = new android.bluetooth.BluetoothDevice();
    }


    public XiaomiBand(String mac){
        //Addres for one device is C8:0F:10:0B:1F:8D
        mBluetoothAdapter.enable();

        this.device = mBluetoothAdapter.getRemoteDevice(mac);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.device.createBond();
        }
        while(this.device.getBondState() == BluetoothDevice.BOND_BONDING);
        if(this.device.getBondState() == BluetoothDevice.BOND_BONDED) {
            this.state = "Found device";

            this.state = this.device.getAddress();
            try {
                UUID idToSend = (UUID) UUID.fromString(String.format(BASE_UUID, "FF05"));
                this.state = "s0";
                this.connect(idToSend);//"0xFF05"));
                //this.sendData(this.bTSocket, 8);
                //this.sendData(this.bTSocket, 2);
                this.cancel();
                //this.state = "opened and closed successfully";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            this.state = "Not Bonded";
        }

    }

    public boolean connect(UUID mUUID) {

        BluetoothDevice bTDevice = this.device;
        BluetoothSocket temp = null;

        try {
            bTSocket = bTDevice.createRfcommSocketToServiceRecord(mUUID);
            this.state = "s1";
        } catch (IOException e) {
            Log.d("CONNECTTHREAD","Could not create RFCOMM socket:" + e.toString());
            return false;
        }
        try {
            bTSocket.connect();
            this.state = "s2";
        } catch(IOException e) {
            this.state = e.toString();
            Log.d("CONNECTTHREAD","Could not connect: " + e.toString());
            //this.state = "s3";
            try {
                bTSocket.close();
                //this.state = "s4";
            } catch(IOException close) {
                Log.d("CONNECTTHREAD", "Could not close connection:" + e.toString());
                return false;
            }
        }
        return true;
    }

    public boolean cancel() {
        try {
            bTSocket.close();
        } catch(IOException e) {
            Log.d("CONNECTTHREAD","Could not close connection:" + e.toString());
            return false;
        }
        return true;
    }

    public void sendData(BluetoothSocket socket, int data) throws IOException{
        ByteArrayOutputStream output = new ByteArrayOutputStream(4);
        output.write(data);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(output.toByteArray());
    }

    public int receiveData(BluetoothSocket socket) throws IOException{
        byte[] buffer = new byte[4];
        ByteArrayInputStream input = new ByteArrayInputStream(buffer);
        InputStream inputStream = socket.getInputStream();
        inputStream.read(buffer);
        return input.read();
    }

    public String SRCommand(String command){

        //this.device.setPairingConfirmation(ture);

        return "null";
    }



}
