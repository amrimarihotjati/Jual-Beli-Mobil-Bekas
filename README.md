# Jual Beli Mobil Bekas 🚗

![Android Platform](https://img.shields.io/badge/Platform-Android-3DDC84?style=flat-square&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-7F52FF?style=flat-square&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-4285F4?style=flat-square&logo=jetpackcompose&logoColor=white)
![Architecture](https://img.shields.io/badge/Architecture-MVVM-005B96?style=flat-square)

**Jual Beli Mobil Bekas** is a modern, clean, and elegant Android application designed to aggregate various used car marketplaces into one convenient hub. Developed with cutting-edge Android technologies and adopting a startup-like aesthetic, this application provides users with an exceptional browsing experience to find their dream cars.

---

## ✨ Features

- **Modern UI/UX**: Designed with a premium, sleek, and intuitive startup-style interface using Material 3 and Jetpack Compose.
- **Dynamic Content Configuration**: Fully powered by a remote `config.json` that dynamically updates slideshow promotions, marketplace listings, and AdMob configurations without requiring app updates.
- **Interactive Onboarding**: A smooth Horizontal Pager introduction for first-time users.
- **Smart Ad Integration (AdMob Next Gen)**:
  - **Native Advanced Ads**: Elegantly woven into the Main Screen feed to maintain user experience.
  - **Interval-based Interstitials**: Intelligently triggered Interstitial Ads based on remote configuration to balance monetization and user satisfaction.
- **Seamless Web Navigation**: Uses Chrome Custom Tabs / Intents to direct users exactly to the marketplace platform they desire.

## 🛠 Tech Stack & Architecture

This project is built focusing on scalability, maintainability, and testing, utilizing the **Clean Architecture** pattern combined with **MVVM (Model-View-ViewModel)**.

- **[Kotlin](https://kotlinlang.org/)**: First-class, modern language for Android development.
- **[Jetpack Compose](https://developer.android.com/jetpack/compose)**: Android's modern toolkit for building native UI seamlessly.
- **[Coroutines & Flow](https://kotlinlang.org/docs/coroutines-overview.html)**: For asynchronous programming and reactive data streams.
- **[Retrofit2](https://square.github.io/retrofit/) & GSON**: A type-safe HTTP client for Android for fetching configuration data.
- **[Coil](https://coil-kt.github.io/coil/)**: Fast, lightweight, and modern image loading library backed by Kotlin Coroutines.
- **[Navigation Compose](https://developer.android.com/jetpack/compose/navigation)**: Declarative routing and deep-link handling across screens.
- **[Google Mobile Ads SDK (AdMob)](https://developers.google.com/admob/android/quick-start)**: To manage and serve next-generation monetization solutions.

## 📱 Application Flow

1. **Splash Screen**: A minimal and professional initial loading screen.
2. **Onboarding / Intro**: A quick overview of the app's capabilities highlighting key features.
3. **Main Dashboard**:
   - **Top Section**: A dynamic, auto-sliding promotion banner.
   - **Middle Section**: Strategically placed Native Ads that feel organic to the layout.
   - **Bottom Section**: A beautifully organized grid of supported marketplaces.
4. **Marketplace Detail**: Detailed views that launch the user effortlessly into the specific partner's ecosystem via external intents.

## ⚙️ Setup & Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/amrimarihotjati/Jual-Beli-Mobil-Bekas.git
   cd Jual-Beli-Mobil-Bekas
   ```

2. **Open the project in Android Studio**
   - We recommend using **Android Studio Ladybug** or newer to fully support the latest Jetpack Compose versions.

3. **AdMob Configuration**
   - The project currently utilizes Google's test ad units for development purposes.
   - Before building for production, ensure you replace the `APPLICATION_ID` in `AndroidManifest.xml` and the specific Ad Unit IDs within your hosted `config.json`.

4. **Build and Run**
   - Hit `Run` (`Shift + F10`) in Android Studio to build and install the application on your emulator or physical device.

## 🏗 Project Structure

```text
app/src/main/java/uk/usedcars/marketplace/dealers/auto/finance/
├── data/
│   ├── api/          # Retrofit Interfaces for remote config
│   └── repository/   # Data handling and repository implementations
├── domain/
│   └── model/        # Core business models (AppConfig, Marketplace, etc.)
├── presentation/
│   ├── ui/
│   │   └── screens/  # Compose screens (Splash, Intro, Main, Detail)
│   └── viewmodel/    # State management via ViewModels (StateFlow)
└── utils/
    └── AdMobManager/ # Utility class for handling Interstitial loading & showing
```

## 📄 License

This project is distributed under the MIT License. See the `LICENSE` file for more information.

---
*Crafted with ❤️ for the automotive community.*
