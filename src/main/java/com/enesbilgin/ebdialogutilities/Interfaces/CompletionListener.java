package com.enesbilgin.ebdialogutilities.Interfaces;

import java.io.Serializable;

public interface CompletionListener extends Serializable {
    void onSuccess();
    void onCancel();
}
