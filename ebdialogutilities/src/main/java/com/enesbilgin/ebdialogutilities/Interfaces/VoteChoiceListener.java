/*
 * Copyright (c)
 * 2019.
 * Enes Bilgin
 */

package com.enesbilgin.ebdialogutilities.Interfaces;

import java.io.Serializable;

public interface VoteChoiceListener extends Serializable {
    void onRedirect();
    void onCancel();
    void onLater();
}
