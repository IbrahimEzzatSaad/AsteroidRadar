package com.udacity.asteroidradar.ui.main

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import com.udacity.asteroidradar.domain.Factory
import com.udacity.asteroidradar.domain.MainViewModel
import com.udacity.asteroidradar.ui.adapter.RecyclerViewAdapter
import com.udacity.asteroidradar.utils.AsteroidsByDate
import com.udacity.asteroidradar.utils.RequestState

class MainFragment : Fragment() {

    /**
     * One way to delay creation of the viewModel until an appropriate lifecycle method is to use
     * lazy. This requires that viewModel not be referenced before onViewCreated(), which we
     * do in this Fragment.
     */
    private val viewModel: MainViewModel by lazy {
        val activity = requireNotNull(activity?.application)
        //The ViewModelProviders (plural) is deprecated.
        //ViewModelProviders.of(this, DevByteViewModel.Factory(activity.application)).get(DevByteViewModel::class.java)
        ViewModelProvider(this, Factory(activity))[MainViewModel::class.java]
    }

    private lateinit var binding : FragmentMainBinding
    private lateinit var  adapter: RecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        adapter = RecyclerViewAdapter(RecyclerViewAdapter.OnClickListener {
            findNavController().navigate(MainFragmentDirections.actionShowDetail(it))
        })

        binding.asteroidRecycler.adapter = adapter


        observersSetup()

        setHasOptionsMenu(true)

        return binding.root
    }


    private fun observersSetup(){

        viewModel.list.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }

        viewModel.state.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it == RequestState.LOADING

        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.show_all_menu -> viewModel.getAsteroidsByDate(AsteroidsByDate.ALL)
            R.id.show_week_menu -> viewModel.getAsteroidsByDate(AsteroidsByDate.WEEK)
            R.id.show_today_menu -> viewModel.getAsteroidsByDate(AsteroidsByDate.DAY)
        }
        return true
    }


}
