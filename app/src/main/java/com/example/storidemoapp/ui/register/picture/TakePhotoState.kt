package com.example.storidemoapp.ui.register.picture

import android.net.Uri
import com.example.shared.commonResult.ResponseType

data class TakePhotoState(
    val isPhotoUnavailable: Boolean = false,
    var isTakePhotoPressed: Boolean = false,
    var responseType: ResponseType = ResponseType.NONE,
    var responseMessageError: String = ""
)
