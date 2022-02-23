package com.example.fetching_api_using_retrofit_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.textclassifier.TextClassifier;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

private Button getButton;
private TextView statusCode;
private TextView  detailedResponse;
//private EditText cityName;
private Spinner spinner;
private String selectedCity="No Selection";//default value

//String url="api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}";
String apikey="taken apikey should be given here to fetch the data!";
//private ListView items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Weather Conditions"); //to change Main Activity's label without affecting app's label.
        setContentView(R.layout.activity_main);
        statusCode=(TextView)findViewById(R.id.txtStatusCode);
        detailedResponse=(TextView) findViewById(R.id.txtDetails);

        //for the selection of the city;
        spinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cities_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCity=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectedCity="No Selection";
                //{Toast.makeText(MainActivity.this,"Please select a city to see it's weather conditions!",
                        //Toast.LENGTH_LONG).show();}
            }
        });

        getButton=(Button) findViewById(R.id.btnGet);
        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedCity.equals("No Selection")) {
                    Toast.makeText(MainActivity.this,"Please select a city to see it's weather conditions!",
                    Toast.LENGTH_LONG).show();
                    detailedResponse.setText("Please select a city to see it's weather conditions!!");
                    return;
                } else {
                    Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class); // with "create", it calls an API according to the
                    Call<Model> call = methods.getWeather(selectedCity, apikey); //cityName is taken from the user.

                    call.enqueue(new Callback<Model>() {
                        @Override
                        public void onResponse(Call<Model> call, Response<Model> response) {
                            statusCode.setText(response.code() + "");
                            detailedResponse.setTextSize(16);

                            Model myResponse = response.body();
                            Main main = myResponse.getMain();
                            Double temp = main.getTemp();
                            Integer temperature = (int) (temp - 273.15); //Kelvin to Celsius,taken as integer

                            ArrayList<Weather> weather = response.body().getWeather();

                            for (Weather weather1 : weather) {
                                detailedResponse.setText(selectedCity + "'s weather features\n\n" + String.valueOf(temperature) + " Celsius(C)" + ", " +
                                        weather1.getDescription().toString());
                            }

                        }

                        @Override
                        public void onFailure(Call<Model> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "An error is occured while fetching the api,try later!",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
}