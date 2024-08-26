# hit-chess
Overview
This chess application is a multiplayer game built using Spring Boot and WebSocket. The app features a 5x5 game board with turn-based gameplay and real-time synchronization between clients. The backend implements game logic and move validation, while the frontend provides an interactive web interface.

Features
Core Game Logic: Implements game rules and piece movements.
WebSocket Integration: Real-time updates and communication between server and clients.
Client Interface: Basic web UI using Bootstrap, HTML, CSS, and JavaScript.
Turn-Based Gameplay: Clear turn indication and handling of moves.
Move Validation: Both client and server-side validation of moves and game state.
Error Handling: Robust handling of edge cases like simultaneous moves, disconnections, and invalid moves.
Technologies Used
Backend: Spring Boot, WebSocket
Frontend: Bootstrap, HTML, CSS, JavaScript
Java Version: 17+
Code Organization
The project is organized into a single repository with a clear separation between client and server code.

Server Code: Located in /src/main/java/com/hit_chess
Client Code: Located in the frontend files within the resources directory.
Setup and Installation
Prerequisites
Java 17+ installed on your system.
Running the Server
Clone the repository:
bash
Copy code
git clone <repository-url>
Navigate to the project directory:
bash
Copy code
cd <project-directory>
Build and run the server:
bash
Copy code
./mvnw spring-boot:run
Alternatively, you can run the application directly from your IDE by executing HitChessApplication.java.
Accessing the Application
Open your browser and go to:

http
Copy code
http://localhost:8082/home
This will load the client interface where you can start the game.
