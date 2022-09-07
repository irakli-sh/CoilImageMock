package com.metro.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import coil.load
import com.metro.myapplication.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        /*Glide.with(this)
            .load("https://metro.co.uk/wp-content/uploads/2021/10/GettyImages-1230120746.jpg?quality=90&strip=all&crop=0px%2C0px%2C1024px%2C768px&resize=480%2C360&zoom=1")
            .into(binding.imageOne)

        Glide.with(this)
            .load("https://metro.co.uk/wp-content/uploads/2022/09/comp-1662362676.png?crop=2px%2C0px%2C1248px%2C703px&resize=650%2C366&quality=90&strip=all&zoom=1")
            .into(binding.imageTwo)*/
        binding.imageOne.load("https://metro.co.uk/wp-content/uploads/2021/10/GettyImages-1230120746.jpg?quality=90&strip=all&crop=0px%2C0px%2C1024px%2C768px&resize=480%2C360&zoom=1")
        binding.imageTwo.load("https://metro.co.uk/wp-content/uploads/2022/09/comp-1662362676.png?crop=2px%2C0px%2C1248px%2C703px&resize=650%2C366&quality=90&strip=all&zoom=1")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}