#  Library Management System

This is a **console-based Java application** designed to manage a digital collection of books. It allows users to add, search, list, and remove books, all handled through a simple terminal interface. The project was developed as part of my learning journey into **object-oriented programming (OOP)**, **data persistence**, and **Java collections**.

##  What This Project Demonstrates

### Object-Oriented Programming (OOP)
- Each book is represented by a dedicated `Book` class encapsulating its properties and behavior.
- The `Library` class manages the entire collection using proper encapsulation and separation of concerns.
- Clear use of **constructors**, **getters/setters**, and **method abstraction**.

### Java Collections & Stream API
- Uses `ArrayList` to store and manipulate books dynamically.
- Applies **Java Stream API** for elegant filtering, searching, and sorting logic.
- Demonstrates safe and efficient iteration over collections.

### File Persistence with Serialization
- Implements **serialization and deserialization** to save and load the book collection from disk.
- Handles file I/O using `ObjectOutputStream` and `ObjectInputStream`.
- Ensures that users' data persists between runs without external databases.

### Robust Exception Handling
- Handles invalid inputs, file-related errors, and null references gracefully.
- Includes custom logic to avoid application crashes during unexpected scenarios.
- Shows attention to input validation and user-friendly feedback.

### Console-Based User Interface
- Menu-driven interaction with intuitive prompts.
- Supports adding new books, displaying all, searching by author/title, and deleting entries.
- Organized flow with clear command selection logic.

---


