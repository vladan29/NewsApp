package com.vladan.newsapp.screens.pager.details

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladan.newsapp.AppConstants
import com.vladan.newsapp.dtomodels.HeadlineDto
import com.vladan.newsapp.repositories.PageDetailRepository
import com.vladan.newsapp.utils.CommonResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PagerDetailViewModel @Inject constructor(
    private val pageDetailRepository: PageDetailRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _headlines = MutableLiveData<CommonResponse<HeadlineDto>>()
    val headlines: LiveData<CommonResponse<HeadlineDto>>
        get() = _headlines
    private var country = "us"
    private var categoryHeadline = ""
    private val sharedPreferencesOnChangeListener =
        SharedPreferences.OnSharedPreferenceChangeListener { sp, p1 ->
            if (p1 == AppConstants.COUNTRY) {
                country = sp.getString(AppConstants.COUNTRY, "us").toString()
                getHeadline()
            }
        }

    init {
        country = sharedPreferences.getString(AppConstants.COUNTRY, "us").toString()
        sharedPreferences.registerOnSharedPreferenceChangeListener(sharedPreferencesOnChangeListener)
    }

    fun getHeadline(category: String = categoryHeadline, sourceCountry: String = country) {
        viewModelScope.launch(Dispatchers.IO) {
            _headlines.postValue(CommonResponse.loading(null))
            pageDetailRepository.getHeadlines(category, sourceCountry).let {
                if (it.isSuccessful) {
                    _headlines.postValue(CommonResponse.success(it.body()))
                } else {
                    _headlines.postValue(
                        CommonResponse.error(
                            it.errorBody().toString(),
                            null
                        )
                    )
                }
            }
        }
    }

    override fun onCleared() {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(
            sharedPreferencesOnChangeListener
        )
        super.onCleared()
    }

    fun setCategory(category: String) {
        categoryHeadline = category
    }
}