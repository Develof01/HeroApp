package com.mx.android.common.base.scope

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel


abstract class ScopedViewModel : ViewModel(), Scope by Scope.Impl() {

    abstract fun setUp(bundle: Bundle? = Bundle())

    init {
        initScope()
    }

    @CallSuper
    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }

}