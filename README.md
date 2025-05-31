# 📡 Network Simulator

> 📌 *This project was originally completed during my first year of college (2024). I recently made small updates to add build/run scripts and decided to upload it now for archival and portfolio purposes.*

---

## 🧠 Project Overview

This is a **discrete event-driven network simulator** implemented entirely in **vanilla Java**.

While it's labeled as a *network simulator*, it **does not involve actual networking concepts** like sockets or protocols. The purpose was to help students understand:

- Abstract classes & inheritance
- Working with pre-written code
- Implementing data structures
- Simulating time-based events


## 🌐 Simulation Details

- The project mimics a **star topology** where all communication is routed through a central "main" host.
- Sub-hosts do not communicate directly with one another.
- The simulation is **event-driven**, continuing until all scheduled events are processed.


## 🔁 Core Mechanics

- A custom `FutureEventList` is used to **simulate a sorted linked list** of events.
- Events are inserted in sorted order based on their `arrivalTime` (O(n) time complexity).
- There are two types of events: `Timer` and `Message`, both of which extend the abstract `Event` class and override `handle()`.
- The system repeatedly processes the earliest event in the list until it is empty.


## 🧱 Structure Overview

- `SimpleHost` objects represent nodes in the network and extend the `Host` class.
- Events are created, scheduled, and dynamically inserted into the sorted event list which is implemented using the LinkedEventList abstract class.
- Each event calls its `handle()` method when processed.

---

## 🗂️ Project Structure
project-root/ <br>
├── src/ # Java source files <br>
├── bin/ # Compiled class files <br>
├── scripts/ # Windows build/run scripts <br>
├── testfiles/ # Input test cases (e.g., simulation1.txt) <br>
└── README.md # Project documentation <br>


## ⚙️ How to Build & Run (on Windows)

### Prerequisites

- Java (JDK 8 or higher)
- Windows command line (e.g., PowerShell or Command Prompt)

### Build & Run

Open a terminal and navigate to the `scripts/` directory. Then, execute the following commands:

```bat
.\build.bat
.\run.bat testfiles\simulation1.txt
```
There are four simulation files available—simply replace simulation1.txt with the desired filename to run different test scenarios.

---

## 🧠 What I Learned

- Gained experience working with **partially written code**, which is a valuable skill in real-world software development where you're often building on existing systems.
- Learned how to implement and extend **abstract classes**, manage custom data structures, and simulate an event loop in Java.

## 🙏 Credits

- Huge thanks to **Professor Ricks** for designing and assigning this project as part of our coursework.
