package idv.fanboat.kottoy.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import idv.fanboat.kottoy.R
import idv.fanboat.kottoy.model.Kotto
import io.reactivex.functions.Consumer

class KottoAdapter(kottos: List<Kotto>, listener: Consumer<Kotto>) :
    RecyclerView.Adapter<KottoAdapter.KottoViewHolder>() {
    var mKottos = kottos
    var mListener = listener

    fun setKottos(kottos: List<Kotto>) {
        mKottos = kottos
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KottoViewHolder {
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.adapter_kotto, parent, false)
        return KottoViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: KottoViewHolder, position: Int) {
        val kotto = mKottos[position]
        holder.tvName.text = kotto.name
        holder.itemView.setOnClickListener { mListener.accept(kotto) }
    }

    override fun getItemCount(): Int {
        return mKottos.size
    }

    class KottoViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val tvName = itemview.findViewById<TextView>(R.id.tv_kotto_name)
    }

}