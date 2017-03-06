package mx.itesm.m1_srb_labo_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mx.itesm.m1_srb_labo_login.mx.itesm.m1_srb_labo_login.model.User;
import mx.itesm.m1_srb_labo_login.mx.itesm.m1_srb_labo_login.model.UserService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edUsername;
    EditText edPassword;
    static final ArrayList<User> usersList = new ArrayList<User>();
    static final int REQUEST_CODE_REGISTRAR = 1;
    static final int REQUEST_CODE_RECUPERAR = 2;
    private static UserService userService = new UserService(usersList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edUsername = (EditText) findViewById(R.id.editUsuario);
        edPassword = (EditText) findViewById(R.id.editPassword);
        TextView tvRecuperar = (TextView) findViewById(R.id.tVRecoverPassword);
        Button btnLogin = (Button) findViewById(R.id.buttonIniciarSession);
        Button btnRegistry = (Button) findViewById(R.id.buttonRegistrarse);

        btnLogin.setOnClickListener(this);
        btnRegistry.setOnClickListener(this);
        tvRecuperar.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            Bundle datos = data.getExtras();
            if (requestCode == REQUEST_CODE_REGISTRAR) {
                userService = (UserService) datos.getSerializable("userService");
            } else if (requestCode == REQUEST_CODE_RECUPERAR) {
                User user = userService.findUser(datos.getString("username"));
                user.setPassword(datos.getString("password"));
            }
        }
    }

    @Override
    public void onClick(View v){

        switch (v.getId()) {
            case R.id.buttonIniciarSession:
                sessionLogin();
                break;
            case R.id.buttonRegistrarse:
                registerUser();
                break;
            case R.id.tVRecoverPassword:
                recoverPassword();
                break;
        }
    }

    private void sessionLogin(){
        Intent intent = new Intent(MainActivity.this, IniciarSesionActivity.class);
        User usr = userService.findUser(edUsername.getText().toString());
        if( usr != null && usr.getPassword().equals(edPassword.getText().toString())) {
             intent.putExtra("username",usr.getUsername());
             intent.putExtra("password",usr.getPassword());
             startActivity(intent);
        }else {
            Toast.makeText(this.getApplicationContext(),"Datos de acceso incorrectos",Toast.LENGTH_LONG).show();
            edUsername.requestFocus();
        }
    }

    private void registerUser() {
        Intent intent = new Intent(MainActivity.this, RegistrarActivity.class);
        intent.putExtra("userService",userService);
        startActivityForResult(intent,REQUEST_CODE_REGISTRAR);
    }

    private void recoverPassword(){
        Intent intent = new Intent(MainActivity.this, ModificarContrasenaActivity.class);
        User usr = userService.findUser(edUsername.getText().toString());
        if( usr != null ) {
            intent.putExtra("username",usr.getUsername());
            intent.putExtra("password",usr.getPassword());
            startActivityForResult(intent,REQUEST_CODE_RECUPERAR);
        }else {
            Toast.makeText(this.getApplicationContext(),"No existe usuario registrado",Toast.LENGTH_LONG).show();
            edUsername.requestFocus();
        }

    }

}
