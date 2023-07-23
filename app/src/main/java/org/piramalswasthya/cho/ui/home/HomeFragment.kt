package org.piramalswasthya.cho.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.piramalswasthya.cho.R
import org.piramalswasthya.cho.databinding.FragmentHomeBinding
import org.piramalswasthya.cho.databinding.FragmentRegisterPatientBinding
import org.piramalswasthya.cho.ui.commons.personal_details.PersonalDetailsFragment
import org.piramalswasthya.cho.ui.edit_patient_details_activity.EditPatientDetailsActivity
import org.piramalswasthya.cho.ui.login_activity.username.UsernameFragmentDirections
import org.piramalswasthya.cho.ui.register_patient_activity.RegisterPatientActivity
import timber.log.Timber

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentVisitDetails = PersonalDetailsFragment()
        childFragmentManager.beginTransaction().replace(binding.patientListFragment.id, fragmentVisitDetails).commit()

        binding.registration.setOnClickListener {
            val intent = Intent(context, RegisterPatientActivity::class.java)
            startActivity(intent)
//            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToRegisterPatientFragment())
        }
        binding.advanceSearch.setOnClickListener {

        }

//        binding.loginSettings.setOnClickListener{
//            try {
//                findNavController().navigate(
//                    UsernameFragmentDirections.actionUsernameFragmentToLoginSettings(binding.etUsername.text.toString()),
//                )
//            }catch (e: Exception){
//                Timber.d("Failed to navigate"+e.message)
//            }
//
//        }
    }
}