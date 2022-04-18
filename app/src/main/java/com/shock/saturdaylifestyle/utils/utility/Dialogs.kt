package com.shock.saturdaylifestyle.utils.utility

import android.app.Dialog
import android.content.Context
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar
import saturdaylifestyle.R

class Dialogs {

    companion object {
        @JvmStatic
        fun getLoadingDialog(context: Context): Dialog {
            val dialog = Dialog(context)
            dialog.setContentView(R.layout.loader_layout)
            dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCancelable(true)
            dialog.setCanceledOnTouchOutside(false)
            val dilatingDotsProgressBar = dialog.findViewById<DilatingDotsProgressBar>(
                R.id.progress_loader
            )
            dilatingDotsProgressBar.showNow()
            return dialog

        }

    }




}