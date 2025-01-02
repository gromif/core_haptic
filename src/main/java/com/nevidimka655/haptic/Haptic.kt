@file:SuppressLint("NewApi")

package com.nevidimka655.haptic

import android.annotation.SuppressLint
import android.content.Context
import android.os.VibrationEffect
import android.os.VibrationEffect.Composition
import android.os.Vibrator

object Haptic {
    private var vibrator: Vibrator? = null

    private val effectClick by lazy { Effect.createPredefined(Effect.EFFECT_CLICK) }
    private val effectClickHeavy by lazy { Effect.createPredefined(Effect.EFFECT_HEAVY_CLICK) }
    private val effectTick by lazy { Effect.createPredefined(Effect.EFFECT_TICK) }
    private val effectToggleOn by lazy {
        Effect.startComposition().addPrimitive(Composition.PRIMITIVE_TICK, 0.8f).compose()
    }
    private val effectToggleOff by lazy {
        Effect.startComposition().addPrimitive(Composition.PRIMITIVE_TICK, 0.2f).addPrimitive(Composition.PRIMITIVE_LOW_TICK, 0.5f, 80).compose()
    }
    private val effectRise by lazy {
        Effect.startComposition().addPrimitive(Composition.PRIMITIVE_LOW_TICK, 0.5f).addPrimitive(Composition.PRIMITIVE_QUICK_RISE, 0.01f, 100).compose()
    }

    fun click() = vibrate(effectClick)
    fun clickHeavy() = vibrate(effectClickHeavy)
    fun tick() = vibrate(effectTick)
    fun toggle(state: Boolean) = vibrate(if (state) effectToggleOn else effectToggleOff)
    fun rise() = vibrate(effectRise)

    fun vibrate(effect: Effect) {
        try {
            vibrator?.vibrate(effect)
        } catch (_: Exception) {}
    }

    fun init(context: Context) {
        if (vibrator == null) vibrator = context.getSystemService(Vibrator::class.java)
    }

}

private typealias Effect = VibrationEffect