## Getting Started

This is discrete simulation of a world.

You can build it with `mvn clean compile` in subdirectory world. 
And then run it with `mvn exec:java`.

#### log messages
You can write log messages by pasting these commands into console:
- log day loop
  - writes messages to players with info about weather and day time changes. 
- log day stop
  - stops the printing, oposite to the item above.
- log creaturesStats loop
  - writes messages to player with info about his actual stats changes. 
- log creaturesStats stop
- log date
- log clouds
- log clouds loop
- log clouds stop
- log clouds 0
- ...
- log clouds 7
  - zero represents no cloud on the sky, 7 represents dark cloudy sky.
- log weather
- log weather loop
- log weather stop

- log weather idle 
- log weather rain
- log weather heavy_rain
- log weather storm
- log weather thunderstorm

- log partDay after_midnight
- log partDay sunrise
- log partDay morning
- log partDay afternoon
- log partDay sunset1
- log partDay sunset2
- log partDay night

- log time 200
	- larger value makes everything in world slower (It makes the delay in the game loop larger). The value should be whole number and should be larger than 0. 200 is a default value and means that one hour in the game takes 12 seconds in real. It means that whole day takes 268 seconds.

- log wind
- log windDirection
- log health
- log calendar loop
    - prints info about data structure calendar, where all next events are saved.
    - prints the next event.
- log calendar stop
- log map
- log map map_id
- log map 0
- log temp
    - means temperature in the map 0
- log addResource x y name amount
- log addResource 1 1 mushrooms 10
- log abilityStats loop
- log abilityStats stop