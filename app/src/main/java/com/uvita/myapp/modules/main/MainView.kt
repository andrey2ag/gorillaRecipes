package com.uvita.myapp.modules.main

import com.uvita.myapp.modules.baseComponents.BaseView

interface MainView : BaseView {
    fun setNavigationTitle(title: String)
    fun initRecipesList()
}