package com.like_magic.notes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.like_magic.notes.R
import com.like_magic.notes.databinding.FragmentListNotesBinding
import com.like_magic.notes.domen.entity.NoteEntity
import com.like_magic.notes.presentation.NoteFragment.Companion.MODE_ADD
import com.like_magic.notes.presentation.NoteFragment.Companion.MODE_EDIT
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ListNotesFragment : MvpAppCompatFragment(), ListNotesView {

    private var _binding:FragmentListNotesBinding? = null
    private val binding:FragmentListNotesBinding
        get() = _binding ?: throw RuntimeException("FragmentListNotesBinding is null")
    private val presenter by moxyPresenter {
        MainPresenter(requireActivity().application)
    }
    private val notesAdapter = ListNotesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.notesRv.adapter = notesAdapter
        presenter.getListNotes()
        setupFab()
        setupItemListener()
    }

    override fun showListNotes(list: List<NoteEntity>) {
        notesAdapter.submitList(list)
    }

    private fun setupFab(){
        binding.addNoteFab.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .add(R.id.main_container, NoteFragment.newInstance(MODE_ADD))
                .commit()
        }
    }

    private fun setupItemListener(){
        notesAdapter.onNoteClickListener = {note->
            requireActivity().supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .add(R.id.main_container, NoteFragment.newInstance(MODE_EDIT, note))
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}