# CollisionVisualizer

A lightweight Java desktop app for **visualizing 2D collision detection** between simple shapes. It renders moving bodies, checks for intersections in real time, and logs detected collisions for later review.

> Built for a CS summative project to make collision algorithms easier to see, test, and compare.

---

## Features
- Real-time **2D collision visualization** (e.g., circles / Squares)
- Step-through / play-pause simulation to inspect exact contact frames
- Adjustable weight (sizes), velocities, and restitution (elasticity of objects)
- **Collision physics calculations**:
  - Initial kinetic energy of each shape
  - Final kinetic energy of each shape after collision
  - Final velocities (magnitude and direction) of each shape
- **Collision logging** to `collision_logs.txt` for reproducible test cases
- Minimal UI with clear overlays: contact points, normals, bounding boxes

---

## How It Works (high level)
- Represents bodies as simple primitives (circle / rectangle)
- Uses classic collision tests ([SAT] for polygons, AABB overlap, circle–circle distance, etc.)
- On contact, records time, pair, and basic contact info to the log
- Renders frames on a Swing canvas

---

## Tech Stack
- **Java** 
- **Ant / NetBeans** project structure (`nbproject/`, `build.xml`)
- Standard Java2D/Swing for rendering ([or JavaFX if used])

---

## Getting Started

### Option A — NetBeans
1. **File → Open Project…** and select the cloned folder.
2. Click **Run** to start the simulator.

### Option B — Ant (CLI)
```bash
ant clean
ant jar
java -jar dist/CollisionVisualizer.jar
