/*
 * Copyright (c)
 * 2026.
 * Enes Bilgin
 */

package com.enesbilgin.ebdialogutilities.Models

import com.enesbilgin.ebdialogutilities.Interfaces.SingleEventListener

class EBButtonModel @JvmOverloads constructor(
    val buttonText: String,
    val onButtonClick: SingleEventListener? = null,
    val buttonTextColor: Int? = null
)