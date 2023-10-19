package com.codepath.articlesearch

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.articlesearch.databinding.ActivityMainBinding
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONException

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

private const val TAG = "MainActivity/"
//private const val SEARCH_API_KEY = BuildConfig.API_KEY
//private const val ARTICLE_SEARCH_URL =
//    "https://api.nytimes.com/svc/search/v2/articlesearch.json?api-key=${SEARCH_API_KEY}"

//class MainActivity : AppCompatActivity() {
//    //    private val articles = mutableListOf<Article>()
////    private lateinit var articlesRecyclerView: RecyclerView
//    private lateinit var binding: ActivityMainBinding
//
//    private fun replaceFragment(articleListFragment: ArticleListFragment) {
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.article_frame_layout, articleListFragment)
//        fragmentTransaction.commit()
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
//
//        // Call helper method to swap the FrameLayout with the fragment
//        replaceFragment(ArticleListFragment())
//    }
//}



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val fragmentManager: FragmentManager = supportFragmentManager

        // define your fragments here
        val bestSellerBooksFragment: Fragment = BestSellerBooksFragment()
        val articleListFragment: Fragment = ArticleListFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // handle navigation selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.nav_books -> fragment = bestSellerBooksFragment
                R.id.nav_articles -> fragment = articleListFragment
            }
            replaceFragment(fragment)
            true
        }

        // Set default selection
        bottomNavigationView.selectedItemId = R.id.nav_books
    }
    private fun replaceFragment(articleListFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.article_frame_layout, articleListFragment)
        fragmentTransaction.commit()
    }
}




//        articlesRecyclerView = findViewById(R.id.articles)
//        val articleAdapter = ArticleAdapter(this, articles)

//        articlesRecyclerView.adapter = articleAdapter


//        lifecycleScope.launch {
//            (application as ArticleApplication).db.articleDao().getAll().collect { databaseList ->
//                databaseList.map { entity ->
//                    DisplayArticle(
//                        entity.headline,
//                        entity.articleAbstract,
//                        entity.byline,
//                        entity.mediaImageUrl
//                    )
//                }.also { mappedList ->
//                    articles.clear()
//                    articles.addAll(mappedList)
//                    articleAdapter.notifyDataSetChanged()
//                }
//            }
//        }



//        articlesRecyclerView.layoutManager = LinearLayoutManager(this).also {
//            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
//            articlesRecyclerView.addItemDecoration(dividerItemDecoration)
//        }

//        val client = AsyncHttpClient()
//        client.get(ARTICLE_SEARCH_URL, object : JsonHttpResponseHandler() {
//            override fun onFailure(
//                statusCode: Int,
//                headers: Headers?,
//                response: String?,
//                throwable: Throwable?
//            ) {
//                Log.e(TAG, "Failed to fetch articles: $statusCode")
//            }

//            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
//                Log.i(TAG, "Successfully fetched articles: $json")
//                try {
//                    val parsedJson = createJson().decodeFromString(
//                        SearchNewsResponse.serializer(),
//                        json.jsonObject.toString()
//                    )
//                    parsedJson.response?.docs?.let { list ->
//                        lifecycleScope.launch(IO) {
//                            (application as ArticleApplication).db.articleDao().deleteAll()
//                            (application as ArticleApplication).db.articleDao().insertAll(list.map {
//                                ArticleEntity(
//                                    headline = it.headline?.main,
//                                    articleAbstract = it.abstract,
//                                    byline = it.byline?.original,
//                                    mediaImageUrl = it.mediaImageUrl
//                                )
//                            })
//                        }
//                    }
//                } catch (e: JSONException) {
//                    Log.e(TAG, "Exception: $e")
//                }
//            }
//
//        })
//
//    }





