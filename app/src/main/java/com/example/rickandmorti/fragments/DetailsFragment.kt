package com.example.rickandmorti.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dotamvvm.R
import com.example.dotamvvm.databinding.FragmentDetailsBinding
import com.example.dotamvvm.databinding.FragmentHeroesBinding
import com.example.rickandmorti.models.Characters
import com.squareup.picasso.Picasso

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class DetailsFragment : Fragment() {

    lateinit var binding: FragmentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val characters = arguments?.getParcelable<Characters>("hero")

        Picasso.get().load(characters?.image).into(binding.imageView)
        binding.tvName.text = characters?.name
        binding.tvSpecies.text = characters?.species
        binding.tvGender.text = characters?.gender
        binding.tvStatus.text = characters?.status
        binding.tvLocation.text = characters?.location?.name

        return binding.root
    }
}