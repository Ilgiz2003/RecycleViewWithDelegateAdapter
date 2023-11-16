package com.contactsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.contactsapp.databinding.FragmentAdditionBinding
import com.contactsapp.databinding.FragmentEditingBinding

class EditingFragment : Fragment() {
    private var _binding: FragmentEditingBinding? = null
    private val binding: FragmentEditingBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditingBinding.inflate(inflater, container, false)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_editingFragment_to_contactsFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            val id = bundle.getInt("id")
            val name = bundle.getString("name")
            val number = bundle.getString("number")
            with(binding) {
                newName.setText(name)
                newNumber.setText(number)
                addBtn.setOnClickListener {
                    if (newName.text != null && newNumber.text != null) {
                        ContactsDataFactory.replaceContact(
                            Contact(
                                id,
                                newName.text.toString(),
                                newNumber.text.toString()
                            )
                        )
                        findNavController().navigate(R.id.action_editingFragment_to_contactsFragment)
                    }
                }
                backBtn.setOnClickListener {
                    findNavController().navigate(R.id.action_editingFragment_to_contactsFragment)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}