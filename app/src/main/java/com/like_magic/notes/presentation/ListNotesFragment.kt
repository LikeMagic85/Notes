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

class ListNotesFragment : MvpAppCompatFragment(), AppViews.ListNotesView {

    private var _binding:FragmentListNotesBinding? = null
    private val binding:FragmentListNotesBinding
        get() = _binding ?: throw RuntimeException("FragmentListNotesBinding is null")
    private val presenter by moxyPresenter {
        ListNotesPresenter(requireActivity().application)
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
            launchNoteFragment(MODE_ADD)
        }
    }

    private fun setupItemListener(){
        notesAdapter.onNoteClickListener = {note->
            launchNoteFragment(MODE_EDIT, note)
        }
    }

    private fun launchNoteFragment(mode:String, note:NoteEntity? = null){
        requireActivity().supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(R.id.main_container, NoteFragment.newInstance(mode, note))
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}