package com.kkaminsky.botbuilder.builder.objects

import com.kkaminsky.botbuilder.config.consts.Consts

object StartDialog: TransitionEvent {
    override val name: String
        get() = Consts.startDialogEventPrefix
}