package com.example.togutravelapp.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.togutravelapp.adapter.ListImageAdapter
import com.example.togutravelapp.adapter.SectionsPagerAdapter
import com.example.togutravelapp.data.DummyRecommendData
import com.example.togutravelapp.data.DummyURL
import com.example.togutravelapp.databinding.ActivityDetailLocationBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailLocationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailLocationBinding
    private lateinit var imageRv : RecyclerView
    private lateinit var locationProfile: ImageView
    private lateinit var locationName : TextView
    private lateinit var locationPrice : TextView
    private lateinit var locationLoc : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val locationDetail = intent.getParcelableExtra<DummyRecommendData>(EXTRA_LOCATIONDETAIL) as DummyRecommendData

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = binding.locationDetailViewpager2
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.locationDetailTabs
        TabLayoutMediator(tabs, viewPager){ tab,pos ->
            tab.text = TAB_TITLES[pos]
        }.attach()

        locationProfile = binding.locationDetailImage
        Glide.with(this)
            .load(locationDetail.recomUrl.toString())
            .centerCrop()
            .into(locationProfile)

        locationPrice = binding.locationDetailPrice
        locationPrice.text = locationDetail.recomPrice.toString()

        locationLoc = binding.locationDetailAddr
        locationLoc.text = locationDetail.recomLoc.toString()

        locationName = binding.locationDetailName
        locationName.text = locationDetail.recomTitle.toString()

        imageRv = binding.locationDetailListImageRv
        imageRv.setHasFixedSize(true)
        setImageData()
    }

    private fun setImageData(){
        val listDummyData = setDummyImageURL()
        imageRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapter = ListImageAdapter(listDummyData)
        imageRv.adapter = adapter
        /*
        adapter.setOnItemClickCallback(object: ListUserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: ItemsItem) {
                val detailed = data.login
                val intentToDetail = Intent(this@MainActivity, UserDetailActivity::class.java)
                intentToDetail.putExtra(EXTRA_USERNAME, detailed)
                startActivity(intentToDetail)
            }
        })*/
    }

    private fun setDummyImageURL() : List<DummyURL>{
        val listURL = arrayOf("https://images.bisnis-cdn.com/posts/2022/05/05/1530199/orchid-forest-cikole-lembang-1.jpg","https://asset.kompas.com/crops/TKZHoKAYpzkYIa2q6xzqwggpaNU=/0x0:0x0/750x500/data/photo/2021/09/16/614317f8e6e89.jpg","https://pingpoint.co.id/media/images/Slide8_ABg9OQH.original.jpg")
        val listURLDummy = mutableListOf<DummyURL>()
        for (i in listURL){
            listURLDummy.add(
                DummyURL(urlImage = i)
            )
        }
        return listURLDummy
    }

    companion object {
        private val TAB_TITLES = listOf("Tinjauan","Objek Wisata","Rekomendasi")
        const val EXTRA_LOCATIONDETAIL = "location_detail"
    }
}