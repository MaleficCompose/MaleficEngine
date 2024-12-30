# MaleficEngine

Welcome to the MaleficEngine project! This document provides an in-depth explanation of how the MaleficEngine works, along with integrated examples from the test cases in `TestContainer`.

## Table of Contents

1. [Introduction](#introduction)
2. [Setup and Installation](#setup-and-installation)
3. [Core Concepts](#core-concepts)
   - [Composable Functions](#composable-functions)
   - [Annotations](#annotations)
   - [Operators](#operators)
4. [Examples](#examples)
   - [ButtonTest](#buttontest)
   - [TextTest](#texttest)
   - [TextTest2](#texttest2)
   - [FuelFactoryText](#fuelfactorytext)
5. [Contributing](#contributing)
6. [License](#license)

## Introduction

MaleficEngine is a Kotlin-based library designed to facilitate the creation of composable UI components using Jetpack Compose. It provides a set of tools and utilities to streamline UI development, testing, and deployment.

## Setup and Installation

To set up the MaleficEngine project, ensure you have the following prerequisites:

- JDK 17 or higher
- Kotlin 1.5 or higher
- Gradle 7.0 or higher

### Maven Dependency

To include MaleficEngine in your project, add the following dependency to your `build.gradle.kts` file:

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("xyz.malefic.compose:engine:<version>")
}
```

Replace `<version>` with the specific version of MaleficEngine you wish to use. The library is published to a local Maven repository located at `xyz.malefic.compose:engine`.

## Core Concepts

### Composable Functions

Composable functions are the building blocks of the MaleficEngine. They are annotated with `@Composable` and are used to define UI components.

### Annotations

The `@ComposableTest` annotation is used to mark functions that are intended for testing purposes. These functions are automatically discovered and can be executed via the main entry point.

### Operators

MaleficEngine uses custom operators to enhance the composability and flexibility of UI components. These operators allow chaining and modification of composable logic.

#### `*` and `*=` Operators

- `*`: This operator applies a composable block to the `fuel` instance of a `ComposableFactory`. It allows chaining of additional composable operations without invoking the main composable function.

- `*=`: This operator applies a composable block to the `fuel` instance and then invokes the composable function. It is useful for chaining operations and executing the main composable logic.

Example usage in `FuelFactoryText`:

```kotlin
TextFactory() / {
    text = "Fuel Composable"
} *= {
    tooltip {
        TextFactory("This is a tooltip for the fuel composable").apply {
            color = MaterialTheme.colors.background
        } *= {
            padding(3.dp)
            background(MaterialTheme.colors.onBackground)
            outline(MaterialTheme.colors.primary)
        }
    }
    center()
}
```

#### `/` and `/=` Operators

- `/`: This operator applies a non-composable block to a `ComposableFactory` instance, allowing modifications without invoking the composable function.

- `/=`: This operator applies a composable block to a `ComposableFactory` instance and then invokes the composable function. It combines modification and invocation in a single step.

## Examples

### ButtonTest

The `ButtonTest` function is a composable test that demonstrates the use of buttons with dynamic text and tooltips. It utilizes the `MaterialTheme` to provide consistent styling across the UI components. The function defines a mutable state variable `buttonText` that toggles between "Option 1" and "Option 2" when the button is clicked. This dynamic behavior is achieved using the `remember` and `mutableStateOf` functions.

The UI layout is structured using `BoxFactory` and `ColumnFactory`, which organize the components vertically. The `ButtonFactory` is used to create buttons, and the `TextFactory` displays the current `buttonText`. Tooltips are added to the buttons using the `tooltip` operator, providing additional information when hovered over.

```kotlin
@Composable
@ComposableTest
fun ButtonTest() {
    var buttonText by remember { mutableStateOf("Option 1") }

    MaterialTheme {
        BoxFactory {
            ColumnFactory {
                ButtonFactory() / {
                    onClick = {
                        buttonText = if (buttonText == "Option 1") "Option 2" else "Option 1"
                    }
                    content = {
                        TextFactory(buttonText)()
                    }
                } *= {
                    tooltip {
                        TextFactory("Hello!")()
                    }
                    space(8.dp)
                }
            }
        }
    }
}
```

### TextTest

The `TextTest` function showcases the use of text components with tooltips and alignment. It uses a `ColumnFactory` to arrange text components vertically. Each `TextFactory` component can have a tooltip, which is a small popup that provides additional information. The tooltip for "Text 1" includes styling such as padding, background, and outline, demonstrating how to customize the appearance of tooltips.

The `divide` operator is used to add a visual separator between components, enhancing the layout's clarity.

```kotlin
@Composable
@ComposableTest
fun TextTest() {
    MaterialTheme {
        BoxFactory {
            ColumnFactory {
                TextFactory("Text 1") *= {
                    tooltip {
                        TextFactory("This is the tooltip of the first text component.") *= {
                            padding(3.dp)
                            background()
                            outline()()
                        }
                    }
                    divide(vertical = false)
                }
                TextFactory("Text 2")()
            }
        }
    }
}
```

### TextTest2

The `TextTest2` function demonstrates a row layout with text components and tooltips. It uses `RowFactory` to arrange text components horizontally. Similar to `TextTest`, tooltips are added to provide additional context for each text component. The `divide` operator is used to separate the text components visually.

The layout is centered both vertically and horizontally, ensuring that the text components are displayed prominently in the UI.

```kotlin
@Composable
@ComposableTest
fun TextTest2() {
    MaterialTheme {
        BoxFactory {
            RowFactory {
                TextFactory("Text 1") *= {
                    tooltip {
                        TextFactory("This is the tooltip of the first text component.")()
                    }
                    divide()
                }
                TextFactory("Text 2")()
            }
        }
    }
}
```

### FuelFactoryText

The `FuelFactoryText` function illustrates a custom text component with a tooltip. It uses `TextFactory` to create a text component labeled "Fuel Composable". The tooltip provides additional information and is styled with padding, background, and outline colors derived from the `MaterialTheme`.

The `center` operator is used to center the text component within its parent, ensuring a balanced and visually appealing layout.

```kotlin
@Composable
@ComposableTest
fun FuelFactoryText() {
    MaterialTheme {
        TextFactory() / {
            text = "Fuel Composable"
        } *= {
            tooltip {
                TextFactory("This is a tooltip for the fuel composable").apply {
                    color = MaterialTheme.colors.background
                } *= {
                    padding(3.dp)
                    background(MaterialTheme.colors.onBackground)
                    outline(MaterialTheme.colors.primary)
                }
            }
            center()
        }
    }
}
```

## Contributing

Contributions are welcome! Please follow the standard GitHub workflow for submitting pull requests and issues.

## License

This project is licensed under the MIT License. See the [LICENSE](https://opensource.org/licenses/MIT) file for more details.
