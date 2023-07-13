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
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learntube.R
import com.example.learntube.databinding.FragmentPostsScreenBinding
import com.example.learntube.domain.models.SearchItem
import com.example.learntube.presentation.adapters.SearchItemAdapter
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

        if (!viewModel.searchQueryState.isNullOrBlank()) {
            textView.text = viewModel.searchQueryState
            lifecycleScope.launch {
                viewModel.getPosts(viewModel.searchQueryState)
                observePosts()
            }
        }

        bindDrawer()

        val listOfCourses = mutableListOf<String>()
        listOfCourses.add("Java Courses");
        listOfCourses.add("Python Courses");
        listOfCourses.add("Kotlin Courses");
        listOfCourses.add("Room");
        listOfCourses.add("Git & Github");
        listOfCourses.add("Hilt");
        listOfCourses.add("Dagger");
        listOfCourses.add("Android Development");


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

            val adapter = ArrayAdapter(requireContext(), R.layout.simple_list_item_1, listOfCourses)
            listView.adapter = adapter

            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    adapter.filter.filter(s)
                }

                override fun afterTextChanged(s: Editable?) {}
            })

            listView.onItemClickListener =
                AdapterView.OnItemClickListener { parentFragment, view, position, id ->
                    textView.text = adapter.getItem(position)

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
    }

    private fun observePosts() {
        viewModel.postList.observe(viewLifecycleOwner) { posts ->
            initRecycler(posts)
        }

        binding.apply {
            loader.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }

    private fun initRecycler(posts: List<SearchItem>) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = SearchItemAdapter(
                context = this@PostsScreen,
                items = posts,
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

    companion object {
        private const val SEARCH_KEY = "searchQuery"
    }
}