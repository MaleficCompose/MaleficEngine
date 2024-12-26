# MaleficEngine

MaleficEngine is a Kotlin-based library designed to facilitate the creation of UI components using Jetpack Compose. It provides a set of tools and wrappers to streamline the development of composable functions as well as provide support for way more customizability.

## Table of Contents

- [Installation](#installation)
- [Overview](#overview)
- [Examples](#examples)
- [Contributing](#contributing)
- [License](#license)

## Installation

To include MaleficEngine in your project, add the following dependency to your `build.gradle.kts` file:

```kotlin
dependencies {
    implementation("xyz.malefic.compose:engine:1.0.0")
}
```

Ensure that you have the necessary repositories configured in your `settings.gradle.kts` or `build.gradle.kts`:

```kotlin
repositories {
    mavenCentral()
}
```

## Overview

MaleficEngine provides a set of factories and annotations to create composable functions efficiently. It includes custom factories like `TextFactory`, `ButtonFactory`, `BoxFactory`, and more, which help structure your UI components. These factories can be customized using the provided methods and properties.

### ComposableFactory Interface

The `ComposableFactory` interface is a key component in MaleficEngine. It defines a contract for creating composable functions, which are the building blocks of Jetpack Compose UI components.

```kotlin
interface ComposableFactory {
    /**
     * Creates a composable function.
     *
     * @return A composable lambda.
     */
    @Composable
    fun compose(): @Composable () -> Unit

    /**
     * Invokes the composed function.
     */
    @Composable
    operator fun invoke() = compose().invoke()
}
```

- **compose Method**: This method returns a composable lambda that defines the structure and properties of the UI component. Each factory class implements this method to specify how its respective UI component should be constructed.

- **invoke Method**: This operator function allows the `ComposableFactory` to be called like a function. It invokes the composable lambda returned by `compose`, providing a convenient way to render the UI component.

### `*=` Operator

The `*=` operator is defined in the `PocketFactoryExtensions.kt` file. It allows you to modify a `ComposableFactory` with a block of code and then immediately invoke it. This operator is particularly useful for applying configurations to a factory before rendering it.

```kotlin
@Composable
operator fun <T : ComposableFactory> T.timesAssign(block: T.() -> Unit) {
    block() // Applies the configuration block to the factory
    this()  // Invokes the factory
}
```

## Examples

### ButtonTest

The `ButtonTest` function demonstrates how to create interactive buttons using `ButtonFactory`. It uses `remember` to manage state changes and demonstrates how to update button text on click events.

```kotlin
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ButtonTest() {
    var buttonText by remember { mutableStateOf("Option 1") } // Initializes button text state

    MaterialTheme {
        BoxFactory {
            ColumnFactory {
                ButtonFactory()
                    .apply {
                        onClick = {
                            buttonText = if (buttonText == "Option 1") "Option 2" else "Option 1" // Toggles button text
                        }
                        content = {
                            TextFactory(buttonText)() // Displays the current button text
                        }
                    }.compose()
                    .tooltip {
                        TextFactory("Hello!") // Tooltip for the button
                    }.space()() // Adds space after the button
                ButtonFactory() *= {
                    onClick = {
                        buttonText = if (buttonText == "Option 1") "Option 2" else "Option 1" // Toggles button text
                    }
                    content = {
                        TextFactory(buttonText)() // Displays the current button text
                    }
                }
            } *= {
                horizontalAlignment = Alignment.CenterHorizontally // Centers content horizontally
                verticalArrangement = Arrangement.Center // Centers content vertically
            }
        } *= {
            contentAlignment = Alignment.Center // Centers the entire Box content
            modifier = Modifier.fillMaxSize() // Fills the available space
        }
    }
}
```

#### Explanation

- **State Management**: The `buttonText` variable is managed using `remember` and `mutableStateOf`, allowing the button text to change dynamically based on user interaction.
- **ButtonFactory**: Two buttons are created using `ButtonFactory`. The first button uses `apply` to set properties and then calls `compose` to render the button. The second button uses the `*=` operator to configure and render the button in one step.
- **Tooltip and Space**: The first button includes a tooltip with the text "Hello!" and a space after it for visual separation.

### TextTest2

The `TextTest2` function showcases a layout with two text components arranged horizontally using `RowFactory`. It demonstrates how to use tooltips and vertical dividers.

```kotlin
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TextTest2() =
    MaterialTheme {
        BoxFactory {
            RowFactory {
                TextFactory("Text 1")
                    .compose()
                    .tooltip {
                        TextFactory("This is the tooltip of the first text component.")() // Tooltip for the first text
                    }.divide(vertical = true)() // Adds a vertical divider
                TextFactory("Text 2")() // Creates a second text component
            } *= {
                verticalAlignment = Alignment.CenterVertically // Centers content vertically
                horizontalArrangement = Arrangement.Center // Centers content horizontally
                modifier = Modifier.fillMaxSize() // Fills the available space
            }
        } *= {
            contentAlignment = Alignment.Center // Centers the entire Box content
            modifier = Modifier.fillMaxSize() // Fills the available space
        }
    }
```

#### Explanation

- **RowFactory**: The `RowFactory` arranges the text components horizontally. The `*=` operator is used to set alignment and arrangement properties before rendering.
- **TextFactory**: Two text components are created. The first text includes a tooltip and a vertical divider for separation.
- **Layout Configuration**: The entire layout is centered both vertically and horizontally within a `BoxFactory`, ensuring the content is displayed in the center of the available space.

## Contributing

Contributions are welcome! Please follow these steps to contribute:

1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Commit your changes.
4. Push your branch and create a pull request.

Please ensure your code adheres to the project's coding standards and includes appropriate tests.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
