package com.enesbilgin.ebdialogutilities.Interfaces;

import java.io.Serializable;
/**
 * Copyright 2019
 * Enes Bilgin
 */
public interface CompletionListener extends Serializable {
    void onSuccess();
    void onCancel();
}
