package com.like_magic.notes.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.like_magic.notes.data.NotesRepositoryImpl
import com.like_magic.notes.databinding.FragmentListNotesBinding
import com.like_magic.notes.domen.entity.NoteEntity
class ListNotesFragment : Fragment(), NotesContract.ListNotesView {

    private var _binding:FragmentListNotesBinding? = null
    private val binding:FragmentListNotesBinding
        get() = _binding ?: throw RuntimeException("FragmentListNotesBinding is null")
    private val notesAdapter = ListNotesAdapter()
    private val presenter by lazy {
        MainPresenter(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.getListNotes()
        setupFab()
        binding.notesRv.adapter = notesAdapter
    }

    override fun showListNotes(list: List<NoteEntity>) {
        notesAdapter.submitList(list)
    }

    private fun setupFab(){
        binding.addNoteFab.setOnClickListener {
            val repository = NotesRepositoryImpl(requireActivity().application)
            repository.addNote(
                NoteEntity(
                    id = 0,
                    title = "Title",
                    description = "Description",
                    time = "14:00",
                    location = "Minsk"
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        presenter.detach()
    }



    companion object {
        fun newInstance() = ListNotesFragment()
    }


}