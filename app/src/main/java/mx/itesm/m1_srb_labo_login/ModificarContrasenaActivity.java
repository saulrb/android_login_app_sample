package mx.itesm.m1_srb_labo_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ModificarContrasenaActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edPassword;
    EditText edConfirmPassword;
    String username = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_contrasena);
        username = getIntent().getExtras().getString("username");
        edPassword = (EditText) findViewById(R.id.editPassword);
        edConfirmPassword = (EditText) findViewById(R.id.editPassword2);
        Button btnAccept = (Button) findViewById(R.id.buttonAceptar);
        btnAccept.setOnClickListener(this);
    }
   @Override
    public void onClick(View v){
        if(edPassword.getText().toString().equals(edConfirmPassword.getText().toString())) {
            Intent intent = new Intent();
            intent.putExtra("password",edPassword.getText().toString());
            intent.putExtra("username",username);
            setResult(RESULT_OK,intent);
            finish();
        }else
            Toast.makeText(this.getApplicationContext(),"Contraseñas no coinciden",Toast.LENGTH_LONG).show();
    }
}
