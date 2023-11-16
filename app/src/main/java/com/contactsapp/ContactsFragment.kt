package com.contactsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.contactsapp.databinding.FragmentContactsBinding
import com.livermor.delegateadapter.delegate.CompositeDelegateAdapter


class ContactsFragment : Fragment(), ContactClickListener {
    private var _binding: FragmentContactsBinding? = null
    private val binding: FragmentContactsBinding
        get() = _binding!!
    private val adapter = ModifyCompositeDelegateAdapter(
        ContactDelegateAdapter(this)
    )
    private val itemTouchHelper = ItemTouchHelper(ContactItemTouchHelperCallback(adapter))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dividerItemDecoration =
            DividerItemDecoration(binding.recyclerView.context, DividerItemDecoration.VERTICAL)
        with(binding) {
            itemTouchHelper.attachToRecyclerView(recyclerView)
            recyclerView.addItemDecoration(dividerItemDecoration)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
            adapter.swapData(ContactsDataFactory.getData())
            addBtn.setOnClickListener {
                findNavController().navigate(R.id.action_contactsFragment_to_additionFragment)
            }
            deleteBtn.setOnClickListener {
                adapter.swapData(ContactsDataFactory.updateCheckboxVisibilityContact(true))
                addBtn.visibility = View.INVISIBLE
                cancelBtn.visibility = View.VISIBLE
                confirmBtn.visibility = View.VISIBLE
                deleteBtn.visibility = View.INVISIBLE
            }
            cancelBtn.setOnClickListener {
                adapter.swapData(ContactsDataFactory.updateCheckboxVisibilityContact(false))
                addBtn.visibility = View.VISIBLE
                cancelBtn.visibility = View.INVISIBLE
                confirmBtn.visibility = View.INVISIBLE
                deleteBtn.visibility = View.VISIBLE
            }
            confirmBtn.setOnClickListener {
                adapter.swapData(ContactsDataFactory.deleteContacts())
                adapter.swapData(ContactsDataFactory.updateCheckboxVisibilityContact(false))
                addBtn.visibility = View.VISIBLE
                cancelBtn.visibility = View.INVISIBLE
                confirmBtn.visibility = View.INVISIBLE
                deleteBtn.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onContactClicked(contact: Contact) {
        val bundle = Bundle()
        bundle.putInt("id", contact.id)
        bundle.putString("name", contact.name)
        bundle.putString("number", contact.telephoneNumber)
        adapter.swapData(ContactsDataFactory.updateCheckboxVisibilityContact(false))
        findNavController().navigate(R.id.action_contactsFragment_to_editingFragment, bundle)
    }

}