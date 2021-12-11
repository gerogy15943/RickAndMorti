package com.example.rickandmorti.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorti.adapters.CharacterAdapter
import com.example.dotamvvm.databinding.FragmentHeroesBinding
import com.example.rickandmorti.viewmodels.MainViewModel
import kotlinx.coroutines.flow.collectLatest

class HeroesFragment : Fragment() {

    lateinit var binding: FragmentHeroesBinding
    private val  viewModel by viewModels<MainViewModel>()
    private var adapter: CharacterAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeroesBinding.inflate(inflater, container, false)
        adapter = CharacterAdapter()
        binding.rvList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvList.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.getHeroes().collectLatest {
                adapter?.submitData(it)
            }
        }
        return binding.root
    }

}
