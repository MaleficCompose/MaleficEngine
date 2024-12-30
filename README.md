# MaleficEngine

MaleficEngine is a Kotlin-based library designed to facilitate the creation of UI components using Jetpack Compose. It provides a set of tools and wrappers to streamline the development of composable functions as well as provide support for way more customizability.

## Table of Contents

- [Installation](#installation)
- [Overview](#overview)
    - [Factories](#factories)
    - [Fuel Class](#fuel-class)
- [Examples](#examples)
- [Contributing](#contributing)
- [License](#license)

## Installation

To include MaleficEngine in your project, add the following dependency to your `build.gradle.kts` file:

```kotlin
dependencies {
    implementation("xyz.malefic.compose:engine:1.1.1")
}
```

Ensure that you have the necessary repositories configured in your `settings.gradle.kts` or `build.gradle.kts`:

```kotlin
repositories {
    mavenCentral()
}
```

## Overview

### Factories

Factories in MaleficEngine are designed to simplify the creation of composable functions. Each factory is a customizable blueprint for a specific UI component, providing a structured way to define, configure, and render composables.

#### ComposableFactory Interface

The `ComposableFactory` interface is the foundation for all factories. It defines a contract for creating composable functions.

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

- **compose Method**: Returns a composable lambda defining the UI component's structure and properties.
- **invoke Method**: Enables the factory to be called like a function, simplifying usage.

#### `*=` Operator

The `*=` operator, defined in the `PocketFactoryExtensions.kt` file, allows you to apply configurations to a factory and immediately invoke it. This makes the code more concise and readable.

```kotlin
@Composable
operator fun <T : ComposableFactory> T.timesAssign(block: @Composable T.() -> Unit) {
    block() // Applies the configuration block to the factory
    this()  // Invokes the factory
}
```

#### Use Cases

Factories are best suited for creating reusable UI components with predefined structures and behaviors. Examples include:

- Buttons (`ButtonFactory`)
- Layout containers (`BoxFactory`, `ColumnFactory`, `RowFactory`)
- Text components (`TextFactory`)

They provide a streamlined way to define and customize components, reducing boilerplate code while maintaining flexibility.

For additional details, refer to the [Dokka documentation](https://engine.compose.malefic.xyz).

### Fuel Class

The `fuel` class is designed for wrapping composable functions that do not have a dedicated factory. It offers advanced customization options, such as adding tooltips, outlines, and other enhancements.

#### How It Works

The `fuel` class acts as a container for a composable function, providing methods to modify and render it with additional features.

```kotlin
class fuel(
    var function: @Composable () -> Unit,
)
```

- **Composable Function Wrapper**: Stores the composable function in the `function` property, enabling enhancements without modifying the original logic.
- **Operator Overloading**: Provides a concise syntax for chaining configurations and rendering the composable.

#### Key Methods

- **`invoke` Operator**: Executes the stored composable function.
- **`*` Operator**: Allows chaining configurations, such as adding tooltips and outlines, in a fluent manner.

#### Use Cases

The `fuel` class is ideal for:

- Wrapping composable functions that lack factory support.
- Adding additional features (e.g., tooltips, outlines) to existing composables.
- Enhancing flexibility and reusability of standalone composable functions.

For more in-depth information, refer to the [Dokka documentation](https://engine.compose.malefic.xyz).

#### Example

The `FuelTest` function demonstrates the use of the `fuel` class to wrap a `TextFactory` composable and add custom configurations:

```kotlin
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FuelTest() {
    MaterialTheme {
        fuel {
            TextFactory("Fuel Composable")()
        } * {
            tooltip {
                TextFactory("This is a tooltip for the fuel composable.")
                    .apply {
                        color = Color.White
                    }.compose()
                    .padding(3.dp)
                    .background(color = Color.Black)
                    .outline(color = Color.Red)()
            }
            center()
        }
    }
}
```

- **Tooltip and Outline**: Adds a tooltip and a red outline around the tooltip content.
- **Enhanced Composability**: Demonstrates how `fuel` enables complex configurations with minimal code.

## Examples

### ButtonTest

The `ButtonTest` function demonstrates how to create interactive buttons using `ButtonFactory`. It uses `remember` to manage state changes and dynamically updates button text based on user interactions.

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
                        TextFactory("Hello!")() // Tooltip for the button
                    }.space(width = 8.dp)() // Adds a spacer after the button
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

### TextTest2

The `TextTest2` function showcases a layout with two text components arranged horizontally using `RowFactory`. It demonstrates tooltips and vertical dividers.

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

## Contributing

Contributions are welcome! Please follow these steps to contribute:

1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Commit your changes.
4. Push your branch and create a pull request.

Please ensure your code adheres to the project's coding standards and includes appropriate tests.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
