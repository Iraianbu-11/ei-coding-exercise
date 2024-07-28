# Astronaut Daily Schedule Organizer

## Project Description
The "Astronaut Daily Schedule Organizer" is a console-based application designed to help astronauts manage their daily tasks efficiently. It allows users to add, remove, view, and manage tasks while ensuring no conflicts in scheduling. 

## Features
1. **Add a New Task**: Add tasks with a description, start time, end time, and priority level.
2. **Remove a Task**: Remove an existing task by its description.
3. **View All Tasks**: Display all tasks sorted by start time.
4. **Validate Tasks**: Ensure new tasks do not overlap with existing ones.
5. **Edit a Task**: Modify the details of an existing task.
6. **Mark Task as Completed**: Mark tasks as done.
7. **View Tasks by Priority**: Filter tasks based on their priority level.

## Additional Features
1. **Logging**: Generates logs based on each time the application ends.
```commandline
app-2024-07-28-18-15-05.log
app-2024-07-28-18-16-59.log
```
2. **Lombok**: Used lombok to reduce the boilerplate code in the application
## Design Patterns Used
1. **Singleton Pattern**: Ensures a single instance of the `ScheduleManager` class that manages all tasks.
2. **Factory Pattern**: A `TaskFactory` class to create task objects.
3. **Observer Pattern**: A `ObserverManager`alerts users of task conflicts

## Setup Instructions
### Prerequisites
Before you begin, ensure you have the following installed on your machine:
- **Java 8+**
- **Maven**
- **Git**
### Clone the Repository
1. Clone the repository using the following command:
    ```sh
    git clone https://github.com/Iraianbu-11/ei-coding-exercise.git
    ```
2. Navigate to the project directory:
    ```sh
    cd exercise-2/astronaut-daily-schedule-organizer
    ```
3. Execute the application using Maven:
    ```sh
     mvn clean compile exec:java
    ```