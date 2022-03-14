package com.cherish.mynewtodoapp

import android.os.Build

import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config( manifest = Config.NONE,
    sdk = [Build.VERSION_CODES.O_MR1],application = HiltTestApplication::class)
class MainFragmentTest {


//    @Test
//    fun testMainActivity() {
//        val frag = launchFragmentInContainer<FragmentMain>()
//        frag.onFragment {
//            it.view?.findViewById<RecyclerView>(R.id.recyclerView)?.hasFocusable()
//                ?.let { it1 -> assert(it1) }
//        }
//    }
}