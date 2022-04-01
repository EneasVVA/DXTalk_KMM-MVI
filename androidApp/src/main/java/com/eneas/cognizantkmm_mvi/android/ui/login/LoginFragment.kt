package com.eneas.cognizantkmm_mvi.android.ui.login

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.eneas.cognizantkmm_mvi.android.MainActivity
import com.eneas.cognizantkmm_mvi.android.databinding.FragmentLoginBinding
import com.eneas.cognizantkmm_mvi.ui.login.LoginViewModel
import com.eneas.cognizantkmm_mvi.ui.login.LoginViewState
import java.lang.RuntimeException
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var mActivity: MainActivity
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenResumed {
            viewModel.viewState
                .onEach(::processViewState)
                .launchIn(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as? MainActivity).let {
            mActivity = it ?: throw RuntimeException()
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        viewModel.attach()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.detach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.emailInput.doOnTextChanged { text, _, _, _ ->
            viewModel.emailChanged(text?.toString().orEmpty())
        }

        binding.passwordInput.doOnTextChanged { text, _, _, _ ->
            viewModel.passwordChanged(text?.toString().orEmpty())
        }

        binding.signInButton.setOnClickListener {
            viewModel.signInButtonClicked()
        }
    }

    private fun processViewState(viewState: LoginViewState) {
        binding.progressBar.visibility = if (viewState.showProgressBar) {
            View.VISIBLE
        } else {
            View.GONE
        }

        binding.emailInputLayout.error = viewState.emailError

        if(viewState.completed) {
            mActivity.navigateToProfile()
        }
    }
}