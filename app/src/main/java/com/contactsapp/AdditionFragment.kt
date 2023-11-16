package com.contactsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.contactsapp.databinding.FragmentAdditionBinding
import com.contactsapp.databinding.FragmentContactsBinding

class AdditionFragment : Fragment() {
    private var _binding: FragmentAdditionBinding? = null
    private val binding: FragmentAdditionBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdditionBinding.inflate(inflater, container, false)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_additionFragment_to_contactsFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            backBtn.setOnClickListener {
                findNavController().navigate(R.id.action_additionFragment_to_contactsFragment)
            }

            addBtn.setOnClickListener {
                if (newName.text != null && newNumber.text != null) {
                    val contact = Contact(
                        id = ContactsDataFactory.getSize() + 1,
                        name = newName.text.toString(),
                        telephoneNumber = newNumber.text.toString()
                    )
                    ContactsDataFactory.addContact(contact)
                    findNavController().navigate(R.id.action_additionFragment_to_contactsFragment)
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}