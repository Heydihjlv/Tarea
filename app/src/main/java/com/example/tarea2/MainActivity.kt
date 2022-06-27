package com.example.tarea2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CosultaV()

    }

    fun CosultaV() {
        val textView = findViewById<TextView>(R.id.textId)
        val queue = Volley.newRequestQueue(this)
        val url = "https://gorest.co.in/public/v1/users"


        val JsonObjectR = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                var cad:String=""
                var id:String
                var name:String
                var email:String
                var gender:String
                var status:String


                val array : JSONArray = response.getJSONArray("data")
                for (i in 0 until array.length())
                {
                    val JObject : JSONObject = array.getJSONObject(i)
                    id = JObject.getString("id")
                    name= JObject.getString("name")
                    email = JObject.getString("email")
                    gender = JObject.getString("gender")
                    status = JObject.getString("status")

                    cad= "$cad{ " +
                                "\n id: $id, " +
                                "\n name: $name, " +
                                "\n email: $email, " +
                                "\n gender: $gender, " +
                                "\n status: $status \n " +
                                "} \n"
                }
                //datos en el TextView
                 textView.text=cad
            },
            { error -> println(error.message) } )
        queue.add(JsonObjectR);

        // Request a string response from the provided URL.

/*
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                textView.text = "Response is: ${response.toString()}"
            },
            Response.ErrorListener { textView.text = "That didn't work!" }
      */







    }


}