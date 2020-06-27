package com.findmore.myshop.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.findmore.myshop.R
import com.findmore.myshop.adapters.ProductsListAdapter
import com.findmore.myshop.adapters.RecyclerViewAdapter
import com.findmore.myshop.models.HomeData
import com.findmore.myshop.models.Products
import com.findmore.myshop.utils.ItemOffsetDecoration
import kotlinx.android.synthetic.main.layout_toolbar.*


class HomeFragment : Fragment(), OnItemClickListener {
    private var homeData: HomeData? = null
    private var homeViewModel: HomeViewModel? = null
    private var likeCategories: RecyclerView? = null
    private var listProducts: RecyclerView? = null
    private var recyclerViewAdapter: RecyclerViewAdapter? = null
    private var productsListAdapter: ProductsListAdapter? = null
    private var homeBannerTitle: TextView? = null
    private var homeBannerDescription: TextView? = null
    private var imgOffer: ImageView? = null
    private var toolbar: Toolbar? = null
    private var navController: NavController? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root: View = inflater.inflate(R.layout.home_fragment, container, false)

        //===================================================
        val toolbar = root.findViewById(R.id.toolbar_home1) as Toolbar?
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar) //toolbar id
        //toolbar?.title = "Androidly"
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        //===================================================
        likeCategories = root.findViewById(R.id.rl_LikeList)
        listProducts = root.findViewById(R.id.rl_Products)
        homeBannerTitle = root.findViewById(R.id.tvTitle)
        homeBannerDescription = root.findViewById(R.id.tvDesc)
        imgOffer = root.findViewById(R.id.imgLimitedOffer)

        //===================================================
        //setup adapter and recyclerview for UserLike categories
        recyclerViewAdapter = RecyclerViewAdapter(activity)
        val layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        likeCategories!!.layoutManager = layoutManager
        likeCategories!!.adapter = recyclerViewAdapter

        //===================================================
        //intialize adapter and recyclerview for Products list
        productsListAdapter = ProductsListAdapter(activity, this)
        val lLayout = GridLayoutManager(activity, 2)
        val itemDecoration = ItemOffsetDecoration(requireActivity(), R.dimen.item_offset)
        listProducts!!.addItemDecoration(itemDecoration)
        listProducts!!.layoutManager = lLayout
        listProducts!!.adapter = productsListAdapter
        //===================================================
        return root
    }

    /*
    * Initialise the Home page Banner views and Add Banners.
    *  */
    private fun initBannerView(data: HomeData?) {
        try {
            homeBannerTitle!!.text = data!!.bannerItems!!.limitedOffers!![0]!!.offerName!!
            homeBannerDescription!!.text = data!!.bannerItems!!.limitedOffers!![0]!!.offerDescriptions !!
            Glide.with(this)
                .load(
                    resources.getIdentifier(
                        "img_perfume_banner",
                        "drawable",
                        requireActivity().packageName
                    )
                ).override(218, 270).into(imgOffer!!)
            productsListAdapter!!.updateData(data.products)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //===================================================
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        homeViewModel!!.getHomeData(activity)
            .observe(viewLifecycleOwner, Observer<Any?> { data ->
                homeData = data as HomeData?
                initBannerView(data) // initialise the Home page banners view.
                recyclerViewAdapter!!.updateData(data?.likeCategories)
            })
    }

    //==============OnKeydown button clicked=====================================
    override fun onItemClick(
        parent: AdapterView<*>?,
        view: View,
        position: Int,
        id: Long
    ) {
        try {
            val product: Products = homeData!!.products!![position]
            when (view.id) {
                R.id.imgCart -> {
                    //add item cart icon view was clicked
                    homeViewModel!!.addToCart(requireActivity(), product)
                    navController!!.navigate(R.id.action_navigation_home_to_navigation_cart)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
