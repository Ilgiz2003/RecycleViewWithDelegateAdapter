package com.contactsapp

import com.livermor.delegateadapter.delegate.CompositeDelegateAdapter
import com.livermor.delegateadapter.delegate.DelegateAdapter

class ModifyCompositeDelegateAdapter(vararg adapters: DelegateAdapter) :
    CompositeDelegateAdapter(*adapters) {

    fun onItemMove(fromPosition: Int, toPosition: Int): List<Contact> {
        return ContactsDataFactory.swapContacts(fromPosition, toPosition)
    }
}