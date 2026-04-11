/*
 * Copyright (c)
 * 2026.
 * Enes Bilgin
 */

package com.enesbilgin.ebdialogutilities.Interfaces

import java.io.Serializable

interface VoteChoiceListener : Serializable {
    fun onRedirect()
    fun onCancel()
    fun onLater()
}