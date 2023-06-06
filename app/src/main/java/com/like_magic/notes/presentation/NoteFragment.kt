package com.like_magic.notes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.like_magic.notes.databinding.FragmentNoteBinding
import com.like_magic.notes.domen.entity.NoteEntity
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class NoteFragment : MvpAppCompatFragment(), AppViews.NoteView {

    private var _binding: FragmentNoteBinding? = null
    private val binding: FragmentNoteBinding
        get() = _binding ?: throw RuntimeException("FragmentNoteBinding is null")
    private val presenter by moxyPresenter { NotePresenter(requireActivity().application) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = parseArgs()
        presenter.setScreenMode(args.first, args.second)
        setupAppBar(args.first, args.second)
    }

    override  fun setRightScreenMode(mode: String, note: NoteEntity?) {
        when (mode) {
            MODE_ADD -> {addMode()}
            MODE_EDIT -> {editMode(note)}
            else -> {
                throw RuntimeException("unknown screen mode")
            }
        }
    }

    private fun editMode(note: NoteEntity?) {
        binding.titleTextInput.setText(note?.title)
        binding.descriptionTextInput.setText(note?.description)
        binding.saveNoteBtn.setOnClickListener {
            val newNote = note?.copy(
                title = binding.titleTextInput.text.toString(),
                description = binding.descriptionTextInput.text.toString()
            )
            newNote?.let { note ->
                presenter.insertNote(note)
            }
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun addMode() {
        binding.saveNoteBtn.setOnClickListener {
            val newNote = NoteEntity(
                title = binding.titleTextInput.text.toString(),
                description = binding.descriptionTextInput.text.toString(),
                location = "Minsk",
                time = "14:00",
                id = 0
            )
            presenter.insertNote(newNote)
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun setupAppBar(mode: String, note: NoteEntity?) {
        when (mode) {
            MODE_ADD -> {
                binding.topAppBar.title = "Add new note"
            }
            MODE_EDIT -> {
                binding.topAppBar.title = "Edit note id: ${note?.id.toString()}"
            }
        }
        binding.topAppBar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs(): Pair<String, NoteEntity?> {
        val mode = arguments?.getString(SCREEN_MODE) ?: "args is null"
        val note = arguments?.getParcelable<NoteEntity>(NOTE)
        return Pair(mode, note)
    }

    companion object {
        private const val SCREEN_MODE = "screen_mode"
        const val MODE_EDIT = "mode_edit"
        const val MODE_ADD = "mode_add"
        private const val NOTE = "note"

        fun newInstance(mode: String, noteEntity: NoteEntity? = null) = NoteFragment().apply {
            arguments = Bundle().apply {
                putString(SCREEN_MODE, mode)
                putParcelable(NOTE, noteEntity)
            }
        }
    }

}