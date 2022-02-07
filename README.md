The classic board game, monopoly.

Developed using TDD and Hexagonal Architecture (aka Ports and Adapters Pattern)

Hexagonal architecture follows the following pattern. The stuff on the left of an arrow drives the stuff on the right of the arrow. 

Input Adapters -> Input Ports -> Domain -> Output Ports -> Output Adapters

Input Ports are the interfaces defined in the ports.in package. <br>
Output Ports are the interfaces defined in the ports.out package. <br>
Input Adapters will live in the adapters.in package (Yet to be created). <br>
Output Adapters, which are the implementations of the Output Port interfaces, are in the adapters.out package.