package com.vladan.newsapp.screens.article

import android.annotation.SuppressLint
import android.net.http.SslError
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.fragment.app.Fragment
import com.vladan.newsapp.databinding.FragmentArticleBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ArticleFragment : Fragment() {
    companion object{
        private val TAG: String = "ArticleFragment"
    }

    private lateinit var binding: FragmentArticleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var url = ""
        if (arguments != null) {
            url = requireArguments().getString("url", "")
        }
        val webViewClient = object : WebViewClient() {

            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                super.onReceivedSslError(view, handler, error)
                Log.d(TAG, error.toString())
            }

            override fun onReceivedHttpError(
                view: WebView?,
                request: WebResourceRequest?,
                errorResponse: WebResourceResponse?
            ) {
                super.onReceivedHttpError(view, request, errorResponse)
                Log.d(TAG, errorResponse?.statusCode.toString())
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                Log.d(TAG, "Loading error")
            }
        }
        binding.webview.webViewClient = webViewClient
        binding.webview.settings.loadsImagesAutomatically = true
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        binding.webview.clearHistory()
        binding.webview.loadUrl(url)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }
}