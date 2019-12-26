package com.example.volleygooglenews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    var list:ArrayList<NewsDataModel>? = null
    var rv:RecyclerView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list = arrayListOf()
        rv = findViewById(R.id.rv1)

        var queue = Volley.newRequestQueue(this)
        queue.add(jsonObjectRequest())

    }
    fun jsonObjectRequest():JsonObjectRequest{

        var url = "https://newsapi.org/v2/top-headlines?sources=google-news&apiKey=4540a65ef9a0412585c20da428903cce"

        return JsonObjectRequest(Request.Method.GET,url,null,
              Response.Listener {

                  var jsonarray = it.getJSONArray( "articles")

                  for( i in 0 until jsonarray.length()){
                      var jsonobject = jsonarray.getJSONObject(i)
                      var title = jsonobject.getString("title")
                      var description = jsonobject.getString("description")
                      var urlToImage = jsonobject.getString("urlToImage")
                      var publishedAt= jsonobject.getString("publishedAt")

                      var article = Article(title,description,urlToImage,publishedAt)

                      var newsdatamodel = NewsDataModel(arrayListOf(article))
                      list?.add(newsdatamodel)

                      rv!!.setHasFixedSize(true)
                      rv!!.layoutManager = LinearLayoutManager(this)
                      rv!!.adapter = ExampleAdaptor(this,list!!)

                  }
              },
              Response.ErrorListener {
                  it.printStackTrace()
              })


    }
}
