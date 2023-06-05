package com.like_magic.notes.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.like_magic.notes.databinding.FragmentListNotesBinding

class ListNotesFragment : Fragment() {

    private val _binding:FragmentListNotesBinding? = null
    private val binding:FragmentListNotesBinding
        get() = _binding ?: throw RuntimeException("FragmentListNotesBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFab()
    }

    private fun setupFab(){
        binding.addNoteFab.setOnClickListener {

        }
    }

    companion object {
        fun newInstance() = ListNotesFragment()
    }
}