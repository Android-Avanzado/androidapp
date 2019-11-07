package com.androidavanzado.createfragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.androidavanzado.createfragment.databinding.FragmentHelloBinding

/**
 * A simple [Fragment] subclass.
 */
class HelloFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentHelloBinding>(
            inflater,
            R.layout.fragment_hello,
            container,
            false
        )

        return binding.root
    }


}
