package com.learn.growagain.tutorial.unittest

import android.content.Context
import androidx.annotation.StringRes


fun getCopyFromResource(context: Context, @StringRes stringResource: Int): String{
    return context.resources.getString(stringResource)
}