package com.example.tpmobile_marteauflorian.Exceptions;

import androidx.annotation.Nullable;

public class ExceptionPriority extends Exception{

    @Nullable
    @Override
    public String getMessage() {
        return "La priorité doit être entre 0 et 4";
    }
}
