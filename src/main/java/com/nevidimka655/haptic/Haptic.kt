@file:SuppressLint("NewApi")

package com.nevidimka655.haptic

import android.annotation.SuppressLint
import android.content.Context
import android.os.VibrationEffect
import android.os.VibrationEffect.Composition
import android.os.Vibrator
import android.view.View
import androidx.core.view.HapticFeedbackConstantsCompat

fun View.hapticToggleOn() = performHapticFeedback(HapticFeedbackConstantsCompat.TOGGLE_ON)
fun View.hapticToggleOff() = performHapticFeedback(HapticFeedbackConstantsCompat.TOGGLE_OFF)
fun View.hapticClick() = performHapticFeedback(HapticFeedbackConstantsCompat.CONTEXT_CLICK)
fun View.hapticLongClick() = performHapticFeedback(HapticFeedbackConstantsCompat.LONG_PRESS)

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

    fun click() {
        vibrator?.vibrate(effectClick)
    }

    fun clickHeavy() {
        vibrator?.vibrate(effectClickHeavy)
    }

    fun tick() {
        try {
            vibrator?.vibrate(effectTick)
        } catch (ignored: Exception) {}
    }

    fun toggle(state: Boolean) {
        try {
            vibrator?.vibrate(
                if (state) effectToggleOn else effectToggleOff
            )
        } catch (ignored: Exception) {}
    }

    fun rise() {
        try {
            vibrator?.vibrate(effectRise)
        } catch (ignored: Exception) {}
    }

    fun init(context: Context) {
        if (vibrator == null) vibrator = context.getSystemService(Vibrator::class.java)
    }

}

private typealias Effect = VibrationEffect