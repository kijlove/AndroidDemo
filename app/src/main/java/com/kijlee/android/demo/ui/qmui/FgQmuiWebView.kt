package com.kijlee.android.demo.ui.qmui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kijlee.android.demo.databinding.LayoutQmuiWebviewBinding
import com.kijlee.android.demo.net.Api
import com.orhanobut.logger.Logger
import com.qmuiteam.qmui.widget.webview.QMUIWebView
import me.jessyan.retrofiturlmanager.RetrofitUrlManager

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.qmui
 * @ClassName:      QmuiWebView
 * @Author:     kij
 * @Description:  qmui的webview
 * @Date:    2022/3/13 2:06 下午
 * @Version:    1.0
 */
class FgQmuiWebView : Fragment() {


    var _layoutBind: LayoutQmuiWebviewBinding? = null
    var item = ""
     var mWebView: QMUIWebView? = null

    private val binding get() = _layoutBind!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {val bundle = FgQmuiWebViewArgs.fromBundle(requireArguments())
            Logger.e(bundle.url)
            qmuiUrl = bundle.url
        }
    }
var qmuiUrl = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _layoutBind = LayoutQmuiWebviewBinding.inflate(layoutInflater)

        val root: View = binding.root

        val httpUrl = RetrofitUrlManager.getInstance().fetchDomain(Api.HEALTH_NAME)
        Logger.e(httpUrl.toString())

        if (httpUrl == null || httpUrl.toString() != Api.HEALTH_URL_DOMAIN
                .toString()
        ) { //可以在 App 运行时随意切换某个接口的 BaseUrl
            RetrofitUrlManager.getInstance().putDomain(Api.HEALTH_NAME, Api.HEALTH_URL_DOMAIN)
        }
        mWebView = QMUIWebView(context)
        binding.qmuiWebView.addWebView(mWebView!!, false)

        mWebView!!.loadUrl("https://www.cn-healthcare.com$qmuiUrl")

        return root
    }

}