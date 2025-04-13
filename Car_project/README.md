# Car Project

This project is a simulation of car behavior, focusing on core Java programming skills such as multi-threading, synchronization, and the observer design pattern. It represents a simple yet powerful demonstration of how concurrent components can interact safely and efficiently.


### src/
- **main/**
  - **java/**
    - **com/example/demo/**
      - `DodajSamochodController.java`  — Controller for the 'Add Car' view (FXML logic handler)
      - `HelloApplication.java`         — Main JavaFX application class (entry point)
      - `HelloController.java`          — Controller for the main view
      - **samochód/**
        - `Komponent.java`                — Base class or interface for car components
        - `Pozycja.java`                  — Class representing position/state in the car (e.g., gear position)
        - `Samochód.java`                 — Central class representing the Car and its composed components
        - `Silnik.java`                   — Engine simulation logic (e.g., power state, revolutions)
        - `SkrzyniaBiegow.java`           — Gearbox behavior, gear handling
        - `SkrzyniaBiegowException.java`  — Custom exception for gearbox-related issues
        - `Sprzeglo.java`                 — Clutch simulation (engagement logic between engine and gearbox)
  - **resources/**
    - **com/example/demo/**
      - `DodajSamochod.fxml`              — FXML layout for 'Add Car' UI
      - `hello-view.fxml`                 — FXML layout for the main interface
      - **images/**
        - `car.png`                       — Image resource used in the application
  - `module-info.java`                    — Java module system definition


## Component Overview

- `Main.java`: Entry point of the application. Sets up and starts the simulation.
- `Car.java`: Central class representing the car. It holds its current state (e.g., speed, engine status) and interacts with other components.
- `Engine.java`: Simulates engine operations running on a separate thread. It changes states such as "engine on/off" and interacts with the car’s state.
- `SpeedController.java`: Responsible for adjusting the car’s speed. Uses synchronization to safely change shared data between threads.
- `Observer.java`: Defines the observer interface for receiving updates about state changes.
- `Display.java`: Implements the observer pattern and reacts to updates from the `Car` class to show current values (e.g., speed changes).

## Highlights

- **Multi-threading**: Engine and speed controller operate in parallel threads, simulating real-time behavior.
- **Synchronization**: Ensures that multiple threads safely modify shared data like speed and engine status without causing conflicts.
- **Observer Pattern**: Allows components like `Display` to automatically update when the car's state changes, promoting loose coupling and better design.
- **Modular Structure**: Clearly separated classes and responsibilities, making the system extendable and maintainable.

## Skills Demonstrated

- Java thread creation and control
- Synchronized methods and thread-safe interaction
- Event-driven programming with custom observer logic
- Clean modular design in a Maven-based project

