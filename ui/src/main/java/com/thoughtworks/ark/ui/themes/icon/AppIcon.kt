package com.thoughtworks.ark.ui.themes.icon

import androidx.annotation.DrawableRes
import com.thoughtworks.ark.ui.R

enum class AppIcon(@DrawableRes val resourceId: Int) {
    ArrowBack(R.drawable.ic_arrow_back),
    ArrowForward(R.drawable.ic_arrow_forward),
    Close(R.drawable.ic_close),
    Cancel(R.drawable.ic_cancel),
    Delete(R.drawable.ic_delete),
    Home(R.drawable.ic_home),
    Menu(R.drawable.ic_menu),
    More(R.drawable.ic_more),
    Favorite(R.drawable.ic_favorite),
    Search(R.drawable.ic_search),
}