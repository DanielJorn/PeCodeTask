package com.example.pecodetask.features.pageContent

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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

        viewModel.notificationClick.observe(viewLifecycleOwner) { showNotification(it) }

        return binding.root
    }

    private fun showNotification(data: NotificationData) {
        val title = getString(R.string.notification_title)
        val text = getString(R.string.notification_text, data.pageNumber)

        sendNotification(requireContext(), title, text)
    }

    private fun sendNotification(
        context: Context,
        title: String,
        text: String,
        notificationId: Int = Random().nextInt()
    ) {
        val notificationManager = getNotificationManager(context)

        if (needToCreateNotificationChannel()) {
            createNotificationChannel(notificationManager)
        }

        val notificationBuilder = createNotificationBuilder(context, title, text)
        notificationManager.notify(notificationId, notificationBuilder.build())
    }

    private fun getNotificationManager(context: Context) =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private fun createNotificationBuilder(
        context: Context,
        title: String,
        text: String
    ): NotificationCompat.Builder {
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        return NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.pecode_logo)
            .setContentTitle(title)
            .setContentText(text)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            getString(R.string.app_name),
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)
    }

    private fun needToCreateNotificationChannel() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

    private fun getArgumentsPageNumber(): Long {
        val args = requireArguments()
        return args.getLong(PAGE_NUMBER)
    }

    companion object {
        private const val PAGE_NUMBER = "PAGE_NUMBER"
        private const val NOTIFICATION_CHANNEL_ID = "com.example.pecodetask"

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