package com.amandamcnair.testingassign2;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import javax.net.ssl.HttpsURLConnection;

public class GetNutritionInfo extends AppCompatActivity {

    //private RecyclerView recyclerView;
    //private RecyclerView.Adapter adapter;

    ArrayList<Nutrition> nutritionAR = new ArrayList<Nutrition>();
    int id = FoodItemsRecyclerView.getIDFromClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getnutritioninfo);

        //doDownload();

        findViewById(R.id.descriptions_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doDownload();
            }
        });
    }

    private StackExchangeDownload dataDownload;

    private void doDownload() {
        if (dataDownload == null) {
            dataDownload = new StackExchangeDownload();
            dataDownload.execute();
        }
    }


    private class StackExchangeDownload extends AsyncTask<Void, Void, ResultData> {
        @Override
        protected ResultData doInBackground(Void... voids) {
            ResultData resultData = new ResultData();

            Uri.Builder builder = Uri.parse("https://api.nal.usda.gov/fdc/v1/" + id).buildUpon();
            builder.appendQueryParameter("api_key", getResources().getString(R.string.api_key));
            //builder.appendQueryParameter("generalSearchInput", "pepperoni pizza");

            try {
                URL url = new URL(builder.toString());
                Log.i("RESULT", url.toString());
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                StringBuilder jsonData = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    jsonData.append(line);
                }

                StringBuilder titleBuilder = new StringBuilder();
                // changed HashSet to TreeSet so that the results are sorted.

                //TreeSet<String> typesList = new TreeSet<>();
                HashSet<String> typesList = new HashSet<>();

                //TreeSet<Integer> FNidList = new TreeSet<>();
                HashSet<Integer> FNidList = new HashSet<>();

                //TreeSet<Integer> NidList = new TreeSet<>();
                HashSet<Integer> NidList = new HashSet<>();

                //TreeSet<String> numberList = new TreeSet<>();
                HashSet<String> numberList = new HashSet<>();

                //TreeSet<String> nameList = new TreeSet<>();
                HashSet<String> nameList = new HashSet<>();

                //TreeSet<Double> amountList = new TreeSet<>();
                HashSet<Double> amountList = new HashSet<>();

                HashSet<String> unitNameList = new HashSet<>();

            /* for(int i = 0; i < foods.length(); i++)
                            {
                                JSONObject food = foods.getJSONObject(i);
                                String nutrient = food.getString("nutrient");
                                taglist.add(nutrient);
                            }*/

                /*JSONObject reader = new JSONObject(jsonData.toString());
                JSONArray nutrition = reader.getJSONArray("foodNutrients");

                for(int i = 0; i < nutrition.length(); i++)
                {
                    JSONObject nutrient = nutrition.getJSONObject(i);
                    JSONArray nutrients = reader.getJSONArray("nutrient");

                    for(int j = 0; j < nutrients.length(); j++) {
                        //JSONObject nutrient = nutritionArray.getJSONObject(i);
                        int id = nutrient.getInt("id");
                        String number = nutrient.getString("number");
                        String name = nutrient.getString("name");


                        nutritionAR.add(new Nutrition(id, number, name));
                        Log.i("Nutrition Object", "" + nutritionAR.get(i).getId());
                        taglist.add(Integer.toString(id));
                        taglist.add(number);
                        taglist.add(name);
                    }

                }

                 */

                /*JSONObject reader = new JSONObject(jsonData.toString());
                JSONArray foodsArray = reader.getJSONArray("foodNutrients");
                for(int i = 0; i < foodsArray.length(); i++)
                {
                    JSONObject food = foodsArray.getJSONObject(i);
                    //JSONArray description = food.getJSONArray("description");
                    //String descript = description.getString(i);


                    String type = food.getString("type");
                    int id = food.getInt("id");
                    JSONObject nutrient = food.getJSONObject("nutrient");


                    //nutritionAR.add(new Food(id, descript, dataType));
                    //Log.i("Food Object", "" + foods.add(new Food(id, descript, dataType)));
                    //Log.i("Nutrition Object", "" + nutritionAR.get(i).getId());
                    taglist.add(type);
                    idlist.add(id);
                    //arraylist.add(nutrient);
                }

                 */

                JSONObject reader = new JSONObject(jsonData.toString());
                JSONArray foodsArray = reader.getJSONArray("foodNutrients");
                for(int i = 0; i < foodsArray.length(); i++)
                {

                    JSONObject food = foodsArray.getJSONObject(i);
                    //JSONArray tags = food.getJSONArray("nutrient");
                    JSONObject tags = food.getJSONObject("nutrient");

                    String type = food.getString("type");
                    int FNid = food.getInt("id");
                    Double amount = food.getDouble("amount");


                    for(int j = 0; j < tags.length(); j++)
                    {

                        int Nid = tags.getInt("id");
                        String number = tags.getString("number");
                        String name = tags.getString("name");
                        String unitName = tags.getString("unitName");

                        numberList.add(number);
                        nameList.add(name);
                        unitNameList.add(unitName);
                    }


                    typesList.add(type);

                    FNidList.add(FNid);

                    NidList.add(FNid);


                    amountList.add(amount);
                    //taglist.add(Integer.toString(id));
                }


                StringBuilder tagBuilder = new StringBuilder();
                for (String type : typesList) {
                    tagBuilder.append("Type: " + type);
                    tagBuilder.append("\n\n");
                }

                /*for (Integer id : FNidList) {
                    tagBuilder.append("Food Nutrient ID: " + id);
                    tagBuilder.append("\n\n");
                }*/

                /*for (Integer id : NidList) {
                    tagBuilder.append("Nutrient ID: " + id);
                    tagBuilder.append("\n\n");
                }*/

                /*for (String number : numberList) {
                    tagBuilder.append("Number: " + number);
                    tagBuilder.append("\n\n");
                }*/

                for (String name : nameList) {
                    for (Double amount : amountList) {
                        for (String unit : unitNameList) {
                            tagBuilder.append("Name: " + name);
                            tagBuilder.append("\nAmount: " + amount + " " + unit);
                            tagBuilder.append("\n\n");
                        }
                    }
                }

                /*for (Double amount : amountList) {
                    tagBuilder.append("Amount: " + amount);
                    tagBuilder.append("\n\n");
                }*/

                /*for (String type : typesList) {
                    tagBuilder.append("Type: " + type);
                    tagBuilder.append("\n\n");
                }

                for (Integer id : FNidList) {
                    tagBuilder.append("Food Nutrient ID: " + id);
                    tagBuilder.append("\n\n");
                }

                for (Integer id : NidList) {
                    tagBuilder.append("Nutrient ID: " + id);
                    tagBuilder.append("\n\n");
                }

                for (String number : numberList) {
                    tagBuilder.append("Number: " + number);
                    tagBuilder.append("\n\n");
                }

                for (String name : nameList) {
                    tagBuilder.append("Name: " + name);
                    tagBuilder.append("\n\n");
                }

                for (Double amount : amountList) {
                    tagBuilder.append("Amount: " + amount);
                    tagBuilder.append("\n\n");
                }*/






                resultData.titleStr = titleBuilder.toString();
                resultData.tagStr = tagBuilder.toString();

                connection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return resultData;
        }

        @Override
        protected void onPostExecute(ResultData resultData) {

            TextView tv = findViewById(R.id.tag_textView);
            tv.setText(resultData.tagStr);

            dataDownload = null;
        }
    }

    private class ResultData {
        String titleStr = "";
        String tagStr = "";
    }
}
