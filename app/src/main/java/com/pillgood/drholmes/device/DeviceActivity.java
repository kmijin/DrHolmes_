package com.pillgood.drholmes.device;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pillgood.drholmes.R;
import com.pillgood.drholmes.api.DeviceAPI;
import com.pillgood.drholmes.api.device.DeviceClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeviceActivity extends Fragment {

    View view;

    private final static String TAG = "DeviceActivity";

    Retrofit retrofit;
    DeviceAPI deviceAPI;

    private TextView mConnectionState;
    private TextView mDataView;
    private String mDeviceName;
    private String mDeviceAddress;
    private BluetoothLeService mBluetoothLeService;
    private ArrayList<ArrayList<BluetoothGattCharacteristic>> mGattCharacteristics =
            new ArrayList<ArrayList<BluetoothGattCharacteristic>>();
    private boolean mConnected = false;
    private BluetoothGattCharacteristic mNotifyCharacteristic;

    private final String LIST_NAME = "NAME";
    private final String LIST_UUID = "UUID";

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference();

    private String[] pillData;
    private String[] pillTF = new String[2];

    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) service).getService();
            if (!mBluetoothLeService.initialize()) {
                Log.e(TAG, "Unable to initialize Bluetooth");
//                finish();
            }
            mBluetoothLeService.connect(mDeviceAddress);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBluetoothLeService = null;
        }
    };

    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BluetoothLeService.ACTION_GATT_CONNECTED.equals(action)) {
                mConnected = true;
                updateConnectionState("Connected");
                getActivity().invalidateOptionsMenu();
                Log.e("CONNECTED", "OK");
            } else if (BluetoothLeService.ACTION_GATT_DISCONNECTED.equals(action)) {
                mConnected = false;
                updateConnectionState("Disconnected");
                getActivity().invalidateOptionsMenu();
                clearUI();
                Log.e("DISCONNECTED", "OK");
            } else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
                displayGattServices(mBluetoothLeService.getSupportedGattServices());
                Log.e("SERVICES DISCOVERED", "OK");
                updateConnectionState("Services are ready");
            } else if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action)) {
                pillData = intent.getStringExtra(BluetoothLeService.EXTRA_DATA).split(" ");
                pillTF[0] = Integer.parseInt(pillData[0]) > 100 ? "1" : "0";
                pillTF[1] = Integer.parseInt(pillData[1]) > 100 ? "1" : "0";
                displayData(pillTF);
                postDataToDB(pillTF);
                Log.e("DATA AVAILABLE", "OK");
            }
        }
    };

    private void clearUI() {
        mDataView.setText("No data");
    }

    Button connectBtn;
    Button dataBtn;
    View.OnClickListener cl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_device, container, false);

        connectBtn = (Button) view.findViewById(R.id.connect_btn);
        dataBtn = (Button) view.findViewById(R.id.get_data_btn);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.connect_btn:
                        getParentFragmentManager().beginTransaction().replace(R.id.main_frame, new DeviceScanActivity()).commit();
                        break;
                    case R.id.get_data_btn:
                        if (mGattCharacteristics != null) {
//                        final BluetoothGattCharacteristic characteristic =
//                                mGattCharacteristics.get(groupPosition).get(childPosition);
                            Log.i(TAG, mGattCharacteristics.toString());
                            final BluetoothGattCharacteristic characteristic =
                                    mGattCharacteristics.get(2).get(0);
                            final int charaProp = characteristic.getProperties();
                            if ((charaProp | BluetoothGattCharacteristic.PROPERTY_READ) > 0) {
                                if (mNotifyCharacteristic != null) {
                                    mBluetoothLeService.setCharacteristicNotification(
                                            mNotifyCharacteristic, false);
                                    mNotifyCharacteristic = null;
                                }
                                mBluetoothLeService.readCharacteristic(characteristic);
                            }
                            if ((charaProp | BluetoothGattCharacteristic.PROPERTY_NOTIFY) > 0) {
                                mNotifyCharacteristic = characteristic;
                                mBluetoothLeService.setCharacteristicNotification(
                                        characteristic, true);
                            }
                        }
                        break;
                }
            }
        };
        connectBtn.setOnClickListener(cl);
        dataBtn.setOnClickListener(cl);

        mConnectionState = (TextView) view.findViewById(R.id.device_connection_state);
        mDataView = (TextView) view.findViewById(R.id.device_data);

        getParentFragmentManager().setFragmentResultListener("device_selected", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                mDeviceName = bundle.getString("device_name");
                mDeviceAddress = bundle.getString("device_address");
                Log.e(TAG, mDeviceName);
                ((TextView) view.findViewById(R.id.device_selected_name)).setText(mDeviceName);
                Log.e(TAG, getActivity()==null?"null":"not null");
                Intent gattServiceIntent = new Intent(getActivity(), BluetoothLeService.class);
                getActivity().bindService(gattServiceIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
                getActivity().bindService(gattServiceIntent, mServiceConnection, Context.BIND_AUTO_CREATE);

                retrofit = new Retrofit.Builder()
                        .baseUrl(deviceAPI.baseURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                deviceAPI = retrofit.create(DeviceAPI.class);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
        if (mBluetoothLeService != null) {
            final boolean result = mBluetoothLeService.connect(mDeviceAddress);
            Log.d(TAG, "Connect request result=" + result);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(mGattUpdateReceiver);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBluetoothLeService != null) {
            getActivity().unbindService(mServiceConnection);
            mBluetoothLeService = null;
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.gatt_services, menu);
        if (mConnected) {
            menu.findItem(R.id.menu_connect).setVisible(false);
            menu.findItem(R.id.menu_disconnect).setVisible(true);
        } else {
            menu.findItem(R.id.menu_connect).setVisible(true);
            menu.findItem(R.id.menu_disconnect).setVisible(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_connect:
                mBluetoothLeService.connect(mDeviceAddress);
                return true;
            case R.id.menu_disconnect:
                mBluetoothLeService.disconnect();
                return true;
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateConnectionState(final String state) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mConnectionState.setText(state);
            }
        });
    }

    private void displayData(String[] data) {
        if (data != null) {
            mDataView.setText("01: " + data[0] + " / 02: " + data[1]);
        }
    }

    private void postDataToDB(String[] data) {
        if (data != null) {
            databaseReference.child("drholmesDevice").child("01").push().setValue(data[0]);
            databaseReference.child("drholmesDevice").child("02").push().setValue(data[1]);

        }
    }

    private void displayGattServices(List<BluetoothGattService> gattServices) {
        if (gattServices == null) return;
        String uuid = null;
        String unknownServiceString = "Unknown service";
        String unknownCharaString = "Unknown characteristic";
        ArrayList<HashMap<String, String>> gattServiceData = new ArrayList<HashMap<String, String>>();
        ArrayList<ArrayList<HashMap<String, String>>> gattCharacteristicData
                = new ArrayList<ArrayList<HashMap<String, String>>>();
        mGattCharacteristics = new ArrayList<ArrayList<BluetoothGattCharacteristic>>();

        for (BluetoothGattService gattService : gattServices) {
            HashMap<String, String> currentServiceData = new HashMap<String, String>();
            uuid = gattService.getUuid().toString();
            currentServiceData.put(
                    LIST_NAME, SampleGattAttributes.lookup(uuid, unknownServiceString));
            currentServiceData.put(LIST_UUID, uuid);
            gattServiceData.add(currentServiceData);

            ArrayList<HashMap<String, String>> gattCharacteristicGroupData =
                    new ArrayList<HashMap<String, String>>();
            List<BluetoothGattCharacteristic> gattCharacteristics =
                    gattService.getCharacteristics();
            ArrayList<BluetoothGattCharacteristic> charas =
                    new ArrayList<BluetoothGattCharacteristic>();

            for (BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics) {
                charas.add(gattCharacteristic);
                HashMap<String, String> currentCharaData = new HashMap<String, String>();
                uuid = gattCharacteristic.getUuid().toString();
                currentCharaData.put(
                        LIST_NAME, SampleGattAttributes.lookup(uuid, unknownCharaString));
                currentCharaData.put(LIST_UUID, uuid);
                gattCharacteristicGroupData.add(currentCharaData);
            }
            mGattCharacteristics.add(charas);
            gattCharacteristicData.add(gattCharacteristicGroupData);
        }
    }

    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BluetoothLeService.ACTION_DATA_AVAILABLE);
        return intentFilter;
    }
    //git-test
}