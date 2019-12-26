package com.example.volleygooglenews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ExampleAdaptor(var c:Context,var list:ArrayList<NewsDataModel>):RecyclerView.Adapter<ExampleAdaptor.ExampleviewHolder>() {

    class ExampleviewHolder : RecyclerView.ViewHolder {
        var iv:ImageView?=null
        var tv1:TextView?=null
        var tv2:TextView?=null
        constructor(v:View) :super(v){
            iv = v.findViewById(R.id.iv1)
            tv1 = v.findViewById(R.id.tv1)
            tv2 = v.findViewById(R.id.tv2)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ExampleviewHolder{
        var inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.card_view,parent,false)
        return ExampleviewHolder(v)
    }

    override fun onBindViewHolder(holder: ExampleviewHolder, position: Int) {
        list.get(position).articles.forEach {
            holder.run {
                Picasso.with(c).load(it.urlToImage).fit().into(iv)
                tv1?.text = it.title
                tv2?.text = it.description
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}