package com.azizbek.testproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azizbek.testproject.databinding.ItemAttributeBinding
import com.azizbek.testproject.databinding.ItemPhoneBinding
import com.azizbek.testproject.hide
import com.azizbek.testproject.loadImageFromUrl
import com.azizbek.testproject.models.PhoneOffer
import com.azizbek.testproject.show

class OffersRvAdapter(private val phones: List<PhoneOffer>) : RecyclerView.Adapter<OffersRvAdapter.PhoneViewHolder>() {

    inner class PhoneViewHolder(private val binding: ItemPhoneBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(offer: PhoneOffer) {
            with(binding) {
                offer.imageUrl?.let { imageUrl ->
                    phoneImage.loadImageFromUrl(imageUrl)
                }
                phoneName.text = offer.name
                brand.text = offer.brand
                category.text = offer.category
                merchant.text = offer.merchant
                if (offer.attributes.isNotEmpty()) {
                    attributesGroup.show()
                    offer.attributes.forEach {
                        val attributeView = ItemAttributeBinding.inflate(LayoutInflater.from(binding.root.context), attributesGroup, false)
                        attributeView.nameTxt.text = "${it.first}:"
                        attributeView.valueTxt.text = it.second
                        attributesGroup.addView(attributeView.root)
                    }
                } else {
                    attributesGroup.hide()
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        return PhoneViewHolder(
            ItemPhoneBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        holder.onBind(phones[position])
    }

    override fun getItemCount() = phones.size


}