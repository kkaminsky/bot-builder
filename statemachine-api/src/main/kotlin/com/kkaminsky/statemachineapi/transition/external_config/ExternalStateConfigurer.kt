package com.kkaminsky.statemachineapi.transition.external_config

import com.kkaminsky.statemachineapi.transition.TransitionConfig

/**
 * Интерфейс сервиса для базовых переходов External типа,
 * при наличии которого не нужно осуществлять проверки, действия
 * и создавать события
 * @param State - enum состояний перехода
 * @param Event - enum тип срабатываемого события при переходе
 * (нужен для типобезопасности конфигуратора конкретно в данном виде обработки)
 */
interface ExternalStateConfigurer <State:Enum<State>, Event:Enum<Event>>
    : com.kkaminsky.statemachineapi.transition.TransitionConfig<State, Event>

