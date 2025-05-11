# Project Title: Forest Simulation

Yauheni Vasiliuk - Team Leader  
Anton Kraievskyi

### Description

The project is a Forest Simulation implemented in Java, where multiple classes represent elements of a virtual ecosystem.

The simulation includes interactions between plants, herbivores, predators, and various random environmental events such as fires, hunters, and a river.

Points being accumulated by predators for hunting and by herbivores for feeding and reproducing.

Random events impact the game dynamics and survival rates, introducing unpredictability.

*The goal* is for predator or herbivore teams to reach a set number of points to win the simulation.

The simulation includes a graphical user interface (UI). The UI displays a forest map, with visual representation for plants, herbivores, predators, hunters, wildfires, and rivers.

*Key features include:*
* Real-time updates of entity positions and interactions.
* Scoreboard panel tracking predator and herbivore points.
* Speed control slider to adjust simulation speed.

__*Programming language - Java*__

### Classes

* Plants;
* Animals:
    * Herbivores;
    * Predators;
* River;
* Wildfire;
* Hunters.

### Data

**Input:**
* Required number of points for victory;
* Number of plants, herbivores, and predators;
* Speed of the simulation;
* Probabilities of random events:
    * item Appearance of hunters;
    * item Occurrence of wildfire;
    * item Risk of drowning in a river.

**Output:**
* Points earned by each team (predators, herbivores);
* Number of remaining animals;
* Number of killed entities;
* Number of random events.  