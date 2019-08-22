package io.legado.app.ui.widget.page

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import io.legado.app.R
import io.legado.app.help.ImageLoader
import io.legado.app.help.ReadBookConfig
import io.legado.app.utils.dp
import io.legado.app.utils.getPrefBoolean
import io.legado.app.utils.getStatusBarHeight
import kotlinx.android.synthetic.main.view_book_page.view.*
import org.jetbrains.anko.matchParent


class ContentView : FrameLayout {

    private val bgImage: AppCompatImageView = AppCompatImageView(context)

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        addView(bgImage, LayoutParams(matchParent, matchParent))
        inflate(context, R.layout.view_book_page, this)
        upStyle()
    }

    fun upStyle() {
        ReadBookConfig.getConfig().apply {
            val pt = if (context.getPrefBoolean("hideStatusBar", false)) {
                paddingTop.dp
            } else {
                paddingTop.dp + context.getStatusBarHeight()
            }
            page_panel.setPadding(paddingLeft.dp, pt, paddingRight.dp, paddingBottom.dp)
            content_text_view.textSize = textSize.toFloat()
            content_text_view.setLineSpacing(lineSpacingExtra, lineSpacingMultiplier)
            content_text_view.letterSpacing = letterSpacing
            content_text_view.setTextColor(textColor())
        }
    }

    fun setBg(bg: Drawable?) {
        //all supported
        ImageLoader.load(context, bg)
            .centerCrop()
            .setAsDrawable(bgImage)
    }

    fun upTime() {

    }

    fun upBattery(battery: Int) {

    }

    fun setContent(page: TextPage?) {
        content_text_view.text = page?.text

        tv_bottom_right.text = page?.index?.toString()
    }
}