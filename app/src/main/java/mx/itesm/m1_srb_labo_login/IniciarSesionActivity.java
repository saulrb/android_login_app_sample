package mx.itesm.m1_srb_labo_login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class IniciarSesionActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvUser;
    TextView tvPassword;
    TextView tvBienvenida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        tvBienvenida = (TextView) findViewById(R.id.text_welcome);
        tvUser = (TextView) findViewById(R.id.text_usuario_logeado);
        tvPassword = (TextView) findViewById(R.id.text_password);
        Button btnSalir = (Button) findViewById(R.id.buttonSalir);

        if(getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            tvUser.setText("Usuario:"+bundle.getString("username"));
            tvPassword.setText("Contraseña:"+bundle.getString("password"));
            tvBienvenida.setText("Bienvenido "+bundle.getString("username"));
        }
        btnSalir.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this.getApplicationContext(), "Su sesoion ha terminado", Toast.LENGTH_LONG).show();;
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(this.getApplicationContext(),"Su session ha terminad",Toast.LENGTH_LONG).show();
            finish();
        }
        return super.onKeyDown(keyCode,event);
    }
}
