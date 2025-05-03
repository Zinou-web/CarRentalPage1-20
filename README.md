# Car Rental App

A modern Android car rental application built with Jetpack Compose following clean architecture principles.

## Features

- Browse available cars
- View detailed car information
- Book cars with or without a driver
- Manage bookings (upcoming and completed)
- User authentication and profile management

## Tech Stack

- **UI**: Jetpack Compose
- **Architecture**: Clean Architecture with MVVM
- **Dependency Injection**: Hilt
- **Local Storage**: Room
- **Image Loading**: Coil
- **Navigation**: Compose Navigation
- **Concurrency**: Kotlin Coroutines & Flow
- **Testing**: JUnit, Compose UI Testing

## Project Structure

```
app/src/main/java/com/example/myapplication/
├── data/
│   ├── local/       # Local storage (Room DB)
│   ├── remote/      # API calls
│   └── repository/  # Repository implementations
├── domain/
│   ├── model/       # Domain entities
│   ├── repository/  # Repository interfaces
│   └── usecase/     # Business logic
└── ui/
    ├── common/      # Shared components
    └── feature/     # Feature modules
        ├── auth/    # Authentication
        ├── car/     # Car listing and details
        ├── booking/ # Booking management
        └── profile/ # User profile
```

## Getting Started

1. Clone the repository
2. Open the project in Android Studio
3. Sync project with Gradle files
4. Run the app on an emulator or physical device

## Requirements

- Android Studio Hedgehog or newer
- Minimum SDK: 26 (Android 8.0)
- Target SDK: 34 (Android 14)
- JDK 17

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

Your Name - [@your_twitter](https://twitter.com/your_twitter)

Project Link: [https://github.com/Zinou-web/CarRentalPage1-20](https://github.com/Zinou-web/CarRentalPage1-20) 