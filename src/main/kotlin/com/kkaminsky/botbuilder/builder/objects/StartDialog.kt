package com.kkaminsky.botbuilder.objects

import com.kkaminsky.botbuilder.consts.Consts

object StartDialog: TransitionEvent {
    override val name: String
        get() = Consts.startDialogEventPrefix
}