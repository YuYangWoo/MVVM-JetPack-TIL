package com.example.hlit_ex.ui.view.dialog

import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.hlit_ex.R
import com.example.hlit_ex.data.model.response.LeagueResponse
import com.example.hlit_ex.data.model.response.SummonerResponse
import com.example.hlit_ex.databinding.DialogSummonerInputBinding
import com.example.hlit_ex.ui.view.fragment.MainFragment
import com.example.hlit_ex.ui.viewmodel.MainViewModel
import com.example.hlit_ex.util.Resource
import com.example.library.binding.BindingDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SummonerInputDialog :
    BindingDialogFragment<DialogSummonerInputBinding>(R.layout.dialog_summoner_input) {
    private val mainViewModel by activityViewModels<MainViewModel>()
    private var summonerResponse: SummonerResponse? = null
    private val progressDialog: ProgressDialog by lazy { ProgressDialog(requireContext()) }

    override fun init() {
        super.init()
        confirmBtn()
//        observerSummonerData()
    }

//    private fun observerSummonerData() {
//        mainViewModel.summonerResponse.observe(viewLifecycleOwner, Observer { resource ->
//            when (resource.status) {
//                Resource.Status.SUCCESS -> {
//                    progressDialog.dismiss()
//                    summonerResponse = resource.data?.body() ?: return@Observer
//                    mainViewModel.requestLeagueInfo(summonerResponse!!.id)
//                    Log.d(TAG, "observerSummonerData: ${summonerResponse}")
//                    dismiss()
//                }
//                Resource.Status.LOADING -> {
//                    progressDialog.show()
//                }
//                Resource.Status.ERROR -> {
//                    Log.d(TAG, "init: 실패${resource.message}")
//                }
//            }
//        })
//    }

    override fun onResume() {
        super.onResume()
        requireContext().dialogFragmentResize(this@SummonerInputDialog, 0.8f, 0.25f)
    }

    private fun confirmBtn() {
        binding.setOnOkClick {
            mainViewModel.requestSummonerInfo(binding.nameInputLayout.editText?.text.toString())
            dismiss()
        }
    }


    companion object {
        private const val TAG = "SummonerInputDialog"
    }
}