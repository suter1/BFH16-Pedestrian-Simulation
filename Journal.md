# Projektjournal

Datum | Zeitaufwand | Wer | Was
--- | --- | --- | ---
23.09.2016 | 5 h | fluht1, suter1 | Projektstart, Brainstorming und Definition der Requirements
28.09.2016 | 3 h | fluht1, suter1 | erstes [Klassendiagramm](./doc/class-diagram_pedestriansimulation.svg) erstellt
30.09.2016 | 4 h | fluht1, suter1 | Projekt aufgesetzt, erste Personen und Areas sichtbar. Basisberechnung für Schritte erstellt.
03.10.2016 | 2 h | fluht1, suter1 | Schritte, Richtungsvektoren, Path implementiert
05.10.2016 | 3 h | fluht1, suter1 | Problemlösungsmeeting 1 "Collision control"
06.10.2016 | 3 h | fluht1 | Ausweichen von Personen gegenüber anderen Personen
06.10.2016 | 3 h | suter1 | Obstacle-Klasse erstellt inkl. Erweiterungspunkte für späteren Dijkstra-Wegsuche
07.10.2016 | 4 h | fluth1, suter1 | Initiale Config erstellt, Obstacles extended edges ohne Fallunterscheidungen implementiert, Klasse GVector eingeführt, refactoring bisheriger Code
07.10.2016 | 4 h | fluth1 | Dijkstra implementiert, findet die Pfade am Anfang noch nicht -> neuer Branch erstellt
08.10.2016 | 4 h | suter1 | Config erweitert mit verschiedenen Auswahlfelder.
09.10.2016 | 4 h | suter1 | Config bearbeitet, damit Personen, Goal und Spawn area abhängig der Config-Daten generiert werden.
12.10.2016 | 4 h | fluht1, suter1 | Dijkstra in erster lauffähigen Version, sehr langsam
14.10.2016 | 6 h | fluht1, suter1 | Perimeter umgeschrieben (unabhängig, von person), Config slider bearbeitet, Refactoring Room, Person, PerimeterManager, Pathmanager
19.10.2016 | 4 h | fluht1, suter1 | Dijkstra gelöscht, eigene Version implementiert -> funktioniert. Config kann dynamisch oder statisch Personentypen erstellen.
21.10.2016 | 4 h | fluht1, suter1 | Reflection, refactoring, person generierung, Config aufgesplittet in Window und Sideconfig.
26.10.2016 | 3 h | fluht1, suter1 | Dijkstra mit PriorityQueue, startup fix und config anpassungen.
28.10.2016 | 4 h | fluht1, suter1 | Draggable persons, reset-button, create room design, refactor
02.11.2016 | 3 h | fluht1, suter1 | Working on movable obstacles
09.11.2016 | 4 h | fluht1, suter1 | Further working on movable obstacles and CSS-Styling (not yet completed finished)
11.11.2016 | 4 h | fluht1, suter1 | Further working on movable obstacles and more CSS-Styling.
16.11.2016 | 4 h | fluht1, suter1 | Asked stackoverflow question about translating, rotating and scaling polygons in java fx, points are not moved but the polygon is and further investigation. Redesigned config window.
18.11.2016 | 4 h | fluht1, suter1 | Statistics improved, rotating and scaling Obstacle moves points too
25.11.2016 | 4 h | fluht1, suter1 | Pie chart implemented and designed, final rotation of spawn and goal area, begin DOV
30.11.2016 | 4 h | fluht1, suter1 | Direction of View DOV (Vector rotation), Stats: Pie chart finished, Bar chart started
07.12.2016 | 3 h | fluht1, suter1 | DOV refactor, stats completed.
09.12.2016 | 3 h | suter1 | Fixed spawn position, fixed problem on zoom out areas, styled alert dialog.
14.12.2016 | 3 h | fluht1, suter1 | spawn fix completed, begin of implementing multiple goal areas.
21.12.2016 | 1 h | fluht1, suter1 | Planning and TODO added, further steps
23.12.2016 | 2 h | fluht1, suter1 | Spawn reset solved, multiple Goalareas ongoing
05.01.2017 | 2 h | suter1 | Draggable Objects blocked while running and released on stop/reset.
10.01.2017 | 2 h | fluht1 | Multiple Goalareas are possible and they are chosen by correctly
11.01.2017 | 3 h | fluht1, suter1 | Stop Event, Areas stay within the Room, Logo, Favicon|
13.01.2017 | 3 h | fluht1, suter1 | Logo positioned variable, Refactoring, Person hidden if intersect with goal |
18.01.2017 | 3 h | fluht1, suter1 | Fixed sliders, refactored a few classes, removed warnings, positioned target vertex in goal, task cancel does not disable start button|
18.01.2017 | 0 h | fluht1, suter1 | finished.