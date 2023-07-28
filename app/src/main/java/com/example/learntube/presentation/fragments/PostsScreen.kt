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
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learntube.R
import com.example.learntube.databinding.FragmentPostsScreenBinding
import com.example.learntube.domain.models.SearchItem
import com.example.learntube.presentation.adapters.SearchItemAdapter
import com.example.learntube.presentation.tutorials.Tutorials
import com.example.learntube.presentation.viewmodels.SearchItemsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.abs


@AndroidEntryPoint
class PostsScreen : Fragment() {
    companion object {
        const val RELEVANCE = "Relevance"
        const val UPLOAD_DATE = "Upload Date"
        const val NAME = "Name"
    }

    private lateinit var binding: FragmentPostsScreenBinding
    private val viewModel: SearchItemsViewModel by viewModels()
    private val searchSpinnerAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter(requireContext(), R.layout.simple_list_item_1, Tutorials().getTutorials())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostsScreenBinding.inflate(inflater, container, false)

        setupSortingView()
        bindViews()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!viewModel.searchQueryState.isNullOrBlank()) {
            lifecycleScope.launch {
                viewModel.getPosts(viewModel.searchQueryState)
                binding.coursesInput.text = viewModel.searchQueryState
                observePosts()
            }
        }
    }

    private fun setupSortingView() {
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.simple_list_item_1,
            listOf(RELEVANCE, UPLOAD_DATE, NAME)
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.sortBySpinner.adapter = adapter

        binding.sortBySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedSortBy = when (position) {
                    1 -> UPLOAD_DATE
                    2 -> NAME
                    else -> RELEVANCE
                }
                lifecycleScope.launch {
                    viewModel.filterSearchItems(selectedSortBy)
                }
                viewModel.filterState = selectedSortBy
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }


    private fun bindViews() {
        with(binding) {
            favouriteScreenTextView.setOnClickListener {
                navigateToPostsFragment()
            }

            coursesInput.setOnClickListener {
                showSearchDialog()
            }

            setupDrawerGesture()
        }
    }

    private fun observePosts() {
        lifecycleScope.launch {
            viewModel.postList.collect { posts ->
                initRecycler(posts)
                updateProgressState(isVisible = false)
            }
        }
    }

    private fun initRecycler(searchItems: List<SearchItem>) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = SearchItemAdapter(
                context = requireContext(),
                searchItems = searchItems,
                onCheckedChanged = { favouriteVideo ->
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.setVideoAsFavorite(favouriteVideo)
                    }
                }
            )
        }
    }

    private fun showSearchDialog() {
        val dialog = Dialog(requireContext())
        dialog.apply {
            setContentView(R.layout.dialog_searchable_spinner)
            window?.setLayout(800, 800)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            show()
        }

        val listView = dialog.findViewById<ListView>(R.id.list_view)
        val editText = dialog.findViewById<EditText>(R.id.edit_text)


        listView.apply {
            adapter = searchSpinnerAdapter
            onItemClickListener =
                AdapterView.OnItemClickListener { _, _, position, _ ->
                    val textView = binding.coursesInput
                    textView.text = searchSpinnerAdapter.getItem(position)

                    val searchInputText = textView.text.toString()
                    updateProgressState(isVisible = true)

                    if (searchInputText.isNotBlank()) {
                        lifecycleScope.launch {
                            viewModel.apply {
                                searchQueryState = searchInputText
                                getPosts(searchQueryState)
                                binding.sortByContainer.visibility = View.VISIBLE
                            }
                            observePosts()
                        }
                    }
                    dialog.dismiss()
                }
        }

        editText.addTextChangedListener(createTextWatcher())
    }

    private fun setupDrawerGesture() {
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

    private fun navigateToPostsFragment() {
        val navOptions = NavOptions.Builder()
            .setEnterAnim(R.anim.slide_up)
            .setExitAnim(R.anim.slide_down)
            .build()

        findNavController().navigate(
            R.id.action_PostsScreen_to_FavouriteVideoScreen,
            null,
            navOptions
        )
    }

    private fun createTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                searchSpinnerAdapter.filter.filter(s)
            }
        }
    }

    private fun updateProgressState(isVisible: Boolean) {
        binding.loader.isVisible = isVisible
        binding.recyclerView.isVisible = !isVisible
    }
}
