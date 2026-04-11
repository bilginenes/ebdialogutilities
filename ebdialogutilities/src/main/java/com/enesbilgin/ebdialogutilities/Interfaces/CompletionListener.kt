/*
 * Copyright (c)
 * 2026.
 * Enes Bilgin
 */

package com.enesbilgin.ebdialogutilities.Interfaces

import java.io.Serializable

/**
 * Copyright 2019
 * Enes Bilgin
 */
interface CompletionListener : Serializable {
    fun onSuccess()
    fun onCancel()
}