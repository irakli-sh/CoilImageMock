package com.metro.myapplication

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.metro.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        setContent {
            val pagerState = rememberPagerState()
            val lazyListState = rememberLazyListState()
            HorizontalPager(
                modifier = Modifier
                    .fillMaxSize(),
                count = 3,
                state = pagerState,
                contentPadding = PaddingValues(0.dp)
            ) { pageIndex ->
                when (pageIndex) {
                    0 -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            state = lazyListState
                        ) {
                            for (image in imageList) {
                                item {
                                    Image(
                                        painter = rememberAsyncImagePainter(image),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(250.dp),
                                        contentScale = ContentScale.FillWidth,
                                    )
                                }
                            }
                        }
                    }
                    1 -> {
                        Text(text = "Page 1")
                    }

                    2 -> {
                        Text(text = "Page 2")
                    }
                }

            }
        }
        DeepFetchJobService.scheduleSync(this)

        /*  binding = ActivityMainBinding.inflate(layoutInflater)
          setContentView(binding.root)

          setSupportActionBar(binding.toolbar)

          val navController = findNavController(R.id.nav_host_fragment_content_main)
          appBarConfiguration = AppBarConfiguration(navController.graph)
          setupActionBarWithNavController(navController, appBarConfiguration)

          binding.fab.setOnClickListener { view ->
              Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                  .setAction("Action", null).show()
          }*/
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}