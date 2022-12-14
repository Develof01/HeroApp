package com.mx.android.common.base.scope

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel

abstract class ScopedViewModel : ViewModel(), Scope by Scope.Impl() {

    abstract fun onInit()

    init {
        initScope()
    }

    @CallSuper
    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }

}