package com.example.pecodetask.features.pageContent

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pecodetask.R
import com.example.pecodetask.databinding.FragmentPageContentBinding
import com.example.pecodetask.features.pageContainer.model.PagerItem
import java.util.*

class PageContentFragment : Fragment() {

    private val viewModel: PageContentViewModel by viewModels(
        factoryProducer = { PageContentViewModelFactory(getArgumentsPageNumber()) }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPageContentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.notificationClick.observe(viewLifecycleOwner) {
            it?.let { showNotification(it) }
        }

        return binding.root
    }

    private fun showNotification(data: NotificationData) {
        sendNotification(requireContext(), data.title, data.text)
    }

    private fun sendNotification(context: Context, title: String, text: String, notificationId: Int = Random().nextInt()) {
        val channelId = "channelID"
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.pecode_logo)
            .setContentTitle(title)
            .setContentText(text)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId, "Channel human readable title", NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(notificationId, notificationBuilder.build())
    }

    private fun getArgumentsPageNumber(): Long {
        val args = requireArguments()
        return args.getLong(PAGE_NUMBER)
    }

    companion object {
        private const val PAGE_NUMBER = "PAGE_NUMBER"

        fun newInstance(data: PagerItem): PageContentFragment {
            val bundle = Bundle().apply {
                putLong(PAGE_NUMBER, data.pageNumber)
            }

            return PageContentFragment().apply {
                arguments = bundle
            }
        }
    }
}