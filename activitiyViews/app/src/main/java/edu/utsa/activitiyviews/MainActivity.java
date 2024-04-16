package edu.utsa.activitiyviews;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.ComponentActivity;

import java.io.IOException;
import java.util.Scanner;

public class MainActivity extends ComponentActivity {
    private Button button;
    private AssetManager assets;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        assets = getAssets();
        setupButtons();
    }

    private void setupButtons() {
        button = (Button) findViewById(R.id.login);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText userText = (EditText) findViewById (R.id.inputName);
                EditText passText = (EditText) findViewById(R.id.inputPassword);
                if(authenticate(userText.getText().toString(),passText.getText().toString())){
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
                else{
                    userText.setText("");
                    passText.setText("");
                    userText.setError("Incorrect username");
                    passText.setError("Incorrect password");
                }
            }
        });
    }
    private boolean authenticate(String username, String password){
        Scanner scan;
        String str = "";
        String [] arr = null;
        boolean authenticated = false;
        try {
            scan = new Scanner(assets.open("login.txt"));
            while(scan.hasNext()){

                //EditText t = (EditText) findViewById(R.id.inputName);

                str = scan.nextLine();
                //t.setText(str);
                arr = str.split(",");
                if(username.equalsIgnoreCase(arr[1]) && password.equals(arr[2])){
                    authenticated = true;
                    break;
                }
            }
            scan.close();
        }
        catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }


        return authenticated;
    }

}
