# BINGO Game Application

This is a multiplayer BINGO game application for Android. The app allows players to connect via Wi-Fi Direct (P2P), play BINGO, and interact with various features such as animations, sound effects, and user settings.

## Features

- **Multiplayer Gameplay**: Connect with other players using Wi-Fi Direct (P2P) and play BINGO.
- **Custom Animations**: Smooth transitions and animations for buttons and UI elements.
- **Sound Effects**: Background music and sound effects with the ability to toggle sound on/off.
- **User Settings**: Save and manage player names and sound preferences using `SharedPreferences`.
- **Wi-Fi Management**: Automatically enable/disable Wi-Fi as needed for gameplay.
- **Modern UI**: Fullscreen mode with custom transitions and animations.

## Setup and Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/sopho1/BINGO_Game.git
2. Open the project in Android Studio.
3. Sync the Gradle files.
4. Build and run the app on an Android device or emulator.

## Permissions
The app requires the following permissions:

ACCESS_FINE_LOCATION: Required for Wi-Fi Direct functionality.
ACCESS_COARSE_LOCATION: Required for Wi-Fi Direct functionality.
INTERNET: Required for multiplayer connectivity.
Make sure to grant these permissions when prompted.

How to Play
Launch the app.
Enter your name in the Name activity if prompted.
Connect to other players using the Connect activity.
Host or join a game.
Play BINGO by marking numbers on your card and aim to get five in a row (horizontally, vertically, or diagonally).
