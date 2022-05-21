package com.kkaminsky.botbuilder.corestatemachine.config

import com.kkaminsky.botbuilder.corestatemachine.config.enums.UserBotEvent
import com.kkaminsky.botbuilder.corestatemachine.config.enums.UserBotState
import org.springframework.context.annotation.Configuration
import org.springframework.statemachine.config.EnableStateMachineFactory
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer

@Configuration
@EnableStateMachineFactory(name = ["coreStateMachineFactory"])
class CoreStateMachineConfig(
    private val coreTransitions: List<com.kkaminsky.statemachineapi.transition.TransitionConfig<UserBotState, UserBotEvent>>,
    private val coreStates: List<com.kkaminsky.statemachineapi.state.StateConfig<UserBotState, UserBotEvent>>
) : EnumStateMachineConfigurerAdapter<UserBotState, UserBotEvent>() {

    override fun configure(config: StateMachineConfigurationConfigurer<UserBotState, UserBotEvent>) {
        config
            .withConfiguration()
            .autoStartup(true)
    }

    override fun configure(transitions: StateMachineTransitionConfigurer<UserBotState, UserBotEvent>) {
        coreTransitions.forEach { transitionConfigure ->
            transitionConfigure.initializeConfig(transitions)
                .and()
        }
    }

    override fun configure(states: StateMachineStateConfigurer<UserBotState, UserBotEvent>) {
        val configuration = states
            .withStates()
            .initial(UserBotState.START)
            .state(UserBotState.START)

        coreStates.forEach { entryConfig ->
            entryConfig.initializeState(configuration)
        }
    }

}