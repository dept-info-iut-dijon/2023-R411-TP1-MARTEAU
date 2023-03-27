package com.example.tpmobile_marteauflorian.Exceptions;

import androidx.annotation.Nullable;

public class ExceptionTitleVide extends Exception {

    @Nullable
    @Override
    public String getMessage() {
        return "Le titre a été vide";
    }
}
