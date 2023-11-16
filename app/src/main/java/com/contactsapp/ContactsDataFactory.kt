package com.contactsapp

import com.github.javafaker.Faker
import java.util.*
import kotlin.collections.ArrayList

object ContactsDataFactory {

    private var contacts: List<Contact>? = null

    private fun fillData() {
        val faker = Faker()
        val contactList = ArrayList<Contact>(100)
        for (i in 1..100) {
            val contact = Contact(
                id = i,
                name = faker.name().fullName(),
                telephoneNumber = faker.phoneNumber().phoneNumber()
            )
            contactList.add(contact)
        }
        contacts = contactList
    }

    fun getSize(): Int {
        return contacts?.size ?: 0
    }

    fun addContact(contact: Contact) {
        val contactList: ArrayList<Contact> = contacts as ArrayList<Contact>
        contactList.add(contact)
        contacts = contactList
    }

    fun replaceContact(contact: Contact) {
        val contactList: ArrayList<Contact> = contacts as ArrayList<Contact>
        contactList[contact.id - 1].name = contact.name
        contactList[contact.id - 1].telephoneNumber = contact.telephoneNumber
        contacts = contactList
    }

    fun updateCheckboxVisibilityContact(isVisible: Boolean): List<Contact> {
        val contactList: ArrayList<Contact> = contacts as ArrayList<Contact>
        val updatedContactList = contactList.map { contact ->
            Contact(contact.id, contact.name, contact.telephoneNumber, isVisible)
        }
        contacts = updatedContactList
        return getData()
    }

    fun deleteContacts(): List<Contact> {
        val contactList: ArrayList<Contact> = contacts as ArrayList<Contact>
        val updatedContactList = contactList.filter { !it.isCheckboxChecked }
        for (i in updatedContactList.indices) {
            updatedContactList[i].id = i + 1
        }
        contacts = updatedContactList
        return getData()
    }

    fun swapContacts(fromPosition: Int, toPosition: Int): List<Contact> {
        val contactList: ArrayList<Contact> = contacts as ArrayList<Contact>
        Collections.swap(contactList, fromPosition, toPosition)
        val tempId = contactList[fromPosition].id
        contactList[fromPosition].id = contactList[toPosition].id
        contactList[toPosition].id = tempId
        contacts = contactList
        return getData()
    }

    fun getData(): List<Contact> {
        if (contacts == null) {
            fillData()
        }
        return requireNotNull(contacts)
    }
}