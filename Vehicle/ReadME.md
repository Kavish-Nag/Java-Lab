Here is a clean **GitHub README.md** you can directly paste into your repository.

---

# Vehicle Management System in Java

## Overview

This project is a simple **Object Oriented Programming based Vehicle Management System** developed in Java. It demonstrates the use of classes, constructors, encapsulation, arrays of objects, and basic vehicle related operations such as acceleration and mileage calculation.

The program creates multiple vehicle objects, stores them inside a garage array, calculates mileage based on fuel type, and prints vehicle details in tabular format.

---

## Features

* Multiple constructors (Constructor Overloading)
* Encapsulation using getters and setters
* Mileage calculation logic
* Vehicle acceleration simulation
* Tabular data printing
* Array of objects implementation
* Use of `java.time.Year`

---

## Project Structure

```
Vehicle.java       -> Vehicle class with attributes and methods
MainVehicle.java   -> Main driver program
```

---

## Class Description

### Vehicle Class

Represents a vehicle with properties and behaviours.

#### Attributes

* `brand`
* `model`
* `yearofMfg`
* `color`
* `fuelType`
* `price`
* `seats`
* `mfgCode` (private)
* `noOfServices` (private)

#### Constructors

1. Default constructor
   Initializes vehicle with predefined values.

2. Parameterized constructor
   Accepts brand, model, year, color, and price.

3. Custom constructor
   Accepts seats, price, fuel type, manufacturing code, and services.

---

### MainVehicle Class

Acts as the entry point of the application.

Responsibilities:

* Creates vehicle objects
* Stores vehicles inside an array called `garage`
* Calculates mileage depending on fuel type
* Prints formatted output


---

## How to Run

### Compile

```
javac Vehicle.java
javac MainVehicle.java
```

### Execute

```
java MainVehicle
```

---

## Sample Program Flow

1. Default vehicle object is created.
2. Custom vehicles are created using different constructors.
3. Vehicles are stored inside an array.
4. Mileage is calculated:

   * Diesel vehicles use fuel amount 50.
   * Petrol and CNG vehicles use fuel amount 40.
5. Data is printed in tabular format.

---




