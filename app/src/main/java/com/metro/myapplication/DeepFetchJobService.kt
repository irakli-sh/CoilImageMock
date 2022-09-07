package com.metro.myapplication

import android.content.ComponentName
import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.*
import androidx.work.multiprocess.RemoteCoroutineWorker
import androidx.work.multiprocess.RemoteWorkerService
import coil.imageLoader
import coil.request.ImageRequest
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.concurrent.TimeUnit

@HiltWorker
class DeepFetchJobService @AssistedInject
constructor(
    @Assisted
    appContext: Context,
    @Assisted
    params: WorkerParameters,
) : RemoteCoroutineWorker(appContext, params) {


    override suspend fun doRemoteWork(): Result {
        val iamgeOne =
            "https://metro.co.uk/wp-content/uploads/2021/10/GettyImages-1230120746.jpg?quality=90&strip=all&crop=0px%2C0px%2C1024px%2C768px&resize=480%2C360&zoom=1"
        val imageTwo =
            "https://metro.co.uk/wp-content/uploads/2022/09/comp-1662362676.png?crop=2px%2C0px%2C1248px%2C703px&resize=650%2C366&quality=90&strip=all&zoom=1"
        val imThree =
            "https://api.time.com/wp-content/uploads/2015/04/google-sign.jpg?quality=85&w=800"
        val four =
            "https://www.howtogeek.com/wp-content/uploads/2021/06/google_g_hero.jpg?width=1198&trim=1,1&bg-color=000&pad=1,1"
        val ff =
            "https://www.suarasurabaya.net/wp-content/uploads/2021/08/google-6054007_960_720.png"
        val six =
            "https://cdn.vox-cdn.com/thumbor/jGJ8H_Z4JPl5CyDY-cIWpkhzELw=/0x0:2040x1360/920x613/filters:focal(857x517:1183x843):format(webp)/cdn.vox-cdn.com/uploads/chorus_image/image/68829483/acastro_210104_1777_google_0001.0.jpg"
        val seven =
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkvBlnlvXRya5H-XAisCKUmib-zKq1w4IpJA&usqp=CAU"
        val eight =
            "https://cdn.searchenginejournal.com/wp-content/uploads/2019/04/duckduckgo-vs.-google_-an-in-depth-search-engine-comparison-60a7d7120fb7b-1520x800.png"

        val nine =
            "https://s3.amazonaws.com/images.seroundtable.com/new-google-1598552387.jpg"

        val ten =
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRonjAT0gD8YJTsPR0GTX4JSvboBMwGgxKRUQ&usqp=CAU"


        val imageList =
            arrayListOf(iamgeOne, imageTwo, imThree, four, ff, six, seven, eight, nine, ten)
        for (image in imageList) {
            val request = ImageRequest.Builder(applicationContext)
                .data(image)
                .build()
            val result = applicationContext.imageLoader.execute(request)
            Log.d("Testets", "url - $result")
        }
        return Result.success()
    }

    companion object {
        private val name = DeepFetchJobService::class.java.simpleName

        fun scheduleSync(context: Context) {
            val fourHours = 4L

            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val serviceName = RemoteWorkerService::class.java.name
            val componentName = ComponentName(BuildConfig.APPLICATION_ID, serviceName)
            val data: Data = Data.Builder()
                .putString(ARGUMENT_PACKAGE_NAME, componentName.packageName)
                .putString(ARGUMENT_CLASS_NAME, componentName.className)
                .build()

            val periodicWorkRequest =
                PeriodicWorkRequestBuilder<DeepFetchJobService>(fourHours, TimeUnit.HOURS)
                    .setInputData(data)
                    .setConstraints(constraints)
                    .setBackoffCriteria(
                        BackoffPolicy.LINEAR,
                        PeriodicWorkRequest.MIN_BACKOFF_MILLIS,
                        TimeUnit.MILLISECONDS
                    )
                    .addTag(name)
                    .build()

            val workPolicy = ExistingPeriodicWorkPolicy.REPLACE
            WorkManager.getInstance(context)
                .enqueueUniquePeriodicWork(name, workPolicy, periodicWorkRequest)
        }
    }
}