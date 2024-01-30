package com.example.jfx.aop

import com.sun.glass.ui.Accessible
import com.sun.glass.ui.ClipboardAssistance
import com.sun.glass.ui.View

class MyEventHandlerProxy(val origin: View.EventHandler) : View.EventHandler() {
    override fun handleViewEvent(view: View?, time: Long, type: Int) {
        origin.handleViewEvent(view, time, type)
    }

    override fun handleKeyEvent(
        view: View?,
        time: Long,
        action: Int,
        keyCode: Int,
        keyChars: CharArray?,
        modifiers: Int
    ) {
        origin.handleKeyEvent(view, time, action, keyCode, keyChars, modifiers)
    }

    override fun handleMenuEvent(
        view: View?,
        x: Int,
        y: Int,
        xAbs: Int,
        yAbs: Int,
        isKeyboardTrigger: Boolean
    ) {
        origin.handleMenuEvent(view, x, y, xAbs, yAbs, isKeyboardTrigger)
    }

    override fun handleMouseEvent(
        view: View?,
        time: Long,
        type: Int,
        button: Int,
        x: Int,
        y: Int,
        xAbs: Int,
        yAbs: Int,
        modifiers: Int,
        isPopupTrigger: Boolean,
        isSynthesized: Boolean
    ) {
        val start = System.currentTimeMillis()
        origin.handleMouseEvent(
            view,
            time,
            type,
            button,
            x,
            y,
            xAbs,
            yAbs,
            modifiers,
            isPopupTrigger,
            isSynthesized
        )
        if (type == com.sun.glass.events.MouseEvent.UP) {
            println("handleMouseEvent cost=${System.currentTimeMillis() - start}ms")
        }
    }

    override fun handleScrollEvent(
        view: View?,
        time: Long,
        x: Int,
        y: Int,
        xAbs: Int,
        yAbs: Int,
        deltaX: Double,
        deltaY: Double,
        modifiers: Int,
        lines: Int,
        chars: Int,
        defaultLines: Int,
        defaultChars: Int,
        xMultiplier: Double,
        yMultiplier: Double
    ) {
        origin.handleScrollEvent(
            view,
            time,
            x,
            y,
            xAbs,
            yAbs,
            deltaX,
            deltaY,
            modifiers,
            lines,
            chars,
            defaultLines,
            defaultChars,
            xMultiplier,
            yMultiplier
        )
    }

    override fun handleInputMethodEvent(
        time: Long,
        text: String?,
        clauseBoundary: IntArray?,
        attrBoundary: IntArray?,
        attrValue: ByteArray?,
        commitCount: Int,
        cursorPos: Int
    ) {
        origin.handleInputMethodEvent(
            time,
            text,
            clauseBoundary,
            attrBoundary,
            attrValue,
            commitCount,
            cursorPos
        )
    }

    override fun getInputMethodCandidatePos(offset: Int): DoubleArray {
        return origin.getInputMethodCandidatePos(offset)
    }

    override fun handleDragStart(
        view: View?,
        button: Int,
        x: Int,
        y: Int,
        xAbs: Int,
        yAbs: Int,
        dropSourceAssistant: ClipboardAssistance?
    ) {
        origin.handleDragStart(view, button, x, y, xAbs, yAbs, dropSourceAssistant)
    }

    override fun handleDragEnd(view: View?, performedAction: Int) {
        origin.handleDragEnd(view, performedAction)
    }

    override fun handleDragEnter(
        view: View?,
        x: Int,
        y: Int,
        xAbs: Int,
        yAbs: Int,
        recommendedDropAction: Int,
        dropTargetAssistant: ClipboardAssistance?
    ): Int {
        return origin.handleDragEnter(
            view,
            x,
            y,
            xAbs,
            yAbs,
            recommendedDropAction,
            dropTargetAssistant
        )
    }

    override fun handleDragOver(
        view: View?,
        x: Int,
        y: Int,
        xAbs: Int,
        yAbs: Int,
        recommendedDropAction: Int,
        dropTargetAssistant: ClipboardAssistance?
    ): Int {
        return origin.handleDragOver(
            view,
            x,
            y,
            xAbs,
            yAbs,
            recommendedDropAction,
            dropTargetAssistant
        )
    }

    override fun handleDragLeave(view: View?, dropTargetAssistant: ClipboardAssistance?) {
        origin.handleDragLeave(view, dropTargetAssistant)
    }

    override fun handleDragDrop(
        view: View?,
        x: Int,
        y: Int,
        xAbs: Int,
        yAbs: Int,
        recommendedDropAction: Int,
        dropTargetAssistant: ClipboardAssistance?
    ): Int {
        return origin.handleDragDrop(
            view,
            x,
            y,
            xAbs,
            yAbs,
            recommendedDropAction,
            dropTargetAssistant
        )
    }

    override fun handleBeginTouchEvent(
        view: View?,
        time: Long,
        modifiers: Int,
        isDirect: Boolean,
        touchEventCount: Int
    ) {
        origin.handleBeginTouchEvent(view, time, modifiers, isDirect, touchEventCount)
    }

    override fun handleNextTouchEvent(
        view: View?,
        time: Long,
        type: Int,
        touchId: Long,
        x: Int,
        y: Int,
        xAbs: Int,
        yAbs: Int
    ) {
        origin.handleNextTouchEvent(view, time, type, touchId, x, y, xAbs, yAbs)
    }

    override fun handleEndTouchEvent(view: View?, time: Long) {
        origin.handleEndTouchEvent(view, time)
    }

    override fun handleScrollGestureEvent(
        view: View?,
        time: Long,
        type: Int,
        modifiers: Int,
        isDirect: Boolean,
        isInertia: Boolean,
        touchCount: Int,
        x: Int,
        y: Int,
        xAbs: Int,
        yAbs: Int,
        dx: Double,
        dy: Double,
        totaldx: Double,
        totaldy: Double,
        multiplierX: Double,
        multiplierY: Double
    ) {
        origin.handleScrollGestureEvent(
            view,
            time,
            type,
            modifiers,
            isDirect,
            isInertia,
            touchCount,
            x,
            y,
            xAbs,
            yAbs,
            dx,
            dy,
            totaldx,
            totaldy,
            multiplierX,
            multiplierY
        )
    }

    override fun handleZoomGestureEvent(
        view: View?,
        time: Long,
        type: Int,
        modifiers: Int,
        isDirect: Boolean,
        isInertia: Boolean,
        x: Int,
        y: Int,
        xAbs: Int,
        yAbs: Int,
        scale: Double,
        expansion: Double,
        totalscale: Double,
        totalexpansion: Double
    ) {
        origin.handleZoomGestureEvent(
            view,
            time,
            type,
            modifiers,
            isDirect,
            isInertia,
            x,
            y,
            xAbs,
            yAbs,
            scale,
            expansion,
            totalscale,
            totalexpansion
        )
    }

    override fun handleRotateGestureEvent(
        view: View?,
        time: Long,
        type: Int,
        modifiers: Int,
        isDirect: Boolean,
        isInertia: Boolean,
        x: Int,
        y: Int,
        xAbs: Int,
        yAbs: Int,
        dangle: Double,
        totalangle: Double
    ) {
        origin.handleRotateGestureEvent(
            view,
            time,
            type,
            modifiers,
            isDirect,
            isInertia,
            x,
            y,
            xAbs,
            yAbs,
            dangle,
            totalangle
        )
    }

    override fun handleSwipeGestureEvent(
        view: View?,
        time: Long,
        type: Int,
        modifiers: Int,
        isDirect: Boolean,
        isInertia: Boolean,
        touchCount: Int,
        dir: Int,
        x: Int,
        y: Int,
        xAbs: Int,
        yAbs: Int
    ) {
        origin.handleSwipeGestureEvent(
            view,
            time,
            type,
            modifiers,
            isDirect,
            isInertia,
            touchCount,
            dir,
            x,
            y,
            xAbs,
            yAbs
        )
    }

    override fun getSceneAccessible(): Accessible {
        return origin.getSceneAccessible()
    }
}