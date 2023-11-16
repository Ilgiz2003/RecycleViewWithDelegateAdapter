package com.contactsapp

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.contactsapp.databinding.ContactItemBinding
import com.livermor.delegateadapter.delegate.ViewBindingDelegateAdapter

class ContactDelegateAdapter(private val clickListener: ContactClickListener) :
    ViewBindingDelegateAdapter<Contact, ContactItemBinding>(ContactItemBinding::inflate) {

    override fun ContactItemBinding.onBind(item: Contact) {
        id.text = item.id.toString()
        name.text = item.name
        number.text = item.telephoneNumber
        checkbox.visibility = if (item.isCheckboxVisible) View.VISIBLE else View.INVISIBLE
        checkbox.isChecked = item.isCheckboxChecked

        checkbox.setOnCheckedChangeListener { _, isChecked ->
            item.isCheckboxChecked = isChecked
        }

        root.setOnClickListener {
            clickListener.onContactClicked(item)
        }
    }

    override fun isForViewType(item: Any): Boolean = item is Contact

    override fun Contact.getItemId(): Any = id

}
