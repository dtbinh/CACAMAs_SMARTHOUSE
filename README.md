CACAMAs_SMARTHOUSE
==================

School project for "Lenguajes de computación" (computational theory (automatons, languages, etc)) : use of automatons for controlling various given parameters, with switching between automatic and manual control.

Instruction set for ComplexManager:

- building:showAll
- building:add:String
- building:#:ignite
- building:#:disable
- building:#:room:showAll
- building:#:room:add:String
- building:#:room:#:disable
- building:#:room:#:automaton:showAll
- building:#:room:#:automaton:#:disable
- building:#:room:#:automaton:#:feed:String
- exit

Example of usage:
- building:add:Casa de Memo
- building:0:room:add:Casa de Memo -> Baño
- building:0:room:add:Casa de Memo -> Cuarto
- building:0:room:add:Casa de Memo -> Lobby
- building:0:ignite
- building:add:Casa de Juanpis
- building:1:room:add:Casa de Juanpis -> Baño
- building:1:room:add:Casa de Juanpis -> Cuarto
- building:1:room:add:Casa de Juanpis -> Lobby
- building:1:ignite
