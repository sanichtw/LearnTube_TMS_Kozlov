package com.example.learntube.presentation.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learntube.R
import com.example.learntube.databinding.FragmentPostsScreenBinding
import com.example.learntube.domain.models.SearchItem
import com.example.learntube.presentation.adapters.SearchItemAdapter
import com.example.learntube.presentation.tutorials.Tutorials
import com.example.learntube.presentation.viewmodels.SearchItemsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.math.abs


@AndroidEntryPoint
class PostsScreen : Fragment() {

    private var _binding: FragmentPostsScreenBinding? = null
    private val viewModel: SearchItemsViewModel by viewModels()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostsScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView = binding.textView

        binding.favouriteScreenTextView?.setOnClickListener {
            findNavController().navigate(R.id.action_PostsScreen_to_FavouriteVideoScreen)
        }

        if (!viewModel.searchQueryState.isNullOrBlank()) {
            textView.text = viewModel.searchQueryState
            lifecycleScope.launch {
                viewModel.getPosts(viewModel.searchQueryState)
                observePosts()
            }
        }

        bindDrawer()

        textView.setOnClickListener {
            val dialog = Dialog(requireContext())
            dialog.apply {
                setContentView(R.layout.dialog_searchable_spinner)
                window?.setLayout(800, 800)
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                show()
            }

            val listView = dialog.findViewById<ListView>(R.id.list_view)
            val editText = dialog.findViewById<EditText>(R.id.edit_text)

            val spinnerAdapter = ArrayAdapter(
                requireContext(),
                R.layout.simple_list_item_1,
                Tutorials().getTutorials()
            )

            listView.apply {
                adapter = spinnerAdapter
                onItemClickListener =
                    AdapterView.OnItemClickListener { _, _, position, _ ->
                        textView.text = spinnerAdapter.getItem(position)

                        val searchInputText = textView.text.toString()
                        binding.apply {
                            loader.visibility = View.VISIBLE
                            recyclerView.visibility = View.GONE
                        }

                        if (searchInputText.isNotBlank()) {
                            lifecycleScope.launch {
                                viewModel.apply {
                                    searchQueryState = searchInputText
                                    getPosts(searchQueryState)
                                }
                                observePosts()
                            }
                        }
                        dialog.dismiss()
                    }
            }

            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun afterTextChanged(s: Editable?) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    spinnerAdapter.filter.filter(s)
                }
            })
        }
    }

    private fun observePosts() {
        viewModel.postList.distinctUntilChanged().observe(viewLifecycleOwner) { posts ->
            initRecycler(posts)
        }

        binding.apply {
            loader.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }

    private fun initRecycler(searchItems: List<SearchItem>) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = SearchItemAdapter(
                context = requireContext(),
                searchItems = searchItems,
                viewModel = viewModel
            )
        }
    }

    private fun bindDrawer() {
        val gestureDetector = GestureDetectorCompat(
            requireContext(),
            object : GestureDetector.SimpleOnGestureListener() {
                override fun onFling(
                    e1: MotionEvent,
                    e2: MotionEvent,
                    velocityX: Float,
                    velocityY: Float
                ): Boolean {
                    // Определение направления свайпа
                    val distanceX = e2.x
                    val distanceY = e2.y

                    if (distanceX > 0 && abs(distanceX) > abs(distanceY)) {
                        // Свайп вправо
                        binding.drawerLayout.openDrawer(binding.drawer)
                        return true
                    }

                    return super.onFling(e1, e2, velocityX, velocityY)
                }
            })

        // Прикрепление обработчика жестов к DrawerLayout
        binding.drawerLayout.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
        }
    }
}