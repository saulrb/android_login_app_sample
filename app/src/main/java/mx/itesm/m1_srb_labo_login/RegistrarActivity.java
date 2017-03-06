package mx.itesm.m1_srb_labo_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import mx.itesm.m1_srb_labo_login.mx.itesm.m1_srb_labo_login.model.User;
import mx.itesm.m1_srb_labo_login.mx.itesm.m1_srb_labo_login.model.UserService;


public class RegistrarActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edUsername;
    EditText edPassword;
    EditText edPasswordConf;
    static UserService userService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        userService = (UserService) getIntent().getExtras().get("userService");
        edUsername = (EditText) findViewById(R.id.editUsuario);
        edPassword = (EditText) findViewById(R.id.editPassword);
        edPasswordConf = (EditText) findViewById(R.id.editPassword2);

        Button btnRegister = (Button) findViewById(R.id.buttonRegistrarse);
        btnRegister.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        if(edUsername.getText() != null &&
            edPassword.getText() != null &&
            edPasswordConf.getText() != null  &&
            edPassword.getText().toString().equalsIgnoreCase(edPasswordConf.getText().toString()) &&
                userService.addUser(new User(edUsername.getText().toString(),edPassword.getText().toString()))   ){
            Toast.makeText(this.getApplicationContext(), "El Usuario se ha registrado exitosamente", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtra("userService",userService);
            setResult(RESULT_OK,intent);
            finish();
        }else {
            Toast.makeText(this.getApplicationContext(),"Datos incorrectos o usuario ya existe",Toast.LENGTH_LONG).show();
        }
    }


}
