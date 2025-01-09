# MinHeap and SortedHeap Implementation in Java

## Overview
This project implements two custom data structures, **MinHeap** and **SortedHeap**, using doubly linked lists in Java. These classes extend the base `Heap` class and provide efficient methods for managing a collection of elements with specific ordering properties.

## Features
- **MinHeap**: Maintains a binary heap structure with the minimum element at the root.
- **SortedHeap**: Keeps elements sorted, enabling fast retrieval and ordered traversal.
- Designed with extensibility and readability in mind.
- Supports efficient insertion, deletion, and retrieval operations.

## Class Structure
- **Heap**: Abstract base class with common functionality for all heap types.
- **MinHeap**: Implements a priority queue where the smallest element is always accessible.
- **SortedHeap**: Ensures elements are stored in sorted order using a doubly linked list.

## Requirements
- **Java 8** or later

## How to Use
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/heap-implementation.git
   cd heap-implementation
   ```
2. Compile the project:
   ```bash
   javac *.java
   ```
3. Run the demo:
   ```bash
   java Main
   ```

## Example Usage

### MinHeap
- Insert elements into the heap.
- Retrieve the smallest element in constant time.
- Remove the smallest element with automatic reordering.

### SortedHeap
- Insert elements while maintaining sorted order.
- Iterate through elements in ascending order.
- Efficient for tasks requiring sorted data.
