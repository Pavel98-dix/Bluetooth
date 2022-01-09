  package com.example.bluetooth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

  public class MainActivity extends AppCompatActivity {
    ImageView img1;
    BluetoothAdapter miBlue;
    TextView txt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img1=(ImageView)findViewById(R.id.imagen);
        txt1=(TextView)findViewById(R.id.tv1);

        miBlue=BluetoothAdapter.getDefaultAdapter();
        if (miBlue==null)
        {
            Toast.makeText(getApplicationContext(),"El dispositivo no cuenta con Bluetooth",Toast.LENGTH_SHORT).show();

        }else
            {
                Toast.makeText(getApplicationContext(),"Bluetooth encontrado",Toast.LENGTH_SHORT).show();
            }

    }

      @Override
      public boolean onCreateOptionsMenu(Menu menu) {
          MenuInflater inflar= getMenuInflater();
          inflar.inflate(R.menu.operaciones,menu);
        return true;
      }

      @Override
      public boolean onOptionsItemSelected(@NonNull MenuItem item) {
          boolean estado= false;
          switch (item.getItemId())
          {
              case R.id.uno:
                    if (!miBlue.isEnabled())
                    {
                        Toast.makeText(getApplicationContext(),"Encendido Bluetooth",Toast.LENGTH_SHORT).show();
                        Intent intencion= new Intent (BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                        startActivityForResult(intencion,0);
                    }else
                    {
                        Toast.makeText(getApplicationContext(),"Bluetooth ya esta encendido",Toast.LENGTH_SHORT).show();
                    }
                    estado=true;
                  break;
              case R.id.dos:
                    if (!miBlue.isDiscovering())
                    {
                        Toast.makeText(getApplicationContext(),"Dispositivo visible",Toast.LENGTH_SHORT).show();
                        Intent intencion2= new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                        startActivityForResult(intencion2,1);

                    }
                    estado=true;
                  break;
              case R.id.tres:
                    if (miBlue.isEnabled())
                    {
                        miBlue.disable();
                        Toast.makeText(getApplicationContext(),"Apagando Bluetooth",Toast.LENGTH_SHORT).show();

                    }
                    estado=true;
                  break;
              case R.id.cuatro:
                    if (miBlue.isEnabled())
                    {
                        txt1.setText("Dispositivos emparejados \n");
                        Set<BluetoothDevice> dispositivos= miBlue.getBondedDevices();
                        for (BluetoothDevice device:dispositivos){
                            txt1.append("\nDispositivos: "+device.getName()+", "+device.getAddress()+", "+device.getBondState());

                        }
                    }else
                    {
                        Toast.makeText(getApplicationContext(),"Debes encender el Bluetooth para emparejar",Toast.LENGTH_SHORT).show();
                    }
                  break;
          }
          return estado;
      }

      @Override
      protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            switch (requestCode){
                case 0:
                        if (requestCode==RESULT_OK)
                        {
                            Toast.makeText(getApplicationContext(),"Encendido",Toast.LENGTH_SHORT).show();
                        }else
                        {
                            Toast.makeText(getApplicationContext(),"No responde",Toast.LENGTH_SHORT).show();
                        }

                    break;


            }
        super.onActivityResult(requestCode, resultCode, data);
      }
  }