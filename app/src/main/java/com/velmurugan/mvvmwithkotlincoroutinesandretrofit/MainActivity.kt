package com.velmurugan.mvvmwithkotlincoroutinesandretrofit

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.Constants.Companion.QUERY_VALUE
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.databinding.ActivityMainBinding
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.extention.showSnackbar
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.extention.showYesNoDialog
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.localDB.ArticleEntity
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.state_models.Resource
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.state_models.ResourceState
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by inject()
    private val adapter = MovieAdapter()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpAdapter()



        viewModel.getAllArticlesResponseLiveData.observe(this) { articleData ->
            obseverveArticleData(articleData)


        }

        viewModel.isDataDeletedLiveData.observe(this) { isDeleted ->

            when (isDeleted.state) {
                ResourceState.LOADING -> {
                    binding.progressDialog.visibility = View.VISIBLE
                }

                ResourceState.SUCCESS -> {
                    binding.progressDialog.visibility = View.GONE
                    binding.constraintMain.showSnackbar(
                        message = "Item Deleted",
                        duration = Snackbar.LENGTH_SHORT
                    )
                    if (isDeleted.data == true) {
                        viewModel.isLocalStorageEmpty()
                    }


                }

                ResourceState.ERROR -> {
                    binding.progressDialog.visibility = View.GONE
                    Toast.makeText(this, isDeleted.message, Toast.LENGTH_SHORT).show()
                }
            }
        }


        viewModel.isLocalStorageEmpty.observe(this) { isLocalStorageEmpty ->

            when (isLocalStorageEmpty.state) {
                ResourceState.LOADING -> {
                    binding.progressDialog.visibility = View.VISIBLE
                }

                ResourceState.SUCCESS -> {
                    binding.progressDialog.visibility = View.GONE
                    if (isLocalStorageEmpty.data == true) {
                        viewModel.getAllArticles(QUERY_VALUE, "39855b9e16bf4b21aabeaa39806004dd")

                    } else {
                        viewModel.fetchDataFromLocal()
                    }

                }

                ResourceState.ERROR -> {
                    binding.progressDialog.visibility = View.GONE
                    Toast.makeText(this, isLocalStorageEmpty.message, Toast.LENGTH_SHORT).show()
                }


            }

        }

        // viewModel.getAllArticles(QUERY_VALUE, "39855b9e16bf4b21aabeaa39806004dd")
        viewModel.isLocalStorageEmpty()

    }

    private fun obseverveArticleData(articleData: Resource<List<ArticleEntity>>) {

        when (articleData.state) {
            ResourceState.LOADING -> {
                binding.progressDialog.visibility = View.VISIBLE
            }

            ResourceState.SUCCESS -> {
                binding.progressDialog.visibility = View.GONE
                articleData.data?.let {
                    setValuesToAdapter(articleData.data)

                }

            }

            ResourceState.ERROR -> {
                binding.progressDialog.visibility = View.GONE
                Toast.makeText(this, articleData.message, Toast.LENGTH_SHORT).show()
            }


        }

    }

    private fun setUpAdapter() {
        binding.recyclerview.adapter = adapter
        adapter.longClicked = {

            this.showYesNoDialog(
                title = "Confirmation",
                message = "Do you want to delete/",
                onPositiveButtonClick = {
                    viewModel.deleteDataFromDB(article = it)
                },
                onNegativeButtonClick = {
                    // Action to perform when the user clicks "No"
                    // Add your logic here
                }
            )


        }
    }

    private fun setValuesToAdapter(data: List<ArticleEntity>) {
        adapter.setMovies(data)
    }


}